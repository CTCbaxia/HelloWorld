/*
MEDIUM
348. Design Tic-Tac-Toe
https://leetcode.com/problems/design-tic-tac-toe/description/

TIME: 10.05 - 1h
RESULT: 89% - 65ms

*/
/*
每次有人下棋，就看下的那一个子，有没有能赢

Time: O(n)
Space: O(1)
*/
class TicTacToe {
    private int[][] grid;
    private int size;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        grid = new int[n][n];
        size = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        grid[row][col] = player;
        int theRow = 0, theCol = 0, theDiag = 0, theAntiDiag = 0;
        for(int i = 0; i < size; i++){//只用看有更新这一行的
            if(grid[row][i] == player) theRow++;
        }
        for(int i = 0; i < size; i++){//只用看有更新这一列的
            if(grid[i][col] == player) theCol++;
        }
        for(int i = 0; i < size; i++){
            if(grid[i][i] == player) theDiag++;
        }
        for(int i = 0; i < size; i++){
            if(grid[i][size - i - 1] == player) theAntiDiag++;
        }
        
        if(theRow == size || theCol == size || theDiag == size || theAntiDiag == size) return player;
        else return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */










 
/*
SOLUTION REFERENCE: 
O(1)
也是自己想的但是有稍微参考答案。
构造函数和类同名，如果有多个构造函数，根据传参的不同来确定用那个构造函数。相当于一个对象以及其属性的初始化。
所以一般在构造函数外面来声明对象需要的一些属性。

*/
class TicTacToe {
    private int[] rows;//the sum of every row
    private int[] cols;//the sum of every col
    private int diagnose, antidiagnose, size;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        size = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int sign = 0;
        if(player == 1) sign = 1;
        else if(player == 2) sign = -1;
        
        rows[row] += sign;
        cols[col] += sign;
        if(row == col) diagnose += sign;
        if(row + col == size - 1) antidiagnose += sign;
        
        if(rows[row] == sign * size || cols[col] == sign * size || diagnose == sign * size || antidiagnose == sign * size) return player;
        else return 0;
    }
}
/*
SOLUTION 0: 
O(n)
每次遍历四遍n

*/
/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */


class TicTacToe {
    private int[][] grid;
    private int size;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        grid = new int[n][n];
        size = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        grid[row][col] = player;
        int theRow = 0, theCol = 0, theDiag = 0, theAntiDiag = 0;
        for(int i = 0; i < size; i++){
            if(grid[row][i] == player) theRow++;
        }
        for(int i = 0; i < size; i++){
            if(grid[i][col] == player) theCol++;
        }
        for(int i = 0; i < size; i++){
            if(grid[i][i] == player) theDiag++;
        }
        for(int i = 0; i < size; i++){
            if(grid[i][size - i - 1] == player) theAntiDiag++;
        }
        
        if(theRow == size || theCol == size || theDiag == size || theAntiDiag == size) return player;
        else return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */