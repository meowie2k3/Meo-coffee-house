package Entity;

import java.awt.image.*;

public class Animation {

    private BufferedImage[] frames;
    private int currentFrame;

    private long startTime;
    private long delay;

    private boolean playedOnce;

    public Animation() {
        playedOnce = false;

    }

    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
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
        if (currentFrame == frames.length-1) {
            playedOnce = true;
        }
        if (currentFrame == frames.length) {
            currentFrame = 0;
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

    public boolean hasPlayedOnce() {
        return playedOnce;
    }

}
