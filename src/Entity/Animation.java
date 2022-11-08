package Entity;

import java.awt.image.*;

public class Animation {

    private BufferedImage[] frames;
    private int currentFrame;

    private long startTime;
    private long delay;

    private boolean playedOnce;
    private int playNumber;

    public Animation() {
        playNumber = 0;
    }

    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
        playNumber = 0;
        playedOnce = false;
    }

    public void setDelay(long d) {
        delay = d;
    }

    public void setFrame(int i) {
        currentFrame = i;
    }

    // whether go to next frame
    public void update() {
        if (delay == -1)
            return;

        long elapsed = (System.nanoTime() - startTime) / 1000000;
        // System.out.println("Time elapses" +elapsed);
        if (elapsed > delay) {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if (currentFrame == frames.length) {
            currentFrame = 0;
            playNumber++;
            playedOnce = true;
        }

    }

    public int getFrame() {
        return currentFrame;
    }

    public BufferedImage getImage() {
        return frames[currentFrame];
    }

    public int getLength() {
        return frames.length;
    }

    public int getPlayNumber() {
        return playNumber;
    }

    public boolean hasPlayedOnce() {
        return playedOnce;
    }

}
