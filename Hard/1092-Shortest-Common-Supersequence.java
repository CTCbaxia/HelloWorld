/*
HARD
1092. Shortest Common Supersequence
*/
/*
Dynamic Programming
最小公约数 --> 最大公倍数
Find the Longest Common Subsequence in str1 and str2 using DP
And then fill the rest of the result

Time: O(mn) + O(m + n)
Space: O(mn)
*/
class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        // find the LCS first and add " " as terminal
        // so that all the rest of the chars in str1 and str2 after the LCS can be added to the res
        // because there is no " " in str1 and str2, which means all chars till end will be appended
        String LCS = findLCS(str1, str2) + " ";
        System.out.println(LCS);
        StringBuilder sb = new StringBuilder();
        int i1 = 0, i2 = 0;
        for(int i = 0; i < LCS.length(); i++, i1++, i2++){//need to increase for all i, i1, i2
            while(i1 < str1.length() && str1.charAt(i1) != LCS.charAt(i)){
                sb.append(str1.charAt(i1++));
            }
            while(i2 < str2.length() && str2.charAt(i2) != LCS.charAt(i)){
                sb.append(str2.charAt(i2++));
            }
            sb.append(LCS.charAt(i));
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
    private String findLCS(String s1, String s2){
        String res = "";
        int n1 = s1.length(), n2 = s2.length();
        String[][] dp = new String[n1 + 1][n2 + 1];
        
        //inialization
        for(int i = 0; i < n1 + 1; i++){
            Arrays.fill(dp[i], "");
        }
        
        //assign value
        for(int i = 1; i < n1 + 1; i++){
            for(int j = 1; j < n2 + 1; j++){
                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + s1.charAt(i - 1);
                    if(dp[i][j].length() > res.length()){
                        res = dp[i][j];
                    }
                }else{
                    dp[i][j] = dp[i][j - 1].length() > dp[i - 1][j].length()
                        ? dp[i][j - 1]
                        : dp[i - 1][j];
                }
            }
        }
        return res;
    }
}





/*❌
Recursion:
Helper function: 
directly merge str2 to str1 by finding the first occurance of the (1st char in str2) in str1
prefix = first substring of str1 divided by c (or ""  if there is no c in str1) + c
append = shortestCommonSupersequence(rest of str1, rest of str2);

shortestCommonSupersequence:
need to try both merging ways(str2 to str1 && str1 to str2)
return the shortest

Time: O(mn)  m = str1.length(), n = str2.length(), worst case: 完全不重合。只用算一次的 * 2
Space: O((m + n)^2) 对 (m + n) 的一次 space 走 (m + n) 深度的递归
*/
class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        String res1 = mergeStr2ToStr1(str1, str2);
        String res2 = mergeStr2ToStr1(str2, str1);
        
        if(res1.length() < res2.length()) return res1;
        else return res2;
        
    }
    private String mergeStr2ToStr1(String str1, String str2){//use str2's 1st char to find in str1
        if(str1.length() == 0) return str2;
        if(str2.length() == 0) return str1;
        
        char c = str2.charAt(0);
        int i = 0;
        for(; i < str1.length(); i++){
            if(str1.charAt(i) == c)  break;
        }
        
        if(i == str1.length()) i = -1;//if there is no char c, add c to the front so we maintain the most choices for future use
        String prefix = (i == -1) ? "" + c : (str1.substring(0, i) + c);
        String append = shortestCommonSupersequence(str1.substring(i + 1), str2.substring(1));
        
        return prefix + append;        
    }

}
