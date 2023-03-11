package GameState;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import javax.imageio.ImageIO;

import Map.Background;
import Entity.Ball;

public class LineGameState extends GameState {

    private Background bg;
    private int score;
    private int level = 5;
    private int boardSize = 2 * level - 1;

    private int[][] board;

    private String[] address =new String[]{
        "/Balls/1.png"
    };
    
    public LineGameState(GameStateManager gsm) {
        this.gsm = gsm;
        bg = new Background("/UI/LineBoard.png", 0);
        score = 0;
        board = new int[boardSize+1][boardSize+1];
    }

    public void init() {
        
    }
    public void update(){
        bg.update();
    }
    public void draw(Graphics2D g){
        bg.draw(g);
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
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseDragged(MouseEvent e) {
    }
    public void mouseMoved(MouseEvent e) {
    }
}
