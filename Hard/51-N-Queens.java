/*
HARD
51. N-Queens

TIME: 
RESULT: 
*/
/*
对于每一行，不断尝试每一个 j ，做 DFS 看这条路径是否能够成立
如果能走完最后一行，到达底部，输出一个path的结果

对于matrix：
row: i 恒定
col: j 恒定
向上对角线: i + j 恒定
向下对角线: i - j 恒定

Time: O(N!)
for each line, we check N possibilities in the next line(remove at least 1 to avoid attack) - N*(N-1)*(N-2)...
T(n) = n*T(n-1)+o(n) 

Space: O(N*N)
*/
class Solution {
    public List<List<String>> solveNQueens(int n){
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        boolean[] cols = new boolean[n];//check each column
        boolean[] d1 = new boolean[2*n - 1];//check /
        boolean[] d2 = new boolean[2*n - 1];//check \
        dfs(board, 0, result, cols, d1, d2);
        return result;
    }
    private void dfs(char[][] board, int row, List<List<String>> result, boolean[] cols, boolean[] d1, boolean[] d2){
        if(row == board.length){//find a good solution
            construct(board, result);
            return;
        }
        for(int j = 0; j < board[0].length; j++){
            int id1 = row + j;//向上对角线 i + j 恒定
            int id2 = row - j + board.length - 1;//向下对角线 i - j 恒定
            if(!cols[j] && !d1[id1] && !d2[id2]){//if [row, j] is valid
                Arrays.fill(board[row], '.');
                board[row][j] = 'Q';
                cols[j] = true; d1[id1] = true; d2[id2] = true;//mark invalid
                dfs(board, row + 1, result, cols, d1, d2);
                cols[j] = false; d1[id1] = false; d2[id2] = false;//mark invalid
            }
        }
        return;
    }
    private void construct(char[][] board, List<List<String>> result){//output a path result
        List<String> res = new ArrayList<>();
        for(int i = 0; i < board.length; i++){
            res.add(new String(board[i]));
        }
        result.add(res);
    }
}