/*
389. Find the Difference
https://leetcode.com/problems/find-the-difference/description/

TIME: 0807 - 30min
RESULT: 89% - 4ms
NOTES:
1. char 可以直接 XOR

METHOD:
1. XOR
*/

class Solution {
    public char findTheDifference(String s, String t) {
        char xor = 0;
        if(s.length() == 0) return t.charAt(0);
        for(int i = 0; i < s.length(); i++){
            xor ^= s.charAt(i);
            xor ^= t.charAt(i);
        }
        xor ^= t.charAt(t.length() - 1);
        return xor;
    }
}




class Solution {
    public char findTheDifference(String s, String t) {
        int sxor = 0;
        int txor = 0;
        int result = 0;
        if(s.length() == 0) return t.charAt(0);
        for(int i = 0; i < s.length(); i++){
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            sxor = sxor ^ (cs - '0');
            txor = txor ^ (ct - '0');
        }
        txor = txor ^ (t.charAt(t.length() - 1) - '0');
        result = sxor ^ txor;
        return (char)(result + '0');
        
    }
}