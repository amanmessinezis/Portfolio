package uk.ac.city.adbt065;
import city.cs.engine.*;

/**
 * The monster class
 */

public class bounceMonster extends StaticBody{ // Has access to StaticBody's data and methods

    // CONSTANTS
    private static final Shape MONSTER_SHAPE = new CircleShape(1f,0,-1);
    private static final BodyImage MONSTER_IMAGE = new BodyImage("data/bounceMonster/idleAnimation.gif",3f);

    /**
     * Constructor that takes the game level as a parameter
     */

    public bounceMonster(gameLevel gl) {
        super(gl, MONSTER_SHAPE);
        addImage(MONSTER_IMAGE);
    }

}
