/*
H
188. Best Time to Buy and Sell Stock IV
*/
/*
Dynamic Programming

buy[i][j]: you have j transaction limit for 0 - i day, and you have one hold in hand
sell[i][j]: you have j transaction limit for 0 - i day, and you don't have hold

buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i])
sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i])

Time: O(mn)
Space: O(mn)
*/
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if(n == 0) return 0;
        
        if(k > n/2) return maxP(prices);//equal to infinity transaction
        
        int[][] buy = new int[n][k + 1], sell = new int[n][k + 1];
        buy[0][0] = -prices[0];
        
        for(int i = 1;i < n; i++) buy[i][0] = Math.max(buy[i-1][0],-prices[i]);
        
        for(int j = 1;j < k + 1; j++) buy[0][j] = -prices[0];
        
        for(int i = 1;i < n;i++){
            for(int j = 1;j < k + 1; j++){
                buy[i][j] = Math.max(sell[i-1][j]-prices[i],buy[i-1][j]);
                sell[i][j] = Math.max(buy[i-1][j-1]+prices[i],sell[i-1][j]);
            }
        }
        // return Math.max(buy[n-1][k],sell[n-1][k]);
        return sell[n-1][k];
    }
    private int maxP(int[] prices){
        int res = 0;
        for(int i=0;i< prices.length; i++){
            if(i > 0 && prices[i] > prices[i-1]){
                res += prices[i] - prices[i-1];
            }
        }
        return res;
    }
}
