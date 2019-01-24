/*
MEDIUM
63. Unique Paths II

*/
/*
Dynamic Programming: 1D Array

Time: O(mn)
Space: O(n)
*/
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	if(obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;

    	int m = obstacleGrid.length;
    	int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 0; i < m; i++){
        	for(int j = 0; j < n; j++){
        		if(obstacleGrid[i][j] == 1){
        			dp[j] = 0;
        		}else{
        			if(j > 0) dp[j] += dp[j - 1];
        		}
        	}
        }
        return dp[n - 1];
    }
}
/*
test:
[
  [0,0,0],
  [0,1,0],
  [1,0,0]
]

*/
/*
Dynamic Programming: 2D Array

Time: O(mn)
Space: O(mn)
*/
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	if(obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;

    	int m = obstacleGrid.length;
    	int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int i = 0; i < m; i++){
        	for(int j = 0; j < n; j++){
        		if(obstacleGrid[i][j] == 1){
        			dp[i][j] = 0;
        		}else{
        			if(i > 0) dp[i][j] += dp[i - 1][j];
        			if(j > 0) dp[i][j] += dp[i][j - 1];
        		}
        	}
        }
        return dp[m - 1][n - 1];
    }
}