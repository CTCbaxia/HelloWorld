/*
MEDIUM
153. Find Minimum in Rotated Sorted Array
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/

TIME: 0902 - 20min
RESULT: 100% - 0ms

//最小值肯定在断层处
//注意题目并没有说一定有 rotate（所有的某一个位置处，可能就是没有该颠倒位）
*/

/*
SOLUTION 0: 简化版本
*/
class Solution {
    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        if(nums[lo] < nums[hi]) return nums[lo];

        while(hi > lo + 1){
            int mid = (lo + hi) / 2;
            if(nums[lo] < nums[mid]){//断层在右边
                lo = mid;
            }else if(nums[lo] > nums[mid]){//断层在左边
                hi = mid;
            }
        }
        return Math.min(nums[lo], nums[hi]);
    }
}

/*
SOLUTION 0: intuitive 版本
*/
class Solution {
    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        
        while(hi > lo + 1){
            
            int mid = (lo + hi) / 2;
            if(nums[lo] < nums[mid] && nums[hi] < nums[mid]){//断层在右边
                lo = mid;
            }else if(nums[lo] > nums[mid] && nums[hi] > nums[mid]){//断层在左边
                hi = mid;
            }else{
                return nums[lo];
            }
            
        }
        return nums[lo] > nums[hi] ? nums[hi] : nums[lo];
    }
}