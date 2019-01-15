/*
HARD
159. Longest Substring with At Most Two Distinct 

TIME: 
RESULT: 
*/
/*
Sliding Window

Time: O(n)
Space: O(1)
*/
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> count = new HashMap<>();
        int start = 0, end = 0, result = 0;
        
        for(; end < s.length(); end++){
            char c = s.charAt(end);
            count.put(c, count.getOrDefault(c, 0) + 1);
            
            while(count.size() > 2){
                char r = s.charAt(start);
                count.put(r, count.get(r) - 1);
                if(count.get(r) == 0) count.remove(r);
                start++;
            }
            result = Math.max(result, end - start + 1);
        }
        return result;
    }
}