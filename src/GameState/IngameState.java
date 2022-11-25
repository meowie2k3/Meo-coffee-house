package GameState;
import java.awt.event.*;
import java.util.ArrayList;

import Main.*;
import Map.*;
import Entity.*;

public class IngameState extends GameState{

    public static Map map;
    public static Background bg;
    public static ArrayList<UxUi> uxui;

    //cat properties
    public static ArrayList<Cat> catList;

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
        catList = new ArrayList<Cat>();
        uxui = new ArrayList<UxUi>();
        map = new Map();
        //map.loadFurniture();
        map.loadUserSavedGame("/UserSavedGame/User1.map");
        
        bg = new Background("/Backgrounds/TestbgIngameState.png", 0);

        String[] iconAddress = {"/Icon/coin.jpg"};
        String[] iconName = {"money", "food", "toy", "cat"};
        int[] iconValue = {map.getFood(), map.getMoney(), map.getToy(), map.getCatNum()};
        for(int i=0;i<1;i++){
            UxUi uiux = new UxUi(iconName[i],iconAddress[i], iconValue[i]);
            uxui.add(uiux);
        }
    }

    //update
    public void update() {
        //update map
        //map.update();

        //update cat
        for(int i=0;i<catList.size();i++){
            catList.get(i).update();
        }


        //update uxui
        for(int i=0;i<uxui.size();i++){
            //uxui.get(i).update();
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
        }catch(Exception e){
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

    }

    public void mouseExited(MouseEvent e) {
        
    }
    //mouse motion listener
    public void mouseMoved(MouseEvent e) {
        
    }
    public void mouseDragged(MouseEvent e) {
        
    }
}