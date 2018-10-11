/*
EASY
541. Reverse String II

TIME: 
RESULT:
NOTES: 
String.valueOf(ch);
*/
class Solution {
    public String reverseStr(String s, int k) {
        int i = 0;
        int len = s.length();
        char[] ch = s.toCharArray();
        while(i < len){
            int lo = i;
            int hi = (i + k >= len) ? len - 1 : i + k - 1;
            while(lo < hi){
                char c = ch[lo];
                ch[lo] = ch[hi];
                ch[hi] = c;
                lo++;
                hi--;
            }
            i = i + 2*k;
        }
        
        return String.valueOf(ch);
    }

}