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
    private final int TOTAL_LINES = 27;
    private final int MAX_CHAR = 55;


    public LevelOne(){
        frame = new JPanel(new BorderLayout());

        dialogues = new Dialogue[TOTAL_LINES + 1];

        // initializes the lines of dialogue
        String[] noChoices = {};
        dialogues[1] = new Dialogue("James", "Hey Max! It's good to see you this lovely Sunday afternoon", noChoices, 2, "james", "theatre");
        
        dialogues[2] = new Dialogue("Max", "Hello, James. Any ideas on which movie we should watch today?", noChoices, 3, "max", "theatre");

        String[] choices3 = {"The Super Mario Bros. Movie", "Guardians of the Galaxy Vol. 3", "John Wick: Chapter 4", "Halloween Ends"};
        int[] lead3 = {4, 4, 4, 4};
        dialogues[3] = new Dialogue("James", "Hmm... there are many good options. I think we should watch:", choices3, lead3, "james", "theatre");

        dialogues[4] = new Dialogue("Max", "Nah, I'd rather watch Barbie. I really love Barbie...", noChoices, 5, "max", "theatre");

        dialogues[5] = new Dialogue("Max", "...and honestly, I don't think your choice is very good. Let's watch Barbie, okay?", noChoices, 6, "max", "theatre");

        String[] choices6 = {"Okay", "I would rather not watch Barbie, though..."};
        int[] lead6 = {9, 7};
        dialogues[6] = new Dialogue("James", "...", choices6, lead6, "james", "theatre");

        dialogues[7] = new Dialogue("James", "(It's best to not argue with Max)", noChoices, 8, "james", "theatre");

        dialogues[8] = new Dialogue("James", "(It's not like my choices really matter, anyway...)", noChoices, 6, "james", "theatre");

        dialogues[9] = new Dialogue("Max", "Great! It's decided then - we're watching Barbie!. Yayyyy~~", noChoices, 10, "max", "theatre");

        dialogues[10] = new Dialogue("Max", "Also, one more thing - I left my wallet at home.", noChoices, 11, "max", "theatre");
        
        dialogues[11] = new Dialogue("Max", "Do you mind paying for my ticket today?", noChoices, 12, "max", "theatre");

        String[] choices12 = {"Uhh... sure.", "No way, pay for it yourself!", "Will you at least pay me back?"};
        int[] lead12 = {15, 13, 14};

        dialogues[12] = new Dialogue("James", "...", choices12, lead12, "james", "theatre");

        dialogues[13] = new Dialogue("James", "(It's best not to argue with Max)", noChoices, 12, "james", "theatre");

        dialogues[14] = new Dialogue("James", "(He'll probably say yes... and never pay me back anyway)", noChoices, 12, "james", "theatre");

        dialogues[15] = new Dialogue("Max", "Alright, thanks, buddy.", noChoices, 16, "max", "theatre");

        dialogues[16] = new Dialogue("James", "*Oh shoot... I'm broke now. That was the last of my allowance...*", noChoices, 17, "james", "theatre");

        dialogues[17] = new Dialogue("James", "(I'll at least enjoy the movie, now...)", noChoices, 18, "james", "theatre");

        dialogues[18] = new Dialogue("Narrator", "After the movie, it was around 11 PM. James was tired and went home to sleep.", noChoices, 19, "narrator", "narrator");
        
        dialogues[19] = new Dialogue("Narrator", "However, he suddenly gets a call from Max at 1 AM!", noChoices, 20, "narrator", "narrator");

        dialogues[20] = new Dialogue("James", "*Ring* *Ring* *Ring*...", noChoices, 21, "james", "james_room");

        dialogues[21] = new Dialogue("James", "Ughhhh... what is that???", noChoices, 22, "james", "james_room");

        dialogues[22] = new Dialogue("James", "Hello? Who is it?", noChoices, 23, "james", "max_room");

        dialogues[23] = new Dialogue("Max", "Yo! It's me!", noChoices, 24, "max", "max_room");

        dialogues[24] = new Dialogue("Max", "I couldn't sleep because I kept thinking about Barbie!", noChoices, 25, "max", "max_room");

        dialogues[25] = new Dialogue("Max", "Oh, my beloved Barbie~~, keeping me up late at night.", noChoices, 26, "max", "max_room");

        dialogues[26] = new Dialogue("Max", "Anyways, I need you to keep me company.", noChoices, 27, "max", "max_room");

        String[] choices27 = {"Okay, fine."};
        int[] lead27 = {28};
        dialogues[27] = new Dialogue("James", "(That wasn't even a question...)", choices27, lead27, "james", "james_room");

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
            if (s == 0) return;
            choiceIndex = (choiceIndex + 1) % s;
            draw.repaint();
        } else if (e.getKeyChar() == 'w'){
            int s = d.getChoices().size();
            if (s == 0) return;
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

            // rendering fonts
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

            // draws the background
            g.drawImage(currentDialogue.getBackgroundImage(), 0, 0, 800, 500, null);

            g.setColor(Color.BLACK);
            g.setFont(nameFont);

            // draws the current dialogue
            g.drawString(currentDialogue.getCharacterName(), 180, 130);

            g.setColor(Color.BLACK);
            g.drawRect(50, 250, 700, 200);

            int y_text = 280;

            g.setFont(textFont);
            g.fillRect(50, 50, 100, 100);


            int pos = 0;
            String currentString = "";

            while (pos < currentDialogue.getDialogueText().length()){
                String currentWord = "";
                int pos2 = pos;

                while (pos2 < currentDialogue.getDialogueText().length() && currentDialogue.getDialogueText().charAt(pos2) != ' '){
                    currentWord += currentDialogue.getDialogueText().charAt(pos2);
                    pos2++;
                }

                pos2++;
                if (pos2 < currentDialogue.getDialogueText().length()) currentWord += " ";

                if (currentWord.length() + currentString.length() <= MAX_CHAR){
                    currentString += currentWord;
                    pos = pos2;      
                } else {
                    g.drawString(currentString, 60, y_text);
                    currentString = currentWord;
                    pos = pos2;
                    y_text += 30;   
                }
            }

            if (currentString != ""){
                g.drawString(currentString, 60, y_text);
                currentString = "";
                y_text += 30;
            }

            g.drawImage(currentDialogue.getCharacterIcon(), 60, 60, 80, 80, null);

            for (int i = 0; i < currentDialogue.getChoices().size(); i++){
                String symbol = (i == choiceIndex ? "*" : "-");
                g.drawString(symbol + " " + currentDialogue.getChoices().get(i), 60, y_text + 30 * i + 15);
            }
        }
    }
}
