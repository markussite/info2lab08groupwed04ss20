package de.htwberlin;


public class Chessboard {

    int[][] chessboard;
    int solutions = 0;

    public Chessboard(int r, int c){
        chessboard = new int[r][c];
    }

    public static void main(String[] args) {
	    Chessboard chessboard = new Chessboard(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
	    //chessboard.matrix();
	    //chessboard.janiksrekursivematrix(7,7);
	    chessboard.placing(0,0);
    }

    /*    0  1  2  3  4  5  6  7
        0[0][0][0][0][0][0][0][0]
        1[0][0][0][0][0][0][0][0]
        2[0][0][0][0][0][0][0][0]
        3[0][0][0][0][0][0][0][0]
        4[0][0][0][0][0][0][0][0]
        5[0][0][0][0][0][0][0][0]
        6[0][0][0][0][0][0][0][0]
        7[0][0][0][0][0][0][0][0]
         */
    public void matrix(){
        //defintion(initialition )
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                chessboard[i][j] = 0; //j= links nach rechts(0-7) und i = oben nach unten(0-7)
            }
        }
    }

    public int janiksrekursivematrix(int r, int c){
        if(r ==-1 || c == -1){
            return 0;
        }
        chessboard[r][c] = 0;
        if(c == 0){
            return janiksrekursivematrix(r-1,c+(chessboard.length-1));
        } else
            return janiksrekursivematrix(r,c-1);
    }

    // platzierten und checken
    public int onBoard(int r, int c) {
        if(r < 0 || c < 0 || r > (chessboard.length-1) || c > (chessboard.length-1)) //checks if it is valid on the board
        {
            return -1;
        }
        return chessboard[r][c]; //returns what the value is at the valid point
    }

    public int validMove(int r, int c) {
        for (int i = 0; i < chessboard.length; i++)
        {
            if(onBoard(r, i) == 1) //checks side to side
            {
                return -1;
            }
            if(onBoard(i, c) == 1) //checks up and down
            {
                return -1;
            }
            if(onBoard(r-i,c-i) == 1){ // checks diagonal from right bottom to left bottom
                return -1;
            }
            if(onBoard(r-i,c+i) == 1){ // checks diagonal from bottom left to right top
                return -1;
            }
        }
        return 0;
    }

    public void placing(int r, int c) {
        if(onBoard(r, c) != -1) {
            if(validMove(r, c) == 0) //if the move is valid (0) then it puts a 1 at those coordinates and moves on to the next method (RowTwo)
            {
                chessboard[r][c] = 1;
                if(r < (chessboard.length-1) ) {
                    placing(r+1, 0);
                } else {
                    solutions++;
                    System.out.println(toString());
                }
            }
            chessboard[r][c] = 0;
            placing(r, c + 1);
        }
    }

    public String toString(){
        System.out.println(print());
        String ans = "Solution: ";
        ans += solutions;

        return ans;
    }

    public String print()
    {
        String sol = "Solution: " + solutions;
        String result = "\n";
        for (int r = 0; r < chessboard.length; r++) {
            for(int c = 0; c < chessboard[r].length; c++) {
                result += chessboard[r][c] + "\t";
            }
            result += "\n";
        }
        return result;
    }
}
