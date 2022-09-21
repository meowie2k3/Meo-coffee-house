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

        //hash cat animation image 
        try{
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream(address));
            sprites = new ArrayList<BufferedImage[]>();

            //get sitToSleep animation
            BufferedImage[] sitToSleep = new BufferedImage[8];
            int tmp =0;
            for(int i = 0; i < 2; i++){
                for(int j = 0; j < 4; j++){
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
            BufferedImage[] sleep = new BufferedImage[1];
            tmp = 0;
            sleep[tmp]= spritesheet.getSubimage(
                0 * width, 
                2 * height,
                width, 
                height);
            sprites.add(sleep);

            //get sit animation
            BufferedImage[] sit = new BufferedImage[4];
            tmp = 0;
            for(int i=3; i<4;i++){
                for(int j=0; j<4;j++){
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
            BufferedImage[] standToSit = new BufferedImage[6];
            tmp = 0;
            for(int i=4; i<6;i++){
                for(int j=0; j<4;j++){
                    if(i==5 && j==3) break;
                    if(i==5 && j == 2) break;
                    standToSit[tmp] = spritesheet.getSubimage(
                        j * width, 
                        i * height, 
                        width, 
                        height);
                    tmp++;
                }
            }
            sprites.add(standToSit);

            //get walk animation
            for(int i=6;i<14;i++){
                BufferedImage[] walk = new BufferedImage[4];
                tmp = 0;
                for(int j=0;j<4;j++){
                    walk[tmp] = spritesheet.getSubimage(
                        j * width, 
                        i * height, 
                        width, 
                        height);
                    tmp++;
                }
                sprites.add(walk);
            }

            //get stand animation
            for(int i=14; i<16;i++){
                for(int j=0;j<4;j++){
                    BufferedImage[] stand = new BufferedImage[1];
                    tmp = 0;
                    stand[tmp] = spritesheet.getSubimage(
                        j * width, 
                        i * height, 
                        width, 
                        height);
                    sprites.add(stand);
                }
            }

            //get sitToScratch animation
            BufferedImage[] sitToScratch = new BufferedImage[20];
            tmp = 0;
            for(int i=16; i<21;i++){
                for(int j=0; j<4;j++){
                    sitToScratch[tmp] = spritesheet.getSubimage(
                        j * width, 
                        i * height, 
                        width, 
                        height);
                    tmp++;
                }
            }
            sprites.add(sitToScratch);

            //get sleepToSit animation
            BufferedImage[] sleepToSit = new BufferedImage[8];
            tmp = 0;
            for(int i=1; i>=0;i--){
                for(int j=3; j>=0;j--){
                    sleepToSit[tmp] = spritesheet.getSubimage(
                        j * width, 
                        i * height, 
                        width, 
                        height);
                    tmp++;
                }
            }
            sprites.add(sleepToSit);

            //get sitToStand animation
            BufferedImage[] sitToStand = new BufferedImage[6];
            tmp = 0;
            for(int i=5; i>=4;i--){
                for(int j=3; j>=0;j--){
                    if(i==5 && j==2) continue;
                    if(i==5 && j==3) continue;
                    sitToStand[tmp] = spritesheet.getSubimage(
                        j * width, 
                        i * height, 
                        width, 
                        height);
                    tmp++;
                }
            }
            sprites.add(sitToStand);


        }catch(Exception e){
            e.printStackTrace();
        }
        animation = new Animation();
        currentAction = SIT;
        animation.setFrames(sprites.get(SIT));
        animation.setDelay(120);
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
    public int getDirection(){
        return currentDirection;
    }
    //setters
    public void setHungry(boolean b){
        isHungry = b;
    }
    public void setSleepy(boolean b){
        isSleepy = b;
    }
    public void setBored(boolean b){
        isBored = b;
    }
    public void setDirection(int i){
        currentDirection = i;
    }
    public void setAction(int i){
        currentAction = i;
    }
    

    public void update(int nextAction){

        int delay = 120;
        //set animation
        if(nextAction == SLEEP){
            if(currentAction == SLEEP){}
            if(currentAction == SIT){
                currentAction = SIT;
                animation.setFrames(sprites.get(SIT_TO_SLEEP));
                animation.setDelay(delay);
                
            }
            if(currentAction == STAND){
                update(SIT);
                currentAction = SIT;
                animation.setFrames(sprites.get(SIT_TO_SLEEP));
                animation.setDelay(delay);
            }
            if(currentAction == WALK){
                update(STAND);
                update(SIT);
                currentAction = SIT;
                animation.setFrames(sprites.get(SIT_TO_SLEEP));
                animation.setDelay(delay);
            }
        }
    
        if(nextAction == SIT){
            if(currentAction == SIT) {}
            if(currentAction == SLEEP){
                currentAction = SIT;
                animation.setFrames(sprites.get(SLEEP_TO_SIT));
                animation.setDelay(delay);
            }
            if(currentAction == STAND){
                currentAction = SIT;
                animation.setFrames(sprites.get(STAND_TO_SIT));
                animation.setDelay(delay);
            }
            if(currentAction == WALK){
                update(STAND);
                currentAction = SIT;
                animation.setFrames(sprites.get(STAND_TO_SIT));
                animation.setDelay(delay);
            }
        }

        if(nextAction == STAND){
            if(currentAction == STAND){}
            if(currentAction == SIT){
                currentAction = STAND;
                animation.setFrames(sprites.get(SIT_TO_STAND));
                animation.setDelay(delay);
            }
            if(currentAction == SLEEP){
                update(SIT);
                currentAction = STAND;
                animation.setFrames(sprites.get(SIT_TO_STAND));
                animation.setDelay(delay);
            }
            if(currentAction == WALK){
                currentAction = STAND;
                animation.setFrames(sprites.get(STAND));
                animation.setDelay(delay);
            }
        }

        if(nextAction == WALK){
            if(currentAction == WALK) {}
            if(currentAction == SIT){
                currentAction = WALK;
                animation.setFrames(sprites.get(WALK + currentDirection));
                animation.setDelay(delay);
            }
            if(currentAction == SLEEP){
                update(SIT);
                currentAction = WALK;
                animation.setFrames(sprites.get(WALK + currentDirection));
                animation.setDelay(delay);
            }
            if(currentAction == STAND){
                currentAction = WALK;
                animation.setFrames(sprites.get(WALK + currentDirection));
                animation.setDelay(delay);
            }
        }

        if(nextAction == SCRATCH){
            if(currentAction == SIT){
                animation.setFrames(sprites.get(SCRATCH));
                animation.setDelay(delay);
                for(int i=0;i< animation.getLength();i++){
                    animation.update();
                }
            }
            else if(currentAction == SLEEP || currentAction == STAND){
                update(SIT);
                animation.setFrames(sprites.get(SCRATCH));
                animation.setDelay(delay);
                for(int i=0;i< animation.getLength();i++){
                    animation.update();
                }
            }
            else if(currentAction == WALK){
                update(STAND);
                update(SIT);
                animation.setFrames(sprites.get(SCRATCH));
                animation.setDelay(delay);
                for(int i=0;i< animation.getLength();i++){
                    animation.update();
                }
            }
        }

        animation.update();
        // System.out.println("currentFrame: " + animation.getFrame() + " " 
        // + animation.hasPlayedOnce() + " " 
        // + currentAction + " "
        // + animation.getLength());
    }

    //draw
    public void draw(Graphics2D g){
        setMapPosition();
        // System.out.println(x + xmap - width /2);
        // System.out.println(y + ymap - height / 2);
        
        g.drawImage(
            animation.getImage(),
            (int)(x + xmap - width /2),
            (int)(y + ymap - height / 2),
            null
        );
    }

    
}
