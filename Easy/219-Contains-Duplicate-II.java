/*
219. Contains Duplicate II
https://leetcode.com/problems/contains-duplicate-ii/description/

TIME: 0719 - 20min
RESULT: 70% - 11ms
Notes: 

METHOD:

*/
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i - k - 1]);
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }
}
/*
SOLUTION 1:
TIME: 0719 - 20min
RESULT: 28% - 12ms

思路：
用 hashmap 存每个 num 位置，如果正在遍历的内容之前出现过，那么用现在的位置 i 减去 map 里对应的上一个位置 map.get(num)，
如果差值小于 k 则返回 true，
否则更新这个 num 对应的 value = i
*/
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; i++){
            if(!map.containsKey(nums[i])) map.put(nums[i],i);
            else{
                if(i - map.get(nums[i]) <= k) return true;
                else map.put(nums[i], i);
            }
        }
        return false;
    }
}