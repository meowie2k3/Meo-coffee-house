package Map;

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

import Main.GamePanel;


public class SoundEffect{
    private Clip clip;
    private int lastFrame;
    private BufferedImage playing;
    private BufferedImage muted;
    private String address;

    //position
    private double x;
    private double y;
    private int size = 20;

    public SoundEffect(double x, double y, String address, boolean initstate){
        try{
            this.x = x;
            this.y = y;
            this.address = address;
            
            playing = ImageIO.read(getClass().getResourceAsStream("/UI/Playing-noBG.png"));
            
            muted = ImageIO.read(getClass().getResourceAsStream("/UI/Muted-noBG.png"));

            loadClip(new File(address));

            if(initstate){
                clip.start();
            }

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
            //System.out.println("Stop");
            clip.stop();
        }
    }
    public void resume() {

        if (clip != null && clip.isRunning()==false) {
            loadClip(new File(address));
            // Make sure we haven't passed the end of the file...
            if (lastFrame < clip.getFrameLength()) {
                clip.setFramePosition(lastFrame);
            } 
            else{
                clip.setFramePosition(0);
            }
            //System.out.println("Start");
            clip.start();
            //System.out.println("Started");
        }
    }

    public int getWidth(){
        return size;
    }
    public int getHeight(){
        return size;
    }

    public boolean contains(int x, int y){
        int scale = GamePanel.SCALE;
        //x and y of the button are top left corner
        if(x >= this.x*scale 
        && x <= this.x*scale + size*scale 
        && y >= this.y*scale 
        && y <= this.y*scale + size*scale){
            return true;
        }
        return false;
    }

    public void toggle(){
        if(clip.isRunning()){
            pause();
        }
        else{
            resume();
        }
    }
    
    public void draw(Graphics2D g){
        if(clip.isRunning()){
            g.drawImage(playing, (int)x,(int) y, null);
        }
        else{
            g.drawImage(muted, (int)x, (int)y, null);
        }
    }

}

