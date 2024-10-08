package GameState;

import java.util.ArrayList;

import Main.game;

import java.awt.event.*;


public class GameStateManager {
    
    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int INGAMESTATE = 1;
    public static final int SHOPSTATE = 2;
    public static final int LINESTATE = 3;

    public GameStateManager(){
        gameStates = new ArrayList<GameState>();
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));//add menu state
        gameStates.add(new IngameState(this));//add ingame state
        gameStates.add(new ShopState(this));
        gameStates.add(new LineGameState(this));
    }

    public void setState(int state){
        currentState = state;
        gameStates.get(currentState).init();
    }
    public void saveData(){
        //save all game state
        for(int i = 0; i < gameStates.size(); i++){
            gameStates.get(i).saveData();
        }
    }
    public void update(){
        gameStates.get(currentState).update();
    }
    public void draw(java.awt.Graphics2D g){
        gameStates.get(currentState).draw(g);
    }
    //key event listener
    public void keyPressed(int k){
        gameStates.get(currentState).keyPressed(k);
    }
    public void keyReleased(int k){
        gameStates.get(currentState).keyReleased(k);
    }
    public void mousePressed(MouseEvent e) {
        gameStates.get(currentState).mousePressed(e);
    }
    //mouse event listener
    public void mouseReleased(MouseEvent e) {
        gameStates.get(currentState).mouseReleased(e);
    }
    public void mouseEntered(MouseEvent e) {
        gameStates.get(currentState).mouseEntered(e);
    }
    
    public void mouseClicked(MouseEvent e) {
        gameStates.get(currentState).mouseClicked(e);
    }
    public void mouseExited(MouseEvent e) {
        gameStates.get(currentState).mouseExited(e);
    }
    //mouse motion listener
    public void mouseMoved(MouseEvent e){
        gameStates.get(currentState).mouseMoved(e);
    }
    public void mouseDragged(MouseEvent e){
        gameStates.get(currentState).mouseDragged(e);
    }

    
}