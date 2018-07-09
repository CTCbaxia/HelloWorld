/*
3. Longest Substring Without Repeating Characters
https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

TIME: 0709
RESULT: 80%
NOTE: HashSet
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0){
            return 0;
        }
        
        HashSet<Character> substring = new HashSet<Character> ();
        int maxlength = 0;
        int i = 0;
        int j = 0;
        while(i < s.length()){
            if(!substring.contains(s.charAt(i))){
                substring.add(s.charAt(i++));
                maxlength = Math.max(maxlength,substring.size());

            }else{
                substring.remove(s.charAt(j++));
            }
        }
        return maxlength;
    }
}