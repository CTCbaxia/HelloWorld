/*
EASY
28. Implement strStr()

TIME: 
RESULT: 
NOTES:
*/
/*
Two Pointers:

Time: O(n^2)
Space: O(1)
*/
class Solution {
    public int strStr(String haystack, String needle) {
        int i1 = 0, i2 = 0;
        int len1 = haystack.length(), len2 = needle.length();
        int res = 0;
        while(i1 < len1 && i2 < len2){
            if(haystack.charAt(i1) != needle.charAt(i2)){
                i1++;
            }else{
                res = i1;
                while(i1 < len1 && i2 < len2 && haystack.charAt(i1) == needle.charAt(i2)){
                    i1++;
                    i2++;
                }
                if(i2 == len2) return res;
                else{
                    i2 = 0;
                    i1 = res + 1;//要回到下一个 i 继续匹配
                } 
            }
        }
        return len2 == 0 ? 0 : -1;
    }
}