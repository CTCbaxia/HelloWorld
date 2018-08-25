/*
MEDIUM
33. Search in Rotated Sorted Array
https://leetcode.com/problems/search-in-rotated-sorted-array/description/

TIME: 0825 - 40min
RESULT: 95% - 8ms
NOTES:
1. discuss 里面有找偏移量的方法也不错
*/
class Solution {
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] == target) return mid; 
            
            if(nums[mid] <= nums[right]){//顺序区域
                if(nums[mid] > target){
                    right = mid - 1;
                }else if(nums[mid] < target){//不一定往右
                    if(nums[right] < target) right = mid - 1;
                    else left = mid + 1;
                }                
            }else{//中间有断层
                if(nums[mid] > target){//不一定往左
                    if(nums[left] > target) left = mid + 1;
                    else right = mid - 1;
                }else if(nums[mid] < target){
                    left = mid + 1;
                }              
            }
        }
        return -1;
    }

}