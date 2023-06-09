import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class Main implements MouseMotionListener, MouseListener, KeyListener {
    public static Main game;

    // 0 is splash, 1 is level one, 2 is level 2, 3 is level 3
    public int state;
    public int choice;
    public boolean spoke_to_daniel = false;
    public int level;

    JFrame frame;
    JPanel panel;

    Screen currentScreen;

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

    public static void main(String[] args) {
        game = new Main();
    }
}