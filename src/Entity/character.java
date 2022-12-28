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

    // drink options
    // 24 options in total
    private static final String[] drinkAddress = new String [] {
        "/Drinks/blackberry and greek yogurt iced tea.png",
        "/Drinks/breve.png",
        "/Drinks/Cafe affogato.png",
        "/Drinks/cafe au laite.png",
        "/Drinks/cafe expresso.png",
        "/Drinks/cappucinno.png",
        "/Drinks/chocolate milkshake.png",
        "/Drinks/coffee pack.png",
        "/Drinks/cream milkshake.png",
        "/Drinks/frapuccino.png",
        "/Drinks/french press.png",
        "/Drinks/green apple iced tea.png",
        "/Drinks/hot chocolate.png",
        "/Drinks/iced black tea with lemon.png",
        "/Drinks/iced capuccino.png",
        "/Drinks/iced chocolate.png",
        "/Drinks/iced coffee.png",
        "/Drinks/mocha frape.png",
        "/Drinks/orange iced tea.png",
        "/Drinks/plastic coffee bottle.png",
        "/Drinks/strawberry iced tea.png",
        "/Drinks/strawberry milkshake.png",
        "/Drinks/warm milk.png",
        "/Drinks/warm tea.png"
    };

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
        switch(direction){
            case leftDown:
                x -= moveSpeed;
                y += moveSpeed;
                break;
            case down:
                y += moveSpeed;
                break;
            case rightDown:
                x += moveSpeed;
                y += moveSpeed;
                break;
            case right:
                x += moveSpeed;
                break;
            case rightUp:
                x += moveSpeed;
                y -= moveSpeed;
                break;
            case up:
                y -= moveSpeed;
                break;
            case leftUp:
                x -= moveSpeed;
                y -= moveSpeed;
                break;
            case left:
                x -= moveSpeed;
                break;
            default:
                System.out.println("Invalid direction");
                break;
        }
    }
    
    public int finalX, finalY;
    public int way ;

    public void walkingOut(int order) {
        clearPopUp();
        if (order == 0) {finalX = -24; finalY = 130;} 
        else if (order == 1) {finalX = -24; finalY = 150;} 
        else if (order == 2) {finalX = -24; finalY = 170;}
        else {finalX = -24; finalY = 190;}

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
            clearPopUp();
            setAction(STAND);
            return;   
        }

    }


    public  void  walkingIn(int order){
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
            if (popup == null) {
                Random rand = new Random();
                int pos = rand.nextInt(24);
                String chosen = drinkAddress[pos];
                PopUp drink = new PopUp(getX(), getY(), chosen);
                popup = drink;
            }
            
            setAction(STAND);
            setCountingTime();
            return;   
        }

        
    

        
        // try {
        //     TimeUnit.SECONDS.sleep(1);
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

    }

    //timing
    private boolean countingTime = false;
    private long start = 0;
    private long limit = 10000;
    public boolean getCountingTime(){
        return countingTime;
    }
    public void setCountingTime() {
        Random rand = new Random();
        if (countingTime) {
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            // System.out.println(timeElapsed);
            
            if (timeElapsed > limit){
                countingTime = false;
                // setCountingTime();
                setAction(WALK + 1);
                way = 1;
                

                return;
            }
            
        }
        else {
            start = System.currentTimeMillis();
            countingTime = true;
        }
    }

    public int tempDirection = -1;
    public void update(){
        int slow = 120;
        int fast = 60;
        //set animation
        if(nextAction == STAND){
            if(currentAction == STAND){
                if(animation.hasPlayedOnce()){
                    animation.setFrames(sprites.get(STAND));
                    animation.setDelay(slow);
                }
                // setCountingTime();
            }
            
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
