import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class LevelTwo extends Screen {
    Drawing draw;
    int a = 0, b = 0;
    int state = 0, mazeloc = -1;
    int choice = 0;
    int [][] maze = {{1,2,3},{4,5,6},{7,8,9}};
    int [][] mazeEx = new int[3][3];
    int [][] canExit = new int [3][3];
    int c = 0, d = 0;
    boolean predialogue, left, right, down, up;
    String answer;

    public LevelTwo() {
        draw = new Drawing();

        draw.addMouseMotionListener(this);
        draw.addMouseListener(this);
        draw.addKeyListener(this);
        draw.setFocusable(true);

        frame.add(draw);
        frame.setVisible(true);
    }

    public void keyTyped(KeyEvent e) {
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
                if(answer.equals("Manipulation and disrespect")) canExit[0][0] = 1;
            }
        }   
        draw.repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') up = true;
        if (e.getKeyChar() == 'a') left = true;
        if (e.getKeyChar() == 's') down = true;
        if (e.getKeyChar() == 'd') right = true;

        draw.repaint();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w') up = false;
        if (e.getKeyChar() == 'a') left = false;
        if (e.getKeyChar() == 's') down = false;
        if (e.getKeyChar() == 'd') right = false;

        draw.repaint();
    }

    class Drawing extends JComponent {
        public void paint(Graphics g) {
            Color explored = new Color(140,70,70, 70);
            Color now = new Color(140,70,70);

            c = 0;
            d = 0;

            if(mazeloc == -1){
                g.drawString("tutorial", 100, 100);
            }
            else if(mazeloc == 0){
                g.drawString("heading home, james gets lost in his thoughts", 100, 100);
                g.drawString("press <m> to continue", 100, 150);
            } else {
                g.drawRect(100,100,500,300);
                if (left) a -= 10;
                if (right) a += 10;
                if (up)  b -= 10;
                if (down) b += 10;
                g.drawRect(a, b, 10, 10);
                g.drawRect(500, 300, 80, 80);

                // MAP SQUARES
                for (int e = 0; e < 3; e++){
                    for (int f = 0; f < 3; f++){
                        if (mazeloc == maze[e][f]){
                            g.setColor(now);
                        } else if (mazeEx[e][f]!=0){
                            g.setColor(explored);
                        } else {
                            g.setColor(Color.WHITE);
                        }
                        g.fillRect(505+f*25,305+e*25, 20, 20);
                        g.setColor(now);
                        g.drawRect(505+f*25,305+e*25, 20, 20);
                    }
                }
            }
            
            if (mazeloc == 1){
                mazeEx[0][0] = 1;
                g.drawString("ROOM 1", 100, 100);
                g.drawString("What are common characteristics of toxic friendships?", 100, 150);
                answer = "generic answer";
                if(canExit[0][0] == 0){
                if(a > 200 && a < 250 && b > 200 && b < 250) answer = "Trust and mutual support";
                else if(a > 200 && a < 250 && b > 250 && b < 300) answer = "Open communication and compromise";
                else if(a > 250 && a < 300 && b > 200 && b < 250) answer = "Manipulation and disrespect";
                else if(a > 250 && a < 300 && b > 250 && b < 300) answer = "Understanding and empathy";
                else answer = "";
                }
                g.drawString(answer, 100, 180);
                g.drawRect(200,200,50,50);
                g.drawRect(200,250,50,50);
                g.drawRect(250,200,50,50);
                g.drawRect(250,250,50,50);
                if(canExit[0][0]!=0){
                    g.drawString("CAN LEAVE", 200, 400);
                if(a >= 500) mazeloc = 2;
                if(b >= 400) mazeloc = 4;}

            }

            if (mazeloc == 2){
                mazeEx[0][1] = 1;
                g.drawString("ROOM 2", 100, 100);
            }
        }
    }
}