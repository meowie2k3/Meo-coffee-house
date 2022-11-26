package Entity;

import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;

public class UI {

    private BufferedImage coin;
    private Font font;
    private Cat cat;

    public UI(Cat cat)  {
        cat = cat;
        try {
            coin = ImageIO.read(getClass().getResourceAsStream("/Coin/coin_store.png"));

            font = new Font("Source Code Pro",Font.PLAIN, 10);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g)    {
        g.drawImage(coin, 5, 6, null);

        g.setFont(font);
        g.setColor(Color.BLACK);

        g.drawString("100", 20, 17);
    }
}
