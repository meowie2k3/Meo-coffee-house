package Entity;

import Map.*;

import java.util.ArrayList;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;

public class Character extends Entity {
    //cat properties
    private String name;
    
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
    // private static final int DOWN = 1;
    // private static final int LEFT = 2;
    // private static final int RIGHT = 3;
    // private static final int UP = 4;

    //constructor
    public Character(Map map, String address){
        super(map);

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

            int[] myNum = {1, 0, 2, 2, 2, 3, 1, 1};
            for(int k= 0; k < 8; k++){
                int i = myNum[k];
                BufferedImage[] walk = new BufferedImage[8];
                tmp = 0;
                for(int j= 0;j < 3;j++){
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
        // animation.setDelay(100);
    }

    public int getDirection(){
        return currentDirection;
    }
    public int getCurentAction(){
        return currentAction;
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
        setAction(WALK);
        super.move(direction);
    }

    public void update(){

        int slow = 120;
        int fast = 60;
        //set animation
        if(nextAction == STAND){
            if(currentAction == STAND){
                if(animation.hasPlayedOnce()){
                    animation.setFrames(sprites.get(STAND));
                }
            }
            
            if(currentAction == WALK){
                setAction(STAND);
                animation.setFrames(sprites.get(STAND));
                animation.setDelay(slow);
            }
        }
    
        if(nextAction == WALK){
            if(currentAction == WALK) {}
            if(currentAction == STAND){
                currentAction = WALK;
                animation.setFrames(sprites.get(WALK + currentDirection));
                animation.setDelay(fast);
            }
        }

        animation.update();

        // System.out.println("current action " + currentAction + " currentFrame: " + animation.getFrame() 
        // + " " + currentAction + " " + animation.hasPlayedOnce() + " "+ animation.getLength());
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
