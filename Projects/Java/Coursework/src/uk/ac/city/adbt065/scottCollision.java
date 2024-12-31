package uk.ac.city.adbt065;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Events that are run when Scott collides into certain objects
 */
public class scottCollision implements CollisionListener {
    // Global variables
    private Scott scott;
    // CONSTANT
    private static final float MONSTER_JUMP = 12.5f;

    // Constructor
    public scottCollision(Scott scott) {
        this.scott = scott;
    }

    // Getters and setters
    public Scott getScott() {
        return scott;
    }

    public void setScott(Scott scott) {
        this.scott = scott;
    }


    // Implements by overriding the collide method from the CollisionListener class
    @Override
    public void collide(CollisionEvent collisionEvent) {
        Body object = collisionEvent.getOtherBody();
        // If Scott touches a wasp, he will respawn and lose a life
        if(object instanceof Wasp){
            if(scott.isSuperPowered()){
                object.destroy();
            } else{
                scott.loseHealth();
            }
        } else if(object instanceof bounceMonster){ // If it's a bounceMonster, he will project upwards, and a sound effect will be called
            scott.jump(MONSTER_JUMP);
            try {
                SoundClip soundEffects = new SoundClip("data/music/boing.wav");   // Open an audio input stream
                soundEffects.setVolume(2.0);
                soundEffects.play();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }
        } else if(object instanceof Coin){ // If it's a coin Scott collides with, the destory method will be called for the coin, as well as increment the coin value for Scott by oone
            object.destroy();
            scott.addCoin();
            // If the current level is not Level2, then the coin on the top right hand of the screen is also removed
            // Level2 doesn't have a "number of coins left" GUI because it is blocked by the friend and the platform on the top right hand of the screen
            if(!(scott.getGl() instanceof Level2)){
                scott.getGl().getGame().getView().removeCoin();
            }
        } else if (object instanceof healthPack){ // If a health pack is collected, then Scott's health is incremented by one
            if(scott.getHealth() != 5){
                scott.addHealth();
            }
            collisionEvent.getOtherBody().destroy();
        } else if(object instanceof StaticBody && scott.isLeft()){ // If Scott collides with a body and is facing left or right, then he will either respawn if he's touching the base ground, or will land appropriately
            if(object.getPosition().y == -15){
                scott.loseHealth();
                scott.setSuperPowered(false);
            } else{
                scott.removeAllImages();
                if(scott.isRunning()){
                    scott.addImage(new BodyImage("data/scott/leftAnimations/run.gif",Scott.getScottInitialHeight()));
                } else{
                    scott.addImage(new BodyImage("data/scott/leftAnimations/idle.gif",Scott.getScottInitialHeight()));
                }
            }
        } else if(object instanceof StaticBody && scott.isRight()) {
            if(object.getPosition().y == -15){
                scott.loseHealth();
                scott.setSuperPowered(false);
            } else{
                scott.removeAllImages();
                if(scott.isRunning()){
                    scott.addImage(new BodyImage("data/scott/RightAnimations/run.gif",Scott.getScottInitialHeight()));
                } else{
                    scott.addImage(new BodyImage("data/scott/RightAnimations/idle.gif", Scott.getScottInitialHeight()));
                }
            }
        } else if(object instanceof Knight){ // If Scott collides with a knight, and he's currently sliding, then he will kill the knight, or else he will be respawned
            if (scott.isSlide() || scott.isSuperPowered()){
                object.destroy();
            } else{
                scott.loseHealth();
            }
        } else if(object instanceof Mushroom){ // If he collides with a mushroom, then he will be set to maximum health
            while(scott.getHealth() != 5){ // Contiinues incrementing health until health reaches 5 (100%)
                scott.addHealth();
            }
            object.destroy();
        }
    }

}
