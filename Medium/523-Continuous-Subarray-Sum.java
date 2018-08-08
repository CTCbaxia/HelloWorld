/*
MEDIUM
523. Continuous Subarray Sum
https://leetcode.com/problems/continuous-subarray-sum/description/

TIME: 0807 - 30min
RESULT:
NOTES: 
*/
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if(k == 0) return false;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for(int i = 0; i < nums.length; i++){
            sum[i + 1] = sum[i] + nums[i];
            int index = i - 1;
            while(index >= 0){
                if(k != 0){
                    if((sum[i + 1] - sum[index--]) % k == 0) return true; 
                }else{
                    if((sum[i + 1] - sum[index--]) == 0) return true;
                }
                
            }
            System.out.println(sum[i + 1]);
        }
        return false;
    }
}