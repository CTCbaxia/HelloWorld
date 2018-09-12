/*
MEDIUM
81. Search in Rotated Sorted Array II
https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/

TIME: 0902 - 1h
RESULT: 100% - 0ms
NOTES:
其实这题的重点是如何确定哪里是顺序，哪里是断层。
如果无法确定（当没有大小区别的时候），就直接慢慢顺移，继续比较，不要匆忙二分法。
顺序或断层，得遵循绝对的大小区别，等于的情况无法判断
*/
/*
SOLUTION REFERENCE: 更简化
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