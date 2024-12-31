package uk.ac.city.adbt065;

import city.cs.engine.BodyImage;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

/**
 * Number of "lives" Scott has before it's game over
 */
public class Heart {

    // Global variables
    private final StaticBody heartBody;
    // CONSTANT
    private static final int Y = 10;

    // Constructor class that create a single heart

    public Heart(gameLevel gl, int x) {
        BodyImage image = new BodyImage("data/other/heart.png");
        heartBody = new StaticBody(gl);
        heartBody.addImage(image);
        heartBody.setPosition(new Vec2(x, Y));
    }

    // Getter
    public StaticBody getHeartBody() {
        return heartBody;
    }
}
