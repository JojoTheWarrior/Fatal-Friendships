import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

/**
 * Diplays the second screen - contains predialogue and the maze game.
 */
public class LevelTwo extends Screen {
    /** The graphics component. */
    private Drawing draw;

    /** The game state. */
    private int state;
    /** Character location in the maze. */
    private int mazeloc;
    /** Mouse x-coordinate on the screen. */
    private int mouse_x;
    /** Mouse y-coordinate on the screen. */
    private int mouse_y;
    /** Array representing whether you can exit each room. */
    private boolean [][] canExit;

    /** Lines of dialogue. */
    private Dialogue[] dialogues;
    /** Total lines of dialogue - size of the dialogues array. */
    private final int TOTAL_LINES = 12;
    /** Current dialogue index. */
    private int dialogueIndex;
    /** Current choice selection, if any. */
    private int choiceIndex;

    /** Character x-coordinate on the screen. */
    private int james_x;
    /** Character y-coordinate on the screen. */
    private int james_y;
    /** The current answer hovered over. */
    private int current_answer;
    /** How much time is left on the timer for an incorrect answer. */
    private int incorrect_cooldown;
    /** Maximum number of characters in a line before a new line. */
    private final int MAX_CHAR = 55;

    /** Each cell in the maze contains one question. */
    private Question [][] maze = {
        {
            new Question("Instead of using your friends, how should you value them?", "Manipulating and taking advantage of their kindness", "Prioritizing your own desires above other's", "Using friends as a mean to achieve personal goals", "Recognizing their worth, appreciating their friendship, and supporting them", 4),
            new Question("What is a key characteristic of a healthy friendship?", "Lack of communication", "Trust and loyalty", "Frequent conflicts and arguments", "Unbalanced power dynamics", 2),
            new Question("What are common characteristics of toxic friendships?", "Mutual respect and support", "Open communication and trust", "Manipulation and control", "Healthy boundaries and compromises", 3)

        },
        {
            new Question("Which of the following demonstrates empathy?", "Showing understanding and compassion towards someone's feelings", "Dismissing someone's feelings and experiences as unimportant", "Ignoring someone's emotions and failing toeacknowledge them", "Invalidating someone's emotions and minimizing their experiences", 1),
            new Question("How do healthy friends communicate effectively?", "Always keeping conversation topics fun", "Listening actively and respecting each others' opinions", "Using passive-aggressive behavior and sarcasm for humor", "Criticizing and belittling each other's choice", 2),
            new Question("Which of the following is not a good way to confront a toxic friend?", "Ignoring the toxic behavior and hoping it will resolve on its own", "Seeking support frmom trusted third party for guidance and advice", "Speak calmly and actively listen", "Speak with a professional / trust individual before", 1)

        },
        {
            new Question("How do healthy friends handle apologies?", "Refuse to apologize even when they're in the wrong", "Take responsibility, express remorse, and make amends", "Offer insincere apologies to pacify the other person", "Expect their friend to apologize first before considering their own apology", 2),
            new Question("How do healthy friends handle personal growth and change?", "Support and encourage each other's personal growth and development", "Discourage and undermine each other's aspirations and goals", "Show indifference and lack of interest in each other's progress", "Compete and feel threatened by each other's growth", 1),
            new Question("How should you deal with gaslighting in a toxic friendship?", "Accept and internalize the gaslighter's manipulation", "Confront the gaslighter aggressively and demand an apology", "Seek support, trust your instincts, and set boundaries", "Engage in gaslighting tactics to fight back and protect yourself", 3)

        }
    };

