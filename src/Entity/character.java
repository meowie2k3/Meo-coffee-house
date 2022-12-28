package Entity;

import Map.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.*;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.*;

public class character extends Entity {
    //character properties
    private String name;
    private String address;

    private PopUp popup;
        
    // //emotion
    // private boolean isHungry;
    // private boolean isSleepy;
    // private boolean isBored;

    // //cost
    // private int foodCost;
    // private int sleepCost;
    // private int playCost;

    private final int iconSize = 16;
    private final int padSize = 24;
    private final int charSize = 48;
    private final int spacing = 5;

    //animation
    private ArrayList<BufferedImage[]> sprites;

    //animation actions

    private static final int STAND = 0;
    private static final int WALK = 1;

    //constructor
    public character(Map map, String address){
        super(map);
        this.address = address;

        //size for reading spritesheet
        width = 48;
        height = 48;

        //physics
        moveSpeed = 0.8;

        //hash character animation image 
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
    public int getfinalX() {
        return finalX;
    }
    public int getfinalY() {
        return finalY;
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

    // way == 0: character goes in
    // way == 1: characrer goes out
    public int way = 0;
    public int finalX, finalY;

    public  void  walkingIn(int order){
        
        if (way == 0) {
            if (order == 0) {finalX = 170; finalY = 75;} 
            else if (order == 1) {finalX = 200; finalY = 75;} 
            else if (order == 2) {finalX = 230; finalY = 75;}
            else {finalX = 260; finalY = 75;}
            
            // get in and move up
            if (getX() == finalX && getY() > finalY) {
                setDirection(5);
                setAction(WALK + getDirection());
                return;
                
            }

            // get in and move right
            if (getX() < finalX && getY() > finalY) {
                setDirection(3);
                setAction(WALK + getDirection());
                return;
            }

            // arrived the position
            if (getX() == finalX && getY() == finalY) {
                // setAction(STAND);
                way = 1;
                // setDirection(1);
                // setAction(WALK + getDirection());
                return;   
            }
            
            // if (getX() ==  finalX && getY() == finalY) {
            //     // get out and move down
                
            // }
            
        }

        else {
            if (order == 0) {finalX = 24; finalY = 130;} 
            else if (order == 1) {finalX = 24; finalY = 150;} 
            else if (order == 2) {finalX = 24; finalY = 170;}
            else {finalX = 24; finalY = 190;}

            // get out and move down
            if (getX() > finalX && getY() < finalY) {
                setDirection(1);
                setAction(WALK + getDirection());
                return;   
                
            }

            // get out and move left
            if (getX() > finalX && getY() == finalY) {                
                setDirection(7);
                setAction(WALK + getDirection());
                return;
            }

            // arrive the initial place
            if (getX() == finalX && getY() == finalY) {
                setAction(STAND);
                // way = 0;
                // setDirection(1);
                // setAction(WALK + getDirection());
                return;   
            }

        }
        // try {
        //     TimeUnit.SECONDS.sleep(1);
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

    }

    public int tempDirection = -1;
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
            
            if(currentAction >= WALK && currentAction <= WALK+7) {
                if (tempDirection != currentDirection) {
                    animation.setFrames(sprites.get(WALK + currentDirection));
                    animation.setDelay(slow);
                    tempDirection = currentDirection;
                }
                
            }
            if(currentAction == STAND){
                currentAction = WALK + currentDirection;
                animation.setFrames(sprites.get(WALK + currentDirection));
                animation.setDelay(slow);
            }
        }

        animation.update();
    }

    public void clearPopUp(){
        popup = null;
    }

    public boolean containPopup(int x, int y){
        if(x > this.x - iconSize/2 && x < this.x + iconSize/2){
            if(y > this.y - charSize / 2 - spacing - padSize/2 - iconSize/2 && y < this.y - charSize / 2 - spacing - padSize/2 + iconSize/2){
                return true;
            }
        }
        return false;
    }

    public void payMoney() {
        popup = new PopUp(this.x, this.y, "/UI/coin.jpg");
    }

    @Override
    public void draw(Graphics2D g){
        super.draw(g);
        if(popup!=null){
            popup.draw(g);
        }
    }
    

    
}
