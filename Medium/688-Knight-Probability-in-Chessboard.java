/*
MEDIUM
688. Knight Probability in Chessboard

TIME: 
RESULT: 
NOTES:

*/
/*
BFS + DP(用queue太慢了)
用dp数组每次存储到达一点的所有路径

Time: O(K*N^2)
Space: O(N^2)
*/
class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        int[][] directions = new int[][]{{2,1},{-2,1},{2,-1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};
        double total = Math.pow(8, K);
        double[][] predp = new double[N][N];//要用double，否则会在数字很大的时候损失精度
        predp[r][c] = 1;

        while(K > 0){
            double[][] curdp = new double[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    for(int[] dir : directions){
                        int movei = i + dir[0];
                        int movej = j + dir[1];
                        if(movei >= 0 && movei < N && movej >= 0 && movej < N){
                            curdp[movei][movej] += predp[i][j];
                        }
                    }
                }
            }
            K--;
            predp = curdp;
        }
        double num = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                num += predp[i][j];
            }
        }
        System.out.println(num);
        return num / total;
    }
}









/*
DP 每走一步，计算棋盘所有点的路径数
没一步都迭代上一轮的结果
也就是每个 point 都累加它四面八方来的和（截止到上一轮）

Time: O(8^K) -- TLE
Space: O(1)

*/
class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        int[][] moves = new int[][]{{-2, 1},{-2, -1},{2, 1}, {2, -1}, {1, 2}, {1, -2},{-1, -2}, {-1, 2}};
        double total = Math.pow(8, K);
        double[][] predp = new double[N][N];
        predp[r][c] = 1;
        //we need to calculate how many paths will end up at point(i,j) from K steps
        for(int k = 0; k < K; k++){//there are K steps
            double[][] curdp = new double[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    //just for 8 directions
                    for(int[] move : moves){
                        int row = i + move[0];
                        int col = j + move[1];
                        if(row >= 0 && row < N && col >= 0 && col < N)
                            curdp[i][j] += predp[row][col];
                    }
                }
            }
            predp = curdp;
        }
        double num = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                num += predp[i][j];
            }
        }
        return num / total;
    }
        
}

/*
DFS 看每一条 path 走到最后（step == 0) 是不是能够在界内，在的话，返回 1 累加

Time: O(8^K) -- TLE
Space: O(1)

Improvement: Like the minimax in AI course, we save the state we have previously so we don't do the dfs again
but we need to also save how many steps left for every saving
*/
class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        double total = 1;
        int step = K;
        while(K-- > 0) total *= 8;
        return dfs(N, step, r, c) / total;
    }
    private double dfs(int N, int step, int i, int j){
        
        if(i < 0 || j < 0 || i >= N || j >= N) return 0;
        else{
            if(step == 0) return 1;
        }
        int res = 0;
        res += dfs(N, step - 1, i - 2, j + 1);
        res += dfs(N, step - 1, i - 2, j - 1);
        res += dfs(N, step - 1, i + 2, j + 1);//1
        res += dfs(N, step - 1, i + 2, j - 1);
        res += dfs(N, step - 1, i + 1, j - 2);
        res += dfs(N, step - 1, i - 1, j - 2);
        res += dfs(N, step - 1, i + 1, j + 2);//2
        res += dfs(N, step - 1, i - 1, j + 2);
        return res;
    }
}