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
public class LevelOne implements KeyListener {
    private Drawing draw;
    private Dialogue[] dialogues;
    private JPanel frame;
    private int dialogueIndex;
    private int choiceIndex;
    private final int TOTAL_LINES = 10;


    public LevelOne(){
        frame = new JPanel(new BorderLayout());

        dialogues = new Dialogue[TOTAL_LINES + 1];

        // initializes the lines of dialogue
        String[] noChoices = {};
        dialogues[1] = new Dialogue("James", "Hey Max! It's good to see you this lovely Sunday afternoon", noChoices, 2, "james", "menu");
        
        dialogues[2] = new Dialogue("Max", "Hello, James. Any ideas on which movie we should watch today?", noChoices, 3, "max", "menu");

        String[] choices3 = {"The Super Mario Bros. Movie", "Guardians of the Galaxy Vol. 3", "John Wick: Chapter 4", "Halloween Ends"};
        int[] lead3 = {4, 4, 4, 4};
        dialogues[3] = new Dialogue("James", "Hmm... there are many good options. I think we should watch:", choices3, lead3, "james", "menu");

        dialogues[4] = new Dialogue("Max", "Nah, I'd rather watch Barbie. I really love Barbie...", noChoices, 5, "max", "menu");

        dialogues[5] = new Dialogue("Max", "...and honestly, I don't think your choice is very good. Let's watch Barbie, okay?", noChoices, 6, "max", "menu");

        String[] choices4 = {"Okay", "I would rather not watch Barbie, though..."};
        int[] lead4 = {9, 7};
        dialogues[6] = new Dialogue("James", "...", choices4, lead4, "james", "menu");

        dialogues[7] = new Dialogue("James", "*It's best to not argue with Max*", noChoices, 8, "james", "menu");

        dialogues[8] = new Dialogue("James", "*It's not like my choices really matter, anyway...*", noChoices, 6, "james", "menu");

        dialogues[9] = new Dialogue("Max", "Great! It's decided then - we're watching Barbie!. Yayyyy~~", noChoices, 10, "max", "menu");

        dialogueIndex = 1;
        choiceIndex = 0;

        draw = new Drawing();
        draw.addKeyListener(this);
        draw.setFocusable(true);
        frame.add(draw);
    }

    public JPanel getFrame(){
        return frame;
    }

    public void keyPressed(KeyEvent e){

    }

    public void keyReleased(KeyEvent e){

    };

    public void keyTyped(KeyEvent e){
        Dialogue d = dialogues[dialogueIndex];

        if (e.getKeyChar() == 's'){
            int s = d.getChoices().size();
            choiceIndex = (choiceIndex + 1) % s;
            draw.repaint();
        } else if (e.getKeyChar() == 'w'){
            int s = d.getChoices().size();
            choiceIndex = (choiceIndex + s - 1) % s;
            draw.repaint();
        } else if (e.getKeyChar() == 'm'){
            dialogueIndex = d.getLead().get(choiceIndex);
            choiceIndex = 0;
            draw.repaint();
        }
    }

    class Drawing extends JComponent {
        public void paint(Graphics g){
            Dialogue currentDialogue = dialogues[dialogueIndex];

            // title
            Font nameFont = null;
            Font textFont = null;

            try {
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                nameFont = Font.createFont(Font.TRUETYPE_FONT, new File("./assets/font/DeterminationMonoWebRegular-Z5oq.ttf")).deriveFont(90f);
                ge.registerFont(nameFont);

                textFont = Font.createFont(Font.TRUETYPE_FONT, new File("./assets/font/DeterminationMonoWebRegular-Z5oq.ttf")).deriveFont(25f);
                ge.registerFont(textFont);
            } catch (Exception e) {

            }

            g.drawImage(currentDialogue.getBackgroundImage(), 0, 0, 800, 500, null);

            g.setColor(Color.BLACK);
            g.setFont(nameFont);

            g.drawString(currentDialogue.getCharacterName(), 150, 100);

            g.setFont(textFont);
            g.drawString(currentDialogue.getDialogueText(), 50, 200);

            g.fillRect(50, 50, 100, 100);
            g.drawImage(currentDialogue.getCharacterIcon(), 60, 60, 80, 80, null);

            for (int i = 0; i < currentDialogue.getChoices().size(); i++){
                String symbol = (i == choiceIndex ? "*" : "-");
                g.drawString(symbol + "\t" + currentDialogue.getChoices().get(i), 60, 250 + 50 * i);
            }
        }
    }
}
