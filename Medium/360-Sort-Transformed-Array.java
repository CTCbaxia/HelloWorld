/*
MEDIUM
360. Sort Transformed Array

TIME: 
RESULT: 
NOTES:

*/
/*
Two Pointers: "a" matters!!
We need to put the result at the right position in result array
if a > 0, then lo or hi at least one of them is the largest, we put the larger one to end of result
if a < 0, then lo or hi at least one of them is the smallest, we put the smaller one to front of result

Time: O(n)
Space: O(1)
*/

class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] result = new int[nums.length];
        int index = a > 0 ? nums.length - 1 : 0;
        int lo = 0, hi = nums.length - 1;
        while(lo <= hi){
            int lon = a * nums[lo] * nums[lo] + b * nums[lo] + c;
            int hin = a * nums[hi] * nums[hi] + b * nums[hi] + c;
            if(a > 0){
                if(lon >= hin){
                    result[index--] = lon;
                    lo++;
                }else{
                    result[index--] = hin;
                    hi--;
                }
            }else{
                if(lon <= hin){
                    result[index++] = lon;
                    lo++;
                }else{
                    result[index++] = hin;
                    hi--;
                }
            }
        }
        return result;
    }
}