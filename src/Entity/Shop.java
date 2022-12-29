package Entity;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

//Chi xinh dep hihi
public class Shop implements ActionListener{

    int c = 0;
    
    Border line;
    JButton s1, s2;

    JPanel panel, panell, panelr, panelCat, panelFood;

    JButton bCat, bFood;

    //Cats
    JButton c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16;

    //Foods
    JButton f1, f2, f3, f4;

    ArrayList<JButton> cats = new ArrayList<JButton>();

    public Shop()  {

        line = BorderFactory.createLineBorder(new Color(125, 125, 0));

        panel.setBounds(20, 75, 900, 560);
        panel.setBackground(new Color(204, 164, 115)); 
        panel.setBorder(line);   
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));   

        panell = new JPanel();
        panell.setPreferredSize(new Dimension(155, 530));
        panell.setBackground(new Color(236, 216, 147));
        panell.setBorder(line);
        panell.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 24));

        panelr = new JPanel();
        panelr.setPreferredSize(new Dimension(700, 530));
        panelr.setBackground(new Color(236, 216, 147));
        panelr.setBorder(line);

        panelCat = new JPanel();
        panelCat.setPreferredSize(new Dimension(700, 530));
        panelCat.setBackground(new Color(236, 216, 147)); 
        panelCat.setBorder(line);
        panelCat.setLayout(new FlowLayout(FlowLayout.CENTER, 35, 24)); 

        //In panel left
        bCat = new JButton("        Cat          ");
        bCat.setHorizontalTextPosition(SwingConstants.CENTER);
        bCat.setBackground(new Color(181, 114, 85));
        bCat.setFocusPainted(false);

        bFood = new JButton("Food for Cats");
        bFood.setHorizontalTextPosition(SwingConstants.CENTER);
        bFood.setBackground(new Color(181, 114, 85));
        bFood.setFocusPainted(false);

        panell.add(bCat);
        panell.add(bFood);

        //16 cats
        c1 = new JButton("Black", new ImageIcon("1black.png"));
        c2 = new JButton("Blue", new ImageIcon("2blue.png"));   
        c3 = new JButton("Brown", new ImageIcon("3brown.png"));   
        c4 = new JButton("Grey", new ImageIcon("4grey.png"));     
        c5 = new JButton("Green", new ImageIcon("5green.png")); 
        c6 = new JButton("Pink", new ImageIcon("6pink.png"));        
        c7 = new JButton("Red", new ImageIcon("7red.png"));        
        c8 = new JButton("White", new ImageIcon("8white.png"));      
        c9 = new JButton("Calico", new ImageIcon("9calico.png"));     
        c10 = new JButton("Clown", new ImageIcon("10clown.png"));     
        c11 = new JButton("Creme", new ImageIcon("11creme.png"));     
        c12 = new JButton("Grey Point", new ImageIcon("12grey_point.png"));  
        c13 = new JButton("Grey Tabby", new ImageIcon("13grey_tabby.png"));   
        c14 = new JButton("Orange Tabby", new ImageIcon("14orange_tabby.png")); 
        c15 = new JButton("Radioactive", new ImageIcon("15radioactive.png"));        
        c16 = new JButton("Seal Point", new ImageIcon("16seal_point.png"));      
        
        cats.add(c1);
        cats.add(c2);
        cats.add(c3);        
        cats.add(c4);
        cats.add(c5);
        cats.add(c6);
        cats.add(c7);
        cats.add(c8);
        cats.add(c9);
        cats.add(c10);
        cats.add(c11);
        cats.add(c12);
        cats.add(c13);
        cats.add(c14);
        cats.add(c15);
        cats.add(c16);

        for (int i = 0; i < 16; i++)   {
            panelCat.add(cats.get(i));
            cats.get(i).addActionListener(this);            
            cats.get(i).setVerticalTextPosition(SwingConstants.BOTTOM);
            cats.get(i).setHorizontalTextPosition(SwingConstants.CENTER);
            cats.get(i).setBackground(new Color(255, 255, 240));
            cats.get(i).setFocusPainted(false);
        }
        

        //food for cats buttons
        f1 = new JButton("Food 1");
        f1.setVerticalTextPosition(SwingConstants.BOTTOM);
        f1.setHorizontalTextPosition(SwingConstants.CENTER);
        f1.setBackground(Color.WHITE);

        f2 = new JButton("Food 2");
        f2.setVerticalTextPosition(SwingConstants.BOTTOM);
        f2.setHorizontalTextPosition(SwingConstants.CENTER);
        f2.setBackground(Color.WHITE);

        f3 = new JButton("Food 3");
        f3.setVerticalTextPosition(SwingConstants.BOTTOM);
        f3.setHorizontalTextPosition(SwingConstants.CENTER);
        f3.setBackground(Color.WHITE);

        f4 = new JButton("Food 4");
        f4.setVerticalTextPosition(SwingConstants.BOTTOM);
        f4.setHorizontalTextPosition(SwingConstants.CENTER);
        f4.setBackground(Color.WHITE);

        bCat.addActionListener(e -> {
            panel.remove(panelr);
            // panel.remove(panelFood);
            panel.add(panelCat);
            panel.revalidate();
            panel.repaint();
        });
        

        bFood.addActionListener(e -> {
            panel.remove(panelr);
            // panel.remove(panelCat);
            panel.add(panelFood);
            panel.revalidate();
            panel.repaint();
        });

        

        panelr.setVisible(true);
        panel.add(panell);
        panel.add(panelr);
        
        panel.setVisible(false);
        
    }

    public void appear()    {
        panel.setVisible(true);
    }
    public void disappear() {
        panel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        for (int i = 0; i < 8; i++)   {
            if (e.getSource() == cats.get(i))   {
                actionNormal(cats.get(i));
                break;
            }
        }     
        for (int i = 8; i < 16; i++)    {
            if (e.getSource() == cats.get(i))   {
                actionSpecial(cats.get(i));
                break;
            }
        }   
    }

    public void actionNormal(JButton button)  {
        int a = JOptionPane.showOptionDialog(panel, 
            "Do you want to pay 100 for this cat?", 
            button.getText(), 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            button.getIcon(),     //no custom icon
            null,  //button titles
            null //default button
        );
    }
    public void actionSpecial(JButton button)  {
        int a = JOptionPane.showOptionDialog(panel, 
            "Do you want to pay 150 for this cat?", 
            button.getText(), 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            button.getIcon(),     //no custom icon
            null,  //button titles
            null //default button
        );
    }

    public void buy(int a)  {
        if (a == JOptionPane.YES_OPTION) {
            // User clicked YES.
        } 
        else if (a == JOptionPane.NO_OPTION) {
            // User clicked NO.
        }
    }
}
