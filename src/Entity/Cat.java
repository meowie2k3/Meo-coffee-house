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
    //number of frames in each animation
    private final int[] numFrames = {9, 6, 6, 4, 20};

    //animation actions
    private static final int SLEEPING = 0;
    private static final int SITTING = 1;
    private static final int STANDINGUP = 2;
    private static final int WALKING = 3;
    private static final int SCRATCHING = 4;

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
        currentAction = SLEEPING;

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

    public void update(){
        //set animation
        if(currentAction == SLEEPING){
            if(commandAction == SITTING){
                currentAction = SITTING;
                animation.setFrames(sprites.get(SITTING));
            }
        }
        
    }
}
