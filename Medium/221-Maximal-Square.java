/*
221. Maximal Square
*/
/*
Dynamic Programming

dp[i][j] : 以 [i][j] 为右下角的正方形的最大 size
如果该点为 1 ，就看左，上，左上三个点的最大 size
取三个里面的最小值，加上 1 
得到 dp[i][j]

画图辅助比较好

Time: O(mn)
Space: O(mn)
*/
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || j == 0 || matrix[i][j] == '0'){//如果在边界，就看本身是不是构成 square。0 也是 0
                    dp[i][j] = matrix[i][j] - '0';
                }else{
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }
}