/*
EASY
246. Strobogrammatic Number

RESULT: 98% - 2ms
NOTES: 

*/
/*
Two Pointers:
0, 0
1, 1
6, 9
8, 8
9, 6

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean isStrobogrammatic(String num) {
        int lo = 0, hi = num.length() - 1;
        while(lo <= hi){
            char l = num.charAt(lo++);
            char h = num.charAt(hi--);
            if(l == '0' && h == '0') continue;
            if(l == '1' && h == '1') continue;
            if(l == '8' && h == '8') continue;
            if(l == '6' && h == '9') continue;
            if(l == '9' && h == '6') continue;
            return false;
        }
        return true;
    }
}