package Main;

import javax.swing.JFrame;

public class game {
    public static void main(String[] args){

        JFrame window = new JFrame("Cats'Adventure");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
        
    }
}