package Map;

import java.awt.image.*;

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
}
