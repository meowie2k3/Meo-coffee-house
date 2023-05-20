package Map;

import java.awt.image.*;
import javax.imageio.*;
import java.awt.Graphics2D;

import Main.GamePanel;

public class Shop {
    
    private BufferedImage s1, s2;

    //position
    private double x;
    private double y;
    private int sizeX = 15;
    private int sizeY = 16;
    boolean state;

    public Shop(double x, double y, boolean initstate){
        try{
            this.state = initstate;
            this.x = x;
            this.y = y;
            
            s1 = ImageIO.read(getClass().getResourceAsStream("/UI/s1.png"));
            s2 = ImageIO.read(getClass().getResourceAsStream("/UI/s2.png"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public int getWidth(){
        return sizeX;
    }
    public int getHeight(){
        return sizeY;
    }
    
    public boolean contains(int x, int y){
        int scale = GamePanel.SCALE;
        if (x >= this.x * scale
        && x <= (this.x + sizeX) * scale
        && y >= this.y * scale
        && y <= (this.y + sizeY) * scale)   {
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g){
        if (state == true)  {            
            g.drawImage(s1, (int)x,(int)y, null);
        }
        else    {            
            g.drawImage(s2, (int)x,(int)y, null);
        }
    }
}