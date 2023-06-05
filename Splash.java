import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class Splash implements MouseMotionListener, MouseListener, KeyListener {
    Drawing draw = new Drawing();
    int x, y, z = 0;
    int a = 0, b = 0;
    int state = 0, mazeloc = -1;
    int choice = 0;
    int [][] maze = {{1,2,3},{4,5,6},{7,8,9}};
    int [][] mazeEx = new int[3][3];
    int c = 0, d = 0;
    boolean predialogue, left, right, down, up;

    public Splash() {
        JFrame frame = new JFrame("Fatal Friendships");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 520);
        draw.addMouseMotionListener(this);
        draw.addMouseListener(this);
        draw.addKeyListener(this);
        draw.setFocusable(true);
        frame.add(draw);
        frame.setVisible(true);
    }

    public void mouseMoved(MouseEvent e) {
        if (state == 0) {
            x = e.getX();
            y = e.getY();
            if (x >= 300 && x <= 500 && y <= 415 && y >= 365)
                z = 1;
            else
                z = 0;

            draw.repaint();
        }
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        if (state == 0) {
            x = e.getX();
            y = e.getY();
            if (x >= 300 && x <= 500 && y <= 415 && y >= 365) {
                state = 1;
            }
            draw.repaint();
        } 
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
        } else if (state == 2) {
            if(mazeloc == -1){
                if(e.getKeyChar() == 'm'){
                    mazeloc = 0;
                }
            } else if(mazeloc == 0){
                if(e.getKeyChar() == 'm'){
                    predialogue = false;
                    mazeloc = 1;
                }
            }
            draw.repaint();

        }
    }

    public void keyPressed(KeyEvent e) {
        if(state == 2){
            if (e.getKeyChar() == 'w') up = true;
            if (e.getKeyChar() == 'a') left = true;
            if (e.getKeyChar() == 's') down = true;
            if (e.getKeyChar() == 'd') right = true;

            draw.repaint();
        }
    }

    public void keyReleased(KeyEvent e) {
        if(state == 2){
            if (e.getKeyChar() == 'w') up = false;
            if (e.getKeyChar() == 'a') left = false;
            if (e.getKeyChar() == 's') down = false;
            if (e.getKeyChar() == 'd') right = false;
            draw.repaint();
        }
    }

    class Drawing extends JComponent {

        public void splashScreen(Graphics g) {
            Font customFont = null;
            try {
                customFont = Font
                        .createFont(Font.TRUETYPE_FONT,
                                new File(
                                        "C:\\Users\\atlan\\Downloads\\ISP 2023\\DeterminationMonoWebRegular-Z5oq.ttf"))
                        .deriveFont(90f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
            } catch (Exception e) {
            }

            BufferedImage bg;
            try {
                bg = ImageIO.read(new File("splash.png"));
                g.drawImage(bg, 0, 0, 800, 500, null);
            } catch (Exception e) {
            }
            g.setColor(new Color(140, 70, 70));
            g.setFont(customFont);
            g.drawString("friendships", 150, 170);
            g.setFont(customFont.deriveFont(50f));
            if (z != 0)
                g.setColor(Color.BLACK);
            else
                g.setColor(new Color(140, 70, 70));
            g.drawString("new game", 300, 405);
        }

        public void menu(Graphics g) {
            Font customFont = null;
            try {
                customFont = Font
                        .createFont(Font.TRUETYPE_FONT,
                                new File(
                                        "C:\\Users\\atlan\\Downloads\\ISP 2023\\DeterminationMonoWebRegular-Z5oq.ttf"))
                        .deriveFont(90f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
            } catch (Exception e) {
            }

            BufferedImage bg;
            try {
                bg = ImageIO.read(new File("menu.png"));
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

        public void levelTwo (Graphics g) {
            Color explored = new Color(140,70,70, 70);
            Color now = new Color(140,70,70);
            c = 0;
            d= 0;
            if(mazeloc == -1){
                g.drawString("tutorial", 100, 100);
            }
            else if(mazeloc == 0){
                g.drawString("heading home, james gets lost in his thoughts", 100, 100);
                g.drawString("press <m> to continue", 100, 150);
            } else {
                g.drawRect(100,100,500,300);
                if(a >= 50) mazeloc = 2;
                if(left) a--;
                if(right) a++;
                if(up)  b--;
                if(down) b++;
                g.drawRect(a, b, 10, 10);
                g.drawRect(500, 300, 80, 80);

                //MAP SQUARES
                for(int e = 0; e < 3; e++){
                    for(int f = 0; f < 3; f++){
                        if(mazeloc == maze[e][f]) g.setColor(now);
                        else if(mazeEx[e][f]!=0) g.setColor(explored);
                        else g.setColor(Color.WHITE);
                        g.fillRect(505+f*25,305+e*25, 20, 20);
                        g.setColor(now);
                        g.drawRect(505+f*25,305+e*25, 20, 20);
                    }
                }
            }
            
            if(mazeloc == 1){
                g.drawString("ROOM 1", 100, 100);
                g.drawString("What are common characteristics of toxic friendships?", 100, 150);
                g.drawRect(200,200,50,50);
                g.drawRect(200,250,50,50);
                g.drawRect(250,200,50,50);
                g.drawRect(250,250,50,50);
            }

            if(mazeloc == 2){
                g.drawString("ROOM 2", 100, 100);
            }
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
            // THIS WILL IMPLEMENT DIFF METHODS BASED ON GAMESTATE
            if (state == 0) {
                splashScreen(g);
            } else if (state == 1) {
                menu(g);
            } else if (state == 2) {
                levelTwo(g);
            } else if (state == 3) {
                credits(g);
            }else if (state == 4) {
                exit(g);
            }

        
        }
    }

    public static void main(String[] args) {
        new Splash();
    }

}