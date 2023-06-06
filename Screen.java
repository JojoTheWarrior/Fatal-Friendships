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
 * Screen.java also serves as an adaptor class for KeyLister and MouseListener.
 */
public class Screen implements KeyListener, MouseListener, MouseMotionListener {
    public JPanel frame;

    public Screen(){
        frame = new JPanel(new BorderLayout());    
    }

    /**
     * @return The JPanel that contains the content of this Screen.
     */
    public JPanel getFrame(){
        return frame;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }
}
