/*
MEDIUM
540. Single Element in a Sorted Array
https://leetcode.com/problems/single-element-in-a-sorted-array/description/

TIME: 0806 - 30min
RESULT: 25% - 1ms
METHOD:
1. 二分法

THOUGHT:
二分法：
包含 single 的数组个数为奇数个 ((right - left) / 2 == 奇数)。
如果 N - 1 / 2 为奇数个，那么中间数的对象应该在它左边（if so, 继续查右边数组） -> 如果在右边，那么只需继续查询左边的数组
如果 N - 1 / 2 为偶数个，那么中间数的对象应该在它右边（if so, 继续查右边数组） -> 如果在左边，那么只需继续查询左边的数组
*/
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        
        while(lo < hi){
            System.out.println(lo + " "+hi);
            int len = (hi - lo) / 2;
            int mid = lo + len;
            if(len % 2 == 0){
                if(nums[mid] == nums[mid - 1]) hi = mid - 2;
                else if(nums[mid] == nums[mid + 1]) lo = mid + 2;
                else return nums[mid];                
            }else{
                if(nums[mid] == nums[mid - 1]) lo = mid + 1;
                else if(nums[mid] == nums[mid + 1]) hi = mid - 1;
                else return nums[mid];   
            }

        }
        return nums[lo];
    }
}
/*
TIME: 0806 - 30min
RESULT: 25% - 1ms
*/
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int len = (right - left) / 2;
            int mid = (right + left) / 2;
            if(len % 2 == 0){
                if(nums[mid + 1] == nums[mid]) left = mid + 2;
                else if(nums[mid - 1] == nums[mid]) right = mid - 2;
                else return nums[mid];
            }else if(len % 2 == 1){
                if(nums[mid - 1] == nums[mid]) left = mid + 1;
                else if(nums[mid + 1] == nums[mid]) right = mid - 1;
                else return nums[mid];                
            }
        }
        return nums[left];
    }
}

/*
TIME: 0806 - 10min
RESULT: 100% - 0ms
*/
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int len = (right - left) / 2;
            int mid = (right + left) / 2;
            if(len % 2 == 1) mid --;
            
            if(nums[mid] == nums[mid + 1]) left = mid + 2;
            else if(mid != 0 && nums[mid] == nums[mid - 1]) right = mid - 2;
            else return nums[mid];
        }
        return nums[left];
    }
}