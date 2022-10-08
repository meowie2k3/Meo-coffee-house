package Map;

import java.awt.image.*;

public class Block {

    private BufferedImage image;
    private int type;
    private String name;
    private String function;

    //block type
    public static final int BLOCKED = 0;
    public static final int NORMAL = 1;
    //future use
    public static final int deco = 0; // carpet, floor type, walkthrough;
    public static final int Chair = 1;
    public static final int table =2;
    public static final int sink = 3;
    public static final int stove = 4;

    public static final int fridge = 5;
    public static final int wall = 6;

    //constructor
    public Block(BufferedImage image, int type){
        this.image = image;
        this.type = type;
    }
    //overwritten constructor just in case :>
    public Block(){}

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
