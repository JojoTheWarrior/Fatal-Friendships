import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

/**
 * Superclass for all screens.
 * All screens will return a JPanel, and what makes it unique is the content within the JPanel.
 * Main.java will always be displaying an object of the Screen class.
 * Screen.java also serves as an adaptor class for KeyLister, MouseListener, and MouseMotionListener.
 */
public class Screen implements KeyListener, MouseListener, MouseMotionListener {
    /** The returned JPanel, to be placed within Main.game.frame */
    public JPanel frame;

    /**
     * Creates a new Screen.
     */
    public Screen(){
        frame = new JPanel(new BorderLayout());    
    }

    /**
     * @return The JPanel that contains the content of this Screen.
     */
    public JPanel getFrame(){
        return frame;
    }

    /**
     * Handles mouse clicks.
     * @param e The mouse click that triggered this method.
     */
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Handles mouse presses.
     * @param e The mouse press that triggered this method.
     */
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Handles when the mouse is released.
     * @param e The mouse release event that triggered this method.
     */
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Handles when the mouse enters.
     * @param e The mouse enter event that triggered this method.
     */
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Handles when the mouse exits.
     * @param e The mouse exit event that triggered this method.
     */
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Handles when a key is typed.
     * @param e The key type event that triggered this method.
     */
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Handles when a key is pressed.
     * @param e The key press event that triggered this method.
     */
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Handles when a key is released.
     * @param e The key release event that triggered this method.
     */
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Handles when the mouse is dragged.
     * @param e The mouse drag that triggered this method.
     */
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Handles when the mouse moves;
     * @param e The mouse move event that triggered this method.
     */
    public void mouseMoved(MouseEvent e) {

    }
}
