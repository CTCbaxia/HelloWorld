/*
H
37. Sudoku Solver
*/
/*
Backtracking + maintain valid boolean array

Time: O(9^m) m is the empty point
Space: O(81)
*/
class Solution {
    boolean[][] cols = new boolean[9][9];
    boolean[][] rows = new boolean[9][9];
    boolean[][] subs = new boolean[9][9];
    public void solveSudoku(char[][] board) {
        //initialization
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.') continue;
                int num = board[i][j] - '0' - 1;
                int k = i / 3 * 3 + j / 3;
                rows[i][num] = true;
                cols[j][num] = true;
                subs[k][num] = true;
            }
        }
        backtracking(board, 0, 0);
    }
    private boolean backtracking(char[][] board, int i, int j){
        if(i * 9 + j >= 81) return true;
        //next point
        int y = (j + 1) % 9;
        int x = y == 0 ? i + 1 : i;
        
        if(board[i][j] != '.') return backtracking(board, x, y);//if this point has num, try next point
        int k = i / 3 * 3 + j / 3;//compute for sub index
        for(int num = 0; num < 9; num++){
            if(!rows[i][num] && !cols[j][num] && !subs[k][num]){
                rows[i][num] = true;
                cols[j][num] = true;
                subs[k][num] = true;
                board[i][j] = (char) (num + 1 + '0');//填上正确的值
                if(backtracking(board, x, y)) return true;
                rows[i][num] = false;
                cols[j][num] = false;
                subs[k][num] = false;
                board[i][j] = '.';//backtracking 抹除，以便下一次 path 走到的时候可以顺利再次赋值
            }
        }
        return false;
    }
}
