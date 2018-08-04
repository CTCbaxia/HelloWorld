/*
283. Move Zeroes
https://leetcode.com/problems/move-zeroes/description/

TIME: 0804 - 30min
RESULT: 100% - 1ms

*/
class Solution {
    public void moveZeroes(int[] nums) {
        int pointer = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                int tmp = nums[i];
                nums[i] = nums[pointer];
                nums[pointer] = tmp;
                pointer++;
            }
        }
        return;
    }
}