    /** Creates a new Level 2. */
    public LevelTwo() {
        dialogueIndex = 1;
        choiceIndex = 0;
        state = 0;
        incorrect_cooldown = 0;

        james_x = 375;
        james_y = 225;

        current_answer = -1;
        mazeloc = 1;

        canExit = new boolean[3][3];

        // initializing dialogues for narrator 
        dialogues = new Dialogue[TOTAL_LINES + 1];

        String[] noChoices = {};
    
        if (Main.game.spoke_to_daniel){
            // james opened up
            dialogues[1] = new Dialogue("Narrator", "On his way home, James thinks about what Daniel said.", noChoices, 2, "narrator", "narrator");

            dialogues[2] = new Dialogue("James", "I think Daniel is right. He's an example of what a good friend should be like.", noChoices, 3, "james_normal_thinking", "menu");

            dialogues[3] = new Dialogue("James", "How will I confront Max about this, though? He is rather intimidating, and I don't know if he will even listen to me.", noChoices, 6, "James_normal_thinking", "menu");
        } else {
            // james did not open up
            dialogues[1] = new Dialogue("Narrator", "On his way home, James reflects on Daniel's kindness towards him.", noChoices, 2, "narrator", "menu");

            dialogues[2] = new Dialogue("James", "Daniel is such a nice person. I don't know if I can trust him yet, but maybe I should have opened up to him about Max.", noChoices, 3, "james_normal_thinking", "menu");

            dialogues[3] = new Dialogue("James", "It would have felt nice to open up to somebody about this, but I don't know who I can talk to.", noChoices, 4, "james_normal_thinking", "menu");

            dialogues[4] = new Dialogue("James", "I need to learn more about how to deal with a toxic friend...", noChoices, 5, "james_normal_thinking", "menu");

            dialogues[5] = new Dialogue("James", "...this cannot go on - Max is really starting to have a negative effect on my life.", noChoices, 6, "james_normal_thinking", "menu");
        }

        dialogues[6] = new Dialogue("Narrator", "James decides that he wants to learn more about toxic friendships, so he heads over to the library.", noChoices, 7, "narrator", "menu");

        dialogues[7] = new Dialogue("Narrator", "There, he finds a game on the computer that will teach him about toxic friendships.", noChoices, 8, "narrator", "menu");

        dialogues[8] = new Dialogue("Narrator", "In the game, James is in a maze with 9 rooms. Each room has a question with four answers.", noChoices, 9, "narrator", "narrator");

        dialogues[9] = new Dialogue("Narrator", "Use WASD to move around, and select an answer with the M key.", noChoices, 10, "narrator", "narrator");

        dialogues[10] = new Dialogue("Narrator", "If you get a question wrong, your maze progress will be reset and you will be sent back to Room #1!", noChoices, 11, "narrator", "narrator");

        dialogues[11] = new Dialogue("Narrator", "Finally, after answering all 9 questions and reaching the last room, enter the doorway to complete Level 2.", noChoices, 12, "narrator", "narrator");

        dialogues[12] = new Dialogue("Narrator", "That's all, have fun!", noChoices, -1, "narrator", "narrator");

        // initializes the questions

        draw = new Drawing();

        draw.addMouseMotionListener(this);
        draw.addMouseListener(this);
        draw.addKeyListener(this);
        draw.setFocusable(true);
        
        frame.add(draw, BorderLayout.CENTER);
    }

    /**
     * Handles mouse movement.
     * @param e The mouse move event that triggered this method.
     */
    public void mouseMoved(MouseEvent e){
        mouse_x = e.getX();
        mouse_y = e.getY();
    }

    /** 
     * Handles mouse clicks.
     * @param e The moues click event that triggered this method.
     */
    public void mouseClicked(MouseEvent e) {
        if (state == 3){
             // end scene
             if (275 <= mouse_x && mouse_x <= 505 && 195 <= mouse_y && mouse_y <= 210){
                // continue to level 3
                Main.game.setState(3);
            }
            if (300 <= mouse_x && mouse_x <= 470 && 265 <= mouse_y && mouse_y <= 280){
                // return to menu
                Main.game.setState(0);
            }
        }
    }

    /**
     * Updates James' position, the room that he is in, and whether or not he has selected an answer.
     */
    public void updatePosition(){
        int row = (mazeloc - 1) / 3, col = (mazeloc - 1) % 3;

        // if james can exit this room, check if he has passed the boundaries
        if (canExit[row][col]){
            current_answer = -1;

            // can he go up?
            if (james_y == 0){
                if (row > 0){
                    // yes
                    mazeloc -= 3;
                    james_x = 375;
                    james_y = 450;
                } else {
                    // no
                    james_y = 0;
                }

                return;
            }

            // can he go down?
            if (james_y >= 450){
                if (row < 2){
                    // yes
                    mazeloc += 3;
                    james_x = 375;
                    james_y = 0;
                } else {
                    // no
                    james_y = 450;
                }

                return;
            }

            // can he go left?
            if (james_x <= 0){
                if (col > 0){
                    // yes
                    mazeloc -= 1;
                    james_x = 750;
                    james_y = 225;
                } else {
                    // no
                    james_x = 0;
                }

                return;
            }

            // can he go right?
            if (james_x >= 750){
                if (col < 2){
                    // yes
                    mazeloc += 1;
                    james_x = 0;
                    james_y = 225;
                } else {
                    // no
                    james_x = 750;
                }

                return;
            }
        } else {
            // which answer is being selected?
            if (!(james_x + 50 < 303 || 369 < james_x) && !(james_y + 50 < 256 || 290 < james_y)){
                // top left
                current_answer = 1;
            } else if (!(james_x + 50 < 432 || 495 < james_x) && !(james_y + 50 < 256 || 294 < james_y)){
                // top right
                current_answer = 2;
            } else if (!(james_x + 50 < 303 || 371 < james_x) && !(james_y + 50 < 331 || 371 < james_y)){
                // bottom left
                current_answer = 3;
            } else if (!(james_x + 50 < 427 || 496 < james_x) && !(james_y + 50 < 331 || 370 < james_y)){
                // bottom right
                current_answer = 4;
            } else {
                current_answer = -1;
            }
        }

        draw.repaint();
    }

