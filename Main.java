import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

/** 
 * Driver class - displays an object of the Screen class and contains the variables representing the user's important choices throughout the game.
 */
public class Main implements MouseMotionListener, MouseListener, KeyListener {
    /** Global version of the game currently being displayed, can be seen in all Screen subclasses. */
    public static Main game;

    /** The state of the game. */
    public int state;
    /** The choice in the main menu. */
    public int choice;
    /** Whether the character spoke honestly to Daniel. */
    public boolean spoke_to_daniel = false;
    /** The level that the user is on (progress saves). */
    public int level;

    /** Main window being displayed. */
    public JFrame frame;
    /** The content panel - the only JPanel that frame contains. */
    public JPanel panel;

    /** Screne currently being displayed. */
    public Screen currentScreen;

    /** Creates a new instance of the game. */
    public Main() {
        state = 0;
        choice = 0;
        level = 1;

        frame = new JFrame("Fatal Friendships");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 520);

        panel = new JPanel(new BorderLayout());

        currentScreen = new Intro();

        panel.add(currentScreen.getFrame(), BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void setState(int x){
        state = x;

        panel.remove(currentScreen.getFrame());

        currentScreen = new Screen();

        if (state == 0){
            currentScreen = new Intro();
        } else if (state == 1){
            currentScreen = new LevelOne();
        } else if (state == 2){
            currentScreen = new LevelTwo();
        } else if (state == 3){
            currentScreen = new LevelThree();
        }

        panel.add(currentScreen.getFrame(), BorderLayout.CENTER);
        // currentScreen.getFrame().requestFocus();
        panel.updateUI();
    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

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

    /**
     * Game driver.
     */
    public static void main(String[] args) {
        game = new Main();
    }
}