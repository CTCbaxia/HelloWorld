/*
EASY
69. Sqrt(x)
https://leetcode.com/problems/sqrtx/description/

TIME: 0911 - 10min
RESULT: 20% - 34ms
NOTES: 
小技巧： 用 mid == x / mid 而非 mid * mid = x。这样可以避免 overflow
*/

/*
SOLUTION 1: Binary Search
TIME: 0911 - 10min
RESULT: 20% - 34ms
*/
class Solution {
    public int mySqrt(int x) {
        int lo = 1, hi = Integer.MAX_VALUE - 1;
        while(lo <= hi){
            int mid = (lo + hi) / 2;
            if(mid == x / mid) return mid;
            else if(mid < x / mid) lo = mid + 1;
            else hi = mid - 1;
        }
        return lo - 1;
    }
}

/*
SOLUTION 0: 累加
TIME: 0911 - 10min
RESULT: 6% - 53ms
*/
class Solution {
    public int mySqrt(int x) {
        int i = 1;
        while(i * i <= x && i * i > 0) i++;
        return i - 1;
    }
}