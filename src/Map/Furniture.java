package Map;

import java.awt.image.*;

public class Furniture {

    private BufferedImage image;
    private String name;
    //constructor
    public Furniture(String name, BufferedImage image){
        this.image = image;
    }
    //overwritten constructor just in case :>
    public Furniture(){

    }
    //setters and getters
    public BufferedImage getImage(){
        return image;
    }
    public String getName(){
        return name;
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }
}
