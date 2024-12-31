package uk.ac.city.adbt065;
import javax.swing.*;


/**
 * Brings everything together
 */

public class Game {

    // Global variables

    private gameLevel gl;
    private final listOfGameLevels gameLevelList;
    private int pointer = 0;
    private final View view;
    private int prevNumberOfHearts;
    private final scottMovement sm;
    private final JFrame frame;
    private boolean superpowered;

    /**
     * Game constructor
     * <br>
     * Takes no parameters
     * <br>
     * Boots up the game
     */

    public Game(){
        gameLevelList = new listOfGameLevels();
        gameLevelList.addLevel(new Level1(this));
        gameLevelList.addLevel(new Level2(this));
        gameLevelList.addLevel(new Level3(this));
        gameLevelList.addLevel(new Level4(this));
        gl = gameLevelList.getGameLevel(pointer);
        gl.populate();
        sm = new scottMovement(gl.getScott());
        scottCollision sc = new scottCollision(gl.getScott());
        gl.getScott().addCollisionListener(sc);
        view = new View(gl);
        view.addKeyListener(sm);
        view.setZoom(20);
        view.addMouseListener(new giveFocus(view));
        // view.setGridResolution(1);

        // Creates frame
        frame = new JFrame("The Tales of Travis and Scott");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        gl.start();

    }

    /**
     * Returns the game level
     * @return The game level
     */

    public gameLevel getGl() {
        return gl;
    }

    /**
     * Returns the position of the array list that holds all the game levels
     * @return The position of the array list that holds all the game levels
     */

    public int getPointer() {
        return pointer;
    }

    /**
     * Return the array list that holds all the game levels
     * @return The array list that holds all the game levels
     */

    public listOfGameLevels getGameLevelList() {
        return gameLevelList;
    }

    /**
     * Return the game view
     * @return The game view
     */

    public View getView() {
        return view;
    }

    /**
     * Returns the hearts from previous level
     * @return The hearts from previous level
     */

    public int getPrevNumberOfHearts() {
        return prevNumberOfHearts;
    }

    /**
     * Returns the frame
     * @return The frame
     */

    public JFrame getFrame() {
        return frame;
    }

    /**
     * Preparation to move to the next level
     */

    public void goToNextLevel(){
        prevNumberOfHearts = gl.getScott().getHealth(); // Roll hearts over
        superpowered = gl.getScott().isSuperPowered();
        if(gl instanceof Level4){ // End the game when attempting to move on to the next level from level 4
            System.out.println("Nice one, fella");
            System.exit(0);
        } else{ // if not level 4, roll over number of hearts and sunflower from the previous level to the next level
            view.getGameMusic().stop();
            gl.stop();
            pointer++;
            theSwitch();
        }
    }

    /**
     * Transitions to the adjacent level
     */

    private void theSwitch() {
        gl = getGameLevelList().getGameLevel(getPointer()); // access next game level
        if(gl instanceof Level1){
            gl = new Level1(this);
        } else if(gl instanceof Level2){
            gl = new Level2(this);
        } else if(gl instanceof Level3){
            gl = new Level3(this);
        } else if(gl instanceof Level4){
            gl = new Level4(this);
        }
        gl.populate();
        scottCollision sc = new scottCollision(gl.getScott());
        gl.getScott().setSuperPowered(superpowered);
        gl.getScott().addCollisionListener(sc);
        gl.getScott().setHealth(prevNumberOfHearts); // set health based on previous level
        gl.getScott().setNumberOfCoins(0);
        view.changeWorld(gl);
        sm.setScott(gl.getScott());
        gl.start();
    }

    /**
     * Preparation to move to the previous level
     */

    public void goBackLevel(){ // Ability to go back a level
        prevNumberOfHearts = gl.getScott().getHealth();
        superpowered = gl.getScott().isSuperPowered();
        if(gl instanceof Level1){ // End the game when attempting to move on to the previous level from level 1
            System.exit(0);
        } else{ // if not level 1, roll over number of hearts and sunflower from the previous level to the next level
            view.getGameMusic().stop();
            gl.stop();
            pointer--;
            theSwitch();
        }
    }

    /**
     * Moves to another level
     * @param gl The game level to go to
     */
    public void setLevel(gameLevel gl){
        prevNumberOfHearts = gl.getScott().getHealth();
        view.getGameMusic().stop();
        this.gl.stop();
        this.gl = gl;
        if(gl instanceof Level1){
            pointer = 0;
        } else if(gl instanceof Level2){
            pointer = 1;
        } else if(gl instanceof Level3){
            pointer = 2;
        } else if(gl instanceof Level4){
            pointer = 3;
        }
        view.changeWorld(gl);
        sm.setScott(gl.getScott());
        gl.start();
    }

    /**
     * Run the game
     * @param args Array of sequence of characters
     */
    public static void main(String[] args){
        new Game();
    }
}
