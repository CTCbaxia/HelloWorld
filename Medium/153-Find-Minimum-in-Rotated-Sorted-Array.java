/*
MEDIUM
153. Find Minimum in Rotated Sorted Array

TIME: 0902 - 20min
RESULT: 100% - 0ms

//最小值肯定在断层处
//注意题目并没有说一定有 rotate（所有的某一个位置处，可能就是没有该颠倒位）
*/

/*
we only need to detect where is the drop
and make sure we will not jump over the drop

Time: O(logn)
Space: O(1)
*/
class Solution {
    public int findMin(int[] nums) {
        if(nums.length == 0) return -1;
        int lo = 0; 
        int hi = nums.length - 1;
        while(lo < hi){
            int mid = lo + (hi - lo)/2; 
            if(nums[hi] > nums[mid]) hi = mid; //通过右边判断，不要左边，因为左边会遇到 lo = mid 的状况
            else lo = mid + 1;//we know mid is not the answer because there is nums[hi] < nums[mid] 
        }
        return nums[lo];//lo == hi
    }
}










class Solution {
    public int findMin(int[] nums) {
        int lo = 0; 
        int hi = nums.length - 1;
        while(lo < hi && lo + 1 != hi){
            int mid = lo + (hi - lo) / 2;
            if(nums[lo] > nums[mid]) hi = mid;
            else if(nums[hi] < nums[mid]) lo = mid;
            else break;
            
        }
        return Math.min(nums[lo],nums[hi]);
    }
}
/*
SOLUTION 0: 简化版本
最后留下的就是 num[lo], nums[hi] 其中左边为最大值，右边为最小值。
（只有在 lo = hi + 1 的时候，才会有 mid = lo，后继赋值 hi = mid，最后 lo = hi 的情况，所有不会跳跃 hi = lo + 1 直接到 hi = lo）
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
        return nums[hi];
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