/*
M
1143. Longest Common Subsequence
*/
/*
DP
dp[i][j] : for text1[0...i] and text2[0...j], the longest common subsequence
如果 当前对比的 char 相同，那么最长为 dp[i - 1][j - 1] + 1
否则，取上一级里面更大的一个值

transfer function:
dp[i][j] = text1[i] == text2[j]
    ? dp[i - 1][j - 1] + 1
    : Math.max(dp[i - 1][j], dp[i][j - 1])


Time: O(n^2)
Space: O(n^2)
*/
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for(int i = 1; i < n1 + 1; i++){
            for(int j = 1; j < n2 + 1; j++){
                dp[i][j] = text1.charAt(i - 1) == text2.charAt(j - 1)
                    ?  dp[i - 1][j - 1] + 1
                    :  Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[n1][n2];
    }
}
