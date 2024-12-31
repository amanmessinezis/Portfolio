package uk.ac.city.adbt065;

import city.cs.engine.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Event listener for the sunflower collectible
 */

public class sunflowerCollision implements CollisionListener, ActionListener { // Must implement all methods from the CollisionListener class
    // Global variable
    private final Sunflower fc;
    private Scott scott;

    // Constructor
    public sunflowerCollision(Sunflower fc) {
        this.fc = fc;
    }

    // What happens when a body collides with the sunflower collectible
    @Override
    public void collide(CollisionEvent collisionEvent) {
        Body object = collisionEvent.getOtherBody();
        // If it's a static body, it bounces off of it and goes in the opposite direction
        if(object instanceof Scott){
            this.scott = (Scott) object;
            fc.destroy();
            ((Scott) object).setSuperPowered(true);
            Timer timer = new Timer(8000,this);
            timer.setRepeats(false);
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scott.setSuperPowered(false);
        scott.getGl().getGame().getView().getGameMusic().play();
    }
}
