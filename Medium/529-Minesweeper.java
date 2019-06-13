/*
MEDIUM
529. Minesweeper
*/
/*
DFS for each unclicked pos, count num of Adj Mines, and decide the next step

Time: O(mn)
Space:O(1)
*/
class Solution {
    int[][] directions = new int[][]{{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        //if it is M
        if(board[x][y] == 'M'){
            board[x][y] = 'X';
            return board;
        } 
        
        //if it is not M
        int numOfMines = countAdjMines(board, click);
        
        if(numOfMines == 0){//if it is B
            board[x][y] = 'B';
            for(int[] dir : directions){
                int newX = x + dir[0];
                int newY = y + dir[1];
                if(isValid(board, newX, newY) && board[newX][newY] == 'E'){
                    updateBoard(board, new int[]{newX, newY});
                }
            }
        }else{//if it should be num
            board[x][y] = (char)('0' + numOfMines);
        }
        return board;
    }
    // private void clickPos(char[][] board, int[] click, )
    private int countAdjMines(char[][] board, int[] pos){
        int count = 0;
        for(int[] dir : directions){
            int x = pos[0] + dir[0];
            int y = pos[1] + dir[1];
            if(isValid(board, x, y) && board[x][y] == 'M') count++;
        }
        return count;
    }
    private boolean isValid(char[][] board, int x, int y){
        if(x >= 0 && x < board.length && y >= 0 && y < board[0].length) return true;
        else return false;
    }
}