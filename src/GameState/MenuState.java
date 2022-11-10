package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import Entity.SoundEffect;
import Main.GamePanel;
import Map.Background;



public class MenuState extends GameState {

    private Background bg;

    private int currentChoice = 0;

    private String[] options = {
        "Continue",
        "Quit"
    };

    private Color titleColor;
    private Font titleFont;

    private Font font;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;
        
        try {
            bg = new Background("/Backgrounds/bg.png", 1);

            bg.setVector(-0.1, 0);


            titleColor = new Color(251, 219, 101);
            titleFont = new Font("Consolas", Font.PLAIN, 28);

            font = new Font("Cambria Math", Font.PLAIN, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {

    }
    public void update(){
        bg.update();
    }

    //...
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel = new JPanel();
    //...
    
    public void draw(Graphics2D g){
        //draw bg
        bg.draw(g);

        //draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("MEOW COFFEE HOUSE", 28, 85);

       

        //draw menu options
        g.setFont(font);
        for(int i = 0; i < options.length; i++){
            if(i == currentChoice){
                g.setColor(Color.RED);
            }
            else{
                g.setColor(new Color(128,0,128));
            }
            g.drawString(options[i], 135, 140 + i * 20);
            //g.drawRect(135, 140 + (i-1) * 20, 70, 20);
            //draw one after another
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
            if(currentChoice < options.length - 1){
                currentChoice++;
            }
        }
        if(k == KeyEvent.VK_UP){
            if(currentChoice > 0){
                currentChoice--;
            }
        }
        if(k == KeyEvent.VK_DOWN){
            if(currentChoice < options.length - 1){
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
        // System.out.println(x + " " + y);
        int scale = GamePanel.SCALE;
        int left = 135 * scale;
        int right = 190 * scale;
        int top = 140 * scale;
        
        if(x > left && x < right && y > 0*scale*20 + top -20 && y < 0*scale*20 + top){
            currentChoice = 0;
        }
        if(x > left && x < right && y > 1*scale*20 + top - 20 && y < 1*scale*20 + top){
            currentChoice = 1;
        }
    }
    public void mouseDragged(MouseEvent e) {
        
    }
}
