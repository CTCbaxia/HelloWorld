/*
438. Find All Anagrams in a String
https://leetcode.com/problems/find-all-anagrams-in-a-string/description/

TIME: 0716 - 2h
RESULT: 44% - 34ms
NOTES: 

METHOD:
Sliding Window
*/
/*
Sliding Window:
count = map.size()
只看 key 的匹配，当 count = 0 的时候，检查 end - start 是不是等于 p.length()

Time: O(n)
Space: O(n)
*/
class Solution {
    //sliding window
    public List<Integer> findAnagrams(String s, String p){
        Map<Character, Integer> map = new HashMap<>();
        //build map
        for(char c : p.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int start = 0, end = 0;
        int count = map.size();
        List<Integer> result = new ArrayList<>();
        
        //find window
        while(end < s.length()){
            char c = s.charAt(end);
            if(map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                if(map.get(c) == 0) count--;
            }
            end++;
            while(count == 0){
                if(end - start == p.length()) result.add(start);
                char removeChar = s.charAt(start++);
                if(map.containsKey(removeChar)){
                    map.put(removeChar, map.get(removeChar) + 1);
                    if(map.get(removeChar) > 0) count++;//因为之前有可能有的key多匹配了，只有某一个removeChar导致map.get() > 0,匹配才失效
                }
            }
        }

        return result;

    }                                


}








/*
Sliding Window:
count = map.size()
只看 key 的匹配，当 count = 0 的时候，检查 end - start 是不是等于 p.length()

Time: O(n)
Space: O(n)
*/
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();

        //build map
        for(char c : p.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //sliding window
        int count = map.size();
        int start = 0, end = 0, len = Integer.MAX_VALUE;
        while(end < s.length()){
            char c = s.charAt(end);
            if(map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                if(map.get(c) == 0) count--;
            }
            end++;
            while(count == 0){//当所有key都匹配完毕
                char c2 = s.charAt(start);
                if(map.containsKey(c2)){
                    map.put(c2, map.get(c2) + 1);
                    if(map.get(c2) > 0) count++;//只会加一次，start 就停止移动了
                }
                if(end - start == p.length()){//检查匹配区间有没有多余字母
                    result.add(start);
                }
                start++;
            }
        }
        return result;
    }
}










/*
SOLUTION 0:
每次都向右移一格，直到所需 p_map 全部被满足（count == 0），
当 count == 0 时，核对 window size 是否等于 p.length()，如果等于，result.add()。一直移动 left 指针直到又有 p_map 未被满足 （count > 0）

RESULT: 44% - 34ms
*/
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        if (p == null || s == null || s.length() < p.length()) return result;

        Map<Character, Integer> p_map = new HashMap<Character, Integer>();

        for(char c : p.toCharArray()){
            p_map.put(c, p_map.getOrDefault(c,0) + 1);
        }
        int count = p_map.size();
        int left = 0;
        int right = 0;
        
        while(right < s.length()){
            char c = s.charAt(right);
            if(p_map.containsKey(c)){
                p_map.put(c, p_map.get(c) - 1);
                if(p_map.get(c) == 0) count--;
            }
            while(count == 0){
                if(p.length() == right - left + 1) result.add(left);
                char c_remove = s.charAt(left);
                
                if(p_map.containsKey(c_remove)){
                    p_map.put(c_remove, p_map.get(c_remove) + 1);
                    if(p_map.get(c_remove) > 0) count++;
                }
                left++; 
            }
            right ++;
        }
        return result;
    }

}



/*
SOLUTION 1:
对于每个同等长度的 substring sort

RESULT: TLE
*/
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        char[] pArr = p.toCharArray();
        Arrays.sort(pArr);
        
        int p_length = p.length();
        for(int i = 0; i < s.length() - p_length + 1; i++){
            String substring = s.substring(i,i + p_length);
            char[] subArr = substring.toCharArray();
            Arrays.sort(subArr);
            if(Arrays.equals(subArr, pArr)) result.add(i);
        }
        return result;
    }
}

/*
SOLUTION 2:
对于每个同等长度的 substring，每次都检查是不是和 p 正好匹配（缺点：每次都要重新计算）

RESULT: 0% - 950ms

*/

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        int length = p.length();
        if (p == null || s == null || s.length() < length) return result;

        for(int j = 0; j < s.length() - length + 1; j++){
            String substring = s.substring(j, j + length);
            if(isAnagram(p, substring)) result.add(j);
        }
        return result;
    }
    private boolean isAnagram(String p, String substring){
        int[] p_char = new int[26];
        for(int i = 0; i < p.length(); i++){
            char c = p.charAt(i);
            p_char[c-'a'] ++;
            
        }
        for(int i = 0; i < substring.length(); i++){
            char c = substring.charAt(i);
            p_char[c - 'a']--;
            if(p_char[c - 'a'] < 0) return false;
        }
        System.out.println(substring);
        return true;
    }
}
/*
SOLUTION 3:
控制 window size 不变，每次都向右移一格，并对于新进 window 和刚出 window 的 char 来调整匹配结果

RESULT: 44% - 34ms

*/
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        if (p == null || s == null || s.length() < p.length()) return result;

        Map<Character, Integer> p_map = new HashMap<Character, Integer>();
        int left = 0;
        int right = 0;
        
        for(char c : p.toCharArray()){
            p_map.put(c, p_map.getOrDefault(c,0) + 1);
        }
        int count = p_map.size();
        
        while(right < s.length()){
            
            // make the window with its size equals length
            while(right < p.length()){
                char c = s.charAt(right);
                if(p_map.containsKey(c)){
                    p_map.put(c, p_map.get(c) - 1);
                    if(p_map.get(c) == 0) count --;
                }
                right++;
                if(count == 0) result.add(left);
                if(right >= s.length()) return result;
            }

            char rightchar = s.charAt(right);
            char leftchar = s.charAt(left);
            if(p_map.containsKey(rightchar)){
                
                p_map.put(rightchar, p_map.get(rightchar) - 1);
                if(p_map.get(rightchar) == 0) count --;
            }
            if(p_map.containsKey(leftchar)){
                int plus = p_map.get(leftchar);
                p_map.put(leftchar, p_map.get(leftchar) + 1);
                if(plus == 0) count ++;
            }
            left ++;
            right ++;
            if(count == 0) result.add(left);
        }
        return result;
    }
}