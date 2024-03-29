package GameState;
import java.awt.event.*;

import java.util.ArrayList;

import java.awt.image.*;
import javax.imageio.ImageIO;

import Main.*;
import Map.*;
import Entity.*;
import Entity.character;

public class IngameState extends GameState{

    public static Map map;
    public static Background bg;
    private SoundEffect soundEffect;
    private Line98 linePlaying;
    private Shop shop;

    // ui
    private UI ui;

    //cat properties
    public static ArrayList<Cat> catList;
    public static ArrayList<character> characterList;
    public static ArrayList<character> bartenderList;
    private static final String[] characterAddress = new String[] {
        "/Character and Furniture/Shiba-Sheet.png",
        "/Character and Furniture/Blackcat-Sheet.png",
        "/Character and Furniture/Hedgedog-Sheet.png",
        "/Character and Furniture/Rabbit-Sheet.png",
        "RabbitMon-Sheet.png"
    };


    //constructor
    public IngameState(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }
    
    // file abstract method
    public void init() {
        catList = new ArrayList<Cat>();
        characterList = new ArrayList<character>();
        bartenderList = new ArrayList<character>();
        map = new Map();

        soundEffect = new SoundEffect(GamePanel.WIDTH - 23,
        GamePanel.HEIGHT - 20,
            "Resources/soundEffect/meow.wav", false);

        linePlaying = new Line98(3, GamePanel.HEIGHT - 42, true);

        shop = new Shop(3, GamePanel.HEIGHT - 20, true);
        
        // load user data
        map.loadFurniture();
        map.loadUserSavedGame("/UserSavedGame/User1.map");
        
        bg = new Background("/Backgrounds/WoodenFloor.png", 0);

        // UI
        ui = new UI();
    }

    // update
    public void update() {

        // update cat
        if(catList.size() != map.getCatNum()){
            System.out.println("bug! " + catList.size() + " " + map.getCatNum());
        }
        for(int i=0;i<catList.size();i++){
            catList.get(i).update();
            //moving
            if(catList.get(i).getCurentAction() >= Cat.WALK && catList.get(i).getCurentAction() <= Cat.WALK+7){
                catList.get(i).move(catList.get(i).getDirection(), 16);
                catList.get(i).bounding();
            }
        }

        // update character
        if(characterList.size() != map.getcharacterNum()){
            System.out.println("bug! " + characterList.size() + " " + map.getcharacterNum());
        }
        for(int i=0;i<characterList.size();i++){
            characterList.get(i).update();
            //moving
            if(characterList.get(i).getCurentAction() >= Cat.WALK && characterList.get(i).getCurentAction() <= Cat.WALK+7){
                characterList.get(i).move(characterList.get(i).getDirection(), 24);
            }            
        }
        for (int i = 0; i < characterList.size(); i++) {
            if (characterList.get(i).way == 0)  {
                characterList.get(i).walkingIn(i);
            }

            else characterList.get(i).walkingOut(i);

        }

        // update bartender
        if (bartenderList.size() != map.getbartenderNum()) {
            System.out.println("bug! " + bartenderList.size() + " " + map.getbartenderNum());
        }
        for(int i=0;i<bartenderList.size();i++){
            bartenderList.get(i).update();
        }     

        //map.SaveUserData("Resources/UserSavedGame/User1.map");
        
    }
    public void saveData() {
        map.SaveUserData("Resources/UserSavedGame/User1.map");
    }
    public void draw(java.awt.Graphics2D g) {

     try{
            bg.draw(g);

            //draw map
            soundEffect.draw(g);
            linePlaying.draw(g);
            shop.draw(g);

            //draw bartender
            for (int i = 0; i < bartenderList.size(); i++) {
                bartenderList.get(i).draw(g);
            }

            //draw UI
            ui.draw(g);
            map.draw(g);

            
            //draw cat
            for(int i=0;i<catList.size();i++){
                catList.get(i).draw(g);
            }

            //draw characters
            for(int i=0;i<characterList.size();i++){
                characterList.get(i).draw(g);
            }

            

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
        // sound
        if(soundEffect.contains(e.getX(), e.getY())){
            soundEffect.toggle();
        }
        // for cat
        for (int i = 0; i < catList.size(); i++) {
            if (catList.get(i).contains(e.getX(), e.getY())) {
                catList.get(i).catDoSomething();          
            }
        }

        // play Line 98
        if (linePlaying.contains(e.getX(), e.getY()))   {
            gsm.setState(GameStateManager.LINESTATE);
        }

        // shopping
        if (shop.contains(e.getX(), e.getY()))  {
            gsm.setState(GameStateManager.SHOPSTATE);
        }

        // for character
        for (int i = 0; i < characterList.size(); i++) {
            if (characterList.get(i).containPopup(e.getX(), e.getY()) && 
            characterList.get(i).getX() == characterList.get(i).getfinalX() &&
            characterList.get(i).getY() == characterList.get(i).getfinalY()) {
                // walking
                characterList.get(i).way = 1;    
                characterList.get(i).setAction(2);  
                map.setMoney(map.getMoney()+50);
                
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