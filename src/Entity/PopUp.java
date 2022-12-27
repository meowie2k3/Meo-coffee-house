package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Map.*;

public class PopUp {
    private int x;
    private int y;
    private BufferedImage icon;
    private BufferedImage whitePad;

    private final int spacing = 10;
    
    public PopUp(int x, int y, String address){
        this.x = x;
        this.y = y;
        try{
            icon = ImageIO.read(getClass().getResourceAsStream(address));
            whitePad = ImageIO.read(getClass().getResource("/UI/whitePad.png"));

        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public void draw(Graphics2D g){
        
    }


}
