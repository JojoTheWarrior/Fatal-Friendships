import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

/** 
 * Displays the third screen - contains predialogue, rock dodging game, and ending dialogue.
 */
public class LevelThree extends Screen {
    /** The graphics component. */
    private Drawing draw;
    /** The game state. */
    private int state;
    /** The index of the dialogue currently being displayed. */
    private int dialogueIndex;
    /** The index of the current choice. */
    private int choiceIndex;
    /** The script containing all the dialogues. */
    private Dialogue[] dialogues;
    /** The total number of lines in the dialogue - size of the dialogues array. */
    private final int TOTAL_LINES = 39;
    /** Maximum number of characters that can be displayed in a line before making a new line. */
    private final int MAX_CHAR = 55;

    /** Max's x-coordinate on the screen. */
    private int max_x;
    /** James' x-coordinate on the screen. */
    private int james_x;
    /** James' y-coordinate on the screen */
    private int james_y;
    /** The speed that James can move at. */
    private int james_speed;
    /** The next x-coordinate that Max wants to throw a rock from. */
    private int max_goal;
    /** The speed that Max moves at. */
    private int max_speed;
    /** The rocks' x-positions. */
    private ArrayList<Integer> rocks_x;
    /** The rocks' y-positions. */
    private ArrayList<Integer> rocks_y;
    /** The speed that the rocks are falling at. */
    private ArrayList<Integer> rocks_falling;
    /** Max's IQ, out of 100. */
    private int max_iq;
    /** James' HP, out of 100. */
    private int james_hp;
    /** The mode that Max is in, during the battle. 0 is throwing rocks normally, 1 speeds up, 2 becomes different speeds of rock, 3 becomes different speeds of movement and much faster rock speed. */
    private int max_state;
    /** The amount of time left on James' immunity. */
    private int james_immunity;
    /** The amount of time left on James' incorrect answer cooldown. */
    private int incorrect_cooldown;
    /** The amount of time left on the losing timer. */
    private int lose_timer;
    /** The amount of time left on the winning timer. */
    private int win_timer;
    /** The amount of time left on the ending timer. */
    private int end_timer;
    /** The multiple choice questions left in the game (shuffled). */
    private ArrayList<Question> mcq;
    /** The multiple choice questions that are originally in the game. */
    private ArrayList<Question> og_mcq;
    /** The question index that the game is currently at (out of 200). */
    private int questionIndex;
    /** The index of the choice that the player is currently selecting. */
    private int questionChoiceIndex;
    /** Maximum number of characters that can be displayed in one line of the question field. */
    private final int MAX_CHAR_Q = 45;
    /** Maximum number of characters that can be displayed in one line of the answer field. */
    private final int MAX_CHAR_A = 40;

