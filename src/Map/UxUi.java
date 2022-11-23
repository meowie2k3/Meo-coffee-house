package Map;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class UxUi {

    private int value;
    private String name;
    private BufferedImage icon;

    // constructor
    public UxUi(String name, String s, int value) {
        try {
            this.name = name;
            this.value = value;
            this.icon = ImageIO.read(getClass().getResourceAsStream(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        if(g == null){
            System.out.println("graphics is null");
            System.exit(0);
        }
        //draw white rectangle pad for text
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 100, 20);
        //draw icon
        g.drawImage(icon, 0, 0, null);
        //write name
        g.setColor(Color.BLACK);
        g.drawString(name + ": ", 20, 10);
        //write value
        g.drawString(value + "", 20, 20);
    }
    

}
