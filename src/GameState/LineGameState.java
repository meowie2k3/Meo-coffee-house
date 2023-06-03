package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import javax.imageio.ImageIO;

import Map.*;
import Entity.*;
import Main.GamePanel;

public class LineGameState extends GameState {

    private Background bg;
    public static int score;
    
    private SoundEffect soundEffect;
    private Line98 linePlaying;

    public BufferedImage lineUI;

    private Board board = new Board();

    
    public LineGameState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            lineUI = ImageIO.read(getClass().getResourceAsStream("/UI/LineUI.png"));  

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
        GamePanel.HEIGHT - 20,
            "Resources/soundEffect/meow.wav", false);

        linePlaying = new Line98(3, 3, false);
    }
    public void update(){
        bg.update();
        board.update();
        //System.out.println(score);
    }
    public void draw(Graphics2D g){
        bg.draw(g);
        soundEffect.draw(g);
        linePlaying.draw(g);
        board.draw(g);
        g.drawImage(lineUI, 66, 12, null);  

        //draw score
        // use #F56C45
        g.setColor(new Color(245, 108, 69));
        g.setFont(g.getFont().deriveFont(10f));
        g.drawString("" + score, 43*GamePanel.SCALE, 9*GamePanel.SCALE);
    }
    public void keyPressed(int k){
    }
    public void keyReleased(int k){
    }
    public void mousePressed(MouseEvent e) {
        //System.out.println(e.getX() + " " + e.getY());
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

        if (board.contains(e.getX(), e.getY())) {
            //System.out.println(board.mousePositionX(e.getX(), e.getY()) + " " + board.mousePositionY(e.getX(), e.getY()));
            board.click(board.mousePositionX(e.getX(), e.getY()),board.mousePositionY(e.getX(), e.getY()));
        }
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseDragged(MouseEvent e) {
    }
    public void mouseMoved(MouseEvent e) {
    }
}