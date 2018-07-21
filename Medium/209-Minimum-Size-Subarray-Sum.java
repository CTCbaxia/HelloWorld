/*
MEDIUM
209. Minimum Size Subarray Sum
https://leetcode.com/problems/minimum-size-subarray-sum/description/

TIME: 0721 - 1h
RESULT: 26% - 3ms
NOTES:
1. 值不确定不能用 map: SUM[i,j] = SUM[0,j] - SUM[0, i - 1] >= s 
*/

/*
*/
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length == 0) return 0;
        int result = nums.length + 1;
        int left = 0; 
        int right = 0;
        int sum = nums[right];
        while(right < nums.length && left <= right){
                if(sum >= s){
                    if(left == right) return 1;
                    else{
                        result = Math.min(result, right - left + 1);
                        sum = sum - nums[left];
                        left++;   
                    }
                }else if(sum < s){
                    right++;
                    if(right >= nums.length) break;
                    sum = sum + nums[right];
                }
        }
        if(result == nums.length + 1) return 0;
        return result;
    }
}