package Map;

import java.awt.image.*;

public class Furniture {

    private BufferedImage image;
    private int type;
    private String name;
    private String function;

    //block type
    public static final int BLOCKED = 0;
    public static final int NORMAL = 1;

    //constructor
    public Furniture(BufferedImage image){
        this.image = image;
        this.type = type;
    }
    //overwritten constructor just in case :>
    public Furniture(){

    }

    //setters and getters
    public BufferedImage getImage(){
        return image;
    }
    public int getType(){
        return type;
    }
    public String name(){
        return name;
    }
    public String function(){
        return function;
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }
    public void setType(int type){
        this.type = type;
    }
}
