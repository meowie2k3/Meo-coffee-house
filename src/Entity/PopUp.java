package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class PopUp {
    //position of object that have pop up
    private double x;
    private double y;
    private BufferedImage icon;
    private BufferedImage whitePad;

    private final int iconSize = 16;
    private final int padSize = 24;
    private final int charSize = 48;

    private final int spacing = 2;
    
    public PopUp(double x, double y, String address){
        this.x = x ;
        this.y = y;
        try{
            icon = ImageIO.read(getClass().getResourceAsStream(address));
            whitePad = ImageIO.read(getClass().getResource("/UI/whitePad.png"));

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean contains(int x, int y){
        int scale = GamePanel.SCALE;
        int x1 = (int) this.x - padSize/2;
        int y1 = (int) this.y - charSize / 4 - spacing - padSize;
        int x2 = x1 + padSize;
        int y2 = y1 + padSize;
        if(x1*scale <= x && x <= x2*scale && y1*scale <= y && y <= y2*scale) return true;
        return false;
    }



    public void draw(Graphics2D g){
        g.drawImage(whitePad,(int) x - padSize/2,
        (int)y - charSize / 2 - spacing/2 - padSize/2, null);
        g.drawImage(icon,(int) x - iconSize/2,
        (int)y - charSize/4 - spacing - padSize/2 - iconSize/2, null);

        
    }


}
