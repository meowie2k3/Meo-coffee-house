package GameState;
import java.awt.event.*;
import java.util.ArrayList;

import Main.*;
import Map.*;
import Entity.*;

public class IngameState extends GameState{

    private Map map;
    private Background bg;

    //cat properties
    private Cat cat;
    private static final int SLEEP = 1;
    private static final int SIT = 2;
    private static final int WALK = 4;
    private static final int STAND = 12;
    private static final int SCRATCH = 20;
    //private ArrayList<String> catAddress;
    

    //constructor
    public IngameState(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }
    
    //file abstract method
    public void init() {
        map = new Map(); //set map block size to 30
        map.loadUserSavedGame("/UserSavedGame/User1.txt");
        bg = new Background("/Backgrounds/TestbgIngameState.png", 0);

        
        cat = new Cat(map, "/Cats/creme_000.png");
        cat.setPosition(100, 150);

        
        

    }
    // public void init(){
    //     map = new Map();
    // }

    //update
    public void update() {
        //update map
        //map.update();

        //update cat
        cat.update(); // SIT, SLEEP, STAND, WALK, SCRATCH
        
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
        //get coordinate of mouse click
        int x = e.getX();
        int y = e.getY();
        //System.out.println("Mouse Clicked at: " + x + ", " + y);
        //if user click to a cat, cat will scratch then move randomly around the map
        if(cat.contains(x, y)){
            cat.setAction(SCRATCH);
        }

    }
    public void mouseExited(MouseEvent e) {
        
    }
    //mouse motion listener
    public void mouseMoved(MouseEvent e) {
        
    }
    public void mouseDragged(MouseEvent e) {
        
    }
}