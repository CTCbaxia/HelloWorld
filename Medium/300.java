/*
300. Longest Increasing Subsequence
https://leetcode.com/problems/longest-increasing-subsequence/description/
*/

/*
For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
Note that there may be more than one LIS combination, it is only necessary for you to return the length.
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