/*
MEDIUM
416. Partition Equal Subset Sum


TIME: 1008 - 2h
RESULT: 76% - 17ms

*/
/*
Dynamic Programming : 2D array 
find subset sum to target = sum/2
dp[i][j] means if we choose subsets from first i elements, could the subsets sum to j
dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];

Time: O(mn)
Space: O(mn)
*/
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int n : nums){
            sum += n;
        }
        if(sum % 2 != 0) return false;
        int target = sum / 2;//target for the subarray
        
        //then the problem becomes whether we can found subset with sum = target
        //initialize dp, choose nothing
        boolean[][] dp = new boolean[nums.length][target + 1];
        for(int i = 0; i < dp.length; i++){
            dp[i][0] = true;
        }
        //initialize dp, choose first element
        if(nums[0] < dp[0].length) dp[0][nums[0]] = true;
        
        //compute, i, j start from 1, because 0 is in initialization
        for(int i = 1; i < nums.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(j < nums[i]) dp[i][j] = dp[i - 1][j];//dp[i - 1][j - nums[i]] index not overflow
                else dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
            }
        }
        return dp[nums.length - 1][target];
    }
}



/*
Dynamic Programming : 1D array 
find subset sum to target = sum/2

start from nums i available, 
dp[j] means if we choose subsets from first i elements, could the subsets sum to j

因为二维 i 都取决于上一层的结果，所以可以变成一维
from: dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
to: dp[j] = dp[j] || dp[j - nums[i]];

Time: O(mn)
Space: O(mn)

*/
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int n : nums){
            sum += n;
        }
        if(sum % 2 != 0) return false;
        int target = sum / 2;//target for the subarray
        
        //then the problem becomes whether we can found subset with sum = target
        boolean[] dp = new boolean[target + 1];//initialize dp
        dp[0] = true;// choose nothing
        
        for(int i = 0; i < nums.length; i++){
            for(int j = target; j >= nums[i]; j--){//be careful that we need 倒着算
                /*
                dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                这里我们需要的是上一层计算出来的 dp[j - nums[i]]
                如果从前往后计算，我们会先更新 dp[j - nums[i]] 的值，那么再套用下面的公式，用的就是这一层的值了
                所以要在更新等式右边的值之前计算他，就需要倒着计算
                */
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}





















//for ms
//DP 1D Array

class Solution {
    public boolean canPartition(int[] nums) {
        if(nums.length == 0) return false;
        int sum = 0;
        for(int n : nums) sum += n;
        
        if(sum % 2 != 0) return false;
        int target = sum / 2;
        
        //then the problem becomes whether we can found subset with sum = target
        boolean[] dp = new boolean[target + 1];
        Arrays.fill(dp, false);
        dp[0] = true;
        
        for(int num : nums){
            for(int i = target; i > 0; i--){
                if(i >= num){
                    dp[i] = dp[i] || dp[i - num];
                }
            }
        }
        return dp[target];

    }

}

//DP 2D Array
class Solution {
    public boolean canPartition(int[] nums) {
        if(nums.length == 0) return false;
        int sum = 0;
        for(int n : nums) sum += n;
        
        if(sum % 2 != 0) return false;
        int target = sum / 2;
        
        //then the problem becomes whether we can found subset with sum = target
        boolean[][] dp = new boolean[nums.length][target + 1];
        
        //choose nothing
        for(int i = 0; i < dp.length; i++){
            dp[i][0] = true;
        }
        //if we only have first element to choose
        for(int i = 0; i < dp[0].length; i++){
            dp[0][i] = false;
        }
        if(nums[0] <= target) dp[0][nums[0]] = true;
        
        for(int i = 1; i < nums.length; i++){
            for(int j = 1; j < target + 1; j++){
                if(j < nums[i]) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
            }
        }
        return dp[nums.length - 1][target];
    }

}

//DFS
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int n : nums) sum += n;
        
        if(sum % 2 != 0) return false;
        int target = sum / 2;
        
        return sumHelper(nums, 0, target);
    }
    private boolean sumHelper(int[] nums, int index, int target){
        if(target == 0) return true;
        if(index >= nums.length) return false;
        if(target < nums[index]) return sumHelper(nums, index + 1, target);
        
        boolean resa = sumHelper(nums, index + 1, target - nums[index]);
        boolean resb = sumHelper(nums, index + 1, target);
        return resa || resb;
    }
}