    /**
     * Handles a key typed.
     * @param e The key type event that triggered this method.
     */
    public void keyTyped(KeyEvent e) {
        if (state == 0){
            // dialogue
            Dialogue currentDialogue = dialogues[dialogueIndex];

            if (e.getKeyChar() == 'm'){
                // there is only moving in this dialogue
                dialogueIndex = currentDialogue.getLead().get(choiceIndex);

                if (dialogueIndex == -1){
                    state = 1;
                }
            }
        } else if (state == 1){
            int row = (mazeloc - 1) / 3, col = (mazeloc - 1) % 3;
            Question currentQuestion = maze[row][col];

            // maze room
            if (e.getKeyChar() == 'w'){
                james_y = Math.max(0, james_y - 5);
            } else if (e.getKeyChar() == 'a'){
                james_x = Math.max(0, james_x - 5);
            } else if (e.getKeyChar() == 's'){
                james_y = Math.min(450, james_y + 5);
            } else if (e.getKeyChar() == 'd'){
                james_x = Math.min(750, james_x + 5);
            } else if (e.getKeyChar() == 'm'){
                if (mazeloc == 9 && canExit[2][2]){
                    // last room is complete
                    boolean isDone = true;
                    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) if (!canExit[i][j]) isDone = false;

                    if (!(james_x + 50 < 363 || 447 < james_x) && !(james_y + 50 < 198 || 323 < james_y)){
                        if (isDone){
                            state = 2;
                        }
                    }
                } else if (current_answer != -1){
                    // an answer is selected
                    if (current_answer == currentQuestion.getCorrect()){
                        canExit[row][col] = true;
                    } else {
                        incorrect_cooldown = 50;
                    }
                }
            }

            updatePosition();
        } else if (state == 2){
            if (e.getKeyChar() == 'm'){
                state = 3;
                Main.game.level = 3;
            }
        }
        
