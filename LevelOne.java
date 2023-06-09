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
public class LevelOne extends Screen {
    private Drawing draw;
    private Dialogue[] dialogues;
    private int dialogueIndex;
    private int choiceIndex;
    private final int TOTAL_LINES = 72;
    private final int MAX_CHAR = 55;
    private int x, y;

    public LevelOne(){
        super();

        dialogues = new Dialogue[TOTAL_LINES + 1];

        // initializes the lines of dialogue
        String[] noChoices = {};
        dialogues[1] = new Dialogue("James", "Hey Max! It's good to see you this lovely Sunday afternoon.", noChoices, 2, "james_normal_happy", "theatre");
        
        dialogues[2] = new Dialogue("Max", "Hello, James. Any ideas on which movie we should watch today?", noChoices, 3, "max_normal", "theatre");

        String[] choices3 = {"The Super Mario Bros. Movie", "Guardians of the Galaxy Vol. 3", "John Wick: Chapter 4", "Halloween Ends"};
        int[] lead3 = {4, 4, 4, 4};
        dialogues[3] = new Dialogue("James", "Hmm... there are many good options. I think we should watch:", choices3, lead3, "james_normal_thinking", "theatre");

        dialogues[4] = new Dialogue("Max", "Nah, I'd rather watch Barbie. I really love Barbie...", noChoices, 5, "max_smirk", "theatre");

        dialogues[5] = new Dialogue("Max", "...and honestly, I don't think your choice is very good. Let's watch Barbie, okay?", noChoices, 6, "max_bored", "theatre");

        String[] choices6 = {"Okay", "I would rather not watch Barbie, though..."};
        int[] lead6 = {9, 7};
        dialogues[6] = new Dialogue("James", "...", choices6, lead6, "james_normal_thinking", "theatre");

        dialogues[7] = new Dialogue("James", "(It's best to not argue with Max)", noChoices, 8, "james_normal_neutral", "theatre");

        dialogues[8] = new Dialogue("James", "(With Max, it's not like my choices really matter, anyway...)", noChoices, 6, "james_normal_rolling", "theatre");

        dialogues[9] = new Dialogue("Max", "Great! It's decided then - we're watching Barbie!", noChoices, 10, "max_smirk", "theatre");

        dialogues[10] = new Dialogue("Max", "Also, one more thing - I left my wallet at home.", noChoices, 11, "max_normal", "theatre");
        
        dialogues[11] = new Dialogue("Max", "Do you mind paying for my ticket today?", noChoices, 12, "max_normal", "theatre");

        String[] choices12 = {"Uhh... sure.", "No way, pay for it yourself!", "Will you at least pay me back?"};
        int[] lead12 = {15, 13, 14};

        dialogues[12] = new Dialogue("James", "...", choices12, lead12, "james_normal_thinking", "theatre");

        dialogues[13] = new Dialogue("James", "(It's best not to argue with Max)", noChoices, 12, "james_normal_neutral", "theatre");

        dialogues[14] = new Dialogue("James", "(He'll probably say yes... and never pay me back anyway)", noChoices, 12, "james_normal_rolling", "theatre");

        dialogues[15] = new Dialogue("Max", "Alright.", noChoices, 16, "max_smirk", "theatre");

        dialogues[16] = new Dialogue("James", "(Oh no... I'm broke now. That was the last of my allowance...)", noChoices, 17, "james_normal_neutral", "theatre");

        dialogues[17] = new Dialogue("James", "(I'll at least enjoy the movie, now...)", noChoices, 18, "james_normal_happy", "theatre");

        dialogues[18] = new Dialogue("Narrator", "After the movie, it was around 11 PM. James was tired and went home to sleep.", noChoices, 19, "narrator", "narrator");
        
        dialogues[19] = new Dialogue("Narrator", "However, he suddenly gets a call from Max at 2 AM!", noChoices, 20, "narrator", "narrator");

        dialogues[20] = new Dialogue("James", "*Ring* *Ring* *Ring*...", noChoices, 21, "james_tired_neutral", "james_room");

        dialogues[21] = new Dialogue("James", "Ughhhh... who is that???", noChoices, 22, "james_tired_rolling", "james_room");

        dialogues[22] = new Dialogue("James", "Hello? Who is it?", noChoices, 23, "james_tired_rolling", "max_room");

        dialogues[23] = new Dialogue("Max", "Yo! It's me!", noChoices, 24, "max_normal", "max_room");

        dialogues[24] = new Dialogue("Max", "I couldn't sleep because I kept thinking about Barbie!", noChoices, 25, "max_normal", "max_room");

        dialogues[25] = new Dialogue("Max", "Oh, my beloved Barbie~~, keeping me up late at night.", noChoices, 26, "max_smirk", "max_room");

        dialogues[26] = new Dialogue("Max", "Anyways, I need you to keep me company.", noChoices, 27, "max_bored", "max_room");

        String[] choices27 = {"Okay, fine."};
        int[] lead27 = {28};
        dialogues[27] = new Dialogue("James", "(That wasn't even a question...)", choices27, lead27, "james_tired_rolling", "james_room");

        dialogues[28] = new Dialogue("James", "What do you want to talk about?", noChoices, 29, "james_tired_neutral", "james_room");

        dialogues[29] = new Dialogue("Max", "Ok so let's talk about Barbie. I really liked the plot of the Barbie movie, and I thought that it did a great job encapsulating the wittiness and sassiness that the original Barbie characters display. The movie did such a great job at capturing the characters' essence that I was even brought back to my childhood.", noChoices, 30, "max_angry", "max_room");

        dialogues[30] = new Dialogue("Max", "Now, don't even get me started on the plot twist. When Barbie and Ken discovered the joys and perils of living amongst humans - which was so different from their ideal life living in Barbie Land - I was so moved by the human struggles that they were going through and how that represents the perpetual coming-of-age change that Barbie fans go through as they grow up and understand the real world.", noChoices, 31, "max_angry", "max_room");

        dialogues[31] = new Dialogue("Narrator", "Max continued to speak about Barbie for the next hour, until he eventually tired himself out and fell asleep.", noChoices, 32, "narrator", "narrator");

        dialogues[32] = new Dialogue("Narrator", "At this point, it was 3 AM - way later than James usually sleeps. The next day, he went to school, barely able to keep his eyes open.", noChoices, 33, "narrator", "narrator");

        dialogues[33] = new Dialogue("James", "(I'm so tired... I can't wait for this class to end so that I can go eat lunch)", noChoices, 34, "james_tired_neutral", "classroom");

        dialogues[34] = new Dialogue("James", "(Oh... wait... I don't have enough money to buy lunch...)", noChoices, 35, "james_tired_rolling", "classroom");

        dialogues[35] = new Dialogue("Narrator", "1 hour later...", noChoices, 36, "narrator", "narrator");

        dialogues[36] = new Dialogue("James", "(Well, at least class is over now.)", noChoices, 37, "james_tired_happy", "classroom");

        dialogues[37] = new Dialogue("Daniel", "Hey! My name's Daniel. You're James, right? I noticed you were looking really tired in class today.", noChoices, 38, "daniel_happy", "classroom");

        dialogues[38] = new Dialogue("Daniel", "I was just wondering if you were alright.", noChoices, 39, "daniel_happy", "classroom");

        dialogues[39] = new Dialogue("James", "Yeah, I'm feeling fine.", noChoices, 40, "james_tired_neutral", "classroom");

        dialogues[40] = new Dialogue("Daniel", "Would you like to eat something?", noChoices, 41, "daniel_happy", "classroom");

        dialogues[41] = new Dialogue("James", "I would like to, but I don't have any money on me...", noChoices, 42, "james_tired_neutral", "classroom");

        dialogues[42] = new Dialogue("Daniel", "Oh, no worries, I'll buy you something. It's on me. What would you like?", noChoices, 43, "daniel_happy", "classroom");

        String[] choices43 = {"Burger", "Salad", "Hot Dog", "You really don't have to do that"};
        int[] lead43 = {44, 45, 46, 47};
        dialogues[43] = new Dialogue("James", "...", choices43, lead43, "james_tired_thinking", "classroom");

        dialogues[44] = new Dialogue("Daniel", "The burgers here aren't as good as McDonald's, but they're alright.", noChoices, 49, "daniel_neutral", "classroom");

        dialogues[45] = new Dialogue("Daniel", "The healthy choice! I think I'll grab a salad for myself, too.", noChoices, 49, "daniel_happy", "classroom");

        dialogues[46] = new Dialogue("Daniel", "I love hot dogs! I would choose that, too.", noChoices, 49, "daniel_happy", "classroom");

        dialogues[47] = new Dialogue("Daniel", "No, it's really alright. I insist.", noChoices, 43, "daniel_happy", "classroom");

        dialogues[48] = new Dialogue("James", "(Daniel is really kind, isn't he? If only Max was more like him.)", noChoices, 43, "james_tired_happy", "classroom");

        dialogues[49] = new Dialogue("Daniel", "Enjoy your meal!", noChoices, 50, "daniel_happy", "classroom");

        dialogues[50] = new Dialogue("Daniel", "By the way - if you don't mind me asking - why don't you carry any money on you? Don't you have an allowance?", noChoices, 51, "daniel_neutral", "classroom");

        String[] choices51 = {"Tell a lie", "Tell the truth"};
        int[] lead51 = {52, 54};
        dialogues[51] = new Dialogue("James", "Well, normally I have my allowance, but...", choices51, lead51, "james_tired_thinking", "classroom");

        dialogues[52] = new Dialogue("James", "I just left it at home today.", noChoices, 53, "james_tired_neutral", "classroom");

        dialogues[53] = new Dialogue("Daniel", "Ah. I see.", noChoices, 63, "daniel_neutral", "classroom");

        dialogues[54] = new Dialogue("James", "My friend Max asked me to buy him a movie ticket yesterday, which used up the last of my cash this week.", noChoices, 55, "james_tired_neutral", "classroom");

        dialogues[55] = new Dialogue("Daniel", "He'll pay you back, right?", noChoices, 56, "daniel_neutral", "classroom");

        dialogues[56] = new Dialogue("James", "Max usually doesn't pay me back.", noChoices, 57, "james_tired_rolling", "classroom");

        dialogues[57] = new Dialogue("Daniel", "\"Usually\"? Does Max do this type of thing often?", noChoices, 58, "daniel_neutral", "classroom");

        dialogues[58] = new Dialogue("James", "Yeah, I guess he does this quite often.", noChoices, 59, "james_tired_neutral", "classroom");

        dialogues[59] = new Dialogue("James", "He also kept me up all night yesterday, which is why I was so tired today.", noChoices, 60, "james_tired_neutral", "classroom");

        dialogues[60] = new Dialogue("Daniel", "Wow.", noChoices, 61, "daniel_neutral", "classroom");

        dialogues[61] = new Dialogue("Daniel", "I gotta say, this person sounds like a pretty toxic friend.", noChoices, 62, "daniel_neutral", "classroom");

        dialogues[62] = new Dialogue("Daniel", "If I were you, I would have a serious talk with him and perhaps consider ending this relationship if it remains toxic.", noChoices, 63, "daniel_neutral", "classroom");

        dialogues[63] = new Dialogue("James", "...", noChoices, 64, "james_tired_neutral", "classroom");

        dialogues[64] = new Dialogue("Daniel", "Well, I'll see you around, James. Hope that food made you feel a little better!", noChoices, 65, "daniel_happy", "classroom");

        dialogues[65] = new Dialogue("James", "Yes, thank you for the food. I'll see you later.", noChoices, 66, "james_tired_happy", "classroom");

        dialogues[66] = new Dialogue("Narrator", "End of Level 1", noChoices, 67, "narrator", "closing_one");

        dialogues[67] = new Dialogue("Narrator", "In Level 1, Max demonstrated 3 key characteristics of a toxic friend.", noChoices, 68, "narrator", "closing_one");

        dialogues[68] = new Dialogue("Narrator", "1) Toxic friends put you down. It was acceptable for Max to prefer Barbie, but he should not have insulted James' choice.", noChoices, 69, "narrator", "closing_one");

        dialogues[69] = new Dialogue("Narrator", "2) Toxic friends use you. Max used James to pay for his movie ticket (with no intention to pay him back) and to keep him company at 2 AM.", noChoices, 70, "narrator", "closing_one");

        dialogues[70] = new Dialogue("Narrator", "3) Toxic friends put themselves first. Good friends should value their friends' opinions, but Max ignored James' movie choice. Also, Max forced James to stay up until 2 AM, which left James very tired the next day.", noChoices, 71, "narrator", "closing_one");

        dialogues[71] = new Dialogue("Narrator", "Lastly, while it is usually a good idea to open to somebody you trust about your toxic relationship, it is always your choice and you should never feel forced to speak about it.", noChoices, 72, "narrator", "closing_one");

        dialogues[72] = new Dialogue("Narrator", "Now that you have learned about some of the characteristics of a toxic friend, help James through Level 2 as he reflects on his relationship with Max and as he learns how to deal with his toxic friend.", noChoices, -1, "narrator", "closing_one");

        dialogueIndex = 1;
        choiceIndex = 0;

        draw = new Drawing();
        draw.addKeyListener(this);
        draw.addMouseMotionListener(this);
        draw.addMouseListener(this);
        draw.setFocusable(true);

        frame.add(draw, BorderLayout.CENTER);
    }

