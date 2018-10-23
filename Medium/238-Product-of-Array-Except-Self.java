/*
MEDIUM
238. Product of Array Except Self

TIME: 30min
RESULT: 100%
NOTES:

*/
/*
DP: save the result + two loop
Time: O(n)
Space: O(1)
*/
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int pro = 1;
        int[] result = new int[len];

        //calculate from left to right
        for(int i = 0; i < len; i++){
            result[i] = pro;
            pro *= nums[i];
        }
        pro = 1;
        //calculate from right to left
        for(int i = len - 1; i >= 0; i--){
            result[i] *= pro;
            pro *= nums[i];
        }
        return result;
    }
}
/*

Time: O(n)
Space: O(n)
*/
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] left = new int[len];//product before i
        int[] right = new int[len];//product after i
        int[] result = new int[len];
        //calculate left and right product
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for(int i = 1; i < len; i++){
            left[i] = left[i - 1] * nums[i - 1];
        }
        for(int i = len - 2; i >= 0; i--){
            right[i] = right[i + 1] * nums[i + 1];
        }
        
        //calculate result
        for(int i = 0; i < len; i++){
            result[i] = left[i] * right[i];
        }
        return result;
    }
}