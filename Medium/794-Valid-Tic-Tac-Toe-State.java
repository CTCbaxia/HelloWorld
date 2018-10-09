/*
MEDIUM
794. Valid Tic-Tac-Toe State


TIME: 1009 - 40min
RESULT: 98% - 5ms
计算整个棋局：各自所走步数 + 棋盘赢局
*/
class Solution {
    public boolean validTicTacToe(String[] board) {
        int p1 = 0, p2 = 0;
        int[] row = new int[3];
        int[] col = new int[3];
        int diagonal = 0, antidiagonal = 0;
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                char c = board[i].charAt(j);
                if(c == 'X'){
                    p1++;
                    row[i]++;
                    col[j]++;
                    if(i == j) diagonal++;
                    if(i + j == board.length - 1) antidiagonal++;
                }else if(c == 'O'){
                    p2++;
                    row[i]--;
                    col[j]--;
                    if(i == j) diagonal--;
                    if(i + j == board.length - 1) antidiagonal--;
                }
            }
        }

        //termination
        boolean p1win = false, p2win =false;
        int wincount = 3;
        for(int i = 0; i < board.length; i++){
            if(row[i] == wincount || col[i] == wincount) p1win = true;
            if(row[i] == -wincount || col[i] == -wincount) p2win = true;
            
        }
        if(diagonal == wincount || antidiagonal == wincount) p1win = true;
        if(diagonal == -wincount || antidiagonal == -wincount) p2win = true;
        //calculate the result of the game
        
        //number
        if(!(p1 == p2 + 1 || p1 == p2)) return false;
        //win state
        if(p1win == true){
            if(p1 != p2 + 1 || p2win == true) return false;
        }
        if(p2win == true){
            if(p1 != p2 || p1win == true) return false;
        }
        return true;

    }
}