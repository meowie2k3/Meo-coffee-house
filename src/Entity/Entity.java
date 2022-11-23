package Entity;

import java.awt.event.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.*;

import Map.*;
import Main.*;

public abstract class Entity {
    //block properties
    protected Map map;
    protected int catSize;
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

    //constructor
    public Entity(Map map){
        this.map = map;
        catSize = map.getCatSize();
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
    //check if x and y is on object
    public boolean contains(int x, int y){
        int scale = GamePanel.SCALE;
        if(x >= this.getX() * scale - this.getWidth() * scale / 2 && 
        x <= this.getX() * scale + this.getWidth() * scale / 2 && 
        y >= this.getY() * scale - this.getHeight() * scale / 2 && 
        y <= this.getY() * scale + this.getHeight() * scale / 2)
        {
            return true;
        }
        else return false;
    }

    //command
    public void move(int direction){
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
