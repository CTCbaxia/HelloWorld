/*
EASY
674. Longest Continuous Increasing Subsequence

RESULT: 98% - 3ms
NOTES: 
这种题肯定要遍历完才会知道最大的
*/
/*
Time: O(n)
Space: O(1)
*/
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length == 0) return 0;
        
        int count = 1;
        int res = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i - 1]) res = Math.max(res, ++count);
            else count = 1;
        }
        return res;
    }
}


//mine
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length == 0) return 0;
        
        int count = 1;
        int res = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i - 1]){
                count++;
                res = Math.max(res, count);
            }else{
                count = 1;
            }
        }
        return res;
    }
}