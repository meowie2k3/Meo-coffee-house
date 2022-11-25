package Map;

import GameState.IngameState;
import Entity.*;
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
    public static final int Chair = 1;
    public static final int Table = 2;
    public static final int Sink = 3;
    public static final int Wardrobe = 4;
    public static final int Fridge = 5;
    // user instance
    private int food;
    private int money;
    private int toy;
    private int catNum;

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
            String[] address = { "/Furniture/Chair.png", "/Furniture/Table.png",
                    "/Furniture/Sink.png", "/Furniture/Wardrobe.png", "/Furniture/Fridge.png" };
            String[] name = { "Chair", "Table", "Sink", "Wardrobe", "Fridge" };
            furnitureList = new ArrayList<Furniture>();
            for (int i = 0; i < address.length; i++) {
                
                BufferedImage image = ImageIO.read(getClass().getResourceAsStream(address[i]));
                furnitureList.add(new Furniture(name[i], image));
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
            // System.out.println("user info" + food + " " + money + " " + toy + " " +
            // catNum);

            String delims = "\\s++";
            for (int i = 0; i < catNum; i++) {
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
            for (int i = 0; i < catNum; i++) {
                writer.println(IngameState.catList.get(i).getAddress());
                writer.println(IngameState.catList.get(i).getX() + " " + IngameState.catList.get(i).getY());
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
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    if (map[i][j] != 0) {
                        g.drawImage(furnitureList.get(map[i][j] - 1).getImage(),
                                j * furnitureSize, i * furnitureSize, null);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
