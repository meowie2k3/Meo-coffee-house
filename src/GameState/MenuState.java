package GameState;

import java.awt.*;
import java.awt.event.*;
import Map.Background;

public class MenuState extends GameState {

    private Background bg;

    private int currentChoice = 0;

    private String[] options = {
        "Start", 
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
        g.drawString("Cats Aventure", 80, 70);
        //draw menu options
        g.setFont(font);
        for(int i = 0; i < options.length; i++){
            if(i == currentChoice){
                g.setColor(Color.GREEN);
            }
            else{
                g.setColor(Color.PINK);
            }
            g.drawString(options[i], 145, 140 + i * 15);
            //draw one after another
        }
    }

    private void selectMenu(){
        if(currentChoice == 0){
            //start
        }
        if(currentChoice == 1){
            //help
        }
        if(currentChoice == 2){
            //quit
            System.exit(0);
        }
    }

    public void keyPressed(int k){
        if(k == KeyEvent.VK_ENTER){
            selectMenu();
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
    }
    public void keyReleased(int k){

    }
}
