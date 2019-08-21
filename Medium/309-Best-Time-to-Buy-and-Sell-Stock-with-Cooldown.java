/*
M
309. Best Time to Buy and Sell Stock with Cooldown
*/
/*
Dynamic Programming
buy[i]: the highest money we can get in buy state for i day 
(buy state means there is one hold in hand now)
sell[i]: the highest money we can get in sell state for i day
(sell state means there is NO hold in hand now)


buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);//need to check if i - 2 >= 0
sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);

Time: O(n)
Space: O(n)
*/
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0) return 0;
        int[] buy = new int[n], sell = new int[n];
        buy[0] = -prices[0];
        for(int i = 1; i < n; i++){
            buy[i] = Math.max(buy[i - 1], (i - 2 >= 0 ? sell[i - 2] : 0) - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[n - 1];
    }
}
