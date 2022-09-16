package GameState;

import java.awt.*;
import java.awt.event.*;
import Map.Background;

public class MenuState extends GameState {

    private Background bg;

    private int currentChoice = 0;

    private String[] options = {
        "Continue",
        "New game", 
        "Help", 
        "Quit"
    };

    private Color titleColor;
    private Font titleFont;

    private Font font;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;

        try {
            bg = new Background("/Backgrounds/menubg.gif", 1);
            bg.setVector(-0.1, 0);

            titleColor = new Color(102, 0, 204);
            titleFont = new Font("Century Gothic", Font.PLAIN, 28);

            font = new Font("Arial", Font.PLAIN, 12);
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
        //draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Cats Adventure", 50, 70);
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
        }
        if(currentChoice == 1){
            //new game
        }
        if(currentChoice == 2){
            //help
        }
        if(currentChoice == 3){
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
        //System.out.println(x + " " + y);
        
        if(x > 270 && x < 370 && y > 260 && y < 280){
            currentChoice = 0;
        }
        if(x > 270 && x < 380 && y > 300 && y < 320){
            currentChoice = 1;
        }
        if(x > 270 && x < 320 && y > 340 && y < 360){
            currentChoice = 2;
        }
        if(x > 270 && x < 315 && y > 380 && y < 400){
            currentChoice = 3;
        }
    }
    public void mouseDragged(MouseEvent e) {
        
    }
}
