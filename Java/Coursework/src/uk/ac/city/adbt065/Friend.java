package uk.ac.city.adbt065;
import city.cs.engine.*;
import city.cs.engine.World;

/**
 * The friend Scott must go to after collecting all the coins in the level
 */

public class Friend extends StaticBody{ // Inherits all members and methods of StaticBody

    // Constant global variables
    private static final Shape FRIEND_SHAPE = new PolygonShape(0.182f,1.13f, 0.732f,0.73f, 0.422f,-1.215f, -0.178f,-1.22f, -0.648f,0.365f);
    private static final float FRIEND_HEIGHT = 2.5f;
    private static final BodyImage FRIEND_IMAGE = new BodyImage("data/friend/leftStills/Armature_Idle_0.png", FRIEND_HEIGHT);

    // Constructor to bring him to the world

    public Friend(World world) {
        super(world, FRIEND_SHAPE);
        addImage(FRIEND_IMAGE);
    }
}
