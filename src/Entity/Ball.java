package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import Main.GamePanel;

public class Ball {
    // position and direction
    protected double x;
    protected double y;

    // animation
    protected Animation animation;

    // movement
    public static final char down = 'D';
    public static final char right = 'R';
    public static final char up = 'U';
    public static final char left = 'L';

    private boolean isClicked_;
    private int level;
    private int colorCode;

    // animation
    // private ArrayList<BufferedImage[]> sprites;
    private BufferedImage[] Lv1;
    private BufferedImage[] Lv2;
    private BufferedImage[] Lv2Clicked;

    private int ballSize = 21;

    private String[] colorAddressLv1 = {
            "/Balls/Lv1/black.png", // id: 1
            "/Balls/Lv1/blue.png", // id: 2
            "/Balls/Lv1/cyan.png", // id: 3
            "/Balls/Lv1/green.png", // id: 4
            "/Balls/Lv1/purple.png", // id: 5
            "/Balls/Lv1/red.png", // id: 6
            "/Balls/Lv1/yellow.png", // id: 7
    };

    private String[] colorAddressLv2 = {
            "/Balls/Lv2/blackC.png", // id: 1
            "/Balls/Lv2/blueC.png", // id: 2
            "/Balls/Lv2/cyanC.png", // id: 3
            "/Balls/Lv2/greenC.png", // id: 4
            "/Balls/Lv2/purpleC.png", // id: 5
            "/Balls/Lv2/redC.png", // id: 6
            "/Balls/Lv2/yellowC.png", // id: 7
    };
    private String[] colorAddressLv2Clicked = {
            "/Balls/Lv2_Clicked/blackC.png", // id: 1
            "/Balls/Lv2_Clicked/blueC.png", // id: 2
            "/Balls/Lv2_Clicked/cyanC.png", // id: 3
            "/Balls/Lv2_Clicked/greenC.png", // id: 4
            "/Balls/Lv2_Clicked/purpleC.png", // id: 5
            "/Balls/Lv2_Clicked/redC.png", // id: 6
            "/Balls/Lv2_Clicked/yellowC.png", // id: 7
    };

    public Ball(int x_pos, int y_pos, int color_id, int lvl) {

        try {
            x = x_pos;
            y = y_pos;
            level = lvl;

            colorCode = color_id;

            BufferedImage img1 = ImageIO.read(getClass().getResource(colorAddressLv1[color_id - 1]));
            BufferedImage img2 = ImageIO.read(getClass().getResource(colorAddressLv2[color_id - 1]));
            BufferedImage img2Clicked = ImageIO.read(getClass().getResource(colorAddressLv2Clicked[color_id - 1]));

            Lv1 = new BufferedImage[1];
            Lv2 = new BufferedImage[1];
            Lv2Clicked = new BufferedImage[1];

            Lv1[0] = img1;
            Lv2[0] = img2;
            Lv2Clicked[0] = img2Clicked;
            
            animation = new Animation();
            animation.setFrames(Lv1);
            isClicked_ = false;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // The move function below will be called in the LinegameState, 
    // by using a loop through each character in the description
    // provided by A-Star algorithm. 
    public void move(String instruction) { 
        for(int i=0;i<instruction.length();i++){

        }
    }

    public void update() {
        if(level==0) animation.setFrames(Lv1);
        else if(isClicked_) animation.setFrames(Lv2Clicked);
        else animation.setFrames(Lv2);

        animation.update();

    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getSize() {
        return ballSize;
    }
    
    public void click(){
        isClicked_ = true;
    }

    public void unclick(){
        isClicked_ = false;
    }

    public boolean isClicked() {
        return isClicked_;
    }

    public int getLevel() {
        return level;
    }

    public int getColor() {
        return colorCode;
    }

    public void draw(Graphics2D g) {
        if(level==0){
            g.drawImage(animation.getImage(),
                (int) x * ballSize + 24*GamePanel.SCALE,
                (int) y * ballSize + 15*GamePanel.SCALE,
                null);
            }
        else{
            g.drawImage(animation.getImage(),
                (int) x * ballSize + 23*GamePanel.SCALE,
                (int) y * ballSize + 14*GamePanel.SCALE - 1,
                null);
        }
    }

    public boolean contains(int x, int y) {
        isClicked_ = true;
        animation.setFrames(Lv2);
        int scale = GamePanel.SCALE;
        int x1 = (int) this.x - ballSize / 2;
        int y1 = (int) this.y - ballSize / 2;
        int x2 = x1 + ballSize / 2;
        int y2 = y1 + ballSize / 2;
        if (x1 * scale <= x && x <= x2 * scale && y1 * scale <= y && y <= y2 * scale)
            return true;
        return false;
    }

}
