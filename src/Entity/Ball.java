package Entity;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.imageio.*;
import java.awt.*;

import GameState.LineGameState;

public class Ball {
    private int x;
    private int y;
    private BufferedImage img;
    private boolean isClicked;
    private int level = 0;

    private String[] color_address = {

    }; 
    

    public Ball(int color_id){
        try{
            img = ImageIO.read(getClass().getResourceAsStream(color_address[color_id]));

        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public int[] randomPosition() {
        int[] pos = new int[2];
        Random rand = new Random();
        pos[0] = rand.nextInt(9);
        pos[1] = rand.nextInt(9);
        return pos;
    }

    public void move(String direction){

    }

    public void draw(Graphics2D g){
        g.drawImage(img, x * LineGameState.BALL_SIZE,
         y * LineGameState.BALL_SIZE, null);
    }
}
