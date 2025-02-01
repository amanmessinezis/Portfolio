package uk.ac.city.adbt065;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Mushroom that when collides with Scott, gives Scott maximum health - which is 5
 */

public class Mushroom extends Walker { // Inherits methods and attributes of the Walker class

    // Global variables
    private static SoundClip fcSound;
    private boolean left = false;
    private gameLevel gl;
    // CONSTANTS
    private static final float MOVE_SPEED = 3;
    private static final float M_HEIGHT = 2;
    private static final Shape M_SHAPE = new CircleShape(1);
    private static final BodyImage M_IMAGE = new BodyImage("data/other/mushroom.png", M_HEIGHT);

    // Constructor
    public Mushroom(gameLevel gl) {
        super(gl, M_SHAPE); // i.e StaticBody(w, healthPackShape)
        addImage(M_IMAGE);
    }

    // Getters and setters

    public gameLevel getGl() {
        return gl;
    }

    public void setGl(gameLevel gl) {
        this.gl = gl;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    // Creates a sound effect for the mushroom

    static {
        try {
            fcSound = new SoundClip("data/music/mushroom.wav");   // Open an audio input stream
        } catch (UnsupportedAudioFileException | IOException |
                LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // Moves the mushroom in the left direction

    public void moveLeft(){
        setLeft(true);
        startWalking(-MOVE_SPEED);
    }

    // Moves the mushroom in the right direction

    public void moveRight(){
        setLeft(false);
        startWalking(MOVE_SPEED);
    }

    // When mushroom collides with Scott, the sound effect is played and the mushroom gets destroyed

    @Override
    public void destroy()
    {
        fcSound.play();
        super.destroy();
    }


}
