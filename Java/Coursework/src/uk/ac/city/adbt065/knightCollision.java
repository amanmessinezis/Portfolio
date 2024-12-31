package uk.ac.city.adbt065;

import city.cs.engine.*;

/**
 * Event listener for Knight
 */

public class knightCollision implements CollisionListener { // Implements all methods in CollisionListener
    // Global variables
    private final Knight knight;

    // Constructs the listener
    public knightCollision(Knight knight) {
        this.knight = knight;
    }

    // Implementation of the CollisionListener
    @Override
    public void collide(CollisionEvent collisionEvent) {
        Body object = collisionEvent.getOtherBody();
        // If the knight collides with a static body, it will walk in the opposite direction
        if(object instanceof StaticBody){
            if(knight.isLeft()){
                knight.moveRight();
            } else if (knight.isRight()){
                knight.moveLeft();
            } else{
                knight.stopWalking();
                knight.removeAllImages();
                knight.addImage(new BodyImage("data/knight/leftAnimations/idle.gif", Knight.getKnightHeight()));
            }
        }
    }
}
