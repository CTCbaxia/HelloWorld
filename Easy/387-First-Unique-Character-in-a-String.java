/*
387. First Unique Character in a String
https://leetcode.com/problems/first-unique-character-in-a-string/description/

TIME: 0718 - 30min
RESULT: 38.61% - 58ms
NOTES: 
对于单词每个 char 的频率，用 char[c - 'a'] 存储比用 hashmap 存储更快。

METHOD:
1. HashMap
2. char[]

*/
/*
int[] freq

Time: O(n)
Space: O(26)
*/
class Solution {
    public int firstUniqChar(String s) {
        int[] freq = new int[26];
        for(char c : s.toCharArray()){
            freq[c - 'a']++;
        }
        for(int i = 0; i < s.length(); i++){
            if(freq[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }
}





/*
SOLUTION 0:

TIME: 0718 - 5min
RESULT: 88% - 17ms
*/
class Solution {
    public int firstUniqChar(String s) {
        char[] charfreq = new char[26];
        for(int i = 0; i < s.length(); i++){
            charfreq[s.charAt(i) - 'a'] ++;
        }
        for(int i = 0; i < s.length(); i++){
            if(charfreq[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }
}



/*
SOLUTION 1:

TIME: 0718 - 30min
RESULT: 38.61% - 58ms
*/
class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> charmap = new HashMap<Character, Integer>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            charmap.put(c,charmap.getOrDefault(c, 0) + 1);
        }
        for(int i = 0; i < s.length(); i++){
            if(charmap.get(s.charAt(i)) > 1) continue;
            else return i; 
        }
        return -1;
    }
}
