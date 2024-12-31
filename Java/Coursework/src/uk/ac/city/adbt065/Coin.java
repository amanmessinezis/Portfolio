package uk.ac.city.adbt065;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Class of a coin that Scott must collect
 */

public class Coin extends StaticBody {

    // Global variables that can be accessed by different blocks within the same class
    private static SoundClip coinSound;
    // CONSTANTS
    private static final Shape COIN_SHAPE = new CircleShape(0.25f);
    private static final BodyImage COIN_IMAGE = new BodyImage("data/other/coin.gif",4f);

    // Constructor
    public Coin(gameLevel gl) {
        super(gl, COIN_SHAPE); // i.e StaticBody(gl, healthPackShape)
        addImage(COIN_IMAGE);
    }

    // Coin sound effect
    static {
        try {
            coinSound = new SoundClip("data/music/coinPickup.wav");   // Open an audio input stream
        } catch (UnsupportedAudioFileException | IOException |
                LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // Calls the coin sound effect as well as destroys the coin collided with when Scott collides with it
    @Override
    public void destroy()
    {
        coinSound.play();
        super.destroy();

    }


}