    public void keyTyped(KeyEvent e){
        if (dialogueIndex == -1) return;

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
            // daniel
            if (dialogueIndex == 51){
                if (choiceIndex == 0) Main.game.spoke_to_daniel = false;
                else Main.game.spoke_to_daniel = true;
            }

            dialogueIndex = d.getLead().get(choiceIndex);
            
            if (dialogueIndex == -1){
                Main.game.level = 2;
            }

            choiceIndex = 0;
            draw.repaint();
        }
    }

    public void mouseMoved(MouseEvent e){
        x = e.getX();
        y = e.getY();

        draw.repaint();
    }

    public void mouseClicked(MouseEvent e){
        if (dialogueIndex == -1){
            // end scene
            if (275 <= x && x <= 505 && 195 <= y && y <= 210){
                // continue to level 2
                Main.game.setState(2);
            }
            if (300 <= x && x <= 470 && 265 <= y && y <= 280){
                // return to menu
                Main.game.setState(0);
            }
        } 
    }

    class Drawing extends JComponent {
        public void paint(Graphics g){
            // renders fonts
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

            // end of game scene
            if (dialogueIndex == -1){
                g.setColor(Color.BLACK);
                g.setFont(textFont);

                if (275 <= x && x <= 505 && 195 <= y && y <= 210){
                    g.setColor(new Color(140, 70, 70));
                } else {
                    g.setColor(Color.BLACK);
                }

                g.drawString("Continue to Level 2", 275, 210);

                if (300 <= x && x <= 470 && 265 <= y && y <= 280){
                    g.setColor(new Color(140, 70, 70));
                } else {
                    g.setColor(Color.BLACK);
                }

                g.drawString("Return to Menu", 300, 280);

                return;
            }

            // otherwise, renders dialogue
            Dialogue currentDialogue = dialogues[dialogueIndex];

            // draws the background
            g.drawImage(currentDialogue.getBackgroundImage(), 0, 0, 800, 500, null);

            g.setColor(Color.BLACK);
            g.setFont(nameFont);

            // draws the current dialogue - narrator case
            if (currentDialogue.getCharacterName().equals("Narrator")){
                g.setColor(Color.BLACK);
                g.drawRect(50, 50, 700, 400);

                int y_text = 80;

                g.setFont(textFont);

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

                return;
            }

            // draws the current dialogue - character case
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
