/*
H
128. Longest Consecutive Sequence
*/
/*
HashMap: 
always keep the bound of a section indicating the length of the section
when having a new num, check [num - 1] and [num + 1] for their current section length
len = left + right + 1

Time: O(n)
Space: O(n)
*/
class Solution {
    public int longestConsecutive(int[] nums) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            if(map.containsKey(num)) continue;//no duplicate, otherwise it will messed up
            int left = map.getOrDefault(num - 1, 0);//connection to left
            int right = map.getOrDefault(num + 1, 0);//connection to right
            
            int len = left + right + 1;
            map.put(num, len);
            map.put(num - left, len);//update
            map.put(num + right, len);//update
            
            res = Math.max(res, len);
        }
        return res;
    }
}
