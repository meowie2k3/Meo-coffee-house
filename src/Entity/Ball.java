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
    

    public Ball(int color_id, int x_pos, int y_pos){
        try{
            img = ImageIO.read(getClass().getResourceAsStream(color_address[color_id-1]));
            x = x_pos; y = y_pos;
            level = 0;
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public void move(String direction){
        char d;
        for (int i = 0; i < direction.length(); i++) {
            d = direction.charAt(i);
            switch (d) {
                case 'U':
                    x--;

                    break;
                case 'D':
                    x++;

                    break;
                case 'L':  
                    y--;

                    break;
                case'R':
                    y++;
                    
                    break;
            }

        }
            

    }

    public void draw(Graphics2D g){
        g.drawImage(img, x * LineGameState.BALL_SIZE,
         y * LineGameState.BALL_SIZE, null);
        
    }
}
