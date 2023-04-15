package Map;

import Entity.Ball;

public class Board {
    private Ball[][] board = new Ball[9][9];
    
    //board[i][j].color: null -> không có, 1 -> màu đỏ, 2 -> màu xanh, 3 -> màu vàng, 4 -> màu tím, 5 -> màu xám, 6 -> màu cam, 7 -> màu đen
    //board[i][j].level: 0 -> nhỏ, 1 -> lớn

    public Board(){
        
    }
    
    //xóa ball
    public void deleteBall(int x, int y){
        board[x][y] = null;
    }

    //thêm ball nhỏ
    public void addSmallBall(int x, int y, int color){
        board[x][y] = new Ball(x, y, color, 0);  
    }

    //thêm ball lớn
    public void addBigBall(int x, int y, int color){
        board[x][y] = new Ball(x, y, color, 1);
    }

    //kiểm tra xóa ball, khi nào: ball từ nhỏ -> lớn, ball mới di chuyển đến vị trí mới, hưng sẽ gọi
    public void checkDeleteBall(int x, int y){
        // count1 sẽ đếm số bóng hàng ngang
        int count1 = 1;

        // kiểm tra hàng ngang phải
        int i = x;
        int j = y;
        while (j>=0 && j<8){
            if (board[i][j].getColor() == board[i][j+1].getColor()){
                count1++;
                j++;
            }
            else break;
        }

        // kiểm tra hàng ngang trái
        j = y;
        while (j>0 && j<=8){
            if (board[i][j].getColor() == board[i][j-1].getColor()){
                count1++;
                j--;
            }
            else break;
        }

        //xóa bóng
        if (count1 >= 5){
            // xóa bóng hàng ngang
            j = y;
            while (j>=0 && j<8){
                if (board[i][j].getColor() == board[i][j+1].getColor()){
                    deleteBall(i, j);
                    j++;
                }
                else break;
            }

            // xóa bóng hàng ngang
            j = y;
            while (j>0 && j<=8){
                if (board[i][j].getColor() == board[i][j-1].getColor()){
                    deleteBall(i, j);
                    j--;
                }
                else break;
            }
        }

        // count2 sẽ đếm số bóng hàng dọc
        int count2 = 1;

        // kiểm tra hàng dọc trên
        i = x;
        j = y;
        while (i>=0 && i<8){
            if (board[i][j].getColor() == board[i+1][j].getColor()){
                count2++;
                i++;
            }
            else break;
        }

        // kiểm tra hàng dọc dưới
        i = x;
        while (i>0 && i<=8){
            if (board[i][j].getColor() == board[i-1][j].getColor()){
                count2++;
                i--;
            }
            else break;
        }

        //xóa bóng
        if (count2 >= 5){
            // xóa bóng hàng dọc trên
            i = x;
            while (i>=0 && i<8){
                if (board[i][j].getColor() == board[i+1][j].getColor()){
                    deleteBall(i, j);
                    i++;
                }
                else break;
            }

            // xóa bóng hàng dọc dưới
            i = x;
            while (i>0 && i<=8){
                if (board[i][j].getColor() == board[i-1][j].getColor()){
                    deleteBall(i, j);
                    i--;
                }
                else break;
            }
        }

        // count3 sẽ đếm số bóng hàng chéo xuôi
        int count3 = 1;

        // kiểm tra hàng chéo xuôi trên
        i = x;
        j = y;
        while (i>=0 && i<8 && j>=0 && j<8){
            if (board[i][j].getColor() == board[i+1][j+1].getColor()){
                count3++;
                i++;
                j++;
            }
            else break;
        }

        // kiểm tra hàng chéo xuôi dưới
        i = x;
        j = y;
        while (i>0 && i<=8 && j>0 && j<=8){
            if (board[i][j].getColor() == board[i-1][j-1].getColor()){
                count3++;
                i--;
                j--;
            }
            else break;
        }

        //xóa bóng
        if (count3 >= 5){
            // xóa bóng hàng chéo xuôi trên
            i = x;
            j = y;
            while (i>=0 && i<8 && j>=0 && j<8){
                if (board[i][j].getColor() == board[i+1][j+1].getColor()){
                    deleteBall(i, j);
                    i++;
                    j++;
                }
                else break;
            }

            // xóa bóng hàng chéo xuôi dưới
            i = x;
            j = y;
            while (i>0 && i<=8 && j>0 && j<=8){
                if (board[i][j].getColor() == board[i-1][j-1].getColor()){
                    deleteBall(i, j);
                    i--;
                    j--;
                }
                else break;
            }
        }

        // count4 sẽ đếm số bóng hàng chéo ngược
        int count4 = 1;

        // kiểm tra hàng chéo ngược trên
        i = x;
        j = y;
        while (i>=0 && i<8 && j>0 && j<=8){
            if (board[i][j].getColor() == board[i+1][j-1].getColor()){
                count4++;
                i++;
                j--;
            }
            else break;
        }

        // kiểm tra hàng chéo ngược dưới
        i = x;
        j = y;
        while (i>0 && i<=8 && j>=0 && j<8){
            if (board[i][j].getColor() == board[i-1][j+1].getColor()){
                count4++;
                i--;
                j++;
            }
            else break;
        }

        //xóa bóng
        if (count4 >= 5){
            // xóa bóng hàng chéo ngược trên
            i = x;
            j = y;
            while (i>=0 && i<8 && j>0 && j<=8){
                if (board[i][j].getColor() == board[i+1][j-1].getColor()){
                    deleteBall(i, j);
                    i++;
                    j--;
                }
                else break;
            }

            // xóa bóng hàng chéo ngược dưới
            i = x;
            j = y;
            while (i>0 && i<=8 && j>=0 && j<8){
                if (board[i][j].getColor() == board[i-1][j+1].getColor()){
                    deleteBall(i, j);
                    i--;
                    j++;
                }
                else break;
            }
        }

    }

}
