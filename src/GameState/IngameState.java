package GameState;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.print.attribute.standard.Sides;

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
            if(catList.get(i).getCurentAction() >= WALK && catList.get(i).getCurentAction() <= WALK+7){
                catList.get(i).move(catList.get(i).getDirection());

                //bounded
                if (catList.get(i).getDirection()==0 || catList.get(i).getDirection()==1 || catList.get(i).getDirection()==2){
                    if (catList.get(i).getY() == GamePanel.HEIGHT - 16){
                        catList.get(i).setAction(STAND + catList.get(i).getDirection());
                        catList.get(i).setAction(SIT);
                    }
                }
                if (catList.get(i).getDirection()==0 || catList.get(i).getDirection()==7 || catList.get(i).getDirection()==6){
                    if (catList.get(i).getX() == 0 + 16){
                        catList.get(i).setAction(STAND + catList.get(i).getDirection());
                        catList.get(i).setAction(SIT);
                    }
                }
                if (catList.get(i).getDirection()==4 || catList.get(i).getDirection()==5 || catList.get(i).getDirection()==6){
                    if (catList.get(i).getY() == 0 + 16){
                        catList.get(i).setAction(STAND + catList.get(i).getDirection());
                        catList.get(i).setAction(SIT);
                    }
                }
                if (catList.get(i).getDirection()==2 || catList.get(i).getDirection()==3 || catList.get(i).getDirection()==4){
                    if (catList.get(i).getX() == GamePanel.WIDTH - 16){
                        catList.get(i).setAction(STAND + catList.get(i).getDirection());
                        catList.get(i).setAction(SIT);
                    }
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
                //cat sleep -> sit
                if (catList.get(i).getCurentAction() == SLEEP) {
                    catList.get(i).setAction(SIT);
                    break;
                }
                //cat sit -> walk randomly, when it get to the edge, it will sit again
                if (catList.get(i).getCurentAction() == SIT || catList.get(i).getCurentAction() == REVERSE_SIT) {
                    int[][] possibleDirection = { { 1, 2, 3, 4, 5 }, //cat at left
                                                    { 0, 1, 5, 6, 7 }, //cat at right
                                                    { 3, 4, 5, 6, 7 }, //cat at bottom
                                                    { 0, 1, 2, 3, 7 }, //cat at top

                                                    { 1, 2, 3}, //cat at topleft coner
                                                    { 0, 1, 7}, //cat at topright coner
                                                    { 3, 4, 5}, //cat at bottomleft coner
                                                    { 5, 6, 7}, //cat at bottomright coner

                                                    { 0, 1, 2, 3, 4, 5, 6, 7 }, //cat at center
                                                };
                    
                    Random rand = new Random();
                    //cat at topleft coner
                    if (catList.get(i).getX() == 0 + 16 && catList.get(i).getY() == 0 + 16) {
                        catList.get(i).setDirection(possibleDirection[4][rand.nextInt(3)]);
                        //System.out.println("topleft " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        catList.get(i).setAction(WALK + catList.get(i).getDirection());
                        break;
                    }
                    //cat at topright coner
                    if (catList.get(i).getX() == GamePanel.WIDTH - 16 && catList.get(i).getY() == 0 + 16) {
                        catList.get(i).setDirection(possibleDirection[5][rand.nextInt(3)]);
                        //System.out.println("topright " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        catList.get(i).setAction(WALK + catList.get(i).getDirection());
                        break;
                    }
                    //cat at bottomleft coner
                    if (catList.get(i).getX() == 0 + 16 && catList.get(i).getY() == GamePanel.HEIGHT - 16) {
                        catList.get(i).setDirection(possibleDirection[6][rand.nextInt(3)]);
                        //System.out.println("bottomleft " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        catList.get(i).setAction(WALK + catList.get(i).getDirection());
                        break;
                    }
                    //cat at bottomright coner
                    if (catList.get(i).getX() == GamePanel.WIDTH - 16 && catList.get(i).getY() == GamePanel.HEIGHT - 16) {
                        catList.get(i).setDirection(possibleDirection[7][rand.nextInt(3)]);
                        //System.out.println("bottomright " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        catList.get(i).setAction(WALK + catList.get(i).getDirection());
                        break;
                    }
                    //cat at left
                    if (catList.get(i).getX() == 0 + 16 && catList.get(i).getY() >= 0 + 16 && catList.get(i).getY() <= GamePanel.HEIGHT - 16) {
                        catList.get(i).setDirection(possibleDirection[0][rand.nextInt(5)]);
                        //System.out.println("left " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        catList.get(i).setAction(WALK + catList.get(i).getDirection());
                        break;
                    }
                    //cat at right
                    if (catList.get(i).getX() == GamePanel.WIDTH - 16 && catList.get(i).getY() >= 0 + 16 && catList.get(i).getY() <= GamePanel.HEIGHT - 16) {
                        catList.get(i).setDirection(possibleDirection[1][rand.nextInt(5)]);
                        //System.out.println("right " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        catList.get(i).setAction(WALK + catList.get(i).getDirection());
                        break;
                    }
                    //cat at bottom
                    if (catList.get(i).getY() == GamePanel.HEIGHT - 16 && catList.get(i).getX() >= 0 + 16 && catList.get(i).getX() <= GamePanel.WIDTH - 16) {
                        catList.get(i).setDirection(possibleDirection[2][rand.nextInt(5)]);
                        //System.out.println("bottom " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        catList.get(i).setAction(WALK + catList.get(i).getDirection());
                        break;
                    }
                    //cat at top
                    if (catList.get(i).getY() == 0 + 16 && catList.get(i).getX() >= 0 + 16 && catList.get(i).getX() <= GamePanel.WIDTH - 16) {
                        catList.get(i).setDirection(possibleDirection[3][rand.nextInt(5)]);
                        //System.out.println("top " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        catList.get(i).setAction(WALK + catList.get(i).getDirection());
                        break;
                    }
                    //cat at center
                    if (catList.get(i).getX() > 0 + 16 && catList.get(i).getX() < GamePanel.WIDTH - 16 && catList.get(i).getY() > 0 + 16 && catList.get(i).getY() < GamePanel.HEIGHT - 16) {
                        catList.get(i).setDirection(possibleDirection[8][rand.nextInt(8)]);
                        //System.out.println("center " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        catList.get(i).setAction(WALK + catList.get(i).getDirection());
                        break;
                    }
                    
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