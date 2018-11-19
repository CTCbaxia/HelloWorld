/*
121. Best Time to Buy and Sell Stock
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

RESULT: 100%
*/
/*
Keep min value, Keep max profit

Time: O(n)
Space: O(1)
*/
class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int res = 0;
        for(int i = 0; i < prices.length; i++){
            res = Math.max(res, prices[i] - min);//the first day we cannot sale
            min = Math.min(prices[i], min);
        }
        return res;
    }
}





class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int min = prices[0];
        int res = 0;
        for(int n : prices){
            if(n > min) res = Math.max(res, n - min);
            else min = n;
        }
        return res;
    }
}



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