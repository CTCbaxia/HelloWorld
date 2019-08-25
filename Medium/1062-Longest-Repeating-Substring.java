/*
M
1062. Longest Repeating Substring
*/
/*
Dynamic Programming
definition:
dp[i][j]: for substring that ending with S[i] and S[j], what is the longest same substring

result:
The largest dp[i][j]

Transfer function:
dp[i][j] = S[i] == S[j] 
         ? dp[i - 1][j - 1] + 1
         : 0

Time: O(mn)
Space: O(mn)*/
class Solution {
    public int longestRepeatingSubstring(String S) {
        int res = 0;
        int n = S.length();
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 1; i < n + 1; i++){
            for(int j = i + 1; j < n + 1; j++){
                if(S.charAt(i - 1) == S.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }
}
