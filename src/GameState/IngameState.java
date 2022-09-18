package GameState;
import java.awt.event.*;
import Main.*;

import Map.*;

public class IngameState extends GameState{

    private Map map;
    
    //constructor
    public IngameState(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }
    //file abstract method
    public void init() {
        map = new Map(30); //set map block size to 30
        map.loadBlocks("/Blocks/BlockCollection.gif");
        map.loadMap("/Maps/level1-1.map");
        map.setPosition(0, 0);
    }
    //update
    public void update() {
        
    }
    public void draw(java.awt.Graphics2D g) {
        //draw map
        //clear screen
        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        //draw map
        map.draw(g);
    }
    //key event listener
    public void keyPressed(int k) {
        
    }
    public void keyReleased(int k) {
        
    }
    //mouse event listener
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
    //mouse motion listener
    public void mouseMoved(MouseEvent e) {
        
    }
    public void mouseDragged(MouseEvent e) {
        
    }
}
