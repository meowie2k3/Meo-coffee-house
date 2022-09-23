package Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class game {
    public static void main(String[] args){

        JFrame window = new JFrame("Meow Coffee House");   
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
         //draw play button
         
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setBounds(50, 120, 300, 300);
        window.add(panel);
    }
}