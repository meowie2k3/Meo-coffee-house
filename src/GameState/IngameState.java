package GameState;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import Main.*;
import Map.*;
import Entity.*;

public class IngameState extends GameState{

    public static Map map;
    public static Background bg;
    public static ArrayList<UxUi> uxui;

    //cat properties
    public static ArrayList<Cat> catList;

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
        uxui = new ArrayList<UxUi>();
        map = new Map();
        
        //load user data
        map.loadFurniture();
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
            if(catList.get(i).getCurentAction() == WALK){
                catList.get(i).move(catList.get(i).getDirection());
                if (catList.get(i).getX() <= 0 + 16 || catList.get(i).getX() >= GamePanel.WIDTH - 16 
                    || catList.get(i).getY() <= 0 + 16 || catList.get(i).getY() >= GamePanel.HEIGHT - 16) {
                    
                    catList.get(i).setAction(STAND + catList.get(i).getDirection());
                }
            }
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
        for (int i = 0; i < catList.size(); i++) {
            if (catList.get(i).contains(e.getX(), e.getY())) {
                if (catList.get(i).getCurentAction() == SLEEP) {
                    catList.get(i).setAction(SIT);
                    break;
                }
                if (catList.get(i).getCurentAction() == SIT || catList.get(i).getCurentAction() == REVERSE_SIT) {
                    Random rand = new Random();
                    catList.get(i).setDirection(rand.nextInt(8));
                    catList.get(i).setAction(WALK + catList.get(i).getDirection());
                    break;
                }
                // if (catList.get(i).getCurentAction() == STAND) {
                //     catList.get(i).setAction(WALK);
                //     break;
                // }
            }
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