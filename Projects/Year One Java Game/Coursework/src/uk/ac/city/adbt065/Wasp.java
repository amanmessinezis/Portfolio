package uk.ac.city.adbt065;
import city.cs.engine.*;
import city.cs.engine.World;

/**
 * Another villain
 */

public class Wasp extends StaticBody{ // Inherits methods and attributes from the StaticBody class

    // Global variables
    private boolean left = true;
    private final boolean right = false;
    // CONSTANTS
    private static final float WASP_HEIGHT = 4;
    private static final Shape WASP_SHAPE = new CircleShape(1);
    private static final BodyImage WASP_IMAGE = new BodyImage("data/wasp/leftAnimations/move.gif", WASP_HEIGHT);

    public Wasp(World world) {
        super(world, WASP_SHAPE);
        addImage(WASP_IMAGE);
    }

    // GETTERS AND SETTERS

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}
