/*
5. Longest Palindromic Substring
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
*/
class Solution1{
    public String longestPalindrom(String s){
        String res = "";
        for(int i = 0; i < s.length(); i++){
            String s1 = pal(s, i, i);
            String s2 = pal(s, i, i + 1);
            if(s1.length() > res.length()) res = s1;
            if(s2.length() > res.length()) res = s2;
        }
        return res;
    }
    public String pal(String s, int l, int r){
        while(l >= 0 && r < s.length()){
            if(s.charAt(l) != s.charAt(r)) return s.substring(l + 1, r);
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }
}

