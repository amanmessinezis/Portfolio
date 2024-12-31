package uk.ac.city.adbt065;

import city.cs.engine.*;

import org.jbox2d.common.Vec2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * Keyboard events
 */

public class scottMovement implements KeyListener{ // Implements methods from the KeyListener class

    // Declares members
    private Scott scott;
    // CONSTANTS
    private static final float MOVE_SPEED = 5;
    private static final float JUMPING_SPEED = 10;



    // Setter

    public void setScott(Scott scott) {
        this.scott = scott;
    }

    // Constructor for class
    public scottMovement(Scott scott) {
        this.scott = scott;
    }

    // Implements by overriding the keyPressed method

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // Only when the game isn't paused
        if(!scott.getGl().isPause()){
            // If A, D or W is pressed, then Scott moves left, right or jumps
            if (code == KeyEvent.VK_A) {
                scott.startWalking(-MOVE_SPEED);
                scott.removeAllImages();
                scott.addImage(new BodyImage("data/scott/leftAnimations/run.gif",Scott.getScottInitialHeight()));
                scott.setLeft(true);
                scott.setRight(false);
                scott.setRunning(true);
            } else if (code == KeyEvent.VK_D) {
                scott.startWalking(MOVE_SPEED);
                scott.removeAllImages();
                scott.addImage(new BodyImage("data/scott/rightAnimations/run.gif",Scott.getScottInitialHeight()));
                scott.setRight(true);
                scott.setLeft(false);
                scott.setRunning(true);
            } else if (code == KeyEvent.VK_W && scott.isLeft()) { // Jumps in the left direction if he's facing left
                scott.removeAllImages();
                scott.addImage(new BodyImage("data/scott/leftAnimations/jump.gif",Scott.getScottInitialHeight()));
                scott.jump(JUMPING_SPEED);
                scott.setJump(true);
            } else if (code == KeyEvent.VK_W && scott.isRight()) { // Jumps in the right direction if he's facing right
                scott.removeAllImages();
                scott.addImage(new BodyImage("data/scott/rightAnimations/jump.gif", Scott.getScottInitialHeight()));
                scott.jump(JUMPING_SPEED);
                scott.setJump(true);
            } else if(code == KeyEvent.VK_W && scott.isJump() && scott.isLeft()){
                scott.removeAllImages();
                scott.addImage(new BodyImage("data/scott/leftAnimations/jump.gif", Scott.getScottInitialHeight()));
                scott.jump(JUMPING_SPEED);
            } else if(code == KeyEvent.VK_W && scott.isJump() && scott.isRight()){
                scott.removeAllImages();
                scott.addImage(new BodyImage("data/scott/rightAnimations/jump.gif", Scott.getScottInitialHeight()));
                scott.jump(JUMPING_SPEED);
            } else if(code == KeyEvent.VK_S && scott.isRunning() && scott.isLeft()){ // Will slide left or right only if he's running
                scott.removeAllImages();
                scott.addImage(new BodyImage("data/scott/leftAnimations/slide.gif",Scott.getScottInitialHeight()));
                scott.setSlide(true);
            } else if(code == KeyEvent.VK_S && scott.isRunning() && scott.isRight()) {
                scott.removeAllImages();
                scott.addImage(new BodyImage("data/scott/rightAnimations/slide.gif", Scott.getScottInitialHeight()));
                scott.setSlide(true);
            }
            else if(code == KeyEvent.VK_K){
                try {
                    gameSaverLoader.save(scott.getGl());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    // Implements the keyReleased method from the KeyListener class

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // If A, D, or S are released, he will stop running or sliding to the left or right
        if (code == KeyEvent.VK_A) {
            scott.removeAllImages();
            scott.addImage(new BodyImage("data/scott/leftAnimations/idle.gif",Scott.getScottInitialHeight()));
            scott.stopWalking();
            scott.setLinearVelocity(new Vec2(0,0));
            scott.setRunning(false);
            if(scott.isSlide()){
                scott.setSlide(false);
            }
        } else if (code == KeyEvent.VK_D) {
            scott.removeAllImages();
            scott.addImage(new BodyImage("data/scott/rightAnimations/idle.gif",Scott.getScottInitialHeight()));
            scott.stopWalking();
            scott.setLinearVelocity(new Vec2(0,0));
            scott.setRunning(false);
            if(scott.isSlide()){
                scott.setSlide(false);
            }
        } else if (code == KeyEvent.VK_S && scott.isSlide() && scott.isLeft()){
            scott.removeAllImages();
            scott.setSlide(false);
            if (scott.isRunning()) {
                scott.addImage(new BodyImage("data/scott/leftAnimations/run.gif",Scott.getScottInitialHeight()));
            }
        } else if (code == KeyEvent.VK_S && scott.isSlide() && scott.isRight()){
            scott.removeAllImages();
            scott.setSlide(false);
            if (scott.isRunning()) {
                scott.addImage(new BodyImage("data/scott/rightAnimations/run.gif",Scott.getScottInitialHeight()));
            }
        }
    }

    public Scott getScott() {
        return scott;
    }

    // Required as part of the implementation, even though it's not used

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
