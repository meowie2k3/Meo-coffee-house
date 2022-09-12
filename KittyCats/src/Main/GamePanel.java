package Main;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    //dimension
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 2;

    //game thread
    private Thread thread;
    private boolean ISrunning;
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
}  
