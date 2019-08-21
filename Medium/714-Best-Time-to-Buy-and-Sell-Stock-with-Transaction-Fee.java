/*
M
714. Best Time to Buy and Sell Stock with Transaction Fee
*/
/*
Dynamic Programming
buy[i]: the highest money we can get in buy state for i day 
(buy state means there is one hold in hand now)
sell[i]: the highest money we can get in sell state for i day
(sell state means there is NO hold in hand now)

buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);

need to be in sell state in the end, so return sell[n - 1];
Time: O(n)
Space: O(1)
*/
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[] buy = new int[n], sell = new int[n];
        buy[0] = -prices[0];
        for(int i = 1; i < n; i++){
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);//pay fee when sell
        }
        return sell[n - 1];
    }
}




/*
Greedy
keep updating the min value
once there is a high value > min + fee, it means we can earn
we then do pending sell

Then we continue to see the next, but set new min = prices[today] - fee
because if we found price[i] < new min,
that means we can earn more by just sell at previous day and buy now
then if we meet a high one, we can earn more buy todays new buy


Open a new transaction only if prices[today] < prices[yesterday] - fee. That's why we set 'minimum = prices[i] - fee'

Time: O(n)
Space: O(1)
*/
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int res = 0;
        int min = prices[0];
        for(int i = 1; i < prices.length; i++){
            if(prices[i] < min){
                min = prices[i];
            }else if(prices[i] > min + fee){
                res += prices[i] - min - fee;//try sell when we can earn
                min = prices[i] - fee;// only if next lower than this value, we start to buy a new one, because we can earn more by adding this transaction
            }
        }
        return res;
    }
}




/*
TLE...
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
