package Entity;

import java.awt.event.*;
import java.awt.*;

import Map.*;
import Main.*;

public abstract class Entity {
    //block properties
    protected Map map;
    protected int blockSize;
    protected double xmap;
    protected double ymap;

    //position and direction
    protected double x;
    protected double y;
    protected int currentDirection=3;




    //dimensions
    protected int width;
    protected int height;

    //animation
    protected Animation animation;
    protected int currentAction;
    protected int nextAction;
    protected int previousAction;//just in case
    protected int commandAction;//command for next action

    //movement
    protected final int leftDown = 0;
    protected final int down = 1;
    protected final int rightDown = 2;
    protected final int right = 3;
    protected final int rightUp = 4;
    protected final int up = 5;
    protected final int leftUp = 6;
    protected final int left = 7;

    //movement physics :>
    protected double moveSpeed;
    protected double dx;
    protected double dy;
    protected double maxSpeed;//accelerating speed
    protected double stopSpeed;//deaccelerating speed

    //constructor
    public Entity(Map map){
        this.map = map;
        blockSize = map.getBlockSize();
    }

    //getters
    public int getX(){
        return (int)x;
    }
    public int getY(){
        return (int)y;
    }
    public int getDirection(){
        return currentDirection;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    //setters
    //regular postion
    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }
    public void setDirection(int direction){
        this.currentDirection = direction;
    }
    //map position (for drawing)
    public void setMapPosition(){
        xmap = map.getX();
        ymap = map.getY();
    }

    //check if the entity is on the map
    public boolean notOnScreen(){
        return 
        x + xmap + width < 0 
        || x + xmap - width > GamePanel.WIDTH 
        || y + ymap + height < 0 
        || y + ymap - height > GamePanel.HEIGHT;
    }

}
