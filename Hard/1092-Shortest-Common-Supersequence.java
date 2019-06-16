/*
HARD
1092. Shortest Common Supersequence

*/

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