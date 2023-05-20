package GameState;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import javax.imageio.ImageIO;

import Map.*;
import Entity.*;
import Main.GamePanel;

public class LineGameState extends GameState {

    private Background bg;
    private int score;
    private int level = 5;
    private int boardSize = 2 * level - 1;

    public static final int BALL_SIZE = 20;
    
    private SoundEffect soundEffect;
    private LinePlaying linePlaying;

    public BufferedImage lineIU;

    private String[] address =new String[]{
        "/Balls/1.png"
    };
    
    public LineGameState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            lineIU = ImageIO.read(getClass().getResourceAsStream("/UI/LineUI.png"));  

        } catch (Exception e) {
            e.printStackTrace();
        }
        init();
        score = 0;
    }

    public void saveData(){}

    public void init() {
        bg = new Background("/Backgrounds/bg_Line.png", 0);

        soundEffect = new SoundEffect(GamePanel.WIDTH - 23,
        GamePanel.HEIGHT - 23,
            "Resources/soundEffect/meow.wav", false);

        linePlaying = new LinePlaying(3, 207, false);
    }
    public void update(){
        bg.update();
    }
    public void draw(Graphics2D g){
        bg.draw(g);
        soundEffect.draw(g);
        linePlaying.draw(g);
        g.drawImage(lineIU, 66, 12, null);  
    }
    public void keyPressed(int k){
    }
    public void keyReleased(int k){
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {    
        if(soundEffect.contains(e.getX(), e.getY())){
            soundEffect.toggle();
        }    

        if (linePlaying.contains(e.getX(), e.getY()))   {
            gsm.setState(GameStateManager.INGAMESTATE);
        }
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseDragged(MouseEvent e) {
    }
    public void mouseMoved(MouseEvent e) {
    }
}