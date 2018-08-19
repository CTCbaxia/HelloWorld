/*
53. Maximum Subarray
https://leetcode.com/problems/maximum-subarray/description/

TIME: 0819 - 30min
RESULT: 11% - 13ms
NOTES:
this is a optimization problem, which can be usually solved by DP( SEE: https://leetcode.com/problems/maximum-subarray/discuss/20193/DP-solution-and-some-thoughts)
DP 的关键就是找到子问题，使子问题能和后续问题产生联系，在此就是包含有 Opt(i) = nums[i] + ((Opt(i - 1) > 0) ? Opt(i - 1) : 0)

*/
/*
SOULTION 2:
TIME: 0819 - 30min
RESULT: 11% - 13ms
*/
class Solution {
    public int maxSubArray(int[] nums) {
        int preOpt = nums[0], result = preOpt;
        for(int i = 1; i < nums.length; i++){
            preOpt = (preOpt > 0) ? nums[i] + preOpt : nums[i];
            result = Math.max(preOpt, result);
        }
        return result;
    }
}
/*
SOULTION 1:
TIME: 0819 - 30min
RESULT: 7% - 14ms
*/
class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0, result = nums[0], left = 0, right = 0;
        while(right < nums.length){
            sum += nums[right];
            right ++;
            result = Math.max(result, sum);
            if(sum <= 0){
                left = right;
                sum = 0;
            }
        }
        return result;
    }
}
/*
SOULTION 0:
TIME: 0819 - 30min
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