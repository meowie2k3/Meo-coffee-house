package Entity;

import Map.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.*;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.*;

public class characterv2 extends Entity {
    //characterv2 properties
    private String name;
    private String address;
        
    // //emotion
    // private boolean isHungry;
    // private boolean isSleepy;
    // private boolean isBored;

    // //cost
    // private int foodCost;
    // private int sleepCost;
    // private int playCost;

    //animation
    private ArrayList<BufferedImage[]> sprites;

    //animation actions

    private static final int STAND = 0;
    private static final int WALK = 1;

    //constructor
    public characterv2(Map map, String address){
        super(map);
        this.address = address;

        //size for reading spritesheet
        width = 48;
        height = 48;

        //physics
        moveSpeed = 1;

        //hash characterv2 animation image 
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream(address));
            sprites = new ArrayList<BufferedImage[]>();
            int tmp;

            //get stand animation
            BufferedImage[] stand = new BufferedImage[1];
            tmp = 0;
            stand[tmp]= spritesheet.getSubimage(
                1 * width, 
                0 * height,
                width, 
                height);
            sprites.add(stand);

            //get walk animation
            int[] myNum = {1, 0, 2, 2, 2, 3, 1, 1};
            for(int k= 0; k < 8; k++){
                int i = myNum[k];
                BufferedImage[] walk = new BufferedImage[3];
                tmp = 0;
                for (int j= 0; j < 3; j++) {
                    walk[tmp] = spritesheet.getSubimage(
                        j * width, 
                        i * height, 
                        width, 
                        height);
                    tmp++;
                }
                sprites.add(walk);
            }
            


        } catch(Exception e) {
            e.printStackTrace();
        }
        animation = new Animation();
        currentAction = STAND;
        nextAction = STAND;
        animation.setFrames(sprites.get(currentAction));
        animation.setDelay(100);
    }

    //getters
    public String getName(){
        return name;
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
    public void setDirection(int i){
        currentDirection = i;
    }
    public void setAction(int i){
        nextAction = i;
        update();
    }
    
    @Override
    public void move(int direction, int halfsize){
        setDirection(direction);
        setAction(WALK + direction);
        super.move(direction, halfsize);
    }

    public void walkingOut() {
        
    }

    // way == 0: characterv2 goes in
    // way == 1: characrer goes out
    public int way = 0;

    public void walkingIn(int order){
        int finalX, finalY;
        if (way == 0) {
            if (order == 0) {finalX = 170; finalY = 75;} 
            else if (order == 1) {finalX = 210; finalY = 75;} 
            else if (order == 2) {finalX = 260; finalY = 75;}
            else {finalX = 310; finalY = 75;}
        }
        else {
            if (order == 0) {finalX = 24; finalY = 130;} 
            else if (order == 1) {finalX = 24; finalY = 150;} 
            else if (order == 2) {finalX = 24; finalY = 170;}
            else {finalX = 24; finalY = 190;}
        }
        
        // horizontal
        if (getX() == finalX && getY() > finalY) {
            // getting in
            if (way == 0) {
                // move up
                setDirection(5);
                setAction(WALK + getDirection());
                return;
            }
            // getting out
            else {
                // move down
                // setDirection(1);
                // setAction(WALK + getDirection());
                // return;
            }
        }

        if (getX() > finalX && getY() == finalY) {
            if (way == 1) {
                // move down
                setDirection(7);
                setAction(WALK + getDirection());
                return;
            }
        }
        
        if (getX() < finalX && getY() > finalY) {
            if (way == 0) {
                setDirection(3);
                setAction(WALK + getDirection());
                return;
            }
            else {
                setDirection(7);
                setAction(WALK + getDirection());
                return;
            }
            
        }

        if (getX() == finalX && getY() == finalY) {
            // setAction(STAND);
            
            // try {
            //     TimeUnit.SECONDS.sleep(1);
            // } catch (InterruptedException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            // }

            way = 1;
            setDirection(1);
            setAction(WALK + getDirection());

            return;
        }

        if (getX() < 0 && getY() > finalY) {
            setAction(STAND);
            return;
        }


        

    }

    // //timing
    // private boolean countingTime = false;
    // private long start = 0;
    // private long limit = 0;
    // public boolean getCountingTime(){
    //     return countingTime;
    // }
    // public void setCountingTime(){
    //     if (countingTime){
    //         long finish = System.currentTimeMillis();
    //         long timeElapsed = finish - start;
    //         //System.out.println(timeElapsed + " " + limit);
    //         if (getCurentAction() >= WALK && getCurentAction() <= WALK+7) {
    //             if (timeElapsed > limit){
    //                 countingTime = false;
    //                 limit = 3000;
    //                 setCountingTime();
    //                 setAction(STAND);
    //                 return;
    //             }   
    //         }
    //         // if (getCurentAction() == STAND) {
    //         //     if (timeElapsed > limit){
    //         //         countingTime = false;
    //         //         setAction(STAND);
    //         //         return;
    //         //     }   
    //         // }
    //     }
    //     else{
    //         start = System.currentTimeMillis();
    //         countingTime = true;
    //     }
    // }

    // when characterv2 is walking
    // public void bounding(){
    //     Random rand = new Random();
    //     if (getDirection() == 0 || getDirection() == 1 || getDirection() == 2){
    //         if (getY() == GamePanel.HEIGHT - 24){
    //             countingTime = false;
    //             limit = rand.nextInt(20000)+1000;
    //             setCountingTime();
    //             setAction(STAND);
    //         }
    //     }
    //     if (getDirection() == 0 || getDirection() == 7 || getDirection() == 6){
    //         if (getX() == 0 + 24){
    //             countingTime = false;
    //             limit = rand.nextInt(20000)+1000;
    //             setCountingTime();
    //             setAction(STAND);
    //         }
    //     }
    //     if (getDirection() == 4 || getDirection() == 5 || getDirection() == 6){
    //         if (getY() == 0 + 24){
    //             countingTime = false;
    //             limit = rand.nextInt(20000)+1000;
    //             setCountingTime();
    //             setAction(STAND);
    //         }
    //     }
    //     if (getDirection() == 2 || getDirection() == 3 || getDirection() == 4){
    //         if (getX() == GamePanel.WIDTH - 24){
    //             countingTime = false;
    //             limit = rand.nextInt(20000)+1000;
    //             setCountingTime();
    //             setAction(STAND);
    //         }
    //     }
    //     setCountingTime();
    // }

    //when we click on characterv2, it will do something
    public void charDoSomething(){
        Random rand = new Random();
        //characterv2 stand -> move

        walkingIn(1);
        return;

        // if (getCurentAction() == STAND) {
        //     countingTime = false;
        //     limit = rand.nextInt(10000)+1000;
        //     setCountingTime();
        //     walking();
        //     return;
        // }
        // //characterv2 walking -> stand    
        // if (getCurentAction() >= WALK && getCurentAction() <= WALK+7) {
        //     countingTime = false;
        //     limit = rand.nextInt(20000)+1000;
        //     setCountingTime();
        //     setAction(STAND);
        //     return;
        // }
    }

    public void update(){
        int slow = 120;
        int fast = 60;
        //set animation
        if(nextAction == STAND){
            // if(currentAction == STAND){
            //     if(animation.hasPlayedOnce()){
            //         animation.setFrames(sprites.get(STAND));
            //         animation.setDelay(slow);
            //     }
            //     // setCountingTime();
            // }
            
            if(currentAction >= WALK && currentAction <= WALK+7){
                currentAction = STAND;
                animation.setFrames(sprites.get(STAND));
                animation.setDelay(slow);
            }
        }
    
        if(nextAction >= WALK && nextAction <= WALK+7){
            if(currentAction >= WALK && currentAction <= WALK+7) {}
            if(currentAction == STAND){
                currentAction = WALK + currentDirection;
                animation.setFrames(sprites.get(WALK + currentDirection));
                animation.setDelay(slow);
            }
        }

        animation.update();
    }

    

    
}
