package uk.ac.city.adbt065;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Class for sunflower collectible that can be instantiated
 */

public class Sunflower extends Walker { // Inherits the data and methods from the Walker class

    // Declares members
    private static SoundClip sunflowerSound;
    private boolean left = false;
    // CONSTANTS
    private static final float MOVE_SPEED = 3;
    private static final float FC_HEIGHT = 2;
    private static final Shape FC_SHAPE = new CircleShape(1);
    private static final BodyImage FC_IMAGE = new BodyImage("data/other/sunflower.png", FC_HEIGHT);
    private final gameLevel gl;

    // Constructor to create the sunflower collectible but doesn't add it to the world just yet

    public Sunflower(gameLevel gl) {
        super(gl, FC_SHAPE); // i.e StaticBody(w, healthPackShape)
        this.gl = gl;
        addImage(FC_IMAGE);
    }

    // Sunflower pickup sound effect

    static {
        try {
            sunflowerSound = new SoundClip("data/music/superpowered.wav");   // Open an audio input stream
        } catch (UnsupportedAudioFileException | IOException |
                LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // Getters and setters

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }


    public void moveLeft(){
        setLeft(true);
        startWalking(-MOVE_SPEED);
    }

    public void moveRight(){
        setLeft(false);
        startWalking(MOVE_SPEED);
    }

    // Destroys the collectible and plays the soundtrack once collided with Scott

    @Override
    public void destroy()
    {
        gl.getGame().getView().getGameMusic().stop();
        sunflowerSound.play();
        super.destroy();

    }


}
