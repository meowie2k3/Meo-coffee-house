package Main;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.*;

import Map.SoundEffect;

import java.awt.*;

public class game {
    public static void main(String[] args){
        
        JFrame window = new JFrame("Meow Coffee House");
        GamePanel gameContent = new GamePanel();
        window.setContentPane(gameContent);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                gameContent.saveData();
                System.exit(0);
            }
        });
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
         
        ImageIcon logo = new ImageIcon("Resources/Icon/money.jpg");
        window.setIconImage(logo.getImage());

    //      ^~^  ,
    //     ('Y') )
    //     /   \/ 
    //    (\|||/)
    }
}