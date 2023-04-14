package Entity;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.awt.*;

import GameState.LineGameState;

public class Ball {
    private int x;
    private int y;
    private BufferedImage img;
    private boolean isClicked;
    private int level;

    

    public Ball(String address){
        try{
            img = ImageIO.read(getClass().getResourceAsStream(address));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }


    public void move(String direction){

    }

    public void draw(Graphics2D g){
        g.drawImage(img, x * LineGameState.BALL_SIZE,
         y * LineGameState.BALL_SIZE, null);
    }
}
