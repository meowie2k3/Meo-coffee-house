package Map;

import java.awt.image.*;
import Main.*;

public class Furniture {

    private BufferedImage image;
    private int x;
    private int y;
    
    //constructor
    public Furniture(BufferedImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }
    //overwritten constructor just in case :>
    public Furniture(){

    }
    //setters and getters
    public BufferedImage getImage(){
        return image;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }
    public int getWidth(){
        return image.getWidth();
    }
    public int getHeight(){
        return image.getHeight();
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
}
