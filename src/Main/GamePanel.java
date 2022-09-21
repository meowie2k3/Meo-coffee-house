package Main;

import javax.swing.*;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;

import GameState.GameStateManager;

@SuppressWarnings("serial") //shut up vs code :>

public class GamePanel extends JPanel 
    implements Runnable, KeyListener,MouseListener, MouseMotionListener{

    //dimension
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 3;

    //game threading
    private Thread thread;
    private boolean IsRun;
    private int FPS = 60; // adjust fps limit
    private long targetTime = 1000 / FPS;

    //assets
    private BufferedImage image;
    private Graphics2D g;

    //state manager
    private GameStateManager gsm;

        //constructor
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
            //important: add event listener
            addKeyListener(this);
            addMouseListener(this);
            addMouseMotionListener(this);
            thread.start();
        }
    }
    //create game window
    private void init(){

        image = new BufferedImage(
        WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        g = (Graphics2D) image.getGraphics();
        IsRun = true;

        gsm = new GameStateManager();
    }
    //game loop
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
        gsm.update();
    }
    //draw to buffer
    private void draw(){
        gsm.draw(g);
    }

    private void drawToScreen(){
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
        g2.dispose();
    }

    
    //key listener methods
    public void keyTyped(KeyEvent key) {

    }
    public void keyPressed(KeyEvent key) {
        gsm.keyPressed(key.getKeyCode());
    }
    public void keyReleased(KeyEvent key) {
        gsm.keyReleased(key.getKeyCode());
    }
    //mouse listener methods
    public void mousePressed(MouseEvent e) {
        gsm.mousePressed(e);
    }
    public void mouseReleased(MouseEvent e) {
        gsm.mouseReleased(e);
    }
    public void mouseEntered(MouseEvent e) {
        gsm.mouseEntered(e);
    }
    public void mouseClicked(MouseEvent e) {
        gsm.mouseClicked(e);
    }
    public void mouseExited(MouseEvent e) {
        gsm.mouseExited(e);
    }
    //mouse motion listener methods
    public void mouseMoved(MouseEvent e) {
        gsm.mouseMoved(e);
    }
    public void mouseDragged(MouseEvent e) {
        gsm.mouseDragged(e);
    }
}  
