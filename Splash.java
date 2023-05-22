import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class Splash implements MouseMotionListener{
    Drawing draw = new Drawing();
    int x, y, z = 0;

    public Splash() {
        JFrame frame = new JFrame("Fatal Friendships");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 520);
        draw.addMouseMotionListener(this);
        frame.add(draw);
        frame.setVisible(true);
    }

    public void mouseMoved(MouseEvent e){
        x = e.getX();
        y = e.getY();
        if(x >= 300 && x <= 500 && y <= 415 && y >= 365) z = 1;
        else z = 0;
        
        draw.repaint();
    }

    public void mouseDragged(MouseEvent e){
    }

    class Drawing extends JComponent {

        public void splashScreen(Graphics g){
            Font customFont = null;
            try {
                customFont = Font.createFont(Font.TRUETYPE_FONT, new File("DeterminationMonoWebRegular-Z5oq.ttf")).deriveFont(90f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
            } catch (Exception e) {
            } 

            BufferedImage bg;
            try{
                bg = ImageIO.read(new File("splash.png"));
                g.drawImage(bg, 0, 0, 800, 500, null);
            } catch (Exception e){
            }
            g.setColor(new Color(140,70,70));
            g.setFont(customFont);
            g.drawString("friendships", 150, 170);
            g.setFont(customFont.deriveFont(50f));
            if(z != 0) g.setColor(Color.BLACK);
            else g.setColor(new Color(140,70,70));
            g.drawString("new game", 300, 405);
        }

        public void paint(Graphics g) {
            // THIS WILL IMPLEMENT DIFF METHODS BASED ON GAMESTATE
            splashScreen(g);
        
    }
}
    public static void main(String[] args) {
        new Splash();
    }

}