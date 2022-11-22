package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import javax.imageio.ImageIO;

import Main.GamePanel;
import Map.Background;

public class MenuState extends GameState {

    private BufferedImage p1;
    private BufferedImage p2;
    private BufferedImage e1;
    private BufferedImage e2;

    private BufferedImage[] Option = {
        p1,
        e1
    };
    
    private Background bg;

    private int currentChoice = 2;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;
        
        try {
            bg = new Background("/Backgrounds/bg.png", 1);

            bg.setVector(0, 0);

            p1 = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/Option/p1.png"));
            p2 = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/Option/p2.png"));
            e1 = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/Option/e1.png"));
            e2 = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/Option/e2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {

    }
    public void update(){
        bg.update();
    }
    
    public void draw(Graphics2D g){
        //draw bg
        bg.draw(g);

        //draw
        g.drawImage(p1, 121, 120, null);
        g.drawImage(e1, 121, 155, null);       

        // //draw menu options
        for(int i = 0; i < Option.length; i++){
            if  (currentChoice == 2) {
                g.drawImage(p1, 121, 120, null);
                g.drawImage(e1, 121, 155, null);
            }
            else if (currentChoice == 0)    {
                g.drawImage(p2, 121, 120, null);
                g.drawImage(e1, 121, 155, null);
            }
            else if (currentChoice == 1)  {
                g.drawImage(p1, 121, 120, null);
                g.drawImage(e2, 121, 155, null);
            }
        }        
    }

    private void selectedMenu(){
        if(currentChoice == 0){
            //continue
            gsm.setState(GameStateManager.INGAMESTATE);
        }
        if(currentChoice ==  1){
            //quit
            System.exit(0);
        }
    }

    public void keyPressed(int k){
        if(k == KeyEvent.VK_ENTER){
            selectedMenu();
        }
        if(k == KeyEvent.VK_W){
            if(currentChoice > 0){
                currentChoice--;
            }
        }
        if(k == KeyEvent.VK_S){
            if(currentChoice < Option.length - 1){
                currentChoice++;
            }
        }
        if(k == KeyEvent.VK_UP){
            if(currentChoice > 0){
                currentChoice--;
            }
        }
        if(k == KeyEvent.VK_DOWN){
            if(currentChoice < Option.length - 1){
                currentChoice++;
            }
        }
    }

    public void keyReleased(int k){

    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Mouse Clicked");
        selectedMenu();
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseMoved(MouseEvent e) {
        int x = (int) e.getX();
        int y = (int) e.getY();
        int scale = GamePanel.SCALE;
        int left = 135 * scale;
        int right = 190 * scale;
        int top = 140 * scale;
        
        if(x > left && x < right && y > 0*scale*20 + top - 45 && y < 0*scale*20 + top + 25){
            currentChoice = 0;
        }
        if(x > left && x < right && y > 1*scale*20 + top - 2 && y < 1*scale*20 + top + 68){
            currentChoice = 1;
        }
        if(x < left || x > right || y < 0*scale*20 + top - 45 || (y > 0*scale*20 + top + 25 && y < 1*scale*20 + top - 2) || y > 1*scale*20 + top + 68)   {
            currentChoice = 2;
        }
    }
    public void mouseDragged(MouseEvent e) {
        
    }
}
