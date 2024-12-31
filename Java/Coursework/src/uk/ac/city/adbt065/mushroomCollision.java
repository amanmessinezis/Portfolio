package uk.ac.city.adbt065;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;

/**
 * Event listener for the mushroom
 */
public class mushroomCollision implements CollisionListener { // Implements CollisionListener's methods
    // Global variables
    private final Mushroom mushroom;

    // Constructor to instantiate the class
    public mushroomCollision(Mushroom mushroom) {
        this.mushroom = mushroom;
    }

    // When it collides with a static body, it will go slide in the opposite direction
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof StaticBody){
            if(mushroom.isLeft()){
                mushroom.moveRight();
            }
            else{
                mushroom.setLeft(true);
                mushroom.moveLeft();
            }
        }
    }
}
