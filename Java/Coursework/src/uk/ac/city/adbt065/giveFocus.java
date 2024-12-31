package uk.ac.city.adbt065;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Focuses the screen to the game when the mouse clicks inside the game
 */

public class giveFocus implements MouseListener { // Implements methods from the MouseListener class
    //Global variable
    private final View vue;

    // Constructs the class to allow instantiation

    public giveFocus(View vue) {
        this.vue = vue;
    }

    // Focuses into the game screen when the mouse clicks inside it

    @Override
    public void mouseEntered(MouseEvent e) {
        vue.requestFocus();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
