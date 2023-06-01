package Map;

import java.awt.image.*;
import javax.imageio.*;
import java.awt.Graphics2D;

import Main.GamePanel;

public class Line98 {
    
    private BufferedImage p1, p2;

    //position
    private double x;
    private double y;
    private int sizeX = 15;
    private int sizeY = 15;
    boolean state;

    public Line98(double x, double y, boolean initstate){
        try{
            this.state = initstate;
            this.x = x;
            this.y = y;
            
            p1 = ImageIO.read(getClass().getResourceAsStream("/UI/p1.png"));
            p2 = ImageIO.read(getClass().getResourceAsStream("/UI/p2.png"));
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
            g.drawImage(p1, (int)x,(int)y, null);
        }
        else    {            
            g.drawImage(p2, (int)x,(int)y, null);
        }
    }
}