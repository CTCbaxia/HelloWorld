/*
M
参考 1144
If you can move an element by increading or decreasing
*/
/*
Greedy
only two cases
只比较在当前的case中，当前的数和前一个相比是否合格。如果不合格，就用最小的 move 让他合格。
因为如果他要同时移动到右边的数也合格（如果目前不合格的话）

Time: O(n)
Space: O(1)
*/
class Solution {
    public int movesToMakeZigzag(int[] nums) {
        if(nums.length <= 1) return 0;
        int case1 = check(nums, true, 1, nums[0]);
        int case2 = check(nums, false, 1, nums[0]);
        return Math.min(case1, case2);
    }
    private int check(int[] nums, boolean shouldInc, int i, int pre){
        if(i >= nums.length) return 0;
        if(shouldInc && pre < nums[i] || !shouldInc && pre > nums[i]){
            return 0 + check(nums, !shouldInc, i + 1, nums[i]);
        }else{
            return Math.abs(pre - nums[i]) + 1 + check(nums, !shouldInc, i + 1, pre + (shouldInc ? 1 : -1));
        } 
    }
}
