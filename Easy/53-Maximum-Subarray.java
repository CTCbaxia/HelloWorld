/*
53. Maximum Subarray
https://leetcode.com/problems/maximum-subarray/description/


*/
/*
RESULT: TLE
*/
class Solution {
    public int maxSubArray(int[] nums) {
        Set<Integer> preSum = new HashSet<Integer>();
        preSum.add(0);
        int sum = 0;
        int result = nums[0];
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            Iterator<Integer> it = preSum.iterator();
            while(it.hasNext()){
                int tmpSum = it.next();
                if(sum - tmpSum > result) result = sum - tmpSum;
            }
            preSum.add(sum);
        }
        return result;
        
    }
}