/*
1. Two Sum
https://leetcode.com/problems/two-sum/description/

TIME: 0713 - 30min
RESULT: 99.4%, 4ms

NOTES: 
用 HashMap 来存储数据。
Hashmap 根据键的 hashCode 值存储数据，大多数情况下可以直接定位到它的值，因而具有很快的访问速度，为了将时间复杂度降到 O(n)，可以用 Hashmap
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> numsMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            if(numsMap.containsKey(target - nums[i])){
                result[0] = numsMap.get(target - nums[i]);
                result[1] = i;
                return result;
            }else{
                numsMap.put(nums[i], i);
            }
        }
        return result;
    }
}
