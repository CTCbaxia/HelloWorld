/*
MEDIUM
395. Longest Substring with At Least K Repeating Characters

TIME: 
RESULT: 
NOTES:

*/
/*
Divide and Conquer: count and divide
we pass the string and find all char less than k repeated time
and separat the string using these chars
and for every substring separated by these chars, we do the same again
we got the result when all elements in the substring have more than k repeated times

如果一个字符的总个数少于k, 那这个字符不可能出现在结果substring里
所以你可以先扫一遍 计数每个字符 用个数少于k的字符分隔整个字符串
第二遍扫的时候在每个substring里做同样的事 计数 分割
最多会有 26 层

Time:O(26 * n) //we at most do 26 recurrence(until no element in string)
Space: 0(26)
*/
class Solution {
    public int longestSubstring(String s, int k) {
        char[] ch = new char[26];
        for(char c : s.toCharArray()){
            ch[c - 'a']++;
        }
        int res = 0;
        int i = 0;
        while(i < s.length()){
            char c = s.charAt(i);
            while(i < s.length() && ch[s.charAt(i) - 'a'] < k){//skip char < k repeated time
                i++;
            } 
            int start = i;
            while(i < s.length() && ch[s.charAt(i) - 'a'] >= k){//find substrings with all chars >= k in this whole string
                i++;
            }
            if(i - start == s.length()) return s.length();// if the whole string is valid, we return len directly
            else res = Math.max(res, longestSubstring(s.substring(start, i), k));// check the substring
        }
        return res;
    }
}