package Main;

import javax.swing.JFrame;
import java.awt.*;

public class game {
    public static void main(String[] args){

        JFrame window = new JFrame("Meow Coffee House");     
        Image icon = Toolkit.getDefaultToolkit().getImage("logo.png"); 
        window.setIconImage(icon);
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}