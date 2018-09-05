/*
MEDIUM
287. Find the Duplicate Number
https://leetcode.com/problems/find-the-duplicate-number/description/

TIME: 0904 - 30min
RESULT: 48% - 3ms
NOTES:
1. 没看懂：https://leetcode.com/problems/find-the-duplicate-number/discuss/72846/My-easy-understood-solution-with-O(n)-time-and-O(1)-space-without-modifying-the-array.-With-clear-explanation.
*/
/*
SOLUTION 0: 区域判断
TIME: 0904 - 30min
RESULT: 48% - 3ms
*/
class Solution {
    public int findDuplicate(int[] nums) {
        int lo = 1, hi = nums.length - 1;//n + 1 = nums.length
        while(lo < hi){
            int mid = (lo + hi) / 2;
            int lower = 0;
            for(int n : nums){
                if(n <= mid) lower++;
            }
            if(lower > mid) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}
/*
Wrong Code: [1,3,2,2] 会死循环。
因为 lo = mid 很有可能是一个没有任何改变的值。int mid = (lo + hi) / 2; 本来就可能有 lo = mid
*/
class Solution {
    public int findDuplicate(int[] nums) {
        int lo = 1, hi = nums.length - 1;//n + 1 = nums.length
        while(lo < hi){
            int mid = (lo + hi) / 2;
            int lower = 0;
            for(int n : nums){
                if(n < mid) lower++;
            }
            if(lower >= mid) hi = mid - 1;
            else lo = mid;
        }
        return lo;
    }
}

//time : O(n^2)
//space: O(1)
class Solution {
    public int findDuplicate(int[] nums) {
        
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[j] == nums[i]) return nums[i];
            }
        }
        return -1;
    }
}