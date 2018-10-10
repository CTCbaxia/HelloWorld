/*
MEDIUM
518. Coin Change


TIME: 
RESULT: 

*/
//DP 1D Array
class Solution {
    public int change(int amount, int[] coins) {
        if(coins.length == 0) return amount == 0 ? 1 : 0;
        
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        
        for(int i = 0; i < coins.length; i++){
            for(int j = coins[i]; j <= amount; j++){
                dp[j] += dp[j - coins[i]];//这里必须正着做因为每一轮你都会用到上一轮的结果，也就是 i - 1 的结果
            }
        }
        return dp[amount];
    }
}
//DP 2D Array
class Solution {
    public int change(int amount, int[] coins) {
        if(coins.length == 0) return amount == 0 ? 1 : 0;
        
        int[][] dp = new int[coins.length][amount + 1];
        for(int i = 0; i < coins.length; i++){
            dp[i][0] = 1;
        }
        
        for(int i = 0; i < coins.length; i++){
            for(int j = 1; j <= amount; j++){
                dp[i][j] = (i == 0) ? 0 : dp[i - 1][j]; //if we only use the elements before the i, how many choices
                if(j >= coins[i]){
                    dp[i][j] += dp[i][j - coins[i]];//if we want to use i, how many choices we got
                }
            }
        }
        return dp[coins.length-1][amount];
    }
}




class Solution {
    public int change(int amount, int[] coins) {
        return sumHelper(amount, coins, 0);
    }
    private int sumHelper(int target, int[] coins, int index){
        int count = 0;
        if(target == 0) return 1;
        if(target < 0) return 0;
        if(target > 0){
            for(int i = index; i < coins.length; i++){
                count += sumHelper(target - coins[i], coins, i);
            }
        }
        return count;
    }
}