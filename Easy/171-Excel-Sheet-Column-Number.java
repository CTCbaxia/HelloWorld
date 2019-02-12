/*
EASY
171. Excel Sheet Column Number
https://leetcode.com/problems/excel-sheet-column-number/description/

TIME: 0911 - 10min
RESULT: 100% - 1ms
NOTES: 
要有一种迭代思维： res = res * 26 + (int)(c - 'A' + 1);
而不是用 res += Math.power(26, n) * (int)(c - 'A' + 1); //这样的 power 得为小数
*/
class Solution {
    public int titleToNumber(String s) {
        int res = 0;
        for(char c : s.toCharArray()) res = res * 26 + (int)(c - 'A' + 1);//字符串在计算的时候，可以不用 (int)
        return res;
    }
}

class Solution {
    public int titleToNumber(String s) {
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(s.length() - 1 - i);
            res += Math.pow(26, i) * ((c - 'A') + 1);
        }
        return res;
    }
}