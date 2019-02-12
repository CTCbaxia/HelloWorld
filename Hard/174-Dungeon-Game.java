/*
HARD
174. Dungeon Game

*/
/*
要找到每一个格子能够顺利到达终点所需的最小数值。
从终点开始走，能够对于过多的健康数值进行修正

want to find all min dp[i][j] along each road
and result should be max among all min dp[i][j]
dp[][] is the min health needed for each point to reach end


Time: O(mn)
Space: O(mn)
*/
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon.length == 0 || dungeon[0].length == 0) return 0;
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i < m + 1; i++){
            dp[i][n] = Integer.MAX_VALUE;
        }
        for(int j = 0; j < n + 1; j++){
            dp[m][j] = Integer.MAX_VALUE;
        }
        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;
        
        for(int i = m - 1; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                int minHealthNeed = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                dp[i][j] = minHealthNeed <= 0 ? 1 : minHealthNeed;
            }
        }
        return dp[0][0];
    }
}




/*
1D DP
*/
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon.length == 0 || dungeon[0].length == 0) return 0;
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n - 1] = 1;//最外面的一层 dp[n] 始终要为 Integer.MAX_VALUE
        
        for(int i = m - 1; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                int minHealthNeed = Math.min(dp[j], dp[j + 1]) - dungeon[i][j];
                dp[j] = minHealthNeed <= 0 ? 1 : minHealthNeed;
                System.out.println(dp[j]);
            }
        }
        return dp[0];
    }
}