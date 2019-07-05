/*
665. Non-decreasing Array
*/
/*
Greedy - Check range between i and i+1
Find the down(fail) trend, 
Correction can be made in either of two ways:

1) Make the i-th number smaller or equal to i + 1 -th number (move down)
2) Make the i + 1 -th number equal to i-th number (move up)

move down has no risk for the future trend, but move up has
so prefer to move down, which is modifying the i-th number


Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] > nums[i + 1]){
                if(i - 1 < 0 || nums[i - 1] <= nums[i + 1]){
                    //change nums[i] by move it down
                    nums[i] = nums[i + 1];
                }else{
                    //change nums[i + 1] by move it up
                    nums[i + 1] = nums[i];
                }
                count++;
                if(count > 1) return false;
            }
        }
        return true;
    }
}



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
                if(i == 0 || i + 1 == nums.length - 1){//如果在首尾，肯定可以解决
                    count++;
                }else if(nums[i - 1] <= nums[i + 2]){
                    // move i + 1, or move i
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