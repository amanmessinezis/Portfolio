package uk.ac.city.adbt065;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 * Event listener for the friend added to Scott
 */

public class friendEncounter implements CollisionListener { // Must implement methods from the CollisionListener class
    // Global variables
    private final gameLevel gl;
    private final Game game;

    // Constructor
    public friendEncounter(gameLevel gl, Game game) {
        this.gl = gl;
        this.game = game;
    }

    // When Scott encounters the friend, if all coins are collected, then the game proceeds to the next level
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Friend && gl.isComplete()){
            game.goToNextLevel();
        }
    }
}
