package GameState;
import java.awt.event.*;
import java.util.ArrayList;

import Main.*;
import Map.*;
import Entity.*;

public class IngameState extends GameState{

    private Map map;
    private Background bg;
    //cat
    private Cat cat;
    //private ArrayList<String> catAddress;
    

    //constructor
    public IngameState(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }
    
    //file abstract method
    public void init() {
        map = new Map(); //set map block size to 30
        map.hashImage("/Character and Furniture/House asset blackcat.png");
        map.loadUserSavedGame("/SavedGame/User.map");
        map.setPosition(0, 0);
        bg = new Background("/Backgrounds/grassbg1.gif", 0);

        
        cat = new Cat(map, "/Cats/brown_000.png");

        
        

    }
    // public void init(){
    //     map = new Map();
    // }

    //update
    public void update() {
        //update map
        //map.update();

        //update cat
        cat.update(cat.getCurrentAction());
        
    }
    public void draw(java.awt.Graphics2D g) {
        //draw map
        //clear screen
        //g.setColor(java.awt.Color.WHITE);
        //g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        //draw background
        bg.draw(g);
        //draw map
        map.draw(g);
        //draw cat
        cat.draw(g);
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
