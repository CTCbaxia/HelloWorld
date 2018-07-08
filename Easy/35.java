/*
35. Search Insert Position
https://leetcode.com/problems/search-insert-position/description/

TIME: 0708
RESULT: 
*/

class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums[0] > target){
            return 0;
        }else if(nums[nums.length - 1] == target){
            return nums.length;
        }
        int position_left = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                return i;
            }else if(nums[i] < target){
                position_left = i;
            }
        }
        return (position_left + 1);
    }
}