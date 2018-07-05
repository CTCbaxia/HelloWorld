/*
121. Best Time to Buy and Sell Stock
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

RESULT: 100%
*/
class Solution {
    public int maxProfit(int[] prices) {

        if(prices.length == 0){
            return 0;
        }

        int buy_min = prices[0];
        int profit = 0;
        for(int i = 1; i < prices.length; i++){
            buy_min = Math.min(buy_min,prices[i - 1]);
            profit = Math.max(profit, prices[i] - buy_min);
        }
        
        return profit;
    }
}