/*
MEDIUM
238. Product of Array Except Self

TIME: 30min
RESULT: 100%
NOTES:

*/
/*
双向计算

Time: O(n)
Space: O(n)
*/
class Solution {
    public int[] productExceptSelf(int[] nums) {//[1,2,3,4]
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        int[] result = new int[nums.length];
        
        int product = 1;
        for(int i = 0; i < nums.length; i++){
            left[i] = product;
            product *= nums[i];
        }//[1,1,2,6]
        
        product = 1;
        for(int i = nums.length - 1; i >= 0; i--){
            right[i] = product;//如果这直接 result[i] *= product，就是 in-place 了
            product *= nums[i];
        }//[24,12,4,1]
        
        for(int i = 0; i < nums.length; i++){
            result[i] = left[i] * right[i];
        }
        return result;
    }
}



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