package Entity;
import java.awt.image.BufferedImage;
import javax.imageio.*;

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
}
