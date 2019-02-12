/*
4. MaximumMinimumPath
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
You are gonna climb mountains represented by a matrix. Each integer in the matrix represents the altitude at that point. You are supposed to climb from the top-left corner to the bottom-right corner and only move rightward or downward in each step.
You can have many paths to do so. Each path has a minimum altitude. Find the maximum among all the minimum altitudes of all paths.

 
[8, 4, 7]
[6, 5, 9]
3 paths  8-4-7-9 min: 4 8-4-5-9 min: 4 8-6-5-9 min: 5 return: 5
*/
/*
DFS
for each path, find the min along the way
when finish the path, update the max for the whole paths try

Time: O(2^n)
Space: O(m + n)
*/
class Solution4{
    int max;
    public int MaximumMinimumPath(int[][] matrix){
        if(matrix.length == 0 || matrix[0].length == 0) return 0;

        max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        dfs(matrix, min, 0, 0);
        return max;
    }
    private void dfs(int[][] matrix, int min, int i, int j){
        if(i >= matrix.length || j >= matrix[0].length) return;

        min = Math.min(min, matrix[i][j]);

        if(i == matrix.length - 1 && j == matrix[0].length - 1){
            max = Math.max(max, min);
            return;
        }

        dfs(matrix, min, i + 1, j);
        dfs(matrix, min, i, j + 1);
        return;
    }
}


/*
DP
dp[][] 从原点到 i, j 点的所有路径上的 min 值中的 max 值

!!! 注意初始化
*/
class Solution4{
    public int MaximumMinimumPath(int[][] matrix){
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];

        
        for(int i = 0; i < m + 1; i++){
            dp[i][0] = Integer.MIN_VALUE;
        }
        for(int j = 0; j < n + 1; j++){
            dp[0][j] = Integer.MIN_VALUE;
        }
        dp[0][1] = matrix[0][0];
        
        
        for(int i = 1; i < m + 1; i++){
            for(int j = 1; j < n + 1; j++){
                int fromLeft = Math.min(dp[i - 1][j], matrix[i - 1][j - 1]);
                int fromUp = Math.min(dp[i][j - 1], matrix[i - 1][j - 1]);
                dp[i][j] = Math.max(fromLeft, fromUp);
            }
        }
        return dp[m][n];

    }
}


