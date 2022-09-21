package Entity;

import Map.*;

import java.util.ArrayList;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;

public class Cat extends Entity {
    //cat properties
    private String name;
    
    //emotion
    private boolean isHungry;
    private boolean isSleepy;
    private boolean isBored;

    //cost
    private int foodCost;
    private int sleepCost;
    private int playCost;

    //animation
    private ArrayList<BufferedImage[]> sprites;

    //direction translate
    private final int downLeft = 0;
    private final int down = 1;
    private final int downRight = 2;
    private final int right = 3;
    private final int upRight = 4;
    private final int up = 5;
    private final int upLeft = 6;
    private final int left = 7;

    //number of frames in each animation
    private final int[] numFrames = {
        8, 1, 4, 6, 
        4,4,4,4,4,4,4,4, 
        1,1,1,1,1,1,1,1, 
        20};

    //animation actions
    private static final int SITTOSLEEP = 0;
    private static final int SLEEP = 1;
    private static final int SIT = 2;
    private static final int STANDTOSIT = 3;
    private static final int walkDownLeft = 4;
    private static final int walkDown = 5;
    private static final int walkDownRight = 6;
    private static final int walkRight = 7;
    private static final int walkUpRight = 8;
    private static final int walkUp = 9;
    private static final int walkUpLeft = 10;
    private static final int walkLeft = 11;
    private static final int standDownLeft = 12;
    private static final int standDown = 13;
    private static final int standDownRight = 14;
    private static final int standRight = 15;
    private static final int standUpRight = 16;
    private static final int standUp = 17;
    private static final int standUpLeft = 18;
    private static final int standLeft = 19;
    private static final int SITTOSCRATCH = 20;


    public Cat(Map map, String address){
        super(map);

        //size for reading spritesheet
        width = 32;
        height = 32;

        //physics
        moveSpeed = 0.3;
        maxSpeed = 1.6;
        stopSpeed = 0.4;

        //cost
        foodCost = 1;
        sleepCost = 1;
        playCost = 1;

        //default emotion
        isHungry = false;
        isSleepy = false;
        isBored = false;
        currentAction = SLEEP;

        //hash cat animation image 
        try{
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream(address));
            sprites = new ArrayList<BufferedImage[]>();

            //get sitting animation
            BufferedImage[] sit = new BufferedImage[SITTING];
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 2; j++){
                sit[j] = spritesheet.getSubimage(
                        j * width, 
                        i * height, 
                        width, 
                        height);
                }
            }
            sprites.add(sit);

            //get standing up animation
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //getters
    public String getName(){
        return name;
    }
    public int getFoodCost(){
        return foodCost;
    }
    public int getSleepCost(){
        return sleepCost;
    }
    public int getPlayCost(){
        return playCost;
    }
    public boolean isHungry(){
        return isHungry;
    }
    public boolean isSleepy(){
        return isSleepy;
    }
    public boolean isBored(){
        return isBored;
    }

    public void update(int action){
        //set animation
        if(currentAction == )
        
    }
}
