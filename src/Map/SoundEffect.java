package Map;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
 
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.image.*;
import javax.imageio.*;
import java.awt.Graphics2D;


public class SoundEffect{
    private Clip clip;
    private int lastFrame;
    private BufferedImage playing;
    private BufferedImage muted;
    //size 20x20

    public SoundEffect(){
        try{
            playing = ImageIO.read(getClass().getResourceAsStream("Resources/UI/Playing.png"));
            muted = ImageIO.read(getClass().getResourceAsStream("Resources/UI/Muted.png"));
            loadClip(new File("Resources/soundEffect/meow.wav"));
        }
        catch(Exception e){
        e.printStackTrace();
        }
    }

    public void loadClip(File audioFile){
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            clip.addLineListener(new LineListener() {
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        event.getLine().close();
                    }
                }
            });
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void pause(){
        if (clip != null && clip.isRunning()) {
            lastFrame = clip.getFramePosition();
            System.out.println("Stop");
            clip.stop();
        }
    }
    public void resume() {

        if (clip != null && !clip.isRunning()) {
            // Make sure we haven't passed the end of the file...
            if (lastFrame < clip.getFrameLength()) {
                clip.setFramePosition(lastFrame);
            } else {
                clip.setFramePosition(0);
            }
            System.out.println("Start");
            clip.start();
        }

    }
    public void draw(Graphics2D g){
        if(clip.isRunning()){
            g.drawImage(playing, 0, 0, null);
        }
        else{
            g.drawImage(muted, 0, 0, null);
        }
    }

}

