/*
3. Longest Substring Without Repeating Characters
https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

TIME: 0709
RESULT: 80%
NOTE: HashSet
*/
/*
Two Pointers + HashMap

map: <char, index> 
关注点在 i - j 之间没有重复值

每次 j 到达一个 char，先看这个 char 在不在 map 中，且是不是在 i - j 之间
- 如果不在，说明是之前 i skip 过去的，不影响
- 如果在，要把 i 挪到 map.get(c) + 1 的位置，越过重复项

Time: O(n)
Space: O(n)
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0;
        Map<Character, Integer> map = new HashMap<>();//map: <char, index> 
        int res = 0;
        while(j < s.length()){
            char c = s.charAt(j);
            if(map.containsKey(c) && i <= map.get(c)){//如果 map 里面有c ，且 c 在 ij 之间
                i = map.get(c) + 1;//jump
            }
            map.put(c, j);//每次都要更新 c 的位置

            res = Math.max(res, j - i + 1);
            j++;
        }
        return res;
    }
}





//HashSet
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