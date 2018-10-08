/*
MEDIUM
416. Partition Equal Subset Sum


TIME: 1008 - 2h
RESULT: 76% - 17ms

*/
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
        //if we only have one thing to choose
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