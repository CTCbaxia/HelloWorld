/*
M
130. Surrounded Regions
*/
/*
Mark the impossible part
1. mark all O point conncted to the boundary to A, which cannot be closed
2. mark all rest O point to X as it must be closed
3. mark all A back to O

X X X X           X X X X             X X X X
X X O X  ->       X X O X    ->       X X X X
X O X X           X 1 X X             X O X X
X O X X           X 1 X X             X O X X

Time: O(mn)
Space: O(mn)
*/
class Solution {
    int m, n;
    public void solve(char[][] board) {
        if(board.length == 0 || board[0].length == 0) return;
        m = board.length;
        n = board[0].length;
        // for row 0 and m - 1
        for(int j = 0; j < n; j++){
            if(board[0][j] == 'O'){
                findConnected(board, 0, j, 'O', 'A');
            }
            if(board[m - 1][j] == 'O'){
                findConnected(board, m - 1, j, 'O', 'A');
            }
        }
        
        // for col 0 and n - 1
        for(int i = 0; i < m; i++){
            if(board[i][0] == 'O'){
                findConnected(board, i, 0, 'O', 'A');
            }
            if(board[i][n - 1] == 'O'){
                findConnected(board, i, n - 1,'O', 'A');
            }
        }
        
        // for all center O, change to X
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O'){
                    findConnected(board, i, j, 'O', 'X');
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'A') board[i][j] = 'O';
            }
        }
        return;         
    }
    private void findConnected(char[][] board, int i, int j, char preVal, char newVal){
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        board[i][j] = newVal;
        for(int[] d : dirs){
            int x = i + d[0];
            int y = j + d[1];
            if(x >= 0 && y >= 0 && x < m && y < n && board[x][y] == preVal){
                findConnected(board, x, y, preVal, newVal);
            }
        }
    }
}