/*
MEDIUM
376. Wiggle Subsequence
https://leetcode.com/problems/minimum-genetic-mutation/description/

TIME: 0915 - 15min
RESULT: 100% - 0ms

*/
/*
SOLUTION 0: 
TIME: 0915 - 15min
RESULT: 100% - 0ms
O(n)
if wiggle then +1;
else wait until the largest number
*/


class Solution {
    public int wiggleMaxLength(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int result = 1;
        int pre = nums[0];
        int sign = 0;
        for(int i = 1; i < nums.length; i++){
            if(pre < nums[i]){
                if(sign == 0 || sign == -1){
                    result++;
                    sign = 1;
                }
            }else if(pre > nums[i]){
                if(sign == 0 || sign == 1){
                    result++;
                    sign = -1;
                }
            }
            pre = nums[i];
        }
        return result;
    }
}
/*
SOLUTION REFERENCE: Greedy 不错的解法
TIME: 0915 - 15min
RESULT: 100% - 0ms

*/

class Solution {
    public int wiggleMaxLength(int[] nums) {
        if(nums.length <= 1) return nums.length;
        int up = 1;
        int down = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i - 1] < nums[i]) up = down + 1;//如果连续，则不会变
            if(nums[i - 1] > nums[i]) down = up + 1;
        }
        return Math.max(up, down);
    }
}
/*
SOLUTION REFERENCE: 没有我的 intuitive
TIME: 0915 - 15min
RESULT: 100% - 0ms

*/
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if(nums.length <= 1) return nums.length;
        
        int preDiff = nums[1] - nums[0];
        int result = (preDiff == 0) ? 1 : 2;
        for(int i = 2; i < nums.length; i++){
            int curDiff = nums[i] - nums[i - 1];
            if(preDiff <= 0 && curDiff > 0 || preDiff >= 0 && curDiff < 0 ){
                result++;
                preDiff = curDiff;
            }
            
        }
        return result;
    }
}