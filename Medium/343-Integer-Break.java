/*
MEDIUM
343. Integer Break
https://leetcode.com/problems/integer-break/description/

TIME: 0912 - 1h
RESULT: 100% - 0ms
NOTES:
对于任意大于 4 的数，总能分解成更小的数使得 n1*n2 > n
最有效的分解因子为 2， 3。其中 3 优于 2（2*2*2 < 3*3）。
所以要将数字分解成 2 和 3 的组合，且 2 的数量不能超过 2 个。

这道题用 DP 解是最直观的。要学会直观解法。
*/
/*
SOLUTION REFERENCE 1: 
TIME: 0912 - 1h
RESULT: 57% - 1ms
*/
class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        
        for(int i = 2; i < n + 1; i++){
            for(int j = 1; j <= i/2; j++){
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]));
            }
        }
        return dp[n];
    }

}
/*
SOLUTION REFERENCE 0: 
TIME: 0912 - 1h
RESULT: 100% - 0ms
*/
class Solution {
    public int integerBreak(int n) {
        if(n == 2) return 1; 
        if(n == 3) return 2;
        int product = 1;
        while(n > 4){
            product *= 3;
            n -= 3;
        }
        product *= n;
        return product;
    }

}
//SOLUTION 0: 
//有分成两份和三份的思想 但没有抓到点子上
class Solution {
    public int integerBreak(int n) {
        return breaker(n, true);
    }
    private int breaker(int n, boolean isFirst){
        int n1 = n/2;
        int n2 = n - n1;
        int n3 = n/3;
        int n4 = n/3;
        int n5 = n - n3 - n4;
        if(n1 * n2 >= n3 * n4 * n5){
            if(isFirst || n1 * n2 > n) return breaker(n1, false) * breaker(n2, false);
            else return n;
        }else{
            if(isFirst || n3 * n4 * n5 > n) return breaker(n3, false) * breaker(n4, false) * breaker(n5, false);
            else return n;            
        }
    }
}