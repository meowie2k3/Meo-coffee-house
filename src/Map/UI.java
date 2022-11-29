package Map;

import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import GameState.IngameState;

public class UI {

    private BufferedImage coin, food, cats;
    private Font font;

    public UI()  {
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
        g.drawImage(coin, 5, 4, null);
        g.drawImage(food, 50, 4, null);
        g.drawImage(cats, 95, 4, null);

        g.setFont(font);
        g.setColor(Color.BLACK);

        //MONEY        
        //g.drawString("100", 20, 14);
        g.drawString(IngameState.map.getMoney() + "", 20, 16);
        //FOOD
        //g.drawString("100", 65, 14);
        g.drawString(IngameState.map.getFood() + "", 65, 14);
        //NUMBER OF CATS        
        //g.drawString("100", 110, 14);
        g.drawString(IngameState.map.getCatNum() + "", 110, 14);
    }
}
