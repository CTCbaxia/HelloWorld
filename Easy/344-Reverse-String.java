/*
EASY
344. Reverse String
https://leetcode.com/problems/reverse-string/description/

TIME: 0929 - 2min
RESULT: 44% - 3ms
NOTES: 

*/
class Solution {
    public String reverseString(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = s.length() - 1; i >= 0; i--){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}