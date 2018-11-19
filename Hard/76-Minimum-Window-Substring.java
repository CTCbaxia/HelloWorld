/*
HARD
76. Minimum Window Substring

TIME: 
RESULT: 
NOTES: https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
*/
/*
Sliding Window
1. 先数 key 的数量
2. count = 小 string 的字母数（需要匹配的数量）
3. 两个指针移动匹配，count = 0 的时候说明匹配成功
注意不在 t 的字母（words[i] == 0）是永远不会在 start 移动的时候变成 > 0 的，只会又负数加为0

Time: O(n)
Space: O(128)
*/
//map
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int start = 0, end = 0, len = Integer.MAX_VALUE;
        int count = map.size();
        String res = "";
        while(end < s.length()){
            char c = s.charAt(end);
            if(map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                if(map.get(c) == 0) count--;
            }
            end++;
            while(count == 0){
                char c2 = s.charAt(start);
                if(map.containsKey(c2)){
                    map.put(c2, map.get(c2) + 1);
                    if(map.get(c2) > 0) count++;
                }
                if(end - start < len){
                    len = end - start;
                    res = s.substring(start, end);
                }
                start++;
            }
        }
        return res;
    }
}








//array
class Solution {
    public String minWindow(String s, String t) {
        int[] words = new int[128];
        
        for(char c : t.toCharArray()){
            words[c]++;
        }
        int count = t.length(), start = 0, end = 0, len = Integer.MAX_VALUE;
        String res = "";
        while(end < s.length()){
            if(words[s.charAt(end)]-- > 0) count--;//匹配且没有超额匹配，注意--是比较之后才运行的
            end++;
            while(count == 0){
                if(end - start < len){
                    len = end - start;
                    res = s.substring(start, end);
                } 
                if(words[s.charAt(start)]++ == 0) count++;//开始后移 start，并还原匹配
                start++;
            }
        }
        return res;
    }
}
