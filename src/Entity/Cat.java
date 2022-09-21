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
    private static final int leftDown = 0;
    private static final int down = 1;
    private static final int rightDown = 2;
    private static final int right = 3;
    private static final int rightUp = 4;
    private static final int up = 5;
    private static final int leftUp = 6;
    private static final int left = 7;

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
    private static final int STAND = 12;
    private static final int SCRATCH = 20;
    private static final int SITTOSCRATCH = 20;
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

        //hash cat animation image 
        try{
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream(address));
            sprites = new ArrayList<BufferedImage[]>();

            //get sitToSleep animation
            BufferedImage[] sitToSleep = new BufferedImage[numFrames[SITTOSLEEP]];
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
            BufferedImage[] sleep = new BufferedImage[numFrames[SLEEP]];
            tmp = 0;
            sleep[tmp]= spritesheet.getSubimage(
                0 * width, 
                2 * height,
                width, 
                height);
            sprites.add(sleep);

            //get sit animation
            BufferedImage[] sit = new BufferedImage[numFrames[SIT]];
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
            BufferedImage[] standToSit = new BufferedImage[numFrames[STANDTOSIT]];
            tmp = 0;
            for(int i=4; i<6;i++){
                for(int j=0; j<4;j++){
                    if(i==5 && j==3) break;
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
                BufferedImage[] walk = new BufferedImage[numFrames[WALK]];
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
                    BufferedImage[] stand = new BufferedImage[numFrames[STAND]];
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
            BufferedImage[] sitToScratch = new BufferedImage[numFrames[SITTOSCRATCH]];
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
            BufferedImage[] sleepToSit = new BufferedImage[numFrames[SLEEPTOSIT]];
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
            BufferedImage[] sitToStand = new BufferedImage[numFrames[SITTOSTAND]];
            tmp = 0;
            for(int i=6; i>=4;i--){
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
        }catch(Exception e){
            e.printStackTrace();
        }
        animation = new Animation();
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

    }

    public void update(int nextAction){

        int delay = 50;
        //set animation
        if(nextAction == SLEEP){
            if(currentAction == SLEEP){
                animation.setFrames(sprites.get(SLEEP));
                animation.setDelay(delay);
            }

            else if(currentAction == SIT){
                currentAction = SLEEP;
                animation.setFrames(sprites.get(SITTOSLEEP));
                animation.setDelay(delay);
            }

            else if(currentAction == STAND){
                update(SIT);
                currentAction = SLEEP;
                animation.setFrames(sprites.get(SITTOSLEEP));
                animation.setDelay(delay);
            }
            else if(currentAction == WALK){
                update(STAND);
                update(SIT);
                currentAction = SLEEP;
                animation.setFrames(sprites.get(SITTOSLEEP));
                animation.setDelay(delay);
            }
        }

        else if(nextAction == SIT){
            if(currentAction == SIT){
                animation.setFrames(sprites.get(SIT));
                animation.setDelay(delay);
            }

            else if(currentAction == SLEEP){
                currentAction = SIT;
                animation.setFrames(sprites.get(SLEEPTOSIT));
                animation.setDelay(delay);
            }

            else if(currentAction == STAND){
                currentAction = SIT;
                animation.setFrames(sprites.get(STANDTOSIT));
                animation.setDelay(delay);
            }
            else if(currentAction == WALK){
                update(STAND);
                currentAction = SIT;
                animation.setFrames(sprites.get(STANDTOSIT));
                animation.setDelay(delay);
            }
        }

        else if(nextAction == STAND){
            if(currentAction == STAND){
                animation.setFrames(sprites.get(STAND));
                animation.setDelay(delay);
            }

            else if(currentAction ==SIT){
                currentAction = STAND;
                animation.setFrames(sprites.get(SITTOSTAND));
                animation.setDelay(delay);
            }

            else if(currentAction == WALK){
                currentAction = STAND;
                animation.setFrames(sprites.get(WALK + currentDirection));
                animation.setDelay(delay);
            }
            else if(currentAction == SLEEP){
                update(SIT);
                currentAction = STAND;
                animation.setFrames(sprites.get(SITTOSTAND));
                animation.setDelay(delay);
            }
        }

        else if(nextAction == WALK){
            if(currentAction == WALK){
                animation.setFrames(sprites.get(WALK + currentDirection));
                animation.setDelay(delay);
            }

            else if(currentAction == STAND){
                currentAction = WALK;
                animation.setFrames(sprites.get(WALK + currentDirection));
                animation.setDelay(delay);
            }
            else if(currentAction == SIT){
                update(STAND);
                currentAction = WALK;
                animation.setFrames(sprites.get(WALK + currentDirection));
                animation.setDelay(delay);
            }
            else if(currentAction == SLEEP){
                update(SIT);
                update(STAND);
                currentAction = WALK;
                animation.setFrames(sprites.get(WALK + currentDirection));
                animation.setDelay(delay);
            }
        }
        else if(nextAction == SCRATCH){
            if(currentAction == SCRATCH){
                animation.setFrames(sprites.get(SCRATCH));
                animation.setDelay(delay);
            }

            else if(currentAction == SIT){
                currentAction = SCRATCH;
                animation.setFrames(sprites.get(SITTOSCRATCH));
                animation.setDelay(delay);
            }
            else if(currentAction == STAND){
                update(SIT);
                currentAction = SCRATCH;
                animation.setFrames(sprites.get(SITTOSCRATCH));
                animation.setDelay(delay);
            }
            else if(currentAction == WALK){
                update(STAND);
                update(SIT);
                currentAction = SCRATCH;
                animation.setFrames(sprites.get(SITTOSCRATCH));
                animation.setDelay(delay);
            }
            else if(currentAction == SLEEP){
                update(SIT);
                currentAction = SCRATCH;
                animation.setFrames(sprites.get(SITTOSCRATCH));
                animation.setDelay(delay);
            }
        }

        animation.update();
        currentAction = SIT;
        animation.setFrames(sprites.get(SIT));
        animation.setDelay(delay);
    }

    //draw
    public void draw(Graphics2D g){
        setMapPosition();
        
        g.drawImage(
            animation.getImage(),
            (int)(x + xmap - width / 2),
            (int)(y + ymap - height / 2),
            null
        );
    }

    //getters
    public int getCurrentAction(){
        return currentAction;
    }
}