        draw.repaint();
    }

    /**
     * The graphical component, drawn using a Grahpics object.
     */
    class Drawing extends JComponent {
        /** Draws on the screen, depends on the state variable. */
        public void paint(Graphics g) {
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

            if (state == 0){
                // dialogue
                Dialogue currentDialogue = dialogues[dialogueIndex];

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

                        if (currentWord.length() + currentString.length() <= 55){
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
                g.setFont(nameFont);
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

                    if (currentWord.length() + currentString.length() <= 55){
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
            } else if (state == 1){
                // draws the maze
                Font customFont = null, font25 = null, font15 = null;
                try {
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

                    customFont = Font.createFont(Font.TRUETYPE_FONT,new File("DeterminationMonoWebRegular-Z5oq.ttf")).deriveFont(27f);
                    font25 = Font.createFont(Font.TRUETYPE_FONT,new File("DeterminationMonoWebRegular-Z5oq.ttf")).deriveFont(25f);
                    font15 = Font.createFont(Font.TRUETYPE_FONT,new File("DeterminationMonoWebRegular-Z5oq.ttf")).deriveFont(15f);
                    ge.registerFont(customFont);
                    ge.registerFont(font25);
                    ge.registerFont(font15);
                } catch (Exception e) {
                }

                g.setColor(new Color(140,70,70));
                g.setFont(customFont);

                // draws the current room
                int row = (mazeloc - 1) / 3, col = (mazeloc - 1) % 3;

                BufferedImage room = null, james_icon = null, minimap = null;

                try {
                    if (canExit[row][col]){
                        room = ImageIO.read(new File("./assets/img/r" + mazeloc + ".png"));
                    } else {
                        room = ImageIO.read(new File("./assets/img/er.png"));
                    }

                    james_icon = ImageIO.read(new File("./assets/img/icon.png"));
                    minimap = ImageIO.read(new File("./assets/img/minimap.png"));
                } catch (Exception e){

                }

                g.drawImage(room, 0, 0, 800, 500, null);

                Question thisQuestion = maze[row][col];

                g.setFont(font25);
                g.setColor(Color.BLACK);
                g.drawString("Room #" + mazeloc, 350, 50);

                g.setColor(new Color(140, 70, 70));
                if (incorrect_cooldown > 0){
                    g.setFont(font25);
                    g.drawString("Incorrect!", 345, 145);
                    g.setFont(font15);
                    g.drawString("You must restart the maze.", 310, 170);

                    incorrect_cooldown--;
                    if (incorrect_cooldown == 0){
                        mazeloc = 1;
                        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) canExit[i][j] = false;
                        james_x = 375;
                        james_y = 225;
                    }
                } else if (canExit[row][col]){
                    if (mazeloc == 9){
                        g.setFont(font25);
                        g.drawString("Correct!", 350, 145);
                        g.setFont(font15);

                        boolean isDone = true;
                        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) if (!canExit[i][j]) isDone = false;

                        if (isDone){
                            g.drawString("Congratulations! You have completed Level 2.", 260, 170);
                            g.drawString("Please exit through the door.", 300, 380);
                        } else {
                            g.drawString("Complete all 9 rooms, then come back to this door.", 255, 170);
                        }
                    } else {
                        g.setFont(font25);
                        g.drawString("Correct!", 350, 145);
                        g.setFont(font15);
                        g.drawString("You may move onto the next room.", 295, 170);
                    }
                } else if (current_answer == -1){
                    // displays the question
                    g.setFont(font25);
                    g.drawString("Question:", 350, 145);

                    g.setFont(font15);

                    String firstLine = "", secondLine = "";
                    int ptr = 0;

                    String words[] = thisQuestion.getQuestion().split(" ");

                    while (ptr < words.length && firstLine.length() + words[ptr].length() <= MAX_CHAR){
                        firstLine += words[ptr] + " ";
                        ptr++;
                    }

                    while (ptr < words.length){
                        secondLine += words[ptr] + " ";
                        ptr++;
                    }

                    g.drawString(firstLine, 205, 170);
                    g.drawString(secondLine, 205, 180);
                } else {
                    // displays the currently selected answer
                    g.setFont(font25);
                    g.drawString("Answer " + "ABCD".charAt(current_answer - 1) + ")", 350, 145);

                    g.setFont(font15);
                    
                    String firstLine = "", secondLine = "";
                    int ptr = 0;

                    String words[] = thisQuestion.getAnswers().get(current_answer - 1).split(" ");

                    while (ptr < words.length && firstLine.length() + words[ptr].length() <= MAX_CHAR){
                        firstLine += words[ptr] + " ";
                        ptr++;
                    }

                    while (firstLine.length() + 2 <= MAX_CHAR){
                        firstLine = " " + firstLine + " ";
                    }

                    while (ptr < words.length){
                        secondLine += words[ptr] + " ";
                        ptr++;
                    }

                    while (secondLine.length() + 2 <= MAX_CHAR){
                        secondLine = " " + secondLine + " ";
                    }

                    g.drawString(firstLine, 205, 170);
                    g.drawString(secondLine, 205, 180);
                }

                // draws james
                g.drawImage(james_icon, james_x, james_y, 50, 50, null);                

                // draws the minimap
                g.drawImage(minimap, 600, 330, 100, 100, null);
                for(int e = 0; e < 3; e++){
                    for(int f = 0; f < 3; f++){
                        if (e == row && f == col) g.setColor(new Color(140, 70, 70));
                        else if(canExit[e][f]) g.setColor(new Color(140, 70, 70, 70));
                        else g.setColor(Color.WHITE);

                        g.fillRect(615+f*25,345+e*25, 20, 20);
                        g.setColor(new Color(140, 70, 70));
                        g.drawRect(615+f*25,345+e*25, 20, 20);
                    }
                }
            } else if (state == 2){
                g.setColor(Color.BLACK);
                g.drawRect(50, 50, 700, 400);
                g.setFont(textFont);
                g.drawString("Now, James understands his situation with Max better.", 60, 80);
                g.drawString("Help him confront Max in Level 3!", 60, 110);
            } else if (state == 3){
                g.setColor(Color.BLACK);
                g.setFont(textFont);

                if (275 <= mouse_x && mouse_x <= 505 && 195 <= mouse_y && mouse_y <= 210){
                    g.setColor(new Color(140, 70, 70));
                } else {
                    g.setColor(Color.BLACK);
                }

                g.drawString("Continue to Level 3", 275, 210);

                if (300 <= mouse_x && mouse_x <= 470 && 265 <= mouse_y && mouse_y <= 280){
                    g.setColor(new Color(140, 70, 70));
                } else {
                    g.setColor(Color.BLACK);
                }

                g.drawString("Return to Menu", 300, 280);
            }

            draw.repaint();
        }
    }
}