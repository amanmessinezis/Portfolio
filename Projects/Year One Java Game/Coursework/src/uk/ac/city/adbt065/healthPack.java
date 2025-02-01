package uk.ac.city.adbt065;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * The health pack class
 */

public class healthPack extends StaticBody { // Inherits the data and methods of the StaticBody class

    // Declares members
    private static SoundClip healthSound;
    // Constants
    private static final Shape HEALTH_PACK_SHAPE = new CircleShape(1);
    private static final BodyImage HEALTH_PACK_IMAGE = new BodyImage("data/other/healthPickup.gif",4f);
    // Constructor
    public healthPack(gameLevel gl, float x, float y) {
        super(gl, HEALTH_PACK_SHAPE); // i.e StaticBody(w, HEALTH_PACK_SHAPE)
        addImage(HEALTH_PACK_IMAGE);
        setPosition(new Vec2(x,y));
    }
    // Sound effect for when Scott collides with the health pack
    static {
        try {
            healthSound = new SoundClip("data/music/healthSound.wav");   // Open an audio input stream
        } catch (UnsupportedAudioFileException | IOException |
                LineUnavailableException e) {
            System.out.println(e);
        }
    }
    // Destroys the health pack in the world and plays the sound effect when collides with Scott
    @Override
    public void destroy()
    {
        healthSound.play();
        super.destroy();
    }
}
