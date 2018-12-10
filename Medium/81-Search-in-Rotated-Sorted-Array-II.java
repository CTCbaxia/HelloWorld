/*
MEDIUM
81. Search in Rotated Sorted Array II
https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/

TIME: 0902 - 1h
RESULT: 100% - 0ms
NOTES:

*/
/*
Two Pointer + Binary Search
先除去头尾的重复元素，至少留两个元素 -- 只有他们会干扰判断方向
然后跟非重复一样判断 target 是否落在单调区间内

Time: O(n) - O(logn)
Space: O(1)
*/
class Solution {
    public boolean search(int[] nums, int target){
        int lo = 0, hi = nums.length - 1;
        while(lo + 1 < hi && nums[lo] == nums[lo + 1]) lo++;
        while(hi - 1 > lo && nums[hi] == nums[hi - 1]) hi--;
        
        /*here lo and hi may still be the same, but mid will not be same as hi until there is only two elements*/
        while(lo <= hi){// we want to check for the target, so use <=
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] == target) return true;

            if(nums[mid] <= nums[hi]){//check with hi to see: right is increasing
                if(target > nums[mid] && target <= nums[hi]) lo = mid + 1;
                else hi = mid - 1;
            }else{
                if(target < nums[mid] && target >= nums[lo]) hi = mid - 1;
                else lo = mid + 1;
            }
        }
        return false;
    }    
}



/*
Solution 1
when it is hard to decide, we move hi one step to the left and see

Time: O(n) - worst case nums = [0,0,0,0,0], target = 1
Space: O(1)
*/
class Solution {
    public boolean search(int[] nums, int target) {
        if(nums.length == 0) return false;
        int lo = 0, hi = nums.length - 1;

        while(lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(nums[mid] == target) return true;
            
            if(nums[hi] > nums[mid]){//left is good
                if(target > nums[mid] && target <= nums[hi]) lo = mid + 1;
                else hi = mid - 1;
            }else if(nums[hi] < nums[mid]){//right is good
                if(target >= nums[lo] && target < nums[mid]) hi = mid - 1;
                else lo = mid + 1;
            }else{
                hi--;//无法判断就只能顺移。nums[hi] == nums[mid] != target
            }
        }
        return false;
    }
}

/*
Solution 2 思路和 1 基本一致
The only thing that makes it hard to decide where to move is the equal values from two sides of the array
So we need to remove them first, and then it becomes easy to use binary search

Time: O(n) - worst case nums = [0,0,0,0,0], target = 1
Space: O(1)
*/
class Solution {
    public boolean search(int[] nums, int target) {
        if(nums.length == 0) return false;
        int lo = 0, hi = nums.length - 1;
        while(lo <= hi && nums[lo] == nums[hi]){//make sure we can decide the drop point by removing same values from both sides
            if(nums[lo] ==  target) return true;
            lo++;
            hi--;
        }
        if(lo > hi) return false;
        while(lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(nums[mid] == target) return true;
            
            if(nums[hi] >= nums[mid]){//left is good
                if(target > nums[mid] && target <= nums[hi]) lo = mid + 1;
                else hi = mid - 1;
            }else{//right is good
                if(target >= nums[lo] && target < nums[mid]) hi = mid - 1;
                else lo = mid + 1;
            }
        }
        return false;
    }
}











/*
SOLUTION REFERENCE: 更简化
其实这题的重点是如何确定哪里是顺序，哪里是断层。
如果无法确定（当没有大小区别的时候），就直接慢慢顺移，继续比较，不要匆忙二分法。
顺序或断层，得遵循绝对的大小区别，等于的情况无法判断
TIME: 0902 - 1h
RESULT: 100% - 0ms
*/
class Solution {
    public boolean search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while(lo <= hi){
            int mid = (lo + hi) / 2;
            if(nums[mid] == target) return true;
            
            if(nums[mid] < nums[hi] || nums[mid] < nums[lo]){//右边顺序，左边断层
                if(nums[mid] < target && nums[hi] >= target){//不一定往右
                    lo = mid + 1;
                }else{
                    hi = mid - 1;
                }
            }else if(nums[mid] > nums[lo] || nums[mid] > nums[hi]){//左边顺序, 右边断层
                if(nums[mid] > target && nums[lo] <= target){//不一定往左
                    if() hi = mid - 1;
                }else{
                    lo = mid + 1;
                }
            }else{//无法判断，此时 nums[lo] = nums[mid] = nums[hi]，只需减小范围再继续查就行
                hi--;
            }

        }
        return false;
    }
}

/*
SOLUTION REFERENCE: 思维比较顺
TIME: 0902 - 1h
RESULT: 100% - 0ms
*/
class Solution {
    public boolean search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while(lo <= hi){
            int mid = (lo + hi) / 2;
            if(nums[mid] == target) return true;
            
            if(nums[mid] < nums[hi] || nums[mid] < nums[lo]){//右边顺序，左边断层
                if(nums[mid] < target){//不一定往右
                    if(nums[hi] >= target) lo = mid + 1;
                    else hi = mid - 1;
                }else{
                    hi = mid - 1;
                }
            }else if(nums[mid] > nums[lo] || nums[mid] > nums[hi]){//左边顺序, 右边断层
                if(nums[mid] > target){//不一定往左
                    if(nums[lo] <= target) hi = mid - 1;
                    else lo = mid + 1;
                }else{
                    lo = mid + 1;
                }
            }else{//无法判断，此时 nums[lo] = nums[mid] = nums[hi]，只需减小范围再继续查就行
                hi--;
            }

        }
        return false;
    }
}

/*
WRONG CODE
*/

有重复值的时候判断不出来顺序还是断层
[1,1,3,1]
3 
class Solution {
    public boolean search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while(lo <= hi){
            int mid = (lo + hi) / 2;
            if(nums[mid] == target) return true;
            
            if(nums[mid] <= nums[hi]){//右边顺序，左边断层，这边的等于有问题
                if(nums[mid] < target){//不一定往右
                    if(nums[hi] >= target) lo = mid + 1;
                    else hi = mid - 1;
                }else{
                    hi = mid - 1;
                }
            }else{//左边顺序, 右边断层
                if(nums[mid] > target){//不一定往左
                    if(nums[lo] <= target) hi = mid - 1;
                    else lo = mid + 1;
                }else{
                    lo = mid + 1;
                }
            }

        }
        return false;
    }
}