package uk.ac.city.adbt065;

import city.cs.engine.SoundClip;
import city.cs.engine.UserView;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Creates a view for the player to look at the world with it's bodies and background
 */

public class View extends UserView { // Inherits all methods and attributes from the UserView class
    // Global variables
    private Image background;
    private int heartsLeft;
    private int coinsLeft;
    private int heartX;
    private SoundClip gameMusic;
    private gameLevel gl;
    // CONSTANTS
    private final ArrayList<Heart> heartList = new ArrayList<>();
    private final ArrayList<coinImage> coinRow = new ArrayList<>();
    private static final int WIDTH = 490;
    private static final int HEIGHT = 490;

    /**
     * View constructor
     * @param gl Game level
     */
    public View(gameLevel gl) {
        super(gl, WIDTH, HEIGHT);
        this.gl = gl;
        background = new ImageIcon("data/forestBackground/forest01.jpg").getImage();
        heartsLeft = gl.getScott().getHealth();
        rowOfHearts();
        rowOfCoins();
        manualControlPanel mcp = new manualControlPanel(this);
        add(mcp.getComboBox());
        try {
            gameMusic = new SoundClip("data/music/level1.wav");  // Open an audio input stream
            gameMusic.loop();  // Set it to continuous playback (looping)
        } catch (UnsupportedAudioFileException | IOException |
                LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Row of hears on the top left of the screen that displays how many hearts Scott has
     */
    public void rowOfHearts(){
        heartX = -10;
        for(int i = 0; i< heartsLeft; i++){
            heartList.add(new Heart(gl, heartX));
            heartX = heartX+1;
        }
    }

    /**
     * Creates a row of coins to show how many coins remain
     */
    public void rowOfCoins() {
        float coinX = 10;
        coinsLeft = gl.getCoinList().size();
        for (int i = 0; i < coinsLeft; i++) {
            coinRow.add(new coinImage(gl, coinX));
            coinX = coinX - 1.5f;
        }
    }

    /**
     * Removes a coin from the screen on the top right
     */
    public void removeCoin(){
        coinRow.get(coinsLeft -1).getCoinBody().destroy();
        coinsLeft = coinsLeft-1;
    }

    /**
     * Adds a heart to the screen
     */
    public void addHeart(){
        heartsLeft++;
        // Only if there are less than 5 hearts
        if(heartsLeft <= 5){
            heartList.add(new Heart(gl, heartX));
            heartX++;
        }
    }

    /**
     *  Remove heart from the screen as well
     */
    public void removeHeart(){
        if(heartsLeft-1 == 0){
            System.out.println("Unlucky, fella");
            System.exit(0);
        } else{
            heartList.get(heartsLeft -1).getHeartBody().destroy();
            heartList.remove(heartsLeft -1);
            gl.getScott().setPosition(gl.getInitialPosition());
            heartX = heartX -1;
            heartsLeft = heartsLeft -1;
        }
    }

    /**
     * Switches worlds keeping the heartS intact
     * @param gl Game level
     */
    public void changeWorld(gameLevel gl){
        this.setWorld(gl);
        this.gl = gl;
        coinRow.clear();
        heartList.clear();
        heartsLeft = gl.getGame().getPrevNumberOfHearts();
        rowOfHearts();
        // Row of coins does not apply for level 2 because it would be blocked by the platform and the friend
        if(!(gl.getGame().getGl() instanceof Level2)){
            rowOfCoins();
        }
        // Different levels have different backgrounds and music
        if(gl.getGame().getGl() instanceof Level1){
            background = new ImageIcon("data/forestBackground/forest01.jpg").getImage();
            try {
                gameMusic = new SoundClip("data/music/level1.wav");   // Open an audio input stream
                gameMusic.loop();  // Set it to continuous playback (looping)
            } catch (UnsupportedAudioFileException | IOException |
                    LineUnavailableException e) {
                System.out.println(e);
            }
        }
        if(gl.getGame().getGl() instanceof Level2){
            background = new ImageIcon("data/forestBackground/forest02.jpg").getImage();
            try {
                gameMusic = new SoundClip("data/music/level2.wav");   // Open an audio input stream
                gameMusic.loop();  // Set it to continuous playback (looping)
            } catch (UnsupportedAudioFileException | IOException |
                    LineUnavailableException e) {
                System.out.println(e);
            }
        }
        if(gl.getGame().getGl() instanceof Level3){
            background = new ImageIcon("data/forestBackground/forest03.png").getImage();
            try {
                gameMusic = new SoundClip("data/music/level3.wav");   // Open an audio input stream
                gameMusic.loop();  // Set it to continuous playback (looping)
            } catch (UnsupportedAudioFileException | IOException |
                    LineUnavailableException e) {
                System.out.println(e);
            }
        }
        if(gl.getGame().getGl() instanceof Level4){
            background = new ImageIcon("data/forestBackground/forest04.jpg").getImage();
            try {
                gameMusic = new SoundClip("data/music/level4.wav");   // Open an audio input stream
                gameMusic.loop();  // Set it to continuous playback (looping)
            } catch (UnsupportedAudioFileException | IOException |
                    LineUnavailableException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Returns the game music
     * @return The game music
     */
    public SoundClip getGameMusic() {
        return gameMusic;
    }

    /**
     * Returns the game level
     * @return The game level
     */
    public gameLevel getGl() {
        return gl;
    }

    /**
     * Sets the game level
     * @param gl Game level
     */
    public void setGl(gameLevel gl) {
        this.gl = gl;
    }

    /**
     * Paints the background to the view
     * @param g The graphics object on which to paint
     */

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }




}
