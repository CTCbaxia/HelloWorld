/*
665. Non-decreasing Array
*/
/*
Check range:
Find at most one down trend, 
check the two side range, 
there has to be at least one num in the range

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] > nums[i + 1]){
                if(i == 0 || i + 1 == nums.length - 1){//首尾
                    count++;
                }else if(nums[i - 1] <= nums[i + 2]){
                    if(nums[i] <= nums[i + 2] || nums[i + 1] >= nums[i - 1]) count++;
                    else return false;
                }else{
                    return false;
                }
            }
            if(count > 1) return false;
        }
        return true;
    }
}