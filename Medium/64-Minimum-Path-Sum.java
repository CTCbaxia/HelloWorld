/*
64. Minimum Path Sum
*/
/*
Dynamic Programming - No Extra Space

min the total path --> min every subpath

Time: O(mn)
Space: O(1)
*/
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i > 0 && j > 0) grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
                else if(i > 0) grid[i][j] += grid[i - 1][j];
                else if(j > 0) grid[i][j] += grid[i][j - 1];
            }
        }
        return grid[m - 1][n - 1];
    }
}




/*
Dynamic Programming -- 2D

min the total path --> min every subpath

Time: O(mn)
Space: O(mn)
*/
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                dp[i][j] = grid[i][j];
                if(i > 0 && j > 0) dp[i][j] += Math.min(dp[i - 1][j], dp[i][j - 1]);
                else if(i > 0) dp[i][j] += dp[i - 1][j];
                else if(j > 0) dp[i][j] += dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}




/*
Dynamic Programming -- 1D

min the total path --> min every subpath

Time: O(mn)
Space: O(n)
*/
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        
        int[] dp = new int[n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int min = grid[i][j];
                if(i > 0 && j > 0) dp[j] = min + Math.min(dp[j], dp[j - 1]);
                else if(i > 0) dp[j] = min + dp[j];
                else if(j > 0) dp[j] = min + dp[j - 1];
                else dp[j] = min;
            }
        }
        return dp[n - 1];
    }
}