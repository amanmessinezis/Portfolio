package uk.ac.city.adbt065;
import city.cs.engine.*;

/**
 * Villain class that when touched by Scott, makes him lose a life and resets him to the starting point of the game level
 */

public class Knight extends Walker{ // Inherits methods and data from the superclass "Walker"

    // Declaring members
    private boolean left = false;
    private boolean right = false;
    // CONSTANTS
    private static final float KNIGHT_HEIGHT = 4;
    private static final float MOVE_SPEED = 3;
    private static final Shape KNIGHT_SHAPE = new BoxShape(0.25f* KNIGHT_HEIGHT,0.25f* KNIGHT_HEIGHT);
    private static final BodyImage KNIGHT_IMAGE = new BodyImage("data/knight/leftAnimations/run.gif", KNIGHT_HEIGHT);

    // Constructor that takes the game level as a parameter and instantiates the Knight class
    public Knight(gameLevel gl) {
        super(gl, KNIGHT_SHAPE);
        addImage(KNIGHT_IMAGE);
    }

    // Moves the knight in the left direction

    public void moveLeft(){
        removeAllImages();
        addImage(new BodyImage("data/knight/leftAnimations/run.gif", KNIGHT_HEIGHT));
        setLeft(true);
        setRight(false);
        startWalking(-MOVE_SPEED);
    }

    // Moves the knight in the right direction

    public void moveRight(){
        removeAllImages();
        addImage(new BodyImage("data/knight/rightAnimations/run.gif", KNIGHT_HEIGHT));
        setLeft(false);
        setRight(true);
        startWalking(MOVE_SPEED);
    }

    // Getter and setter

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

    public static float getKnightHeight() {
        return KNIGHT_HEIGHT;
    }
}
