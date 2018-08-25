/*
MEDIUM
91. Decode Ways
https://leetcode.com/problems/decode-ways/description/

TIME: 0824 - 2h
RESULT: 97% - 1ms

*/
/*
SOLUTION 2: DP 不要辅助函数
TIME: 0824 - 5min
RESULT: 97% - 1ms
*/
//
class Solution {
    int count = 0;
    public int numDecodings(String s) {
        //from index to the last, calculate the number of ways
        int len = s.length();
        if(len == 0) return 0;
        
        int[] dp = new int[len + 1];
        dp[len] = 1;
        dp[len - 1] = ((s.charAt(len - 1) - '0') > 0) ? 1 : 0;
        for(int i = len - 2; i >= 0; i--){
            int num1 = s.charAt(i) - '0';
            int num2 = (num1 == 0) ? 0 : num1 * 10 + (s.charAt(i + 1) - '0');
            dp[i] = ((num1 > 0) ? 1 : 0) * dp[i + 1] + ((num2 > 0 && num2 < 27) ? 1 : 0) * dp[i + 2];
        }
        return dp[0];
    }
}

/*
SOLUTION 1: DP
TIME: 0824 - 30min
RESULT: 67% - 2ms
*/
class Solution {
    int count = 0;
    public int numDecodings(String s) {
        //from index to the last, calculate the number of ways
        int len = s.length();
        if(len == 0) return 0;
        int[] dp = new int[len + 1];
        dp[len] = 1;
        dp[len - 1] = isValid(s.charAt(len - 1) - '0');
        for(int i = len - 2; i >= 0; i--){
            int num1 = s.charAt(i) - '0';
            int num2 = (num1 == 0) ? 0 : num1 * 10 + (s.charAt(i + 1) - '0');
            dp[i] = isValid(num1) * dp[i + 1] + isValid(num2) * dp[i + 2];
        }
        return dp[0];
    }
    private int isValid(int num){
        if(num > 0 && num < 27) return 1;
        else return 0;
    }

}


/*
SOLUTION 0: 全局变量 + DFS
TIME: 0824 - 30min
RESULT: 1% - 588ms
*/
class Solution {
    int count = 0;
    public int numDecodings(String s) {
        nextWord(s, 0);
        return s.length() == 0 ? 0 : count;
    }
    private void nextWord(String s, int index){
        if(index >= s.length()){
            if(index == s.length()) count++;
            return;
        }
        
        int digit1 = s.charAt(index) - '0';
        if(digit1 > 0){ 
            nextWord(s, index + 1);
            if(index + 1 < s.length()){
                int digit2 = s.charAt(index + 1) - '0';
                if(digit1 * 10 + digit2 > 0 && digit1 * 10 + digit2 < 27){
                    nextWord(s, index + 2);
                }
            }
        }
        return;
    }
}