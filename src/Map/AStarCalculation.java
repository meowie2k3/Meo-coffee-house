package Map;

import java.lang.Math;
import java.util.*;
import Entity.Ball;
//import collection

class Pair {
    int first, second;

    Pair() {
    }

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class pPair {
    int first;
    Pair second;

    pPair() {
    }

    pPair(int first, Pair second) {
        this.first = first;
        this.second = second;
    }
}

class cell {
    // Row and Column index of its parent
    // Note that 0 <= i <= ROW-1 & 0 <= j <= COL-1
    int parent_i, parent_j;
    // f = g + h
    int f, g, h;
}

public class AStarCalculation {

    public static final int ROW = 9;
    public static final int COL = 9;

    private static void memset(boolean[][] arr, boolean value, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = value;
            }
        }
    }

    private static boolean isValid(int row, int col) {
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL);
    }

    private static boolean isBlocked(int[][] grid, int row, int col) {
        if(grid[row][col] == 1){
            return false;
        }
        else{
            return true;
        }
    }

    private static boolean isDestination(int row, int col, Pair dest) {
        if (row == dest.first && col == dest.second)
            return true;
        else
            return false;
    }

    private static int calculateHValue(int row, int col, Pair dest) {
        // Manhattan distance
        // only 4 directions
        return Math.abs(row - dest.first) + Math.abs(col - dest.second);
    }
    
    //result rules: U -> up, D -> down, L -> left, R -> right, return false if any error
    private static String tracePath(cell[][] cellDetails, Pair dest) {
        int row = dest.first;
        int col = dest.second;
        String res = "";
        Stack<Pair> Path = new Stack<Pair>();

        // System.out.println("Cell Details:");
        // for (int i = 0; i < ROW; i++) {
        //     System.out.print("[");
        //     for (int j = 0; j < COL; j++) {
        //         System.out.print(cellDetails[i][j].f+ " ");
        //     }
        //     System.out.println("]");
        // }

        while (!(cellDetails[row][col].parent_i == row && cellDetails[row][col].parent_j == col)) {
            Path.push(new Pair(row, col));
            int temp_row = cellDetails[row][col].parent_i;
            int temp_col = cellDetails[row][col].parent_j;
            row = temp_row;
            col = temp_col;
        }

        Path.push(new Pair(row, col));
        while (!Path.empty()) {
            Pair p = Path.peek();
            Path.pop();
            if (!Path.empty()) {
                Pair p2 = Path.peek();
                if (p.first == p2.first) {
                    if (p.second > p2.second) {
                        res += "L";
                    } else {
                        res += "R";
                    }
                } else {
                    if (p.first > p2.first) {
                        res += "U";
                    } else {
                        res += "D";
                    }
                }
            }
        }
        
        return res;
    }

    static String AStarInstruction(Ball[][] grid, int xStart, int yStart, int xDest, int yDest){
        int[][] gridRes = new int[ROW][COL];
        // System.out.println("starting point: " + xStart + " " + yStart);
        // System.out.println("destination point: " + xDest + " " + yDest);
        for (int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                if(grid[i][j] != null && grid[i][j].getLevel() == 1){
                    gridRes[j][i] = 0;
                }
                else{
                    gridRes[j][i] = 1;
                }
            }
        }
        //print grid res
        // for (int i = 0; i < ROW; i++){
        //     System.out.print("{ ");
        //     for(int j = 0; j < COL; j++){
        //         System.out.print(gridRes[i][j] + ", ");
        //     }
        //     System.out.println("},");
        // }
        // System.out.println();
        Pair src = new Pair(xStart, yStart);
        Pair dest = new Pair(xDest, yDest);
        return AStarSearch(gridRes, src, dest);
    }

    static String AStarSearch(int[][] grid, Pair src, Pair dest) {

        // source valid check
        if (!isValid(src.first, src.second)) {
            //System.out.println("Source is invalid");
            return "false";
        }

        // destination valid check
        if (!isValid(dest.first, dest.second)) {
            //System.out.println("Destination is invalid");
            return "false";
        }

        // destination blocked check
        if (isBlocked(grid, dest.first, dest.second)) {
            //System.out.println("Destination is blocked");
            return "false";
        }

        boolean[][] closedList = new boolean[ROW][COL];
        memset(closedList, false, ROW);

        // Declare a 2D array of structure to hold the details of that cell
        cell[][] cellDetails = new cell[ROW][COL];

        int i, j;

        for (i = 0; i < ROW; i++) {
            for (j = 0; j < COL; j++) {
                cellDetails[i][j] = new cell();
                cellDetails[i][j].f = Integer.MAX_VALUE;
                cellDetails[i][j].g = Integer.MAX_VALUE;
                cellDetails[i][j].h = Integer.MAX_VALUE;
                cellDetails[i][j].parent_i = -1;
                cellDetails[i][j].parent_j = -1;
            }
        }

        // Initialising the parameters of the starting node
        i = src.first;
        j = src.second;
        cellDetails[i][j].f = 0;
        cellDetails[i][j].g = 0;
        cellDetails[i][j].h = 0;
        cellDetails[i][j].parent_i = i;
        cellDetails[i][j].parent_j = j;

        ArrayList<pPair> openList = new ArrayList<pPair>();

        // Put the starting cell on the open list and set its f as 0
        openList.add(new pPair(0, new Pair(i, j)));

        // We set this boolean value as false as initially the destination is not reached.
        boolean foundDest = false;

        while (!openList.isEmpty()) {
            pPair p = openList.get(0);

            //print p
            //System.out.println("p: " + p.first + " " + p.second.first + " " + p.second.second);

            // Remove this vertex from the open list
            openList.remove(0);

            // Add this vertex to the closed list
            i = p.second.first;
            j = p.second.second;
            closedList[i][j] = true;

            ArrayList<Pair> successors = new ArrayList<Pair>();
            if (isValid(i - 1, j)) {
                successors.add(new Pair(i - 1, j));
            }
            if (isValid(i + 1, j)) {
                successors.add(new Pair(i + 1, j));
            }
            if (isValid(i, j + 1)) {
                successors.add(new Pair(i, j + 1));
            }
            if (isValid(i, j - 1)) {
                successors.add(new Pair(i, j - 1));
            }
            
            // sort the successors, smallest calculateHValue to largest
            Collections.sort(successors, new Comparator<Pair>() {
                @Override
                public int compare(Pair p1, Pair p2) {
                    int h1 = calculateHValue(p1.first, p1.second, dest);
                    int h2 = calculateHValue(p2.first, p2.second, dest);
                    if (h1 < h2) {
                        return -1;
                    } else if (h1 > h2) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

            //print successors
            // System.out.println("successors: ");
            // for (Pair p1 : successors) {
            //     System.out.println(p1.first + " " + p1.second);
            // }

            // To store the 'g', 'h' and 'f' of the 4 successors
            int gNew, hNew, fNew;

            // Looping through all the successors
            for (Pair successor : successors) {
                int row = successor.first;
                int col = successor.second;

                if (isValid(row, col)) {
                    if (isDestination(row, col, dest)) {
                        // Set the Parent of the destination cell
                        cellDetails[row][col].parent_i = i;
                        cellDetails[row][col].parent_j = j;
                        //System.out.println("The destination cell is found\n");
                        foundDest = true;
                        return tracePath(cellDetails, dest);
                    } else if(!closedList[row][col] && !isBlocked(grid, row, col)){
                        gNew = cellDetails[i][j].g + 1;
                        hNew = calculateHValue(row, col, dest);
                        fNew = gNew + hNew;
                        //System.out.println("fNew: " + fNew);

                        if (cellDetails[row][col].f == Integer.MAX_VALUE || cellDetails[row][col].f > fNew) {
                            openList.add(new pPair(fNew, new Pair(row, col)));

                            // Update the details of this cell
                            cellDetails[row][col].f = fNew;
                            cellDetails[row][col].g = gNew;
                            cellDetails[row][col].h = hNew;
                            cellDetails[row][col].parent_i = i;
                            cellDetails[row][col].parent_j = j;
                        }
                    }
                }
            }
        }
        if (foundDest == false) {
            //System.out.println("Failed to find the Destination Cell\n");
            return "false";
        }
        return "false";
    }
}