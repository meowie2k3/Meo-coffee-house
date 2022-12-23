package Map;

import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import GameState.IngameState;

public class UI {

    private BufferedImage coin, food, cats;
    private Font font;

    private JButton shop;

    public UI()  {
        try {
            coin = ImageIO.read(getClass().getResourceAsStream("/UI/coin_store.png"));
            food = ImageIO.read(getClass().getResourceAsStream("/UI/food_store.png"));
            cats = ImageIO.read(getClass().getResourceAsStream("/UI/cat_num.png"));

            font = new Font("Source Code Pro", Font.PLAIN, 9);

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
        int spacing = 1;
        //MONEY        
        //g.drawString("100", 20, 14);
        g.drawString(IngameState.map.getMoney() + "", 20+spacing, 15);
        //FOOD
        //g.drawString("100", 65, 14);
        g.drawString(IngameState.map.getFood() + "", 65+spacing, 15);
        //NUMBER OF CATS        
        //g.drawString("100", 110, 14);
        g.drawString(IngameState.map.getCatNum() + "", 110+spacing, 15);
    }
}
