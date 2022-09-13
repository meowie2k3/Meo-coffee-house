package Main;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel 
    implements Runnable, KeyListener {
    //dimension
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 2;

    //game threading
    private Thread thread;
    private boolean IsRun;
    private int FPS = 60; // adjust fps
    private long targetTime = 1000 / FPS;

    //assets
    private BufferedImage image;
    private Graphics2D g;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void init(){

        image = new BufferedImage(
        WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        g = (Graphics2D) g;
        IsRun = true;
    }

    public void run(){
        init();
        long start;
        long elapsed;
        long wait;
        //looping
        while(IsRun){

            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;

            try{
                Thread.sleep(wait);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }

    private void update(){

    }

    private void draw(){

    }

    private void drawToScreen(){
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0,null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent key) {

    }

    public void keyPressed(KeyEvent key) {

    }
    public void keyReleased(KeyEvent key) {

    }
}  
