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
/*
先找最左边的点，然后找最右边的点。
如果最左边的点都没找到，就直接返回 [-1,-1]
*/
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if(nums.length == 0) return result;
        
        int lo = 0, hi = nums.length - 1;
        //find the left, find first greater or equal
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(nums[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        if(nums[hi] == target) result[0] = hi;
        else return result;
        
        //find the right, find last smaller or equal
        hi = nums.length - 1; //!!! so we don't need to reset lo because lo = hi => target = first position
        while(lo < hi){
            int mid = lo + (hi - lo)/2 + 1;//make mid biased to the right so we won't loop forever
            if(nums[mid] > target) hi = mid - 1;
            else lo = mid;
        }
        result[1] = lo;//lo = hi = 最后一个小于等于 target 的点
        return result;
    }
}
/*
只写一个 firstGreaterOrEqual
注意如果要保证 firstGreaterOrEqual 
需要 hi = nums.length;
这样才能保证要么出来的值是大于等于 target，如果最大值是 target，应该返回数组长度 （也就是如果有，那么应该在那一位上）
*/

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        
        int first = firstGreaterOrEqual(nums, target);
        if(first == nums.length || nums[first] != target) return result;
        
        return new int[]{first, firstGreaterOrEqual(nums, target + 1) - 1};
    }
    private int firstGreaterOrEqual(int[] nums, int target){
        int lo = 0, hi = nums.length;//当想要确保得到的 hi 一定是大于或者等于 target 的位置，就应该赋值在边界
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(nums[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return hi;
    }
}
//find one target first
//for the left part, the next position find is the right side of the next generation of searching
//for the right part, the next position find is the left side of the next generation of searching
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        int lo = 0;
        int hi = nums.length - 1;
        while(lo < hi){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] < target) lo = mid + 1;
            else if(nums[mid] > target) hi = mid;
            else{
                //we find the target
                int newlo = mid;
                int newhi = mid;

                //找第一个 >= target 的点
                while(lo < newhi){
                    int newmid = lo + (newhi - lo) / 2;
                    if(nums[newmid] < target) lo = newmid + 1;
                    else newhi = newmid; // hi store the last element that is >= target
                }
                result[0] = newhi;

                //找最后一个 <= target 的点
                while(newlo + 1 < hi){
                    int newmid = newlo + (hi - newlo) / 2;
                    if(nums[newmid] > target) hi = newmid - 1;
                    else newlo = newmid;
                }
                //这样会出来两个结果，newlo肯定小于等于 target(在这里就是等于)，只需验证 hi 是不是小于等于
                result[1] = nums[hi] == target ? hi : newlo;
                return result;
            }
        }
        if(nums.length > 0 && nums[hi] == target){
            result[0] = hi;
            result[1] = hi;
        }else{
            result[0] = -1;
            result[1] = -1;            
        }
        return result;
        
    }
}
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