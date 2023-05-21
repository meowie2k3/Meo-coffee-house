package Entity;

import Map.*;
import Map.Map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.imageio.*;
import java.awt.*;
import javax.imageio.ImageIO;
import Main.GamePanel;
import GameState.LineGameState;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;

public class Ball extends Entity {
    private int x;
    private int y;
    private BufferedImage img;
    private boolean isClicked_;
    private int level = 0;
    private int colorCode;
    private String address = "";

    // animation
    private ArrayList<BufferedImage[]> sprites;

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

    public Ball(Map map, int x_pos, int y_pos, int color_id, int lvl) {
        super(map);
        this.address = colorAddressLv1[color_id - 1];
        try {
            x = x_pos;
            y = y_pos;
            level = 0;
            colorCode = color_id;

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream(colorAddressLv1[color_id - 1]));
            sprites = new ArrayList<BufferedImage[]>();
            int tmp;

            // get stand animation
            BufferedImage[] stand = new BufferedImage[1];
            tmp = 0;
            stand[tmp] = spritesheet.getSubimage(
                    1 * ballSize,
                    0 * ballSize,
                    ballSize,
                    ballSize);
            sprites.add(stand);

            // get walk animation
            int[] myNum = { 1, 0, 2, 2, 2, 3, 1, 1 };
            for (int k = 0; k < 8; k++) {
                int i = myNum[k];
                BufferedImage[] walk = new BufferedImage[1];
                tmp = 0;
                for (int j = 0; j < 3; j++) {
                    walk[tmp] = spritesheet.getSubimage(
                            j * ballSize,
                            i * ballSize,
                            ballSize,
                            ballSize);
                    tmp++;
                }
                sprites.add(walk);
            }
            animation = new Animation();
            currentAction = STAND;
            nextAction = STAND;
            animation.setFrames(sprites.get(currentAction));
            animation.setDelay(100);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void move_(String direction) {
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
                case 'R':
                    y++;

                    break;
            }

        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(img, x * LineGameState.BALL_SIZE,
                y * LineGameState.BALL_SIZE, null);

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

    @Override
    public void move(int direction, int halfsize) {
        setDirection(direction);
        setAction(WALK + direction);
        switch (direction) {
            case leftDown:
                x -= moveSpeed;
                y += moveSpeed;
                break;
            case down:
                y += moveSpeed;
                break;
            case rightDown:
                x += moveSpeed;
                y += moveSpeed;
                break;
            case right:
                x += moveSpeed;
                break;
            case rightUp:
                x += moveSpeed;
                y -= moveSpeed;
                break;
            case up:
                y -= moveSpeed;
                break;
            case leftUp:
                x -= moveSpeed;
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

    public void walkingIn(int order) {
        if (order == 0) {
            finalX = 170;
            finalY = 75;
        } else if (order == 1) {
            finalX = 200;
            finalY = 75;
        } else if (order == 2) {
            finalX = 230;
            finalY = 75;
        } else {
            finalX = 260;
            finalY = 75;
        }

        // get in and move up
        if (getX() == finalX && getY() > finalY) {
            setDirection(5);
            setAction(WALK + getDirection());
            return;

        }

        // get in and move right
        if (getX() < finalX && getY() > finalY) {
            setDirection(3);
            setAction(WALK + getDirection());
            return;
        }

        // arrived the position
        if (getX() == finalX && getY() == finalY) {
            if (popup == null) {
                Random rand = new Random();
                // there are 23 drinks
                int pos = rand.nextInt(23);
                String chosen = drinkAddress[pos];
                PopUp drink = new PopUp(getX(), getY(), chosen);
                popup = drink;
            }

            setAction(STAND);
            setCountingTime();
            return;
        }

    }

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
                    animation.setFrames(sprites.get(STAND));
                    animation.setDelay(slow);
                }
                // setCountingTime();
            }

            if (currentAction >= WALK && currentAction <= WALK + 7) {
                currentAction = STAND;
                animation.setFrames(sprites.get(STAND));
                animation.setDelay(slow);

            }
        }

        if (nextAction >= WALK && nextAction <= WALK + 7) {

            if (currentAction >= WALK && currentAction <= WALK + 7) {
                if (tempDirection != currentDirection) {
                    animation.setFrames(sprites.get(WALK + currentDirection));
                    animation.setDelay(slow);
                    tempDirection = currentDirection;
                }

            }
            if (currentAction == STAND) {
                currentAction = WALK + currentDirection;
                animation.setFrames(sprites.get(WALK + currentDirection));
                animation.setDelay(slow);

            }

        }

        animation.update();

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
