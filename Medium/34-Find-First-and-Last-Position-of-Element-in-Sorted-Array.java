/*
MEDIUM
34. Find First and Last Position of Element in Sorted Array
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/

TIME: 0825 - 40min
RESULT: 100% - 4ms
NOTES:
1. 我的思路是先找到一个等值点，然后左右扩散
2. 还有一种思路是先找到最左边的点，再找到最右边的点
*/
//find one target first
//for the left part, the next position find is the right side of the next generation of searching
//for the right part, the next position find is the left side of the next generation of searching
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        int left = 0, right = nums.length - 1;
        
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] > target) right = mid - 1;
            else if(nums[mid] < target) left = mid + 1;
            else{
                left = mid;
                right = mid;
                while(left != -1){
                    result[0] = left;
                    left = nextSearch(nums, target, 0, left - 1);
                }
                while(right != -1){
                    result[1] = right;
                    right = nextSearch(nums, target, right + 1, nums.length - 1);
                }
                break;
            }
        }
        return result;
        
    }
    private int nextSearch(int[] nums, int target, int left, int right){
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] ==  target) return mid;
            else if(nums[mid] > target) right = mid - 1;
            else left = mid + 1;
        }        
        return -1;
    }
    
}