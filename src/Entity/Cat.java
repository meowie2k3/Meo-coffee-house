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
    private static final int[] walkArr ={0,1,2,3,4,5,6,7};
    private static final int leftDown = 0;
    private static final int down = 1;
    private static final int rightDown = 2;
    private static final int right = 3;
    private static final int rightUp = 4;
    private static final int up = 5;
    private static final int leftUp = 6;
    private static final int left = 7;
    private static final int[] standArr ={0,1,2,3,4,5,6,7};

    //number of frames in each animation
    private final int[] numFrames = {
        8, 1, 4, 6, 
        4,
        1,
        20};
    
    //animation actions
    private static final int SITTOSLEEP = 0;
    private static final int SLEEP = 1;
    private static final int SIT = 2;
    private static final int STANDTOSIT = 3;
    private static final int WALK = 4;
    private static final int STAND = 5;
    private static final int SITTOSCRATCH = 6;
    //reversible animation
    private static final int SLEEPTOSIT = 7;
    private static final int SITTOSTAND = 8;

    //constructor
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
            BufferedImage[] sitToSleep = new BufferedImage[numFrames[SITTOSLEEP]];
            int tmp =0;
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 2; j++){
                    sitToSleep[tmp] = spritesheet.getSubimage(
                        j * width, 
                        i * height, 
                        width, 
                        height);
                    tmp++;
                }
            }
            sprites.add(sitToSleep);
            //get sleep animation
            BufferedImage[] sleep = new BufferedImage[numFrames[SLEEP]];
            tmp = 0;
            sleep[tmp]= spritesheet.getSubimage(
                0 * width, 
                3 * height, 
                width, 
                height);
            sprites.add(sleep);
            //get sit animation
            BufferedImage[] sit = new BufferedImage[numFrames[SIT]];
            tmp = 0;
            for(int i=3; i<4;i++){
                for(int j=0; j<1;j++){
                    sit[tmp] = spritesheet.getSubimage(
                        j * width, 
                        i * height, 
                        width, 
                        height);
                    tmp++;
                }
            }
            sprites.add(sit);
            //get standToSit animation
            BufferedImage[] standToSit = new BufferedImage[numFrames[STANDTOSIT]];
            tmp = 0;
            


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
        if(action == SLEEP){
            if(currentAction == SLEEP) return;
            else if(currentAction == SIT){
                currentAction = SITTOSLEEP;
            }
            else if(currentAction == )
        }
        
    }
}
