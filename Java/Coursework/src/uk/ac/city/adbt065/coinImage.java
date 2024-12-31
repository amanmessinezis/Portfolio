package uk.ac.city.adbt065;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * The image of the coin
 */

public class coinImage {
    // Global variables

    private final StaticBody coinBody;
    private static final int Y = 10; // Constant

    // Constructor that takes the game level and the x position as arguments and brings it to the world

    public coinImage(gameLevel gl, float x) {
        BodyImage coinImage = new BodyImage("data/other/coin.gif",4f);
        coinBody = new StaticBody(gl);
        coinBody.addImage(coinImage);
        coinBody.setPosition(new Vec2(x, Y));
    }

    public StaticBody getCoinBody() {
        return coinBody;
    }
}
