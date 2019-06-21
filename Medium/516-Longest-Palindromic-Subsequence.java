/*
MEDIUM
516. Longest Palindromic Subsequence

*/
/*
DP
dp[i][j] 代表从第 i 位到第 j 位的最长 subsequence 数量


initialization:
dp[i][i] = 1;

Transform:
dp[i][j] = 
1) dp[i + 1][j - 1] + 2, if s[i] == s[j]
2) Math.max(dp[i + 1][j], dp[i][j - 1]), if s[i] != s[j]

Time: O(n^2)
Space: O(n^2)
*/
class Solution {
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        //initialization
        for(int i = 0; i < dp.length; i++){
            dp[i][i] = 1;
        }
        //Transform
        for(int i = dp.length - 1; i >= 0; i--){
            for(int j = i + 1; j < dp[i].length; j++){
                if(s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i + 1][j - 1] + 2;
                else{
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }

}




/*
Recursion + Two Pointers
Time: O(n^3)
Space: O(n)
*/
class Solution {
    public int longestPalindromeSubseq(String s) {
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            String sLeft = s.substring(0, i);
            String sRight1 = new StringBuilder(s.substring(i)).reverse().toString();
            String sRight2 = new StringBuilder(s.substring(i + 1)).reverse().toString();
            res = Math.max(res, findLongestPal(sLeft, sRight1)*2);
            res = Math.max(res, findLongestPal(sLeft, sRight2)*2 + 1);
        }
        return res;
    }
    private int findLongestPal(String s1, String s2){
        if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) return 0;
        if(s1.charAt(0) == s2.charAt(0)){
            return 1 + findLongestPal(s1.substring(1), s2.substring(1));
        }else{
            int res1 = findLongestPal(s1.substring(1), s2);
            int res2 = findLongestPal(s1, s2.substring(1));
            return Math.max(res1, res2);
        } 

    }
}


/*
比上一个复杂一些
Recursion + Two Pointers
Time: O(n^3)
Space: O(n)
*/
class Solution {
    public int longestPalindromeSubseq(String s) {
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            String sLeft = s.substring(0, i);
            String sRight1 = new StringBuilder(s.substring(i)).reverse().toString();
            String sRight2 = new StringBuilder(s.substring(i + 1)).reverse().toString();
            res = Math.max(res, findLongestPal(sLeft, sRight1)*2);
            res = Math.max(res, findLongestPal(sLeft, sRight2)*2 + 1);
        }
        return res;
    }
    private int findLongestPal(String s1, String s2){
        if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) return 0;
        int res = 0;
        if(s1.charAt(0) == s2.charAt(0)) return 1 + findLongestPal(s1.substring(1), s2.substring(1));
        for(int i1 = 0; i1 < s1.length(); i1++){
            for(int i2 = 0; i2 < s2.length(); i2++){
                if(s1.charAt(i1) == s2.charAt(i2)){
                    res = 1 + findLongestPal(s1.substring(i1 + 1), s2.substring(i2 + 1));
                }
            }
        }
        for(int i2 = 0; i2 < s2.length(); i2++){
            for(int i1 = 0; i1 < s1.length(); i1++){
                if(s1.charAt(i1) == s2.charAt(i2)){
                    res = Math.max(res, 1 + findLongestPal(s1.substring(i1 + 1), s2.substring(i2 + 1))) ;
                }
            }
        }
        return res;
    }
}