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
Dynamic Programming
dp[i]: 以 i-th index 结尾的，最大presum
如果前面的dp[i - 1] < 0， dp[i] 就是 num[i]自己

Time: O(n)
Space: O(1)
*/
class Solution {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];//non empty array
        for(int i = 1; i < nums.length; i++){
            if(dp[i - 1] > 0) dp[i] = dp[i - 1] + nums[i];
            else dp[i] = nums[i];
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}







class Solution {
    public static int maxSubArray(int[] A) {
        if(A.length == 0) return 0;
        int[] dp = new int[A.length];
        dp[0] = A[0];
        int res = dp[0];
        for(int i = 1; i < A.length; i++){
            dp[i] = dp[i - 1] < 0 ? A[i] : A[i] + dp[i - 1];
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}




class Solution {
    public static int maxSubArray(int[] A) {
        if(A.length == 0) return 0;
        int[] sum = new int[A.length + 1];
        sum[0] = 0;
        for(int i = 1; i < sum.length; i++){
            sum[i] = sum[i - 1] + A[i - 1];
        }
        int res = A[0];
        for(int i = 0; i < sum.length; i++){
            for(int j = i + 1; j < sum.length; j++){
                res = Math.max(res, sum[j] - sum[i]);
            }
        }
        return res;
    }
}



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