/*
724. Find Pivot Index
https://leetcode.com/problems/find-pivot-index/description/

TIME: 0713 - 30min
RESULT: 94% - 31ms
Notes: 

METHOD:
O(n) 的方法应该可以遍历完成。
对每个 nums[i] 计算左边和右边的 sum，判断是否相等。

sum_left + nums[i] + sum_right = SUM
sum_left == sum_right
nums[i] == SUM - 2 * sum_left

所以求 sum_left 就好。求值只需要循环一遍
*/

class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0;
        Map<Integer, Integer> preSum = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            sum = sum + nums[i];
            preSum.put(i, sum);// pre sum till i
        }

        for(int i = 0; i < nums.length; i++){
            if(i == 0){
                if(preSum.get(nums.length - 1) - nums[i] == 0) return i;
            }else{
                if(nums[i] == preSum.get(nums.length - 1) - 2 * preSum.get(i - 1)) return i;
            }
        }
        return -1;
    }
}