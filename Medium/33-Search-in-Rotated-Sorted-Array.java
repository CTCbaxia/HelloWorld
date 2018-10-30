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
        int lo = 0; 
        int hi = nums.length - 1;
        while(lo <= hi){//compare all suitable elements in nums
            int mid = lo + (hi - lo)/2;
            if(nums[mid] == target) return mid;
            
            if(nums[hi] > nums[mid]){//right good, increasing
                if(target > nums[mid] && target <= nums[hi]) lo = mid + 1; //如果 target 落在 right 的范围（因为右边的大小有序）
                else hi = mid - 1;
            }else{//left good, increasing
                if(target >= nums[lo] && target < nums[mid]) hi = mid - 1;//如果 target 落在 left 的范围（因为左边的大小有序）
                else lo = mid + 1;
            }
        }
        //lo != hi but must be not match in nums
        return -1;
    }

}
/*
先判断到底那边是线性完全 increasing 的(condition A or B)
然后判断 target 在不在那一半里
       /|
     A/ | 
     /  |   hi
    lo  |  /
        | /B
        |/

*/




class Solution {
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1;
        int lo = 0; 
        int hi = nums.length - 1;
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(nums[hi] > nums[mid]){//right good
                if(nums[mid] > target) hi = mid;
                else if(nums[mid] < target){
                    if(nums[hi] < target) hi = mid;
                    else lo = mid + 1;
                }else{
                    return mid;
                }
            }else{//left good
                if(nums[mid] > target){
                    if(nums[lo] > target) lo = mid + 1;
                    else hi = mid;
                }else if(nums[mid] < target) lo = mid + 1;
                else{
                    return mid;
                }
            }
        }
        //lo == hi
        return (nums[hi] == target) ? hi : -1;
    }

}

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