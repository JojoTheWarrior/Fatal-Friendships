import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

/**
 * Displays the first screen - including the splash screen, menu, credits, and the start game button.
 */
public class Intro extends Screen {
    /** Graphics component */
    private Drawing draw;
    /** Mouse coordinates */
    private int x, y;

    /** Represents the current state of the game. 0 is splash screen, 1 is menu, 2 is play, 3 is credits, 4 is exit. */
    private int state = 0;
    /** Current choice in menu. */
    private int choice = 0;

    /**
     * Displays the first screen - adds the Drawing component to the JFrame.
     */
    public Intro() {
        super();

        draw = new Drawing();
        draw.addMouseMotionListener(this);
        draw.addMouseListener(this);
        draw.addKeyListener(this);
        draw.setFocusable(true);

        frame.add(draw);
        frame.setVisible(true);
    }

    /**
     * Updates mouse coordinates.
     */
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        draw.repaint();
    }

    /**
     * Registers mouse clicks.
     */
    public void mouseClicked(MouseEvent e) {
        if (state == 0) {
            // menu
            if (300 <= x && x <= 500 && 365 <= y && y <= 415) {
                state = 1;
            }
        }

        draw.repaint();
    }

    /**
     * Handles keys pressed. Generally, WASD is to move and M is for progression / selection.
     */
    public void keyTyped(KeyEvent e) {
        if (state == 1) {
            if (choice < 2 && e.getKeyChar() == 's')
                choice++;
            else if (choice > 0 && e.getKeyChar() == 'w')
                choice--;
            else if (e.getKeyChar() == 'm')
                state += choice + 1;
            draw.repaint();
        } else if (state == 3){
            if (e.getKeyChar() == 'm'){
                state = 0;
                draw.repaint();
            }
        } else if (state == 4){
            if (e.getKeyChar() == 'm'){
                Main.game.frame.dispatchEvent(new WindowEvent(Main.game.frame, WindowEvent.WINDOW_CLOSING));
            }
        }
    }

    /**
     * Graphics drawing component.
     */
    class Drawing extends JComponent {
        /**
         * Displays the splash screen.
         * @param g The Graphics component drawn onto.
         */
        public void splashScreen(Graphics g) {
            Font customFont = null;
            try {
                customFont = Font
                        .createFont(Font.TRUETYPE_FONT,
                                new File(
                                        "./assets/font/DeterminationMonoWebRegular-Z5oq.ttf"))
                        .deriveFont(90f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
            } catch (Exception e) {

            }

            BufferedImage bg;
            try {
                bg = ImageIO.read(new File("./assets/img/splash.png"));
                g.drawImage(bg, 0, 0, 800, 500, null);
            } catch (Exception e) {

            }

            g.setColor(new Color(140, 70, 70));
            g.setFont(customFont);
            g.drawString("friendships", 150, 170);
            g.setFont(customFont.deriveFont(50f));

            if (300 <= x && x <= 500 && 365 <= y && y <= 415) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(new Color(140, 70, 70));
            }

            // either says "new game" or "continue game"
            if (Main.game.level == 1){
                g.drawString("new game", 300, 405);
            } else {
                g.drawString("continue", 295, 405);
            }
        }

        /**
         * Displays the menu.
         * @param g The Graphics component drawn onto.
         */
        public void menu(Graphics g) {
            Font customFont = null;
            try {
                customFont = Font
                        .createFont(Font.TRUETYPE_FONT,
                                new File(
                                        "./assets/font/DeterminationMonoWebRegular-Z5oq.ttf"))
                        .deriveFont(90f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
            } catch (Exception e) {
            }

            BufferedImage bg;
            try {
                bg = ImageIO.read(new File("./assets/img/menu.png"));
                g.drawImage(bg, 0, 0, 800, 500, null);
            } catch (Exception e) {
            }
            g.setColor(new Color(140, 70, 70));
            g.setFont(customFont.deriveFont(40f));
            g.drawString("play", 360, 300);
            g.drawString("credits", 360, 350);
            g.drawString("exit", 360, 400);

            int y = 300 + choice * 50;
            g.drawString("*", 320, y);
        }

        /**
         * Starts the game at the appropriate level.
         * @param g The Graphics component drawn onto.
         */
        public void play(Graphics g){
            Main.game.setState(Main.game.level);
        }

        /**
         * Displays the credits.
         * @param g The Graphics component drawn onto.
         */
        public void credits(Graphics g) {
            Font customFont = null;
            try {
                customFont = Font
                        .createFont(Font.TRUETYPE_FONT,
                                new File(
                                        "DeterminationMonoWebRegular-Z5oq.ttf"))
                        .deriveFont(25f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
            } catch (Exception e) {
            }
            g.setColor(new Color(140,70,70));
            g.setFont(customFont);
            g.drawString("CREDITS", 50, 100);
            g.drawString("This game was made by Alyn Huang and Joshua Wang as our ", 50, 150);
            g.drawString("2023 ICS4U ISP with Krasteva, V.", 50, 200);
            g.drawString("Press M to go back to the menu", 50, 250);
        }

        /**
         * Final screeen before exiting the game.
         * @param g The Graphics component drawn onto.
         */
        public void exit(Graphics g) {
            Font customFont = null;
            try {
                customFont = Font
                        .createFont(Font.TRUETYPE_FONT,
                                new File(
                                        "DeterminationMonoWebRegular-Z5oq.ttf"))
                        .deriveFont(25f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
            } catch (Exception e) {
            }
            g.setColor(new Color(140,70,70));
            g.setFont(customFont);
            g.drawString("Thanks for playing the game!", 50, 100);
            g.drawString("Press M to exit", 50, 150);
        }

        /**
         * Draws the appropriate screen, depends on state.
         * @param g The Graphics component drawn onto.
         */
        public void paint(Graphics g) {
            if (state == 0) {
                splashScreen(g);
            } else if (state == 1) {
                menu(g);
            } else if (state == 2) {
                play(g);
            } else if (state == 3) {
                credits(g);
            } else if (state == 4) {
                exit(g);
            }
        }
    }
}