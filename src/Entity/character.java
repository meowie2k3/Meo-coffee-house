package Entity;

import Map.*;

import java.util.ArrayList;
import java.util.Random;

import javax.imageio.*;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.*;

public class character extends Entity {
    //character properties
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
    public character(Map map, String address){
        super(map);
        this.address = address;

        //size for reading spritesheet
        width = 48;
        height = 48;

        //physics
        moveSpeed = 0.3;

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
                for(int j= 0; j < 3; j++){
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

    public void walking(){
        int[][] possibleDirection = { { 1, 2, 3, 4, 5 }, //character at left
                                        { 0, 1, 5, 6, 7 }, //character at right
                                        { 3, 4, 5, 6, 7 }, //character at bottom
                                        { 0, 1, 2, 3, 7 }, //character at top

                                        { 1, 2, 3}, //character at topleft coner
                                        { 0, 1, 7}, //character at topright coner
                                        { 3, 4, 5}, //character at bottomleft coner
                                        { 5, 6, 7}, //character at bottomright coner

                                        { 0, 1, 2, 3, 4, 5, 6, 7 }, //character at center
                                    };
        
        Random rand = new Random();
        //character at topleft coner
        if (getX() == 0 + 24 && getY() == 0 + 24) {
            setDirection(possibleDirection[4][rand.nextInt(3)]);
            //System.out.println("topleft " + characterList.get(i).getDirection() + " currenAction " + characterList.get(i).getCurentAction());
            setAction(WALK + getDirection());
            return;
        }
        //character at topright coner
        if (getX() == GamePanel.WIDTH - 24 && getY() == 0 + 24) {
            setDirection(possibleDirection[5][rand.nextInt(3)]);
            //System.out.println("topright " + characterList.get(i).getDirection() + " currenAction " + characterList.get(i).getCurentAction());
            setAction(WALK + getDirection());
            return;
        }
        //character at bottomleft coner
        if (getX() == 0 + 24 && getY() == GamePanel.HEIGHT - 24) {
            setDirection(possibleDirection[6][rand.nextInt(3)]);
            //System.out.println("bottomleft " + characterList.get(i).getDirection() + " currenAction " + characterList.get(i).getCurentAction());
            setAction(WALK + getDirection());
            return;
        }
        //character at bottomright coner
        if (getX() == GamePanel.WIDTH - 24 && getY() == GamePanel.HEIGHT - 24) {
            setDirection(possibleDirection[7][rand.nextInt(3)]);
            //System.out.println("bottomright " + characterList.get(i).getDirection() + " currenAction " + characterList.get(i).getCurentAction());
            setAction(WALK + getDirection());
            return;
        }
        //character at left
        if (getX() == 0 + 24 && getY() >= 0 + 24 && getY() <= GamePanel.HEIGHT - 24) {
            setDirection(possibleDirection[0][rand.nextInt(5)]);
            //System.out.println("left " + characterList.get(i).getDirection() + " currenAction " + characterList.get(i).getCurentAction());
            setAction(WALK + getDirection());
            return;
        }
        //character at right
        if (getX() == GamePanel.WIDTH - 24 && getY() >= 0 + 24 && getY() <= GamePanel.HEIGHT - 24) {
            setDirection(possibleDirection[1][rand.nextInt(5)]);
            //System.out.println("right " + characterList.get(i).getDirection() + " currenAction " + characterList.get(i).getCurentAction());
            setAction(WALK + getDirection());
            return;
        }
        //character at bottom
        if (getY() == GamePanel.HEIGHT - 24 && getX() >= 0 + 24 && getX() <= GamePanel.WIDTH - 24) {
            setDirection(possibleDirection[2][rand.nextInt(5)]);
            //System.out.println("bottom " + characterList.get(i).getDirection() + " currenAction " + characterList.get(i).getCurentAction());
            setAction(WALK + getDirection());
            return;
        }
        //character at top
        if (getY() == 0 + 24 && getX() >= 0 + 24 && getX() <= GamePanel.WIDTH - 24) {
            setDirection(possibleDirection[3][rand.nextInt(5)]);
            //System.out.println("top " + characterList.get(i).getDirection() + " currenAction " + characterList.get(i).getCurentAction());
            setAction(WALK + getDirection());
            return;
        }
        //character at center
        if (getX() > 0 + 24 && getX() < GamePanel.WIDTH - 24 && getY() > 0 + 24 && getY() < GamePanel.HEIGHT - 24) {
            setDirection(possibleDirection[8][rand.nextInt(8)]);
            //System.out.println("center " + characterList.get(i).getDirection() + " currenAction " + characterList.get(i).getCurentAction());
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
                    setAction(STAND);
                    return;
                }   
            }
            // if (getCurentAction() == STAND) {
            //     if (timeElapsed > limit){
            //         countingTime = false;
            //         setAction(STAND);
            //         return;
            //     }   
            // }
        }
        else{
            start = System.currentTimeMillis();
            countingTime = true;
        }
    }

    //bounding when character is walking
    public void bounding(){
        Random rand = new Random();
        if (getDirection() == 0 || getDirection() == 1 || getDirection() == 2){
            if (getY() == GamePanel.HEIGHT - 24){
                countingTime = false;
                limit = rand.nextInt(20000)+1000;
                setCountingTime();
                setAction(STAND);
            }
        }
        if (getDirection() == 0 || getDirection() == 7 || getDirection() == 6){
            if (getX() == 0 + 24){
                countingTime = false;
                limit = rand.nextInt(20000)+1000;
                setCountingTime();
                setAction(STAND);
            }
        }
        if (getDirection() == 4 || getDirection() == 5 || getDirection() == 6){
            if (getY() == 0 + 24){
                countingTime = false;
                limit = rand.nextInt(20000)+1000;
                setCountingTime();
                setAction(STAND);
            }
        }
        if (getDirection() == 2 || getDirection() == 3 || getDirection() == 4){
            if (getX() == GamePanel.WIDTH - 24){
                countingTime = false;
                limit = rand.nextInt(20000)+1000;
                setCountingTime();
                setAction(STAND);
            }
        }
        setCountingTime();
    }

    //when we click on character, it will do something
    public void charDoSomething(){
        Random rand = new Random();
        //character stand -> move

        if (getCurentAction() == STAND) {
            countingTime = false;
            limit = rand.nextInt(10000)+1000;
            setCountingTime();
            walking();
            return;
        }
        //character walking -> stand    
        if (getCurentAction() >= WALK && getCurentAction() <= WALK+7) {
            countingTime = false;
            limit = rand.nextInt(20000)+1000;
            setCountingTime();
            setAction(STAND);
            return;
        }
    }

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
                setCountingTime();
            }
            
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

    

    // //draw
    // public void draw(Graphics2D g){
    //     setMapPosition();
    //     // System.out.println(x + xmap - width /2);
    //     // System.out.println(y + ymap - height / 2);
        
    //     g.drawImage(
    //         animation.getImage(),
    //         (int)(x + xmap - width /2),
    //         (int)(y + ymap - height / 2),
    //         null
    //     );
    // }

    
}
