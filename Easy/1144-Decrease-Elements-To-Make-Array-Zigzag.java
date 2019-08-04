/*
E
1144. Decrease Elements To Make Array Zigzag
*/
/*
only two cases

Time: O(n)
Space: O(1)
*/
class Solution {
    public int movesToMakeZigzag(int[] nums) {
        if(nums.length <= 1) return 0;
        int case1 = check(nums, 0);
        int case2 = check(nums, 1);
        return Math.min(case1, case2);
    }
    private int check(int[] nums, int i){
        if(i >= nums.length) return 0;
        int left = i == 0 ? nums[i] + 1 : nums[i - 1];
        int right = i == nums.length - 1 ? nums[i] + 1  : nums[i + 1];
        int bar = Math.min(left, right);
        int move = bar > nums[i] ? 0 : nums[i] - (bar - 1);
        return move + check(nums, i + 2);
    }
}
