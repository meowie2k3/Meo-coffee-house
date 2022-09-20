package Map;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.*;
import java.io.*; // for reader

import javax.imageio.*;

public class Map {
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
    private int blockSize = 66;
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
    public Map(){
        
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
    public void loadBlocks(String s){

        try{
            blockCollection = ImageIO.read(
                getClass().getResourceAsStream(s)
            );
            numBlockAcross = blockCollection.getWidth() / blockSize;
            blocks = new Block[2][numBlockAcross];

            BufferedImage subImage;
            for(int col = 0; col < numBlockAcross; col++){
                subImage = blockCollection.getSubimage(
                    col * blockSize,
                    0,
                    blockSize,
                    blockSize
                );
                blocks[0][col] = new Block(subImage, Block.NORMAL);
                subImage = blockCollection.getSubimage(
                    col * blockSize,
                    blockSize,
                    blockSize,
                    blockSize
                );
                blocks[1][col] = new Block(subImage, Block.BLOCKED);//second row is blocked
            }
            //first row is normal, second row is blocking block
        }catch (Exception e){ 
            e.printStackTrace();
        }

    }
    //load map files into memory
    public void loadUserSavedGame(String s){
        try{
            InputStream in = getClass().getResourceAsStream(s);//read file from link
            BufferedReader br = new BufferedReader(
                new InputStreamReader(in)
                );
            
            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            width = numCols * blockSize;
            height = numRows * blockSize;

            xmin = GamePanel.WIDTH - width;
            xmax = 0;
            ymin = GamePanel.HEIGHT - height;
            ymax = 0;

            String delims = "\\s+";//split by space
            for(int row = 0; row < numRows; row++){
                String line = br.readLine();
                String[] tokens = line.split(delims);//split into tokens by space
                for(int col = 0; col < numCols; col++){
                    map[row][col] = Integer.parseInt(tokens[col]);
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
