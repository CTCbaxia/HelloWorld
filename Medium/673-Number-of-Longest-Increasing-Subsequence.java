/*
MEDIUM
673. Number of Longest Increasing Subsequence

TIME: 
RESULT: 
NOTES:
*/

/*
Use two array to store info

len: max len that end at i
count: paths that has max len end at i, it should be cumulative for paths

two for loop
if the nums[j] < nums[i], check the len

Time: O(n)
Space: O(n)
*/
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int[] len = new int[nums.length];//max len that end at i
        int[] count = new int[nums.length];//how many number of that len path end at i
        int result = 0;
        int maxlen = 0;
        for(int i = 0; i < nums.length; i++){
        	len[i] = 1;
            count[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    if(len[j] + 1 == len[i]) count[i] += count[j];
                    else if(len[j] + 1 > len[i]){
                        len[i] = len[j] + 1;
                        count[i] = count[j];
                    }
                }
            }
            if(len[i] == maxlen) result += count[i];
            else if(len[i] > maxlen){
                maxlen = len[i];
                result = count[i];
            }
        }
        
        return result;
    }
}












//2D array
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        
        int[][] dp = new int[2][nums.length];
        dp[0][0] = 1;
        dp[1][0] = 1;
        int maxlen = 1;
        for(int i = 1; i < nums.length; i++){
            int max = 0;//the max value of dp[0][j] that satisfy nums[j] < nums[i]
            int count = 1;//count should be at least 1, youself
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    if(dp[0][j] > max){
                        count = dp[1][j];
                        max = dp[0][j];
                    }else if(dp[0][j] == max){
                        count += dp[1][j];
                    }
                } 
            }
            dp[1][i] = count;
            dp[0][i] = max + 1;
            maxlen = Math.max(maxlen, dp[0][i]);
        }
        int result = 0;
        for(int i = 0; i < nums.length; i++){
            if(dp[0][i] == maxlen) result += dp[1][i];
        }
        return result;
    }
}