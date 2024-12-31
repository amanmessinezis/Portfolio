package uk.ac.city.adbt065;
import city.cs.engine.*;

/**
 * The main character
 */

public class Scott extends Walker{ // Inherits the Walker class' attributes and methods

    //Declaring members
    private int health;
    private boolean left = false;
    private boolean right = true;
    private boolean running = false;
    private boolean jump = false;
    private boolean slide = false;
    private boolean superPowered = false;
    private int numberOfCoin;
    private final gameLevel gl;
    // CONSTANTS
    private static final float SCOTT_INITIAL_HEIGHT = 2.5f;
    private static final Shape SCOTT_SHAPE = new PolygonShape(0.182f,1.13f, 0.732f,0.73f, 0.422f,-1.215f, -0.178f,-1.22f, -0.648f,0.365f);
    private static final BodyImage SCOTT_IMAGE = new BodyImage("data/scott/rightStills/Armature_Idle_0.png", SCOTT_INITIAL_HEIGHT);

    // Constructs the class to create an object of the class

    public Scott(gameLevel gl) {
        super(gl, SCOTT_SHAPE);
        this.gl = gl;
        addImage(SCOTT_IMAGE);
        health = 4;
    }

    // Getters and setters and other methods

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getNumberOfCoins() {
        return numberOfCoin;
    }

    public void setNumberOfCoins(int numberOfCoin) {
        this.numberOfCoin = numberOfCoin;
    }

    public static float getScottInitialHeight() {
        return SCOTT_INITIAL_HEIGHT;
    }

    public boolean isSlide() {
        return slide;
    }

    public void setSlide(boolean slide) {
        this.slide = slide;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public gameLevel getGl() {
        return gl;
    }

    public boolean isSuperPowered() {
        return superPowered;
    }

    public void setSuperPowered(boolean superPowered) {
        this.superPowered = superPowered;
    }

    // Increment health by one

    public void addHealth(){
        health++;
        if(health <= 5){ // If health is at 5, then it will not increment, because 5 is the maximum health
            gl.getGame().getView().addHeart();
        }
    }

    // Decrements health by one
    public void loseHealth(){
        health--;
        gl.getGame().getView().removeHeart();
    }
    // Increments coin by one
    public void addCoin(){numberOfCoin++;}
}
