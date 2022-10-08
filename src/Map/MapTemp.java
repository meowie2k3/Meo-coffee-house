package Map;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.*;
import java.io.*; // for reader
import java.util.ArrayList;

import javax.imageio.*;

public class MapTemp {
    //position
    private double x;
    private double y;
    //bounds
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;

    private double tween;//smooth camera movement

    //map instance
    private int[][] map;
    private int blockSize = 65;
    private int catSize = 32;
    private int drinkSize = 16;
    private int numRows;
    private int numCols;
    private int width;
    private int height;

    //blockCollection
    private BufferedImage blockCollection;
    private int numBlockAcross;
    private Block[][] blocks;

    //draw function
    //only draw the blocks that are on the screen
    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;

    //constructor
    public MapTemp(){
        
        numRowsToDraw = GamePanel.HEIGHT / blockSize; //add 2 to avoid black line
        numColsToDraw = GamePanel.WIDTH / blockSize;
        tween = 0.07;//test multiple value
    }

    //getters
    public int getBlockSize(){
        return blockSize;
    }
    public int getX(){
        return (int)x;
    }
    public int getY(){
        return (int)y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int getType(int row, int col){
        int rc = map[row][col];
        int r = rc / numBlockAcross;
        int c = rc % numBlockAcross;
        return blocks[r][c].getType();
    }
    //setters
    public void setPosition(double x, double y){
        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;//smooth camera movement
        //make sure the camera doesn't go out of dimension
        limitDimensions();
        //calculate the offset (which block to start drawing)
        //avoid drawing the whole map (memory explode :>)
        colOffset = (int)-this.x / blockSize;
        rowOffset = (int)-this.y / blockSize;
    }
    private void limitDimensions(){
        if(x < xmin) x = xmin;
        if(y < ymin) y = ymin;
        if(x > xmax) x = xmax;
        if(y > ymax) y = ymax;
    }



    //load blockCollection into memory
    public void hashImage(String s){

        try{
            
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //load map files into memory
    public void loadUserSavedGame(String s){
        try{
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(s));
            ArrayList<Block> block = new ArrayList<Block>();
            for(int i=0;i<6;i++){
                for(int j=0;j<6;j++){
                    if(i== 0 && j==4){
                        block.add(new Block(image.getSubimage(
                            j*blockSize,
                         i*blockSize, 
                         blockSize, 
                         blockSize), 
                         4)); // type fridge
                    }
                    if(i== 0 && j==5){
                        block.add(new Block(image.getSubimage(
                            j*blockSize,
                         i*blockSize, 
                         blockSize, 
                         blockSize), 
                         3)); // type sink
                    }
                    if(i==1 && j==0){
                        block.add(new Block(image.getSubimage(
                            j*blockSize,
                         i*blockSize, 
                         blockSize, 
                         blockSize), 
                         4)); // type stove
                    }
                    //all decorations
                    if(i==1 && j == 2){
                        block.add(new Block(image.getSubimage(
                            j*blockSize,
                         i*blockSize, 
                         blockSize, 
                         blockSize), 
                         0)); // mixer
                    }
                    
                    

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //draw function
    public void draw(Graphics2D g){
        //start from the row offset
        for(int row = rowOffset; row < rowOffset + numRowsToDraw; row++){
            if(row >= numRows) break;//avoid out of bound :<
            //start from the column offset
            for(int col = colOffset; col < colOffset + numColsToDraw; col++){
                if(col >= numCols) break;//avoid out of bound (nothing else to draw :<)
                if(map[row][col] == 0) continue;//keep drawing :v
                int rc = map[row][col];
                int r = rc / numBlockAcross;
                int c = rc % numBlockAcross;
                //start drawing
                g.drawImage(
                    blocks[r][c].getImage(),//image
                    (int)x + col * blockSize,//x
                    (int)y + row * blockSize,//y
                    null//observer
                );
            }
        }
    }
    
}
