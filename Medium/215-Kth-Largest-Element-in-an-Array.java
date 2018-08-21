/*
MEDIUM
215. Kth Largest Element in an Array
https://leetcode.com/problems/kth-largest-element-in-an-array/description/

TIME: 0820 - 5min
RESULT: 57% - 9ms
NOTES: 

*/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int index = nums.length - k;
        return nums[index];
    }
}
