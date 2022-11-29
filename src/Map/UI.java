package Map;

import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import Entity.*;

public class UI {

    private BufferedImage coin, food, cats;
    private Font font;
    private Cat cat;

    private Map map;

    public UI(Cat cat)  {
        cat = cat;
        try {
            coin = ImageIO.read(getClass().getResourceAsStream("/UI/coin_store.png"));
            food = ImageIO.read(getClass().getResourceAsStream("/UI/food_store.png"));
            cats = ImageIO.read(getClass().getResourceAsStream("/UI/cat_num.png"));

            font = new Font("Source Code Pro", Font.PLAIN, 10);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g)    {
        g.drawImage(coin, 5, 6, null);
        g.drawImage(food, 50, 6, null);
        g.drawImage(cats, 95, 6, null);

        g.setFont(font);
        g.setColor(Color.BLACK);

        g.drawString("100", 20, 17);
    }
}
