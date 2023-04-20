package Map;

import java.lang.Math;

public class AStarCalculation {

    //typedef pair<int, int> Pair;
    //typedef pair<double, pair<int, int>> pPair;

    public static final int ROW = 9;
    public static final int COL = 9;

    class cell{
        int parent_i, parent_j;
        double f, g, h;
    };

    boolean isValid(int row, int col){
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL);
    }
    


}
