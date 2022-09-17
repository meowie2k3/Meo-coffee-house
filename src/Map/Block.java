package Map;

import java.awt.image.*;

public class Block {

    private BufferedImage image;
    private int type;

    //block type
    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;

    //constructor
    public Block(BufferedImage image, int type){
        this.image = image;
        this.type = type;
    }
    //setters and getters
    public BufferedImage getImage(){
        return image;
    }
    public int getType(){
        return type;
    }
}