package GameState;
import java.awt.event.*;
import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.Random;

import javax.print.attribute.standard.Sides;

import Main.*;
import Map.*;
import Entity.*;
import Entity.character;

public class IngameState extends GameState{

    public static Map map;
    public static Background bg;

    //ui
    private UI ui;
    private Cat cat;
    private Map m;

    //cat properties
    public static ArrayList<Cat> catList;
    public static ArrayList<character> characterList;

    //animation actions
    private static final int SIT_TO_SLEEP = 0;
    private static final int SLEEP = 1;
    private static final int SIT = 2;
    private static final int STAND_TO_SIT = 3;
    private static final int WALK = 4;
    private static final int STAND = 12;
    private static final int SCRATCH = 20;
    private static final int SIT_TO_SCRATCH = 20;
    //reversible animation
    private static final int SLEEP_TO_SIT = 21;
    private static final int SIT_TO_STAND = 22;
    private static final int REVERSE_SIT = 23;
    //private ArrayList<String> catAddress;
    

    //constructor
    public IngameState(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }
    
    //file abstract method
    public void init() {
        catList = new ArrayList<Cat>();
        characterList = new ArrayList<character>();
        map = new Map();
        
        //load user data
        map.loadFurniture();
        map.loadUserSavedGame("/UserSavedGame/User1.map");
        
        bg = new Background("/Backgrounds/TestbgIngameState.png", 0);

        //UI
        ui = new UI(m);
    }

    //update
    public void update() {
        //update map
        //map.update();

        //update cat
        if(catList.size() != map.getCatNum()){
            System.out.println("bug! " + catList.size() + " " + map.getCatNum());
        }
        for(int i=0;i<catList.size();i++){
            catList.get(i).update();
            //moving
            if(catList.get(i).getCurentAction() >= WALK && catList.get(i).getCurentAction() <= WALK+7){
                catList.get(i).move(catList.get(i).getDirection());
                catList.get(i).bounding();
            }
        }
        //update character
        if(characterList.size() != map.getcharacterNum()){
            System.out.println("bug! " + characterList.size() + " " + map.getcharacterNum());
        }
        for(int i=0;i<characterList.size();i++){
            characterList.get(i).update();
        }
    }
    public void draw(java.awt.Graphics2D g) {
        //draw map
        //clear screen
        //g.setColor(java.awt.Color.WHITE);
        //g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        try{
            //draw background

            bg.draw(g);
            //draw map
            map.draw(g);
            //draw cat
            for(int i=0;i<catList.size();i++){
                catList.get(i).draw(g);
            }
            for(int i=0;i<characterList.size();i++){
                characterList.get(i).draw(g);
            }

            //draw UI
            ui.draw(g);

        } catch(Exception e) {
            e.printStackTrace();
        }
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
        for (int i = 0; i < catList.size(); i++) {
            if (catList.get(i).contains(e.getX(), e.getY())) {
                catList.get(i).catDoSomething();          
            }
        }
    }

    public void mouseExited(MouseEvent e) {
        
    }
    //mouse motion listener
    public void mouseMoved(MouseEvent e) {
        // int x = e.getX();
        // int y = e.getY();
        // System.out.println("mouse moved " + x + " " + y);
        
    }
    public void mouseDragged(MouseEvent e) {
        
    }
}