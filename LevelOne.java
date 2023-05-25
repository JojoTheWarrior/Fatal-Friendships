import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

/**
 * 
 */
public class LevelOne extends MouseAdapter {
    private Drawing draw;
    private Dialogue[] dialogues;
    private JPanel frame;
    private int dialogueIndex;
    private final int TOTAL_LINES = 10;


    public LevelOne(){
        draw = new Drawing();
        frame = new JPanel(new BorderLayout());

        dialogues = new Dialogue[TOTAL_LINES + 1];

        // initializes the lines of dialogue
        String[] noChoices = {};
        dialogues[1] = new Dialogue("James", "Hey Max! It's good to see you this lovely Sunday afternoon", noChoices, "james", "menu");
        
        dialogues[2] = new Dialogue("Max", "Hello, James. Any ideas on which movie we should watch today?", noChoices, "max", "menu");

        dialogueIndex = 0;

        frame.add(draw);
    }

    public void levelOne(Graphics g){

    }

    public void keyTyped(){
        draw.repaint();
    }

    class Drawing extends JComponent {
        public void addDialogue(){
            Dialogue currentDialogue = dialogues[dialogueIndex];

            JLabel characterPicture = new JLabel(new ImageIcon(currentDialogue.getCharacterIcon()));
            frame.add(characterPicture, BorderLayout.CENTER);
        }

        public void paint(Graphics g){
            addDialogue();
        }
    }
}
