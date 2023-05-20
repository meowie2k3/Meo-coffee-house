package Map;

import java.lang.Math;
import java.util.Set;
import java.util.Stack;

class Pair{
    int first, second;
    Pair(){}
    Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
}

class pPair{
    double first;
    Pair second;
    pPair(){}
    pPair(double first, Pair second){
        this.first = first;
        this.second = second;
    }
}

class cell{
    //Row and Column index of its parent
    //Note that 0 <= i <= ROW-1 & 0 <= j <= COL-1
    int parent_i, parent_j;
    //f = g + h
    double f, g, h;
}

public class AStarCalculation {

    //typedef pair<int, int> Pair;
    //typedef pair<double, pair<int, int>> pPair;

    public static final int ROW = 9;
    public static final int COL = 9;

    

    static void memset(boolean[][] arr, boolean value, int size){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                arr[i][j] = value;
            }
        }
    }

    static boolean isValid(int row, int col){
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL);
    }
    
    static boolean isUnBlocked(int grid[][], int row, int col)
    {
    // Returns true if the cell is not blocked else false
    if (grid[row][col] == 1)
        return (true);
    else
        return (false);
    }

    static boolean isDestination(int row, int col, Pair dest)
    {
        if (row == dest.first && col == dest.second)
            return (true);
        else
            return (false);
    }

    static double calculateHValue(int row, int col, Pair dest){
        //Manhattan distance
        //only 4 directions
        return ((double)Math.abs(row - dest.first) + Math.abs(col - dest.second));
    }

    static void tracePath(cell[][] cellDetails, Pair dest){
        System.out.println("\nThe Path is ");
        int row = dest.first;
        int col = dest.second;

        Stack<Pair> Path = new Stack<Pair>();

        while(!(cellDetails[row][col].parent_i == row && cellDetails[row][col].parent_j == col)){
            Path.push(new Pair(row, col));
            int temp_row = cellDetails[row][col].parent_i;
            int temp_col = cellDetails[row][col].parent_j;
            row = temp_row;
            col = temp_col;
        }

        Path.push(new Pair(row, col));
        while(!Path.empty()){
            Pair p = Path.peek();
            Path.pop();
            System.out.print("-> (" + p.first + ", " + p.second + ") ");
        }
        return;
    }

    static void AStarSearch(int[][] grid, Pair src, Pair dest){
        
        //source valid check
        if(!isValid(src.first, src.second)){
            System.out.println("Source is invalid");
            return;
        }

        //destination valid check
        if(!isValid(dest.first, dest.second)){
            System.out.println("Destination is invalid");
            return;
        }

        //destination blocked check
        if(!isUnBlocked(grid, dest.first, dest.second)){
            System.out.println("Destination is blocked");
            return;
        }

        //Create a closed list and initialise it to false which
        //means that no cell has been included yet
        //This closed list is implemented as a boolean 2D array
        boolean[][] closedList = new boolean[ROW][COL];
        memset(closedList, false, ROW);

        //Declare a 2D array of structure to hold the details
        //of that cell
        cell[][] cellDetails = new cell[ROW][COL];

        int i, j;

        for(i = 0; i < ROW; i++){
            for(j = 0; j < COL; j++){
                cellDetails[i][j] = new cell();
                cellDetails[i][j].f = Double.MAX_VALUE;
                cellDetails[i][j].g = Double.MAX_VALUE;
                cellDetails[i][j].h = Double.MAX_VALUE;
                cellDetails[i][j].parent_i = -1;
                cellDetails[i][j].parent_j = -1;
            }
        }

        //Initialising the parameters of the starting node
        i = src.first;
        j = src.second;
        cellDetails[i][j].f = 0.0;
        cellDetails[i][j].g = 0.0;
        cellDetails[i][j].h = 0.0;
        cellDetails[i][j].parent_i = i;
        cellDetails[i][j].parent_j = j;

        /*
        Create an open list having information as-
        <f, <i, j>>
        where f = g + h,
        and i, j are the row and column index of that cell
        Note that 0 <= i <= ROW-1 & 0 <= j <= COL-1
        This open list is implenented as a set of pair of pair.*/
        Set<pPair> openList = new java.util.HashSet<pPair>();

        //Put the starting cell on the open list and set its
        //f as 0
        openList.add(new pPair(0.0, new Pair(i, j)));

        //We set this boolean value as false as initially
        //the destination is not reached.
        boolean foundDest = false;

        while(!openList.isEmpty()){
            pPair p = openList.iterator().next();

            //Remove this vertex from the open list
            openList.remove(p);

            //Add this vertex to the closed list
            i = p.second.first;
            j = p.second.second;
            closedList[i][j] = true;

            /*
            Generating all the 4 successor of this cell

                    N
                    |
                    |
            W ----Cell---- E
                    |
                    |
                    S
            Cell-->Popped Cell (i, j)
            N --> North, S --> South, E --> East, W --> West
            */

            //To store the 'g', 'h' and 'f' of the 4 successors
            double gNew, hNew, fNew;

            //----------- 1st Successor (North) ------------

            //Only process this cell if this is a valid one
            if(isValid(i-1, j) == true){

                if(isDestination(i-1, j, dest)==true){
                    //Set the Parent of the destination cell
                    cellDetails[i-1][j].parent_i = i;
                    cellDetails[i-1][j].parent_j = j;
                    System.out.println("The destination cell is found\n");
                    tracePath(cellDetails, dest);
                    foundDest = true;
                    return;
                }
                //If the successor is already on the closed
                //or if it is blocked, then ignore it.
                //else do the following
                else if(closedList[i-1][j] == false && 
                        isUnBlocked(grid, i-1, j) == true){
                    gNew = cellDetails[i][j].g + 1.0;
                    hNew = calculateHValue(i-1, j, dest);
                    fNew = gNew + hNew;

                    if(cellDetails[i-1][j].f == Double.MAX_VALUE ||
                            cellDetails[i-1][j].f > fNew){
                        openList.add(new pPair(fNew, new Pair(i-1, j)));

                        //Update the details of this cell
                        cellDetails[i-1][j].f = fNew;
                        cellDetails[i-1][j].g = gNew;
                        cellDetails[i-1][j].h = hNew;
                        cellDetails[i-1][j].parent_i = i;
                        cellDetails[i-1][j].parent_j = j;
                    }
                }
            }

            //----------- 2nd Successor (South) ------------

            //Only process this cell if this is a valid one
            if(isValid(i+1, j) == true){
                //If the destination cell is the same as the
                //current successor
                if(isDestination(i+1, j, dest) == true){
                    //Set the Parent of the destination cell
                    cellDetails[i+1][j].parent_i = i;
                    cellDetails[i+1][j].parent_j = j;
                    System.out.println("The destination cell is found\n");
                    tracePath(cellDetails, dest);
                    foundDest = true;
                    return;
                }
                else if(closedList[i+1][j] == false &&
                        isUnBlocked(grid, i + 1, j) == true){
                    gNew = cellDetails[i][j].g + 1.0;
                    hNew = calculateHValue(i+1, j, dest);
                    fNew = gNew + hNew;

                    if(cellDetails[i + 1][j].f == Double.MAX_VALUE ||
                        cellDetails[i + 1][j].f > fNew){
                        openList.add(new pPair(fNew, new Pair(i+1, j)));

                        //Update the details of this cell
                        cellDetails[i+1][j].f = fNew;
                        cellDetails[i+1][j].g = gNew;
                        cellDetails[i+1][j].h = hNew;
                        cellDetails[i+1][j].parent_i = i;
                        cellDetails[i+1][j].parent_j = j;

                    }
                }
            }

            //----------- 3rd Successor (East) ------------

            if(isValid(i, j + 1) == true){
                if(isDestination(i, j + 1, dest) == true){
                    cellDetails[i][j + 1].parent_i = i;
                    cellDetails[i][j + 1].parent_j = j;
                    System.out.println("The destination cell is found\n");
                    tracePath(cellDetails, dest);
                    foundDest = true;
                    return;
                }
                
            }
        }
    }
}