    /** Creates a new Level Three. */
    public LevelThree(){
        draw = new Drawing();
        state = 0;
        max_x = 100;
        max_speed = 10;
        max_goal = (int) (Math.random() * 300) + 25;
        max_state = 0;
        james_speed = 20;
        james_x = 185;
        james_y = 420;
        questionIndex = 0;
        questionChoiceIndex = 0;
        incorrect_cooldown = 0;
        lose_timer = 0;
        win_timer = 0;
        end_timer = 0;

        rocks_x = new ArrayList<Integer>();
        rocks_y = new ArrayList<Integer>();
        rocks_falling = new ArrayList<Integer>();

        dialogues = new Dialogue[TOTAL_LINES + 1];

        String[] noChoices = {};
        dialogues[1] = new Dialogue("James", "Max! We meet again...", noChoices, 2, "james_normal_neutral", "colosseum");

        dialogues[2] = new Dialogue("James", "Before, you have deceived and manipulated me. I was a fool for not seeing you for who you really were.", noChoices, 3, "james_normal_neutral", "colosseum");

        dialogues[3] = new Dialogue("Max", "I have... no idea what you are talking about.", noChoices, 4, "max_bored", "colosseum");

        dialogues[4] = new Dialogue("James", "Gaslighting... typical.", noChoices, 5, "james_normal_rolling", "colosseum");

        dialogues[5] = new Dialogue("James", "However, I have learned about signs of a toxic friendship and how to deal with a toxic friend. I understand this situation better, now.", noChoices, 6, "james_normal_neutral", "colosseum");

        dialogues[6] = new Dialogue("Max", "Hah...", noChoices, 7, "max_smirk", "colosseum");

        dialogues[7] = new Dialogue("Max", "Fine! I admit that I have used you in the past and treated you badly.", noChoices, 8, "max_angry", "colosseum");

        dialogues[8] = new Dialogue("James", "...", noChoices, 9, "james_normal_neutral", "colosseum");

        dialogues[9] = new Dialogue("James", "Is that all?", noChoices, 10, "james_normal_rolling", "colosseum");
        
        dialogues[10] = new Dialogue("Max", "WELL, WHAT ARE YOU GOING TO DO ABOUT IT?", noChoices, 11, "max_angry", "colosseum");

        dialogues[11] = new Dialogue("Max", "YOU ARE TOO WEAK TO DO ANYTHING!", noChoices, 12, "max_angry", "colosseum");

        dialogues[12] = new Dialogue("James", "No.", noChoices, 13, "james_normal_neutral", "colosseum");

        dialogues[13] = new Dialogue("James", "I have taken too much abuse from you.", noChoices, 14, "james_normal_neutral", "colosseum");

        dialogues[14] = new Dialogue("Max", "Haha!", noChoices, 15, "max_smirk", "colosseum");

        dialogues[15] = new Dialogue("Max", "Are you going to fight me?", noChoices, 16, "max_smirk", "colosseum");

        dialogues[16] = new Dialogue("James", "No. Violence is the worst way to resolve an issue.", noChoices, 17, "james_normal_neutral", "colosseum");

        dialogues[17] = new Dialogue("James", "I'll defeat you...", noChoices, 18, "james_normal_neutral", "colosseum");

        dialogues[18] = new Dialogue("James", "...through an informative lesson-battle about how to maintain a healthy friendship!", noChoices, 19, "james_normal_happy", "colosseum");

        dialogues[19] = new Dialogue("James", "I'll be answering educational multiple choice questions to deal damage!", noChoices, 20, "james_normal_happy", "colosseum");

        dialogues[20] = new Dialogue("Max", "...", noChoices, 21, "max_bored", "colosseum");

        dialogues[21] = new Dialogue("Max", "Well, screw that. I'm throwing rocks at you!", noChoices, 22, "max_bored", "colosseum");

        dialogues[22] = new Dialogue("Max", "Every time I hit you with a rock, you take 15 damage! Prepare to die, fool!", noChoices, 23, "max_angry", "colosseum");

        dialogues[23] = new Dialogue("James", "Every time I correctly answer a multiple choice question, you gain 5 IQ and I gain back 5 HP! I will dodge rocks with WASD, change answers with the arrow keys, and submit answers using M. Prepare to learn a lot about healthy friendships!", noChoices, -1, "james_normal_happy", "colosseum");

        dialogueIndex = 1;
        choiceIndex = 0;

        // rendering the multiple choice questions
        mcq = new ArrayList<Question>();
        og_mcq = new ArrayList<Question>();

        mcq.add(new Question("What are common characteristics of toxic friendships?", "Mutual respect and support", "Open communication and trust", "Manipulation and control", "Healthy boundaries and compromises", 3));

        mcq.add(new Question("What is a key characteristic of a healthy friendship?", "Lack of communication", "Trust and loyalty", "Frequent conflicts and arguments", "Unbalanced power dynamics", 2));

        mcq.add(new Question("How do healthy friends respect each others' boundaries?", "They communicate openly about their boundaries and honor them", "They constantly push each other's boundaries to encourage personal growth", "They ignore each other's boundaries for the sake of spontaneity", "They manipulate each other's boundaries to maintain control", 1));

        mcq.add(new Question("How do healthy friends support each others’ opinions?", "By avoiding discussions about opinions to prevent conflict", "By dismissing and invalidating differing opinions", "By actively listening, respecting, and acknowledging diverse viewpoints", "By pressuring each other to conform to similar opinions", 3));

        mcq.add(new Question("In a healthy friendship, how should disagreements be settled?", "Engaging in active listening, respectful communication, and finding a compromise.", "Allowing the disagreement to escalate into a heated argument.", "Ignoring the disagreement and avoiding confrontation", "Imposing one person's perspective and dismissing the other's viewpoint", 1));

        mcq.add(new Question("Instead of using your friends, how should you value them?", "Manipulating and taking advantage of their kindness", "Prioritizing your own desires above other's", "Using friends as a mean to achieve personal goals", "Recognizing their worth, appreciating their friendship, and supporting them", 4));

        mcq.add(new Question("Is it true or false that friends should only engage in conversation about fun topics?", "True, friends should only discuss light and entertaining topics to maintain an engaging relationship", "False, friends should have conversations about both fun and serious subjects to develop a deeper relationship", "True, serious discussions should be avoided in friendships to maintain a carefree atmosphere", "False, friends should exclusively focus on serious and intellectual discussions to develop a meaningful relationship", 2));

        mcq.add(new Question("Why is trust important in a healthy friendship?", "Trust allows friends to easily control and manipulate each other", "Trust promotes an environment for open communication and vulnerability", "Trust creates a sense of dependency and reliance on one another", "Trust leads to frequent conflicts and misunderstandings", 2));

        mcq.add(new Question("Which of the following demonstrates empathy?", "Showing understanding and compassion towards someone's feelings", "Dismissing someone's feelings and experiences as unimportant", "Ignoring someone's emotions and failing to acknowledge them", "Invalidating someone's emotions and minimizing their experiences", 1));

        mcq.add(new Question("How do healthy friends communicate effectively?", "Always keeping conversation topics fun", "Listening actively and respecting each others' opinions", "Using passive-aggressive behavior and sarcasm for humor", "Criticizing and belittling each other's choice", 2));

        mcq.add(new Question("How do healthy friends apologize?", "Acknowledging their mistake to themselves and taking responsibility for their actions", "Making excuses and deflecting blame onto others", "Ignoring the situation and hoping it will resolve on its own", "Offering a sincere and heartfelt apology without making excuses", 4));
        
        mcq.add(new Question("How should healthy friends maintain a sense of equality in their friendship?", "Establishing a power dynamic and friendship hierarchy", "Manipulating and controlling each other to maintain dominance", "Treating each other as equals with mutual respect", "One friend takes on most of the responsibilities and decision-making", 3));

        mcq.add(new Question("How do healthy friends handle constructive criticism?", "Reacting impulsively without considering the feedback", "Engaging in a productive dialogue to understand the perspective", "Taking time to reflect and respond thoughtfully later", "Escalating the situation and involving others to gain support", 2));

        mcq.add(new Question("Which of the following is not a good way to confront a toxic friend?", "Ignoring the toxic behavior and hoping it will resolve on its own", "Seeking support from trusted third party for guidance and advice", "Speak calmly and actively listen", "Speak with a professional / trust individual before", 1));

        mcq.add(new Question("How can healthy friends provide constructive criticism without being harsh?", "By being overly critical and judgmental in their feedback", "By avoiding giving any feedback to prevent potential conflict", "By using a gentle and tactful approach, focusing on specific behaviors or actions rather than attacking the person", "By dismissing or ignoring the need for constructive criticism altogether", 3));

        mcq.add(new Question("What is an important aspect of trust in a healthy friendship?", "Spreading rumors and gossiping", "Sharing personal information without consent", "Honoring confidentiality and keeping secrets", "Doubting and questioning the other person's intentions", 3));

        mcq.add(new Question("How do healthy friends support each other during tough times?", "Dismiss their friend's problems as insignificant", "Criticize and blame their friend for their difficulties", "Encourage their friend to handle everything independently", "Offer genuine care, empathy, and a listening ear", 4));

        mcq.add(new Question("How do healthy friends handle apologies?", "Refuse to apologize even when they're in the wrong", "Take responsibility, express remorse, and make amends", "Offer insincere apologies to pacify the other person", "Expect their friend to apologize first before considering their own apology", 2));

        mcq.add(new Question("How do healthy friends handle personal growth and change?", "Support and encourage each other's personal growth and development", "Discourage and undermine each other's aspirations and goals", "Show indifference and lack of interest in each other's progress", "Compete and feel threatened by each other's growth", 1));

        mcq.add(new Question("How should you deal with gaslighting in a toxic friendship?", "Accept and internalize the gaslighter's manipulation", "Confront the gaslighter aggressively and demand an apology", "Seek support, trust your instincts, and set boundaries", "Engage in gaslighting tactics to fight back and protect yourself", 3));

        for (Question q : mcq) og_mcq.add(q);

        Collections.shuffle(mcq);

        dialogues[24] = new Dialogue("Max", "James... you actually defeated me.", noChoices, 25, "max_angry", "rubble");

        dialogues[25] = new Dialogue("Max", "After listening to your 20 multiple choice questions, I think I am starting to understand my wrongdoings.", noChoices, 26, "max_bored", "rubble");

        dialogues[26] = new Dialogue("Max", "In the past, I have repeatedly used you and put myself before you.", noChoices, 27, "max_bored", "rubble");

        dialogues[27] = new Dialogue("Max", "I never once stopped to consider to the damage that my actions have done to you - one whom I called a friend.", noChoices, 28, "max_bored", "rubble");

        dialogues[28] = new Dialogue("Max", "However, I want you to know that I never meant to hurt you.", noChoices, 29, "max_bored", "rubble");

        dialogues[29] = new Dialogue("Max", "I did not know what the right way to behave in a friendship was. I was misguided...", noChoices, 30, "max_bored", "rubble");

        dialogues[30] = new Dialogue("Max", "...but your educational multiple choice questions have enlightened me on what a healthy friendship looks like.", noChoices, 31, "max_bored", "rubble");

        dialogues[31] = new Dialogue("Max", "In full honesty: I thought that I was doing the right thing!", noChoices, 32, "max_bored", "rubble");

        dialogues[32] = new Dialogue("Max", "You may accept my apology - or not. Either way, please know that I only ever wanted to be your friend.", noChoices, 33, "max_bored", "rubble");

        String choices33[] = {"Spare him", "Obliterate him"};
        int lead33[] = {34, 38};
        dialogues[33] = new Dialogue("James", "...", choices33, lead33, "james_tired_neutral", "rubble");

        dialogues[34] = new Dialogue("James", "I forgive you.", noChoices, 35, "james_normal_neutral", "rubble");

        dialogues[35] = new Dialogue("James", "Nobody is perfect, and I should not blame you for being misconceptioned.", noChoices, 36, "james_normal_neutral", "rubble");

        dialogues[36] = new Dialogue("James", "Maintaining a healthy friendship - like all other skills - requires practice. From today, as long as you learned from your mistakes and never go back to your old ways, I believe that we can have a healthy friendship.", noChoices, 37, "james_normal_happy", "rubble");

        dialogues[37] = new Dialogue("Max", "Thank you, James. You are a great friend.", noChoices, -1, "max_bored", "rubble");

        dialogues[38] = new Dialogue("James", "No.", noChoices, 39, "james_normal_happy", "rubble");

        dialogues[39] = new Dialogue("Max", "Ok.", noChoices, -1, "max_smirk", "rubble");

        max_iq = 0;
        james_hp = 100;

        draw.addMouseListener(this);
        draw.addKeyListener(this);
        draw.addMouseMotionListener(this);
        draw.setFocusable(true);

        frame.add(draw);
        frame.setVisible(true);
    }

