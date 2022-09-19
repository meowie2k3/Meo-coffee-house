package Entity;
import java.awt.event.*;


public abstract class Entity {
    protected EntityManager em;

    public abstract void init();
    public abstract void init(int userID);
    public abstract void update();
    public abstract void draw(java.awt.Graphics2D g);
    public abstract void mouseClicked(MouseEvent e);
    public abstract void mouseMoved(MouseEvent e);
    
}
