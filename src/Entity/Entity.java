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

    //position and vector
    protected double x;
    protected double y;//position
    protected double dx;
    protected double dy;//direction

    //dimensions
    protected int width;
    protected int height;

    //animation
    protected Animation animation;
    protected int currentAction;
    protected int previousAction;//just in case
    protected int commandAction;//command for next action

    //movement
    protected boolean downleft;
    protected boolean down;
    protected boolean downRight;
    protected boolean right;
    protected boolean upRight;
    protected boolean up;
    protected boolean upLeft;
    protected boolean left;

    //movement physics :>
    protected double moveSpeed;
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
    public void setVector(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }
    //map position (for drawing)
    public void setMapPosition(){
        xmap = map.getX();
        ymap = map.getY();
    }

    //setters for movement
    public void setDownLeft(boolean b){
        downleft = b;
    }
    public void setDown(boolean b){
        down = b;
    }
    public void setDownRight(boolean b){
        downRight = b;
    }
    public void setRight(boolean b){
        right = b;
    }
    public void setUpRight(boolean b){
        upRight = b;
    }
    public void setUp(boolean b){
        up = b;
    }
    public void setUpLeft(boolean b){
        upLeft = b;
    }
    public void setLeft(boolean b){
        left = b;
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
