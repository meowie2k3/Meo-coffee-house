package Map;

import GameState.IngameState;
import Entity.*;
import Entity.character;

import java.awt.*;
import java.awt.image.*;
import java.io.*; // for reader
import java.util.ArrayList;

import javax.imageio.*;

public class Map {
    private int x;
    private int y;
    // map instance
    private int[][] map;
    private int furnitureSize = 40;
    private int catSize = 32;
    private int drinkSize = 16;
    // map size
    private int numRows = 6;
    private int numCols = 8;
    // furiture
    private ArrayList<Furniture> furnitureList;
    // user instance
    private int food;
    private int money;
    private int toy;
    private int catNum;
    private int characterNum;
    private int bartenderNum;

    // constructor
    public Map() {
    }

    // getters
    public int getCatSize() {
        return catSize;
    }

    public int getCatNum() {
        return catNum;
    }

    public int getcharacterNum() {
        return characterNum;
    }

    public int getbartenderNum() {
        return bartenderNum;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    // get user properties
    public int getFood() {
        return food;
    }

    public int getMoney() {
        return money;
    }

    public int getToy() {
        return toy;
    }

    public void loadFurniture() {
        try {
            //System.out.println("load furniture");
            String[] address = {"/Furniture/DrinkBar.png", "/Furniture/Chair.png", "/Furniture/Table.png"};
            int[] x = {139, 0, 0};
            int[] y = {0, 0, 0};
            furnitureList = new ArrayList<Furniture>();
            for (int i = 0; i < address.length; i++) {
                
                BufferedImage image = ImageIO.read(getClass().getResourceAsStream(address[i]));
                furnitureList.add(new Furniture(image, x[i], y[i]));
                //System.out.println(address[i] + " loaded");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // load .map files into memory
    public void loadUserSavedGame(String address) {
        try {
            InputStream in = getClass().getResourceAsStream(address);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            food = Integer.parseInt(br.readLine());
            money = Integer.parseInt(br.readLine());
            toy = Integer.parseInt(br.readLine());
            catNum = Integer.parseInt(br.readLine());
            characterNum = Integer.parseInt(br.readLine());
            bartenderNum = Integer.parseInt(br.readLine());
            //System.out.println("user info" + food + " " + money + " " + toy + " " + catNum);

            String delims = "\\s++";
            for(int i=0; i < catNum;i++){
                String curr = br.readLine();
                // System.out.println("address " + curr);

                Cat tmp = new Cat(IngameState.map, curr);
                String line = br.readLine();
                String[] tokens = line.split(delims);
                // System.out.println("Position " + Integer.parseInt(tokens[0]) +" " +
                // Integer.parseInt(tokens[1]));
                tmp.setPosition(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));

                IngameState.catList.add(tmp);
            }
            
            delims = "\\s++";
            for(int i=0; i < characterNum;i++){
                String curr = br.readLine();
                //System.out.println("address " + curr);

                character tmp = new character(IngameState.map, curr);
                String line = br.readLine();
                String[] tokens = line.split(delims);
                //System.out.println("Position " + Integer.parseInt(tokens[0]) +" " + Integer.parseInt(tokens[1]));
                tmp.setPosition(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
                
                IngameState.characterList.add(tmp);
            }

            delims = "\\s++";
            for(int i=0; i < bartenderNum;i++){
                String curr = br.readLine();
                //System.out.println("address " + curr);

                character tmp = new character(IngameState.map, curr);
                String line = br.readLine();
                String[] tokens = line.split(delims);
                //System.out.println("Position " + Integer.parseInt(tokens[0]) +" " + Integer.parseInt(tokens[1]));
                tmp.setPosition(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
                
                IngameState.bartenderList.add(tmp);
            }
            

            //load array map
            map = new int[numRows+1][numCols+1];
            for (int i = 0; i < numRows; i++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for (int j = 0; j < numCols; j++) {
                    map[i][j] = Integer.parseInt(tokens[j]);
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void SaveUserData(String address) {
        try {
            PrintWriter writer = new PrintWriter(address, "UTF-8");
            writer.println(food);
            writer.println(money);
            writer.println(toy);
            writer.println(catNum);
            writer.println(characterNum);
            writer.println(bartenderNum);
            for (int i = 0; i < catNum; i++) {
                writer.println(IngameState.catList.get(i).getAddress());
                writer.println(IngameState.catList.get(i).getX() + " " + IngameState.catList.get(i).getY());
            }
            for (int i = 0; i < characterNum; i++) {
                writer.println(IngameState.characterList.get(i).getAddress());
                writer.println(IngameState.characterList.get(i).getX() + " " + IngameState.characterList.get(i).getY());
            }
            for (int i = 0; i < bartenderNum; i++) {
                writer.println(IngameState.bartenderList.get(i).getAddress());
                writer.println(IngameState.bartenderList.get(i).getX() + " " + IngameState.bartenderList.get(i).getY());
            }
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    writer.print(map[i][j] + " ");
                }
                writer.println();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // draw function
    public void draw(Graphics2D g) {
        //System.out.println("furniture " + furnitureList.size());
        try {
            //draw drink bar first
            g.drawImage(furnitureList.get(0).getImage(), furnitureList.get(0).getX(),
                furnitureList.get(0).getY(), null);
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    if (map[i][j] != 0) {
                        g.drawImage(furnitureList.get(map[i][j]).getImage(),
                                j * furnitureSize, i * furnitureSize, null);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}