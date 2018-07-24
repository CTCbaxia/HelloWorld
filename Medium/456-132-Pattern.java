/*
MEDIUM
456. 132 Pattern
https://leetcode.com/problems/132-pattern/description/

TIME: 0724 - 10min
RESULT: 
METHOD:

NOTES:

*/
/*
SOLUTION 1:

RESULT: TLE
METHOD: 三层遍历
*/
class Solution {
    public boolean find132pattern(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] + 1 < nums[j]);
                for(int k = j + 1; k < nums.length; k++){
                    if(nums[k] < nums[j] && nums[k] > nums[i]) return true;
                }
            }
        }
        return false;
    }
    
}