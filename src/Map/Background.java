package Map;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

import Main.GamePanel;

public class Background {
    private BufferedImage image;

    private double x;
    private double y;
    private double dx;
    private double dy;

    private double moveScale;//how much the background moves relative to the foreground

    public Background(String s, double ms){
        try{
            image = ImageIO.read(getClass().getResourceAsStream(s));
            moveScale = ms;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setPosition(double x, double y){
        this.x = (x * moveScale) % GamePanel.WIDTH;
        this.y = (y * moveScale) % GamePanel.HEIGHT;
    }
    public void setVector(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }
    public void update(){
        x += dx;
        y += dy;
    }
    public void draw(Graphics2D g){

        //print bug
        if(g == null){
            System.out.println("graphics is null");
            System.exit(0);
        }

        g.drawImage(image, (int)x, (int)y, null);

        if(x < 0){
            g.drawImage(image, (int)x + GamePanel.WIDTH, (int)y, null);
        }//if the background is moving to the left, draw another background to the right of it
        if(x > 0){
            g.drawImage(image, (int)x - GamePanel.WIDTH, (int)y, null);
        }//if the background is moving to the right, draw another background to the left of it
    }

}