/*
EASY
674. Longest Continuous Increasing Subsequence

RESULT: 98% - 3ms
NOTES: 
这种题肯定要遍历完才会知道最大的
*/
/*
Count++ or set 0

Time: O(n)
Space: O(1)
*/
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int res = 0, len = 0;
        for(int i = 0; i < nums.length; i++){
            if(i == 0 || i > 0 && nums[i] > nums[i - 1]){
                len++;
            }else{
                res = Math.max(res, len);
                len = 1;
            }
        }
        res = Math.max(res, len);//要注意end
        return res;
    }
}




/*
每次更新最长距离，如果遇到 drop 就把 len 清零
Time: O(n)
Space: O(1)
*/
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length == 0) return 0;
        int res = 1;
        int len = 1;//if we have at a num, len should be 1
        for(int i = 0; i < nums.length - 1; i++){//mind the boundary
            if(nums[i] < nums[i + 1]){
                len++;
                res = Math.max(res, len);
            }else len = 1;
        }
        return res;
    }
}


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