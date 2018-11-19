/*
122. Best Time to Buy and Sell Stock II
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/

TIME: 1011
RESULT: 99%, 1ms
NOTE: 
*/
/*
keep updating the min, buy when get larger number and update min as current number

Time: O(n)
Space: O(1)
*/
class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int res = 0;
        for(int i = 0; i < prices.length; i++){
            if(prices[i] > min){
                res += prices[i] - min;
                min = prices[i];
            }else{
                min = Math.min(min, prices[i]);
            }
        }
        return res;
    }
}









class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int min = prices[0];
        int sum = 0;
        for(int n : prices){
            if(n < min) min = n;
            else if(n > min){
                sum += n - min;
                min = n;
            }
            
        }
        return sum;
    }
    
}



/*
122. Best Time to Buy and Sell Stock II
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/

TIME: 0708
RESULT: 0%, 530ms ???
NOTE: dp should based on what you have already built (mind the order)
*/
class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0){
            return 0;
        }
        
        int[] dp = new int[prices.length];
        
        for(int i = prices.length - 1; i >= 0; i--){
            if( i == prices.length - 1){
                dp[i] = 0;
            }else{
                int choose_i = 0;
                int max_choose_i = 0;
                int not_choose_i = 0;
                //choose i
                for(int j = i + 1; j < prices.length; j++){
                    if(prices[j] > prices[i] ){
                        if(j + 1 < prices.length){
                            choose_i = prices[j] - prices[i] + dp[j + 1];
                            max_choose_i = Math.max(max_choose_i, choose_i);
                        }else{
                            choose_i = prices[j] - prices[i];
                            max_choose_i = Math.max(max_choose_i, choose_i);
                        }
                    }
                }
                
                //not choose i
                not_choose_i = dp[i + 1];
                dp[i] = Math.max(max_choose_i, not_choose_i);
            }
        }
        return dp[0];
    }
    
}