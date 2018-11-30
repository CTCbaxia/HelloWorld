/*
HARD
10. Regular Expression Matching

TIME: 
RESULT: 
*/
/*
Dynamic Programming:

dp[i][j] is true: until s.charAt(i - 1) and p.charAt(j - 1) is matched
if p.charAt(i - 1) is letter or '.'
    dp[i][j] = dp[i - 1][j - 1] iff s.charAt(i - 1) matches p.charAt(j - 1)
if p.charAt(i - 1) == '*'
    f[i][j] is true iff any of the following is true
    1) "a*" repeats 0 time and matches empty: dp[i][j - 2]
    2) "a*" repeats >= 1 times, so we need to see if s.charAt(i - 1) matches p.charAt(j - 2) (the letter before *)
        if they match, then we treat * as at least one repeat of s.charAt(i - 1), then: dp[i][j] = dp[i - 1][j]
        
Time: O(mn)
Space: O(mn)
*/
class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        
        //initialization
        dp[0][0] = true;//two empty string can match
        for(int j = 2; j < p.length() + 1; j++){//if s is empty, a*b*c* also match
            dp[0][j] = p.charAt(j-1) == '*' && dp[0][j-2];
        }
        //check
        for(int i = 1; i < s.length() + 1; i++){
            for(int j = 1; j < p.length() + 1; j++){
                if(p.charAt(j - 1) != '*'){// letter or '.'
                    if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.'){//if this char match
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }else{
                    if(j > 1){
                        dp[i][j] = dp[i][j - 2];// a* as empty
                        if(s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'){//看 s 的对应letter 和 p 的*之前的letter
                            dp[i][j] = dp[i][j] || dp[i - 1][j];//a* repeat >= 1 times
                        } 
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

}


/*
Recursion: 更 tuitive 一些
1. first compare the first letter (mind the bound)
2. check for the rest:
    - if p's next is *: try any of these scenario
        1) totally ignore the first two letters of p
        2) if the first match, try to skip first letter of s, and compare with the original p
        (每次把 s 往后挪一个，看能不能跟现有的p匹配。如果最后 s 的 a 都挪完了，下一个 recursion 就当做 1) totally ignore)
    - else
        first match and should also match from the second letter
*/
class Solution {
    public boolean isMatch(String s, String p) {
        if(p.length() == 0) return s.length() == 0;//如果根本没有 pattern，s 也应该为空
        boolean firstMatch = s.length() > 0 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');// 第一个letter是否match
        if(p.length() > 1 && p.charAt(1) == '*'){
            /*
            1. a* as empty: p 越过前两个 letter，再看是否match
            2. a* as >= 1 repeat: s 越过当前 letter
            */
            return isMatch(s, p.substring(2)) || firstMatch && isMatch(s.substring(1), p);
        }else{
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

}
