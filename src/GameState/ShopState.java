package GameState;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

import Map.Background;

import java.awt.event.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

import Main.*;
import Map.*;
import Entity.*;
import Entity.character;

public class ShopState extends GameState{
    private BufferedImage c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16;

    private BufferedImage s2;

    private Background bg;

    private int choice = 0;
    // private GameStateManager gsm;

    public ShopState(GameStateManager gsm)    {
        this.gsm = gsm;

        try {
            bg = new Background("Resources/Backgrounds/shop.png", 0);
            
            c1 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c1.png"));
            c2 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c2.png"));
            c3 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c3.png"));
            c4 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c4.png"));
            c5 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c5.png"));
            c6 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c6.png"));
            c7 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c7.png"));
            c8 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c8.png"));
            c9 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c9.png"));
            c10 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c10.png"));
            c11 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c11.png"));
            c12 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c12.png"));
            c13 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c13.png"));
            c14 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c14.png"));
            c15 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c15.png"));
            c16 = ImageIO.read(getClass().getResourceAsStream("Resources/Backgrounds/Shop/c16.png"));

            s2 = ImageIO.read(getClass().getResourceAsStream("/UI/s2.png"));
      
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {}
    public void update()   {}
    public void saveData(){}

    public void draw(Graphics2D g)    {
        g.setColor(new Color(204, 164, 115));
        g.drawRect(5, 20, 310, 190);
        g.fillRect(5, 20, 310, 190);

        g.setColor(new Color(236, 216, 147));
        g.drawRect(10, 35, 60, 160);
        g.fillRect(10, 35, 60, 160);
        g.drawRect(80, 35, 230, 160);
        g.fillRect(80, 35, 230, 160);

        g.setColor(new Color(181, 114, 85));
        g.drawRect(15, 45, 50, 15);
        g.fillRect(15, 45, 50, 15);

        g.setColor(Color.BLACK);
        g.drawString("Cats", 30, 55);

        g.drawImage(s2, 3, 209, null);

        if (choice == 1)    {
            //first row
            g.drawImage(c1, 85, 45, null);
            g.drawImage(c2, 145, 45, null);
            g.drawImage(c3, 205, 45, null);
            g.drawImage(c4, 265, 45, null);

            //second row
            g.drawImage(c5, 85, 85, null);
            g.drawImage(c6, 145, 85, null);
            g.drawImage(c7, 205, 85, null);
            g.drawImage(c8, 265, 85, null);

            //third row
            g.drawImage(c9, 85, 125, null);
            g.drawImage(c10, 145, 125, null);
            g.drawImage(c11, 205, 125, null);
            g.drawImage(c12, 265, 125, null);

            //last row
            g.drawImage(c13, 85, 165, null);
            g.drawImage(c14, 145, 165, null);
            g.drawImage(c15, 205, 165, null);
            g.drawImage(c16, 265, 165, null);
            // choice = 0;
        }

    }

    public void keyPressed(int k)  {}
    public void keyReleased(int k) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e)    {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("mouse moved " + x + " " + y);
        int scale = GamePanel.SCALE;
        int left = 3*scale;
        int right = 33*scale;
        int top = 209*scale;
        int bottom = 239*scale;

        if (x > left && x < right && y > top && y < bottom ) {            
                gsm.setState(GameStateManager.INGAMESTATE);
        }

        if (x > 15*3 && x < 65*3 && y > 45*3 && y < 60*3)   {
            choice = 1;
        }
    }
    public void mouseExited(MouseEvent e)  {}
    public void mouseMoved(MouseEvent e)   {}
    public void mouseDragged(MouseEvent e)  {}
}
