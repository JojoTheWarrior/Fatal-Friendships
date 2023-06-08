import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class Splash implements MouseMotionListener, MouseListener, KeyListener {
    Drawing draw = new Drawing();
    int x, y, z = 0;
    int a = 141, b = 312;
    int state = 0, mazeloc = -1;
    int choice = 0;
    int [][] maze = {{1,2,3},{4,5,6},{7,8,9}};
    int [][] mazeEx = new int[3][3];
    int [][] canExit = new int [3][3];
    int c = 0, d = 0;
    boolean predialogue, left, right, down, up, exit = false;
    String answer;

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

        System.out.println(e.getX()+" "+e.getY());
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
            } else if(mazeloc == 1){
                if(e.getKeyChar() == 'm'){
                    if(answer.equals("* Manipulation and disrespect")) canExit[0][0] = 1;
                }
            }   
            draw.repaint();

        }
    }

    public void keyPressed(KeyEvent e) {
        if(state == 2){

            if(!exit){
                if(b > 93 && e.getKeyChar() == 'w') up = true;
                else up = false;
                if (a > 121 && e.getKeyChar() == 'a') left = true;
                else left = false;
                if (b < 320 && e.getKeyChar() == 's') down = true;
                else down = false;
                if (a < 590 && e.getKeyChar() == 'd') right = true;
                else right = false;
            } else {
                if (e.getKeyChar() == 'w') up = true;
                if (e.getKeyChar() == 'a') left = true;
                if (e.getKeyChar() == 's') down = true;
                if (e.getKeyChar() == 'd') right = true;
            }

            draw.repaint();
        }
    }

    public void keyReleased(KeyEvent e) {
        if(state == 2){
            if(!exit){
                if (e.getKeyChar() == 'w') up = false;
                if (e.getKeyChar() == 'a') left = false;
                if (e.getKeyChar() == 's') down = false;
                if (e.getKeyChar() == 'd') right = false;
            } else {
                if (e.getKeyChar() == 'w') up = false;
                if (e.getKeyChar() == 'a') left = false;
                if (e.getKeyChar() == 's') down = false;
                if (e.getKeyChar() == 'd') right = false;
            }
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
                                        "DeterminationMonoWebRegular-Z5oq.ttf"))
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
                                        "DeterminationMonoWebRegular-Z5oq.ttf"))
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
                mazeRoom(g);
                if(left) a-=10;
                if(right) a+=10;
                if(up)  b-=10;
                if(down) b+=10;
                BufferedImage icon;
                try {
                    icon = ImageIO.read(new File("icon.png"));
                    g.drawImage(icon, a, b, 75, 75, null);
                } catch (Exception e) {
                }
                
                BufferedImage map;
                try {
                    map = ImageIO.read(new File("minimap.png"));
                    g.drawImage(map, 600, 330, 100, 100, null);
                } catch (Exception e) {
                }

                //MAP SQUARES
                for(int e = 0; e < 3; e++){
                    for(int f = 0; f < 3; f++){
                        if(mazeloc == maze[e][f]) g.setColor(now);
                        else if(mazeEx[e][f]!=0) g.setColor(explored);
                        else g.setColor(Color.WHITE);
                        g.fillRect(615+f*25,345+e*25, 20, 20);
                        g.setColor(now);
                        g.drawRect(615+f*25,345+e*25, 20, 20);
                    }
                }
            }
            
        }

        public void mazeRoom(Graphics g){
            Font customFont = null;
            try {
                customFont = Font
                        .createFont(Font.TRUETYPE_FONT,
                                new File(
                                        "DeterminationMonoWebRegular-Z5oq.ttf"))
                        .deriveFont(27f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
            } catch (Exception e) {
            }
            g.setColor(new Color(140,70,70));
            g.setFont(customFont);
            if(mazeloc == 1){
                BufferedImage r1;
                try {
                    if(exit) r1 = ImageIO.read(new File("r1.png"));
                    else r1 = ImageIO.read(new File("er.png"));
                    g.drawImage(r1, 0, 0, 800, 500, null);
                } catch (Exception e) {
                }
                mazeEx[0][0] = 1;
                if(!exit){
                    g.drawString("What are common", 200, 157);
                    g.drawString("characteristics of toxic", 200, 184);
                    g.drawString("friendships?", 200, 211);
                } else {
                    g.drawString("good job! manipulation and", 200, 157);
                    g.drawString("disrespect in a relationship", 200, 184);
                    g.drawString("are signs that it may be toxic", 200, 211);
                }
                answer = "";
                if(canExit[0][0] == 0){
                    g.setColor(Color.WHITE);
                if(a > 280 && a < 330 && b > 210 && b < 260){
                    g.fillRect(200,130,350,85);
                    answer = "* Trust and mutual support";
                } else if(a > 280 && a < 330 && b > 300 && b < 330) {
                    g.fillRect(200,130,350,85);
                    answer = "* Open communication";
                } else if(a > 410 && a < 460 && b > 210 && b < 260) {
                    g.fillRect(200,130,350,85);
                    answer = "* Manipulation and disrespect";
                } else if(a > 410 && a < 460 && b > 300 && b < 330) {
                    g.fillRect(200,130,350,85);
                    answer = "* Understanding and empathy";
                } else answer = "";
                }
                g.setColor(new Color(140,70,70));
                g.drawString(answer, 200, 157);
                if(canExit[0][0]!=0){
                    changeRoom();
                } else {
                    exit = false;
                }

            }

            if(mazeloc == 2){
                draw.repaint();
                BufferedImage r2;
                try {
                    if(exit) r2 = ImageIO.read(new File("r2.png"));
                    else r2 = ImageIO.read(new File("er.png"));
                    g.drawImage(r2, 0, 0, 800, 500, null);
                } catch (Exception e) {
                }
                mazeEx[0][1] = 1;
                
                if(!exit){
                    g.drawString("How do healthy friends respect", 200, 157);
                    g.drawString("each others' boundaries?", 200, 184);
                } else {
                    g.drawString("good job! manipulation and", 200, 157);
                    g.drawString("disrespect in a relationship", 200, 184);
                    g.drawString("are signs that it may be toxic", 200, 211);
                }

                answer = "";
                if(canExit[0][1] == 0){
                    g.setColor(Color.WHITE);
                if(a > 280 && a < 330 && b > 210 && b < 260){
                    g.fillRect(200,130,370,85);
                    answer = "* push boundaries to encourage personal growth";
                } else if(a > 280 && a < 330 && b > 300 && b < 330) {
                    g.fillRect(200,130,370,85);
                    answer = "* ignore boundaries for the sake of spontaneity";
                } else if(a > 410 && a < 460 && b > 210 && b < 260) {
                    g.fillRect(200,130,370,85);
                    answer = "* communicate openly and honour boundaries";
                } else if(a > 410 && a < 460 && b > 300 && b < 330) {
                    g.fillRect(200,130,370,85);
                    answer = "* manipulate boundaries to maintain control";
                } else answer = "";
                }
                g.setColor(new Color(140,70,70));
                g.drawString(answer, 200, 157);

                if(canExit[0][1]!=0){
                    g.drawString("CAN LEAVE", 200, 400);
                    changeRoom();
                } else {
                    exit = false;
                }
            }

            if(mazeloc == 3){
                mazeEx[0][2] = 1;
                g.drawString("ROOM 3", 100, 100);
                if(canExit[0][2]!=0){
                    g.drawString("CAN LEAVE", 200, 400);
                    changeRoom();
                }
            }

            if(mazeloc == 4){
                BufferedImage r4;
                try {
                    if(exit) r4 = ImageIO.read(new File("r4.png"));
                    else r4 = ImageIO.read(new File("er.png"));
                    g.drawImage(r4, 0, 0, 800, 500, null);
                } catch (Exception e) {
                }
                mazeEx[1][0] = 1;
                if(canExit[1][0]!=0){
                    changeRoom();
                } else {
                    exit = false;
                }
            }

            if(mazeloc == 5){
                a = 0;
                b = 0;
                mazeEx[1][1] = 1;
                g.drawString("ROOM 5", 100, 100);
                if(canExit[1][1]!=0){
                    g.drawString("CAN LEAVE", 200, 400);
                    changeRoom();
                }
            }

            if(mazeloc == 6){
                a = 0;
                b = 0;
                mazeEx[1][2] = 1;
                g.drawString("ROOM 6", 100, 100);
                if(canExit[1][2]!=0){
                    g.drawString("CAN LEAVE", 200, 400);
                    changeRoom();
                }
            }

            if(mazeloc == 7){
                a = 0;
                b = 0;
                mazeEx[2][0] = 1;
                g.drawString("ROOM 7", 100, 100);
                if(canExit[2][0]!=0){
                    g.drawString("CAN LEAVE", 200, 400);
                    changeRoom();
                }
            }

            if(mazeloc == 8){
                a = 0;
                b = 0;
                mazeEx[2][1] = 1;
                g.drawString("ROOM 8", 100, 100);
                if(canExit[2][1]!=0){
                    g.drawString("CAN LEAVE", 200, 400);
                    changeRoom();
                }
            }

            if(mazeloc == 9){
                a = 0;
                b = 0;
                mazeEx[2][2] = 1;
                g.drawString("ROOM 9", 100, 100);
                if(canExit[2][2]!=0){
                    g.drawString("CAN LEAVE", 200, 400);
                    changeRoom();
                }
            }
        }

        public void changeRoom(){
            exit = true;
            if(a >= 800){
                d++;
                mazeloc = maze[c][d];
                a = 141;
                b = 312;
            }
            if(b >= 400){
                c++;
                mazeloc = maze[c][d];
                a = 141;
                b = 312;
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