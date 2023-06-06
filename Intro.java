import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class Intro extends Screen {
    Drawing draw;
    int x, y, z = 0;

    // 0 is splash screen, 1 is menu, 2 is levels, 3 is credits, 4 is exit
    int state = 0;
    int choice = 0;

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

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {
        if (state == 0) {
            // menu
            if (300 <= x && x <= 500 && 365 <= y && y <= 415){
                state = 1;
            }
        } 

        draw.repaint();
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
        if(state == 1){
            if(choice < 2 && e.getKeyChar() == 's') choice ++;
            else if(choice > 0 && e.getKeyChar() == 'w') choice --;
            else if(e.getKeyChar() == 'm') state += choice + 1;
            draw.repaint();
        }
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    class Drawing extends JComponent {
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

            if (300 <= x && x <= 500 && 365 <= y && y <= 415){
                g.setColor(Color.BLACK);
            } else {
                g.setColor(new Color(140, 70, 70));
            }

            g.drawString("new game", 300, 405);
        }

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

            int y = 300 + choice*50;
            g.drawString("*", 320, y);
        }

        public void levels(Graphics g) {
            Main.game.setState(1);
        }

        public void credits(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawString("real credits", 150, 170);
        }

        public void exit(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawString("bye", 150, 170);
        }

        public void paint(Graphics g) {
            if (state == 0) {
                splashScreen(g);
            } else if (state == 1) {
                menu(g);
            } else if (state == 2) {
                levels(g);
            } else if (state == 3) {
                credits(g);
            } else if (state == 4) {
                exit(g);
            }
        }
    }

    /* 
    public static void main(String[] args){
        new Splash();
    }
    */
}