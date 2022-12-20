package Main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Map.SoundEffect;

import java.awt.*;

public class game {
    public static void main(String[] args){
        
        JFrame window = new JFrame("Meow Coffee House");   
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
         
        ImageIcon logo = new ImageIcon("Resources/Icon/money.jpg");
        window.setIconImage(logo.getImage());
        
    }
}