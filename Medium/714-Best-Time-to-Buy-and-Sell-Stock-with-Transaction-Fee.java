/*
MEDIUM
714. Best Time to Buy and Sell Stock with Transaction Fee

*/
/*
DP
dp[i][j]: largest revenue between i and j

Initialization:

dp[i][i] = 0;

Transform:  dp[i][j] should always be >= 0
i  k   j
1) only do transaction with the [i],[j] value
    dp[i][j] = Math.max(dp[i][j], prices[j] - prices[i] - fee);
2) if do transaction between i and j
    dp[i][j] = Math.max(dp[i][j], dp[i][i + k] + dp[i + k + 1][j]);
    
Time: O(n^3)
Space: O(n^2)
*/
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][prices.length];
        for(int i = 0; i < dp.length; i++){
            dp[i][i] = 0;
        } 
        for(int i = dp.length - 1; i >= 0; i--){
            for(int j = i + 1; j < dp[i].length; j++){
                //if only do transaction with the [i],[j] value
                dp[i][j] = Math.max(dp[i][j], prices[j] - prices[i] - fee);
                for(int k = 0; k <= j - i - 1;  k++){
                    //if do transaction between [i],[j] value
                    dp[i][j] = Math.max(dp[i][j], dp[i][i + k] + dp[i + k + 1][j]);
                }
            }
        }
        return dp[0][prices.length - 1];
    }
}