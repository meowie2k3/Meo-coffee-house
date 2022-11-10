package Map;

import Main.GamePanel;

import java.util.Scanner;
import java.awt.*;
import java.awt.image.*;
import java.io.*; // for reader

import javax.imageio.*;

public class Map {
    private int x;  
    private int y;
    //map instance
    private int[][] map;
    private int furnitureSize = 40;
    private int catSize = 32;
    private int drinkSize = 16;
    //map size
    private int numRows=6;
    private int numCols=8;
    //furiture
    private Furniture[][] furniture;
    public static final int Chair = 1;
    public static final int table =2;
    public static final int sink = 3;
    public static final int stove = 4;
    public static final int fridge = 5;
    //user instance
    private int food;
    private int money;
    private int toy;
    private int catNum;



    //constructor
    public Map(){


    }

    //getters
    public int getCatSize(){
        return catSize;
    }
    public int getCatNum(){
        return catNum;
    }
    public int getX(){
        return (int)x;
    }
    public int getY(){
        return (int)y;
    }
    //load map files into memory
    public void loadUserSavedGame(String s){
        try{
            Scanner sc = new Scanner(new File(s));
            int food = sc.nextInt();
            int money = sc.nextInt();
            int toy = sc.nextInt();
            int catNum = sc.nextInt();
            System.out.println(food + " " + money + " " + toy + " " + catNum);
            for(int i=0;i< catNum;i++){

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //draw function
    public void draw(Graphics2D g){
        
    }
    
}
