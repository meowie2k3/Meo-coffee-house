package Entity;

import Map.*;
import Main.*;

import java.util.ArrayList;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.util.Random;

public class Cat extends Entity {
    //cat properties
    private String name;
    private String address;
    
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
    private static final int REVERSE_SIT = 23;

    //constructor
    public Cat(Map map, String address){
        super(map);
        this.address = address;

        //size for reading spritesheet
        width = 32;
        height = 32;

        //physics
        moveSpeed = 0.3;

        //cost
        foodCost = 1;
        sleepCost = 1;
        playCost = 1;

        //default emotion
        isHungry = false;
        isSleepy = false;
        isBored = false;

        //hash cat animation image   
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream(address));
            sprites = new ArrayList<BufferedImage[]>();

            //get sitToSleep animation
            BufferedImage[] sitToSleep = new BufferedImage[8];
            int tmp = 0;
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

            //get reverseSit animation
            BufferedImage[] reverseSit = new BufferedImage[4];
            tmp = 0;
            for(int i=3; i>=3;i--){
                for(int j=3; j>=0;j--){
                    reverseSit[tmp] = spritesheet.getSubimage(
                        j * width, 
                        i * height, 
                        width, 
                        height);
                    tmp++;
                }
            }
            sprites.add(reverseSit);


        } catch(Exception e) {
            e.printStackTrace();
        }
        animation = new Animation();
        currentAction = SLEEP;
        nextAction = SLEEP;
        animation.setFrames(sprites.get(currentAction));
        animation.setDelay(100);
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
    public int getCurentAction(){
        return currentAction;
    }

    public int getAction(){
        return currentAction;
    }
    public String getAddress(){
        return address;
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
        nextAction = i;
        update();
    }
    
    @Override
    public void move(int direction){
        setDirection(direction);
        setAction(WALK + direction);
        super.move(direction);
    }

    //walking 
    public void walking(){
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
                    if (getX() == 0 + 16 && getY() == 0 + 16) {
                        setDirection(possibleDirection[4][rand.nextInt(3)]);
                        //System.out.println("topleft " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        setAction(WALK + getDirection());
                        return;
                    }
                    //cat at topright coner
                    if (getX() == GamePanel.WIDTH - 16 && getY() == 0 + 16) {
                        setDirection(possibleDirection[5][rand.nextInt(3)]);
                        //System.out.println("topright " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        setAction(WALK + getDirection());
                        return;
                    }
                    //cat at bottomleft coner
                    if (getX() == 0 + 16 && getY() == GamePanel.HEIGHT - 16) {
                        setDirection(possibleDirection[6][rand.nextInt(3)]);
                        //System.out.println("bottomleft " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        setAction(WALK + getDirection());
                        return;
                    }
                    //cat at bottomright coner
                    if (getX() == GamePanel.WIDTH - 16 && getY() == GamePanel.HEIGHT - 16) {
                        setDirection(possibleDirection[7][rand.nextInt(3)]);
                        //System.out.println("bottomright " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        setAction(WALK + getDirection());
                        return;
                    }
                    //cat at left
                    if (getX() == 0 + 16 && getY() >= 0 + 16 && getY() <= GamePanel.HEIGHT - 16) {
                        setDirection(possibleDirection[0][rand.nextInt(5)]);
                        //System.out.println("left " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        setAction(WALK + getDirection());
                        return;
                    }
                    //cat at right
                    if (getX() == GamePanel.WIDTH - 16 && getY() >= 0 + 16 && getY() <= GamePanel.HEIGHT - 16) {
                        setDirection(possibleDirection[1][rand.nextInt(5)]);
                        //System.out.println("right " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        setAction(WALK + getDirection());
                        return;
                    }
                    //cat at bottom
                    if (getY() == GamePanel.HEIGHT - 16 && getX() >= 0 + 16 && getX() <= GamePanel.WIDTH - 16) {
                        setDirection(possibleDirection[2][rand.nextInt(5)]);
                        //System.out.println("bottom " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        setAction(WALK + getDirection());
                        return;
                    }
                    //cat at top
                    if (getY() == 0 + 16 && getX() >= 0 + 16 && getX() <= GamePanel.WIDTH - 16) {
                        setDirection(possibleDirection[3][rand.nextInt(5)]);
                        //System.out.println("top " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        setAction(WALK + getDirection());
                        return;
                    }
                    //cat at center
                    if (getX() > 0 + 16 && getX() < GamePanel.WIDTH - 16 && getY() > 0 + 16 && getY() < GamePanel.HEIGHT - 16) {
                        setDirection(possibleDirection[8][rand.nextInt(8)]);
                        //System.out.println("center " + catList.get(i).getDirection() + " currenAction " + catList.get(i).getCurentAction());
                        setAction(WALK + getDirection());
                        return;
                    }
    }

    //timing
    private boolean countingTime = false;
    private long start = 0;
    private long limit = 0;
    public boolean getCountingTime(){
        return countingTime;
    }
    public void setCountingTime(){
        Random rand = new Random();
        if (countingTime){
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            //System.out.println(timeElapsed + " " + limit);
            if (getCurentAction() >= WALK && getCurentAction() <= WALK+7) {
                if (timeElapsed > limit){
                    countingTime = false;
                    limit = rand.nextInt(20000)+1000;
                    setCountingTime();
                    setAction(STAND + getDirection());
                    setAction(SIT);
                    return;
                }   
            }
            if (getCurentAction() == SIT || getCurentAction() == REVERSE_SIT) {
                if (timeElapsed > limit){
                    countingTime = false;
                    setAction(SLEEP);
                    return;
                }   
            }
        }
        else{
            start = System.currentTimeMillis();
            countingTime = true;
        }
    }

    //bounding when cat is walking
    public void bounding(){
        Random rand = new Random();
        if (getDirection()==0 || getDirection()==1 || getDirection()==2){
            if (getY() == GamePanel.HEIGHT - 16){
                countingTime = false;
                limit = rand.nextInt(20000)+1000;
                setCountingTime();
                setAction(STAND + getDirection());
                setAction(SIT);
            }
        }
        if (getDirection()==0 || getDirection()==7 || getDirection()==6){
            if (getX() == 0 + 16){
                countingTime = false;
                limit = rand.nextInt(20000)+1000;
                setCountingTime();
                setAction(STAND + getDirection());
                setAction(SIT);
            }
        }
        if (getDirection()==4 || getDirection()==5 || getDirection()==6){
            if (getY() == 0 + 16){
                countingTime = false;
                limit = rand.nextInt(20000)+1000;
                setCountingTime();
                setAction(STAND + getDirection());
                setAction(SIT);
            }
        }
        if (getDirection()==2 || getDirection()==3 || getDirection()==4){
            if (getX() == GamePanel.WIDTH - 16){
                countingTime = false;
                limit = rand.nextInt(20000)+1000;
                setCountingTime();
                setAction(STAND + getDirection());
                setAction(SIT);
            }
        }
        setCountingTime();
    }

    //when we click on cat, it will do something
    public void catDoSomething(){
        Random rand = new Random();
        //cat sleep -> sit
        if (getCurentAction() == SLEEP) {
            countingTime = false;
            limit = rand.nextInt(20000)+1000;
            setCountingTime();
            setAction(SIT);
            return;
        }
        //cat sit -> 2 choice randome (walk or scratch)
        //if choice is walk -> walk randomly, when it get to the edge, it will sit again
        if (getCurentAction() == SIT || getCurentAction() == REVERSE_SIT) {
            int choice = rand.nextInt(2);
            if (choice == 0) {
                countingTime = false;
                limit = rand.nextInt(10000)+1000;
                setCountingTime();
                walking();
            } else {
                setAction(SCRATCH);
                countingTime = false;
                limit = rand.nextInt(20000)+1000;
                setCountingTime();
            }
            return;
        }
        //cat walking -> sit    
        if (getCurentAction() >= WALK && getCurentAction() <= WALK+7) {
            countingTime = false;
            limit = rand.nextInt(20000)+1000;
            setCountingTime();
            setAction(STAND + getDirection());
            setAction(SIT);
            return;
        }
    }

    public void update(){
        // long finish = System.currentTimeMillis();
        // long timeElapsed = finish - start;
        // System.out.println(countingTime+" "+timeElapsed+" "+limit);
        int slow = 120;
        int fast = 60;
        //set animation
        if(nextAction == SLEEP){
            if(currentAction == SLEEP){
                if(animation.hasPlayedOnce()){
                    animation.setFrames(sprites.get(SLEEP));
                }
            }
            if(currentAction == SIT || currentAction == REVERSE_SIT){
                currentAction = SLEEP;
                animation.setFrames(sprites.get(SIT_TO_SLEEP));
                animation.setDelay(slow);
                
            }
        }
    
        if(nextAction == SIT){
            if(currentAction == SIT) {
                //if it has been play once, set the animation to reverse sit
                if(animation.hasPlayedOnce()){
                    currentAction = REVERSE_SIT;
                    animation.setFrames(sprites.get(REVERSE_SIT));
                    animation.setDelay(slow);
                }
                setCountingTime();
            }
            if(currentAction == REVERSE_SIT){
                if(animation.hasPlayedOnce()){
                    currentAction = SIT;
                    animation.setFrames(sprites.get(SIT));
                    animation.setDelay(slow);
                }
                setCountingTime();
            }
            if(currentAction == SLEEP){
                currentAction = SIT;
                animation.setFrames(sprites.get(SLEEP_TO_SIT));
                animation.setDelay(slow);
            }
            if(currentAction >= STAND && currentAction <= STAND+7){
                currentAction = SIT;
                animation.setFrames(sprites.get(STAND_TO_SIT));
                animation.setDelay(slow);
            }
            if(currentAction == SCRATCH){
                currentAction = SIT;
                animation.setFrames(sprites.get(SIT));
                animation.setDelay(slow);
            }
        }

        if(nextAction >= STAND && nextAction <= STAND + 7){
            if(currentAction >= STAND && currentAction <= STAND + 7){
                if(animation.hasPlayedOnce()){
                    animation.setFrames(sprites.get(STAND + currentDirection));
                }
            }
            if(currentAction == SIT || currentAction == REVERSE_SIT){
                currentAction = STAND + currentDirection;
                animation.setFrames(sprites.get(SIT_TO_STAND));
                animation.setDelay(fast);
            }
            if(currentAction >= WALK && currentAction <= WALK + 7){
                currentAction = STAND + currentDirection;
                animation.setFrames(sprites.get(STAND + currentDirection));
                animation.setDelay(fast);
            }
        }

        if(nextAction >= WALK && nextAction <= WALK+7){
            if(currentAction >= WALK && currentAction <= WALK+7) {}
            if(currentAction == SIT || currentAction == REVERSE_SIT){
                currentAction = WALK + currentDirection;
                animation.setFrames(sprites.get(WALK + currentDirection));
                animation.setDelay(fast);
            }
            if(currentAction >= STAND && currentAction <= STAND+7){
                currentAction = WALK + currentDirection;
                animation.setFrames(sprites.get(WALK + currentDirection));
                animation.setDelay(fast);
            }
        }

        if(nextAction == SCRATCH){
            if(currentAction == SCRATCH){
                if(animation.hasPlayedOnce()){
                    setAction(SIT);
                    animation.setFrames(sprites.get(SIT));
                    animation.setDelay(slow);
                }
            }

            if(currentAction == SIT || currentAction == REVERSE_SIT){
                currentAction = SCRATCH;
                animation.setFrames(sprites.get(SIT_TO_SCRATCH));
                animation.setDelay(slow);
            }
        }
        
        animation.update();

        // System.out.println("current action " + currentAction + " currentFrame: " + animation.getFrame() 
        // + " " + currentAction + " " + animation.hasPlayedOnce() + " "+ animation.getLength());
    }
    
}
