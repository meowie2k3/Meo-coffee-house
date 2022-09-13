package Main;

import javax.swing.*;

public class game{
    public static void main(String[] args){
        JFrame window = new JFrame("KittyCats");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
        
    }
}