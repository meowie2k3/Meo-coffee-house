package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.imageio.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import Main.GamePanel;
import GameState.LineGameState;
import java.util.ArrayList;
import java.util.Random;
import java.awt.image.*;

public class Ball {
    // position and direction
    protected double x;
    protected double y;
    protected int currentDirection = 3;

    // animation
    protected Animation animation;
    protected int currentAction;
    protected int nextAction;
    protected int previousAction;// just in case
    protected int commandAction;// command for next action

    // movement physics :>
    protected double moveSpeed;

    // movement
    public final char down = 'D';
    public final char right = 'R';
    public final char up = 'U';
    public final char left = 'L';

    private BufferedImage img;
    private boolean isClicked_;
    private int level = 0;
    private int colorCode;
    private String address = "";

    // animation
    private BufferedImage[] sprites;

    private int ballSize = 15;
    private static final int STAND = 0;
    private static final int WALK = 1;
    private String moveDescription = "";

    private String[] colorAddressLv1 = {
            "/Balls/Lv1/blackC.png", // id: 1
            "/Balls/Lv1/blueC.png", // id: 2
            "/Balls/Lv1/cyanC.png", // id: 3
            "/Balls/Lv1/greenC.png", // id: 4
            "/Balls/Lv1/purpleC.png", // id: 5
            "/Balls/Lv1/redC.png", // id: 6
            "/Balls/Lv1/yellowC.png", // id: 7
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
        this.address = colorAddressLv1[color_id - 1];
        try {
            x = x_pos;
            y = y_pos;
            level = 0;
            colorCode = color_id;

            BufferedImage img = ImageIO.read(getClass().getResource(colorAddressLv1[color_id - 1]));

            BufferedImage[] sprites = new BufferedImage[1];
            sprites[0] = img;
            
            animation = new Animation();
            currentAction = STAND;
            nextAction = STAND;
            animation.setFrames(sprites);
            animation.setDelay(100);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g) {
        g.drawImage(img, (int) x * ballSize,
                (int) y * ballSize, null);
    }

    public boolean contains(int x, int y) {
        int scale = GamePanel.SCALE;
        int x1 = (int) this.x - ballSize / 2;
        int y1 = (int) this.y - ballSize / 2;
        int x2 = x1 + ballSize / 2;
        int y2 = y1 + ballSize / 2;
        if (x1 * scale <= x && x <= x2 * scale && y1 * scale <= y && y <= y2 * scale)
            return true;
        return false;
    }

    public void setDirection(int i) {
        currentDirection = i;
    }

    public void setAction(int i) {
        nextAction = i;
        update();
    }

    public void move(char direction) {
        setDirection(direction);
        setAction(WALK + direction);
        switch (direction) {
            case down:
                y += moveSpeed;
                break;
            case right:
                x += moveSpeed;
                break;
            case up:
                y -= moveSpeed;
                break;
            case left:
                x -= moveSpeed;
                break;
            default:
                System.out.println("Invalid direction");
                break;
        }
    }

    public int finalX, finalY;
    public int way;

    // timing
    private boolean countingTime = false;
    private long start = 0;
    private long limit = 10000;

    public boolean getCountingTime() {
        return countingTime;
    }

    public void setCountingTime() {
        Random rand = new Random();
        if (countingTime) {
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            // System.out.println(timeElapsed);

            if (timeElapsed > limit) {
                countingTime = false;
                // setCountingTime();
                setAction(WALK + 1);
                way = 1;

                return;
            }

        } else {
            start = System.currentTimeMillis();
            countingTime = true;
        }
    }

    public int tempDirection = -1;

    public void update() {
        int slow = 120;
        int fast = 60;
        // set animation
        if (nextAction == STAND) {
            if (currentAction == STAND) {
                if (animation.hasPlayedOnce()) {
                    animation.setFrames(sprites);
                    animation.setDelay(slow);
                }
                // setCountingTime();
            }

            if (currentAction >= WALK) {
                currentAction = STAND;
                animation.setFrames(sprites);
                animation.setDelay(slow);

            }
        }

        if (nextAction >= WALK) {

            if (currentAction >= WALK) {
                if (tempDirection != currentDirection) {
                    animation.setFrames(sprites);
                    animation.setDelay(slow);
                    tempDirection = currentDirection;
                }

            }
            if (currentAction == STAND) {
                currentAction = WALK + currentDirection;
                animation.setFrames(sprites);
                animation.setDelay(slow);

            }

        }

        animation.update();

    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
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

}
