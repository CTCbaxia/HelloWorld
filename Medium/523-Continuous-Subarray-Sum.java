/*
MEDIUM
523. Continuous Subarray Sum
https://leetcode.com/problems/continuous-subarray-sum/description/

TIME: 0807 - 30min
RESULT: 82% - 7ms
NOTES: 
1. % 除数不能为 0 
2. % 相加减的关系：模相等，则他们的差一定可以整除 k
3. 一个负数 num % k，结果为负数的摩，这个时候加上一个 k 就是正数结果了
*/
/*
Map<Integer, Integer> : <presum, index>
**note:
cannot mode 0
这个题 k 是可以等于负数的，k 等于负数摩的结果和k为正一样，且也成立

**tips: if we have negative value, 
**still mod k, and for res = num % k, if res < 0, res += k
**do the same thing

Time: O(n)
Space: O(k)
*/
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int presum = 0;
        map.put(presum, -1);
        for(int i = 0; i < nums.length; i++){
            presum += nums[i];
            if(k != 0) presum %= k;//cannot mod 0
            if(map.containsKey(presum)){
                if(i - map.get(presum) >= 2) return true;
            }else{
                //only add when there is no presum before, otherwise you will make the index bigger
                map.put(presum, i);
            }
        }
        return false;
    }
}



/*
preSum + mod

Time: O(n)
Space: O(n)
*/
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);//index -1
        int preSum = 0;
        for(int i = 0; i < nums.length; i++){
            preSum += nums[i];
            if(k != 0) preSum %= k;
            if(map.containsKey(preSum)){
                if(i - map.get(preSum) > 1) return true;
            }else{
                //prefer smaller index, if has a preSum, don't update index. 
                //if no preSum, put a new
                map.put(preSum, i);
            }
        }
        return false;
    }
}






/*
PreSum % k + Map <presum, index>

(sum - presum) % k = 0 ---> sum % k = presum % k
记住检查 len 大于 2

Time: O(n)
Space: O(k) sum % k 不会超过 k
*/
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();//presum, index
        map.put(0, -1);//index, so -1
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(k != 0) sum = sum % k;//k == 0 不能 mod，但是是有可能找到 multiple of 0 的
            if(map.containsKey(sum)){
                if(i - map.get(sum) > 1) return true;
            }else{
                map.put(sum, i);
            }
        }
        return false;
    }
}







class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(k != 0) sum %= k;
            if(map.containsKey(sum)){
                if(i - map.get(sum) > 1) return true;
            }else{
                map.put(sum, i);
            }
        }
        return false;
    }
}



/*
SOLUTION 0:
TIME: 0807 - 30min
RESULT: 18% - 37ms
*/

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for(int i = 0; i < nums.length; i++){
            sum[i + 1] = sum[i] + nums[i];
            int index = i - 1;
            while(index >= 0){
                if(k != 0){
                    if((sum[i + 1] - sum[index--]) % k == 0) return true; 
                }else{
                    if((sum[i + 1] - sum[index--]) == 0) return true;
                }
                
            }
        }
        return false;
    }
}