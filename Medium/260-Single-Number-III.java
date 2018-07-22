/*
MEDIUM
260. Single Number III
https://leetcode.com/problems/single-number-iii/description/

TIME: 0722 - 1h
RESULT: 100% - 1ms
METHOD:
1. HashSet
2. XOR, &&




NOTES:
1. 比较符的优先级大于按位与（== has higher priority than &. You might want to wrap your operations in () to specify your own priority.）
2. REFERENCE: 
    - https://leetcode.com/problems/single-number-iii/discuss/68900/Accepted-C++Java-O(n)-time-O(1)-space-Easy-Solution-with-Detail-Explanations
    - https://segmentfault.com/a/1190000004886431
*/

/*
SOLUTION REFERENCE:

TIME: 0722 - 1h
RESULT: 100% - 1ms
*/
class Solution {
    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for(int i = 0; i < nums.length; i++){
            diff ^= nums[i];
        }
        int flag = diff & (-diff);
        int diff2 = 0;
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i++){
            if((flag & nums[i]) == 0){
                diff2 ^= nums[i];
            } 
        }
        result[0] = diff2;
        result[1] = diff2 ^ diff;
        return result;
    }
}

/*
SOLUTION REFERENCE:

TIME: 0722 - 10min
RESULT: 23% - 6ms
*/

class Solution {
    public int[] singleNumber(int[] nums) {
        Set<Integer> doublenum = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(!doublenum.contains(nums[i])) doublenum.add(nums[i]);
            else doublenum.remove(nums[i]);
        }
        int[] result = new int[2];
        if(doublenum.size() < 2) return result;
        Iterator<Integer> single = doublenum.iterator();
        result[0] = single.next();
        result[1] = single.next();
        return result;
    }
}