    /** The graphical component - uses a Graphics component to draw. */
    class Drawing extends JComponent {
        /** Draws on the screen - depends on state. */
        public void paint(Graphics g){
            if (state == 0){
                // dialogue scene
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
            } if (state == 1){
                // renders fonts and images
                Font font15 = null;
                Font font25 = null;
                BufferedImage max_pfp = null;
                BufferedImage james_pfp = null;

                try {
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    font15 = Font.createFont(Font.TRUETYPE_FONT, new File("./assets/font/DeterminationMonoWebRegular-Z5oq.ttf")).deriveFont(15f);
                    ge.registerFont(font15);

                    font25 = Font.createFont(Font.TRUETYPE_FONT, new File("./assets/font/DeterminationMonoWebRegular-Z5oq.ttf")).deriveFont(25f);
                    ge.registerFont(font25);

                    james_pfp = ImageIO.read(new File("./assets/img/james_normal_happy.png"));
                    max_pfp = ImageIO.read(new File("./assets/img/max_angry.png"));
                } catch (Exception e) {

                }

                // layout
                g.setColor(Color.BLACK);

                g.drawRect(50, 50, 300, 400);
                g.drawRect(400, 50, 350, 175);
                g.drawRect(400, 275, 350, 175);

                // statistics
                g.setFont(font25);

                g.drawString("Statistics", 510, 300);

                g.fillRect(420, 305, 60, 60);
                g.fillRect(420, 380, 60, 60);

                g.drawImage(max_pfp, 425, 310, 50, 50, null);
                g.drawImage(james_pfp, 425, 385, 50, 50, null);

                g.setFont(font15);
                g.drawString(max_iq + " / 100 IQ", 660, 335);
                g.drawString(james_hp + " / 100 HP", 660, 410);

                g.fillRect(495, 315, 160, 40);
                g.fillRect(495, 390, 160, 40);

                int len = 150 * max_iq / 100;

                g.setColor(new Color(20, 105, 225));
                g.fillRect(500, 320, len, 30);

                g.setColor(Color.WHITE);
                g.fillRect(500 + len, 320, 150 - len, 30);

                len = 150 * james_hp / 100;

                g.setColor(Color.RED);
                g.fillRect(500, 395, len, 30);

                g.setColor(Color.WHITE);
                g.fillRect(500 + len, 395, 150 - len, 30);

                if (win_timer > 0){

                } else {
                    // draws falling rocks
                    if (max_x < max_goal) max_x += max_speed;
                    else max_x -= max_speed;

                    if (Math.abs(max_x - max_goal) <= 2 * max_speed){
                        // throw a rock!
                        rocks_x.add(max_goal + 15);
                        rocks_y.add(50);

                        if (max_state == 0){
                            rocks_falling.add(5);
                        } else if (max_state == 1){
                            rocks_falling.add(10);
                        } else if (max_state >= 2){
                            rocks_falling.add((int) (Math.random() * 5) + 10);
                        }

                        max_goal = (int) (Math.random() * 300) + 25;
                        
                        if (max_state == 0){
                            max_speed = 10;
                        } else if (max_state == 1){
                            max_speed = 15;
                        } else if (max_state == 2){
                            max_speed = 15;
                        } else if (max_state == 3){
                            max_speed = (int) (Math.random() * 15) + 15;
                        }
                    }
                }  

                if (rocks_y.size() >= 1000){
                    rocks_y.remove(0);
                    rocks_x.remove(0);
                    rocks_falling.remove(0);
                }

                // change all the rocks
                for (int i = rocks_x.size() - 1; i >= 0; i--){
                    rocks_y.set(i, rocks_y.get(i) + rocks_falling.get(i));

                    if (max_state == 0){
                        g.setColor(new Color(15, 0, 0));
                    } else if (max_state == 1){
                        g.setColor(new Color(100, 10, 10));
                    } else if (max_state == 2){
                        g.setColor(new Color(150, 0, 0));
                    } else if (max_state == 3){
                        g.setColor(new Color(255, (int) (Math.random() * 255), (int) (Math.random() * 255)));
                    }

                    // check for collision 
                    if (!(james_x + 15 < rocks_x.get(i) || rocks_x.get(i) + 25 < james_x) && !(james_y + 15 < rocks_y.get(i) || rocks_y.get(i) + 25 < james_y) && james_immunity == 0){
                        james_hp = Math.max(0, james_hp - 15);
                        james_immunity = 100;
                        incorrect_cooldown = 0;

                        if (james_hp == 0) lose_timer = 50;
                    }

                    g.fillRect(rocks_x.get(i), rocks_y.get(i), 25, 25);
                }

                james_immunity = Math.max(0, james_immunity - 1);

                // coloring max
                if (max_state == 0){
                    g.setColor(new Color(15, 0, 0));
                } else if (max_state == 1){
                    g.setColor(new Color(100, 10, 10));
                } else if (max_state == 2){
                    g.setColor(new Color(150, 0, 0));
                } else if (max_state == 3){
                    g.setColor(new Color(255, (int) (Math.random() * 255), (int) (Math.random() * 255)));
                }

                g.fillRect(max_x - 5, 45, 60, 60);
                g.drawImage(max_pfp, max_x, 50, 50, 50, null);

                // drawing james
                if (james_immunity > 0){
                    g.setColor(new Color(255, 100, 100));
                } else {
                    g.setColor(new Color(255, 0, 0));
                }

                g.fillRect(james_x, james_y, 15, 15);

                if (lose_timer > 0){
                    // restarts the game
                    g.setFont(font25);
                    g.setColor(Color.RED);
                    g.drawString("Game Over!", 515, 80);

                    lose_timer--;

                    if (lose_timer == 0){
                        // resets all variables before restarting the game
                        state = 0;
                        max_x = 100;
                        max_speed = 10;
                        max_goal = (int) (Math.random() * 300) + 25;
                        max_state = 0;
                        james_speed = 20;
                        james_x = 185;
                        james_y = 420;
                        questionIndex = 0;
                        questionChoiceIndex = 0;
                        incorrect_cooldown = 0;
                        lose_timer = 0;
                        dialogueIndex = 1;
                        choiceIndex = 0;

                        rocks_x = new ArrayList<Integer>();
                        rocks_y = new ArrayList<Integer>();
                        rocks_falling = new ArrayList<Integer>();

                        mcq = new ArrayList<Question>();

                        for (Question q : og_mcq) mcq.add(q);

                        Collections.shuffle(mcq);

                        max_iq = 0;
                        james_hp = 100;
                    }
                } else if (win_timer > 0){
                    // moves onto the final dialogue
                    g.setFont(font25);
                    g.setColor(Color.GREEN);
                    g.drawString("You won!", 515, 80);

                    // deleting all the rocks
                    for (int i = rocks_x.size() - 1; i >= 0; i--){
                        rocks_x.remove(i);
                        rocks_y.remove(i);
                        rocks_falling.remove(i);
                    }

                    win_timer--;

                    if (win_timer == 0){
                        // moves onto the last part of the dialogue
                        state = 2;
                        dialogueIndex = 24;
                        choiceIndex = 0;
                    }

                } else {
                    // display the multiple choice question
                    g.setFont(font25);
                    g.setColor(Color.BLACK);

                    g.drawString("Questions", 515, 80);

                    if (incorrect_cooldown > 0){
                        g.setFont(font15);
                        g.setColor(Color.RED);
                        g.drawString("Incorrect!", 520, 170);
                        incorrect_cooldown--;

                        g.setColor(Color.BLUE);
                        g.fillRect(james_x, james_y, 15, 15);
                    } else {
                        g.setFont(font15);
                        Question currentQuestion = mcq.get(questionIndex);

                        String words[] = currentQuestion.getQuestion().split(" ");

                        // at most two lines are necessary
                        String firstLine = "", secondLine = "", thirdLine = "";

                        int ptr = 0;

                        while (ptr < words.length && firstLine.length() + words[ptr].length() <= MAX_CHAR_Q){
                            firstLine += words[ptr] + " ";
                            ptr++;
                        } 

                        while (ptr < words.length){
                            secondLine += words[ptr] + " ";
                            ptr++;
                        }

                        g.drawString(firstLine, 410, 120);
                        g.drawString(secondLine, 410, 140);

                        // draw the current question
                        g.setFont(font25);
                        g.drawString("<", 410, 200);
                        g.drawString(">", 725, 200);

                        words = currentQuestion.getAnswers().get(questionChoiceIndex).split(" ");
                        ptr = 0;
                        firstLine = "";
                        secondLine = "";
                        boolean isSecondLine = false, isThirdLine = false;

                        // at most three lines - equalizes them
                        while (ptr < words.length && firstLine.length() + words[ptr].length() <= MAX_CHAR_A){
                            firstLine += words[ptr] + " ";
                            ptr++;
                        }

                        while (firstLine.length() + 2 <= MAX_CHAR_A){
                            firstLine = " " + firstLine + " ";
                        }

                        while (ptr < words.length && secondLine.length() + words[ptr].length() <= MAX_CHAR_A){
                            secondLine += words[ptr] + " ";
                            isSecondLine = true;
                            ptr++;
                        }

                        while (secondLine.length() + 2 <= MAX_CHAR_A){
                            secondLine = " " + secondLine + " ";
                        }

                        while (ptr < words.length){
                            thirdLine += words[ptr] + " ";
                            isThirdLine = true;
                            ptr++;
                        }

                        while (thirdLine.length() + 2 <= MAX_CHAR_A){
                            thirdLine = " " + thirdLine + " ";
                        }

                        g.setFont(font15);

                        if (isThirdLine){
                            g.drawString(firstLine, 435, 180);
                            g.drawString(secondLine, 435, 200);
                            g.drawString(thirdLine, 435, 220);
                        } else if (isSecondLine){
                            g.drawString(firstLine, 435, 190);
                            g.drawString(secondLine, 435, 210);
                        } else {
                            g.drawString(firstLine, 435, 195);
                        }
                    }
                    
                }

                draw.repaint();
            } else if (state == 2){
                // winning scene
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

                // end timer
                if (end_timer > 0){
                    g.setFont(nameFont);
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, 800, 500);

                    g.setColor(new Color(140, 70, 70, 255 - (255 * end_timer / 750)));
                    g.drawString("The End", 230, 240);

                    end_timer = Math.max(end_timer - 1, 1);

                    g.setFont(textFont);
                    g.drawString("By Alyn Huang and Joshua Wang", 215, 300);
                    g.drawString("Press M to exit the game", 238, 350);

                    draw.repaint();
                } else {
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
    }
    
    /**
     * Handles key pressed.
     * @param e The key pressed event that triggered this method.
     */
    public void keyPressed(KeyEvent e){
        if (state == 0){
            // dialogue scene
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

                if (dialogueIndex == -1) state = 1;

                choiceIndex = 0;
                draw.repaint();
            }
        } else if (state == 1){
            if (incorrect_cooldown > 0) return;

            Question currentQuestion = mcq.get(questionIndex);

            if (e.getKeyChar() == 'w'){
                if (james_y - james_speed > 50) james_y -= james_speed;
            } else if (e.getKeyChar() == 'a'){
                if (james_x - james_speed > 50) james_x -= james_speed;
            } else if (e.getKeyChar() == 's'){
                if (james_y + james_speed < 435) james_y += james_speed;
            } else if (e.getKeyChar() == 'd'){
                if (james_x + james_speed < 335) james_x += james_speed;
            } else if (e.getKeyChar() == 'm'){
                if (questionChoiceIndex == currentQuestion.getCorrect() - 1){
                    // correct answer!
                    questionIndex++;
                    max_iq += 5;
                    james_hp = Math.min(100, james_hp + 5);

                    if (max_iq < 25){
                        max_state = 0;
                    } else if (max_iq < 50){
                        max_state = 1;
                    } else if (max_iq < 75){
                        max_state = 2;
                    } else {
                        max_state = 3;
                    }

                    if (max_iq == 100){
                        james_immunity = 50;
                        win_timer = 50;
                    }
                } else {
                    // incorrect!
                    Question rem = mcq.remove(questionIndex);
                    mcq.add(rem);
                    incorrect_cooldown = 25;
                }
            }

            // for the questions
            if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                questionChoiceIndex = (questionChoiceIndex + 1) % 4;
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
                questionChoiceIndex = (questionChoiceIndex + 3) % 4;
            } 

            draw.repaint();
        } else if (state == 2){
            if (end_timer > 0){
                if (e.getKeyChar() == 'm'){
                    Main.game.frame.dispatchEvent(new WindowEvent(Main.game.frame, WindowEvent.WINDOW_CLOSING));
                }
            }
            // winning dialogue
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

                if (dialogueIndex == -1){
                    // the end
                    end_timer = 750;
                }

                choiceIndex = 0;
                draw.repaint();
            }
        }
    }
}
