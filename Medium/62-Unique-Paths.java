/*
MEDIUM
62. Unique Paths

*/
/*
Dynamic Programming
matrix 上面每一个点的路径数，都是由其上，左两个点的路径数加和而来

因为 dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
所以 每个 point 的值只取决于他的上一个，和左边一个。
可以用一维数组按行计算 dp, 再从左至右计算 dp

Time: O(mn)
Space: O(n)
*/
class Solution {
    public int uniquePaths(int m, int n) {
        if(m == 0 || n == 0) return 0;
        
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 0; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}

/*
Follow-up: 继续缩小space
Dynamic Programming
matrix 上面每一个点的路径数，都是由其上，左两个点的路径数加和而来

因为 dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
所以 每个 point 的值只取决于他的上一个，和左边一个。
可以用一维数组按行计算 dp, 再从左至右计算 dp

follow-up:
Q: if n is quite larger than m?
A: switch (m,n)
Time: O(mn)
Space: O(min(m,n))
*/
class Solution {
    public int uniquePaths(int m, int n) {
        if(m == 0 || n == 0) return 0;
        if(n > m) return uniquePaths(n, m);//space: O(min(m,n))
        
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 0; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}

/*
Original
Dynamic Programming
matrix 上面每一个点的路径数，都是由其上，左两个点的路径数加和而来

Time: O(mn)
Space: O(mn)
*/
class Solution {
    public int uniquePaths(int m, int n) {
        if(m == 0 || n == 0) return 0;
        
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i > 0) dp[i][j] = dp[i - 1][j];
                if(j > 0) dp[i][j] += dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
