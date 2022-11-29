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
    protected int charSize;
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
    public final int leftDown = 0;
    public final int down = 1;
    public final int rightDown = 2;
    public final int right = 3;
    public final int rightUp = 4;
    public final int up = 5;
    public final int leftUp = 6;
    public final int left = 7;

    //movement physics :>
    protected double moveSpeed;

    //constructor
    public Entity(Map map){
        this.map = map;
        catSize = map.getCatSize();
        charSize = map.getcharacterNum();
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
    public int getCurentAction(){
        return currentAction;
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
        //System.out.println(getX()+" "+getY());
        if ((getX() >= (0 + 16) && getX() <= (GamePanel.WIDTH - 16)) && (getY() >= (0 + 16.0) && getY() <= (GamePanel.HEIGHT - 16.0))) {
            //System.out.println("moving");
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


// for Character: 1 0 2 2 2 3 1 1