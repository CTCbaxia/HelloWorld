/*
MEDIUM
33. Search in Rotated Sorted Array
https://leetcode.com/problems/search-in-rotated-sorted-array/description/

TIME: 0825 - 40min
RESULT: 95% - 8ms
NOTES:
1. discuss 里面有找偏移量的方法也不错
*/
/*
see which side, left or right, is in increasing order
and see if the target falls into that side
先判断到底那边是线性完全 increasing 的(condition A or B)
然后判断 target 在不在那一半里
       /|
     A/ | 
     /  |   hi
    lo  |  /
        | /B
        |/
Time: O(logn)
Space: O(1)
*/
class Solution {
    public int search(int[] nums, int target) {
        int lo = 0; 
        int hi = nums.length - 1;
        while(lo <= hi){//compare all suitable elements in nums
            int mid = lo + (hi - lo)/2;
            if(nums[mid] == target) return mid;
            
            if(nums[hi] > nums[mid]){//right is increasing(先用右边，因为 lo 可能等于 mid)
                if(target > nums[mid] && target <= nums[hi]) lo = mid + 1; //如果 target 落在 right 的范围
                else hi = mid - 1;
            }else{//left good, increasing
                if(target >= nums[lo] && target < nums[mid]) hi = mid - 1;//如果 target 落在 left 的范围
                else lo = mid + 1;
            }
        }
        //lo != hi but must be not match in nums
        return -1;
    }

}

//或者先看target，再看drop point
class Solution {
    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] > target){
                //check drop point
                if(nums[hi] > nums[mid]) hi = mid - 1;
                else {
                    if(nums[lo] > target) lo = mid + 1;
                    else hi = mid - 1;
                }
            }else if(nums[mid] < target){
                if(nums[hi] < nums[mid]) lo = mid + 1;
                else{
                    if(nums[hi] < target) hi = mid - 1;
                    else lo = mid + 1;
                }
            }else return mid;
        }
        return -1;
    }

}






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
