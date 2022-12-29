package GameState;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;

import Main.*;
import Map.*;
import Entity.*;
import Entity.character;

public class IngameState extends GameState{

    public static Map map;
    public static Background bg;
    private SoundEffect soundEffect;

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

    //shop
    private Shop shop;
    private JButton s1, s2;

    //constructor
    public IngameState(GameStateManager gsm){
        this.gsm = gsm;
        // gsm.add(s1);
        init();
        
    }
    
    // file abstract method
    public void init() {
        catList = new ArrayList<Cat>();
        characterList = new ArrayList<character>();
        bartenderList = new ArrayList<character>();
        map = new Map();

        soundEffect = new SoundEffect(GamePanel.WIDTH - 23,
        GamePanel.HEIGHT - 23,
            "Resources/soundEffect/meow.wav", false);
        
        // load user data
        map.loadFurniture();
        map.loadUserSavedGame("/UserSavedGame/User1.map");
        
        bg = new Background("/Backgrounds/WoodenFloor.png", 0);

        // UI
        ui = new UI();

        // Shop
        shop = new Shop();
        
        JButton s1 = new JButton(new ImageIcon("s1.png"));  
        s1.setBounds(5, 650, 30, 30);      
        s1.setBackground(Color.WHITE);
        s1.setBorderPainted(false);

        JButton s2 = new JButton(new ImageIcon("s2.png"));  
        s2.setBounds(5, 650, 30, 30);      
        s2.setBackground(Color.WHITE);
        s2.setBorderPainted(false);
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
            soundEffect.draw(g);

            //draw bartender
            for (int i = 0; i < bartenderList.size(); i++) {
                bartenderList.get(i).draw(g);
            }


            //draw UI
            ui.draw(g);
            map.draw(g);

            // //draw shop
            // shop.draw(g);
            
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
        if(soundEffect.contains(e.getX(), e.getY())){
            soundEffect.toggle();
        }
        // for cat
        for (int i = 0; i < catList.size(); i++) {
            if (catList.get(i).contains(e.getX(), e.getY())) {
                catList.get(i).catDoSomething();          
            }
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
                map.SaveUserData("Resources/UserSavedGame/User1.map");
            }
        }

        // if (map.containsFurniture(e.getX(), e.getY())) {
        //     System.out.println("furniture");
        // }
        // else {
        //     System.out.println("no furniture");
        // }
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

    public static void add(JButton s1) {
    }

    public static void remove(JButton s1) {
    }

    public static void add(JPanel panel) {
    }
}