/*
EASY
263. Ugly Number
https://leetcode.com/problems/ugly-number/description/

TIME: 0909 - 5min
RESULT: 100% - 0ms
NOTES: 
1. 考虑 0 余所有数均为 0，单挑出 0 的情况，避免死循环
2. 负数全部都为 false
*/
class Solution {
    public boolean isUgly(int num) {
        if(num == 0) return false;
        while(num % 2 == 0) num /= 2;
        while(num % 3 == 0) num /= 3;
        while(num % 5 == 0) num /= 5;
        if(num == 1) return true;
        else return false;
    }
}