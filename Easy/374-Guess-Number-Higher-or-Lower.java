/*
EASY
374. Guess Number Higher or Lower
https://leetcode.com/problems/guess-number-higher-or-lower/description/

TIME: 0809 - 25min
RESULT: 100% - 0ms
NOTES: 
1. 注意 (l + r)/2 的时候不能越过 Integer.MAX_VALUE。 所以在 n 接近 Integer.MAX_VALUE 的时候，用 (l + r)/2 计算 mid 会越界

METHOD:
1. 二分法

*/
/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); 
*/

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int l = 1, r = n;
        int mid = l + (r - l) / 2; // mid = (l + r) / 2 会越界
        while(guess(mid) != 0){
            if(guess(mid) == 1){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
            mid = l + (r - l) / 2;
        }
        return mid;
    }
}