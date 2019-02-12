/*
House Robber 
不能选相邻的value而不是index (比如 1 1 1 2 2 2 3 3 3 结果是1 1 1 3 3 3) 
也就是给一串数字，要求能够得到最大sum的子集，子集里面所有元素都不相邻
maxsum = Math.max(maxsum[k] + arr, maxsum[i-1]), if Math.abs(arr - arr[i-1]) == 1
                          = maxsum[i-1] + arr else
*/
class Solution {

    public int rob(int[] nums) {
        Arrays.sort(nums);
        
    }
}