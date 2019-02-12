/*
322. Coin Change

Time: O(mn)
Space: O(mn)
*/
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for(int i = 0; i < dp.length; i++){
            dp[i][0] = 0;
        }
        for(int j = 1; j < dp[0].length; j++){
            dp[0][j] = j >= coins[0] ? dp[0][j - coins[0]] + 1 : amount + 1;
        }
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                int notChoose = dp[i - 1][j];
                int choose = (j - coins[i] >= 0) ? dp[i][j - coins[i]] + 1 : amount + 1;
                dp[i][j] = Math.min(notChoose, choose);
            }
        }
        return dp[coins.length - 1][amount] > amount ? -1 : dp[coins.length - 1][amount];
    }
}


//1D Array
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        
        dp[0] = 0;
        
        for(int i = 0; i < coins.length; i++){
            for(int j = 0; j < amount + 1; j++){
                //对于一维来说，j < coins[i] 时候数值不变
                if(j >= coins[i]) dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}








class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for(int i = 0; i < dp.length; i++){
            dp[i][0] = 0;
        }
        for(int j = 1; j < dp[0].length; j++){
            dp[0][j] = j >= coins[0] && dp[0][j - coins[0]] >= 0 ? dp[0][j - coins[0]] + 1 : -1;
        }
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                int notChoose = dp[i][j] = dp[i - 1][j];
                int choose = j >= coins[i] && dp[i][j - coins[i]] >= 0 ? dp[i][j - coins[i]] + 1 : -1;
                
                if(notChoose == -1 && choose == -1) dp[i][j] = -1;
                else if(notChoose == -1) dp[i][j] = choose;
                else if(choose == -1) dp[i][j] = notChoose;
                else dp[i][j] = Math.min(notChoose, choose);
                
            }
        }
        return dp[coins.length - 1][amount];
    }
}