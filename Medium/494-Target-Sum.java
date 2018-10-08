/*
494. Target Sum
https://leetcode.com/problems/target-sum/description/

TIME: 1008 - 20min
RESULT: 100% - 7ms

*/

//------2 ROUND FOR MS------------------------------------------------------------
/*
DP + 1D Array
just need to make sum of subset = (S + sumofnums) / 2
and the question becomes subset of nums with sum to a target
*/
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int n : nums) sum += n;
        if((S + sum) % 2 > 0 || sum < S) return 0;
        return findSubset(nums,(S + sum)/2);
    }
    private int findSubset(int[] nums, int target){
        int[] dp = new int[target + 1];
        dp[0] = 1;
        
        //for every n in nums, we want to see what if we add n, will there be any solution?
        for(int n : nums){
            for(int i = target; i >= n; i--){
                dp[i] += dp[i - n];
            }
        }
        return dp[target];
    }
}



//DFS + Brute Force
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        List<Integer> result = new ArrayList<Integer>();
        findHelper(nums, 0, S, result);
        return result.size();
    }
    private void findHelper(int[] nums, int index, int target, List<Integer> result){
        if(index == nums.length && target == 0){
            result.add(0);
            return;
        }
        else if(index >= nums.length) return;
        findHelper(nums, index + 1, target - nums[index], result);
        findHelper(nums, index + 1, target + nums[index], result);
        return;
    }
}





//------1 ROUND------------------------------------------------------------
/*
METHOD:
穷举迭代法

RESULT: TIME LIMITED
*/
class Solution {
    public int findTargetSumWays(int[] nums, int S) {

        int res = 0;
        List<Integer> result = new ArrayList<Integer>();
        result.add(0);
        for(int i = 0; i < nums.length; i++){
            result = sub(nums[i], result);
            
        }
        
        for(int j = 0; j < result.size(); j++){
            if( result.get(j) == S){
                res = res + 1;
            }
        }
        return res;
        
    }
    private List<Integer> sub(int number, List<Integer> sum){
        List<Integer> next = new ArrayList<Integer>();
        for(int i = 0; i < sum.size(); i++){
            next.add(sum.get(i) + number);
        }
        for(int i = 0; i < sum.size(); i++){
            next.add(sum.get(i) - number);
        }
        return next;
    }
}



/*
DP:
METHOD：
构造二维数组，存下到每个元素为止，要加和到某个值（s）的方案数

RESULT: 82%
*/

class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int i:nums){
            sum = sum + i; // i represents all elements in array
        }
        if (S > sum || S < -sum){
            return 0;
        }else{
            int[][] dp = new int [nums.length][2*sum + 1];
            
            //initialize the first row
            //for(int i = 0; i < 2*sum + 1; i++){
            //    dp[0][i] = 0;
            //}
            if(Math.abs(nums[0]) == 0){
                dp[0][sum] = 2;
            }else{
                dp[0][sum + Math.abs(nums[0]) ] = 1;
                dp[0][sum - Math.abs(nums[0]) ] = 1;
            }

            
            for(int i = 1; i < nums.length; i++){
                for(int j = 0; j < 2*sum + 1; j++){

                    if(j - nums[i] >= 0 && j + nums[i] < 2*sum + 1){
                        
                        dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]];
                        
                    }else if (j - nums[i] < 0 && j + nums[i] < 2*sum + 1){
                        
                        dp[i][j] = dp[i - 1][j + nums[i]];
                        
                    }else if (j - nums[i] >= 0 && j + nums[i] >= 2*sum + 1){
                        dp[i][j] = dp[i - 1][j - nums[i]];
                    }else{
                        dp[i][j] = 0;
                    }
                }
            }
            return dp[nums.length - 1][S + sum];
        }
    }


}
