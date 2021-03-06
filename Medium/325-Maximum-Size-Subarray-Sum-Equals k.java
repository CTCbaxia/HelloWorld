/*
MEDIUM
325. Maximum Size Subarray Sum Equals k

TIME: 
RESULT: 
NOTES:
*/
/*
Presum + HashMap

map.put(0, -1)
keep smallest index for a sum(don't update value for existing key)
update the max res when find match

Time: O(n)
Space: O(n)
*/
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();//presum, index
        int sum = 0;
        int res = 0;//the size has to be >= 0
        
        map.put(0, -1);
        
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum - k)){
                res = Math.max(res, i - map.get(sum - k));
            }
            if(!map.containsKey(sum)) map.put(sum, i);
        }
        return res;
    }
}














/*
算一遍累计和，然后就跟 two sum 的做法一样了
记得不要更新key，因为肯定是保留最前面的 index，这样才会有最大 subarray

Time: O(n)
Space: O(n)
*/
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int result = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > 0) nums[i] += nums[i - 1];
            if(!map.containsKey(nums[i])) map.put(nums[i], i);
            if(nums[i] == k){
                result = Math.max(result, i + 1);
                continue;
            }else if(map.containsKey(nums[i] - k)){
                int j = map.get(nums[i] - k);
                result = Math.max(result, i - j);
            }
        }
        return result;
    }
}










//复杂了
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int result = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > 0) nums[i] += nums[i - 1];
            if(!map.containsKey(nums[i])) map.put(nums[i], i);
            if(nums[i] == k) result = i + 1;//if the sum from first to i == k
        }
        
        for(int i = 0; i < nums.length; i++){
            if((map.containsKey(nums[i] - k) && map.get(nums[i] - k) <= i)){
                int j = map.get(nums[i] - k);
                result = Math.max(result, i - j);
            }
        }
        return result;
    }
}