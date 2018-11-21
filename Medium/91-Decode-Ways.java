/*
MEDIUM
91. Decode Ways
https://leetcode.com/problems/decode-ways/description/

TIME: 0824 - 2h
RESULT: 97% - 1ms

*/
/*
Dynamic Programming
dp[i] means how many result if we have string s with size of i
and it means we choose all elements until index (i - 1) in s

dp[i] = 
if(num at i > 0) + dp[i - 1]
if(num at (i-1, i) <= 26 && >= 10) + dp[i - 2]

Time: O(n)
Space: O(n)
*/
  
class Solution {
    public int numDecodings(String s) {
        int len = s.length();
        if(len == 0) return 0;
        int[] dp = new int[len + 1];//dp[i] string with size of i (如果取到 s 里面 i-1), how many result
        
        dp[0] = 1;
        dp[1] = s.charAt(0) - '0' == 0 ? 0 : 1;
        for(int i = 2; i <= len; i++){
            //i 是 string size，对应到 s 里面 i - 1 的元素
            int n1 = Integer.parseInt(s.substring(i - 1, i));//size i 的 string 里面最后一个元素 (i - 1)
            int n2 = Integer.parseInt(s.substring(i - 2, i));//最后两个元素
            if(n1 > 0) dp[i] += dp[i - 1];
            if(n2 >= 10 && n2 <= 26) dp[i] += dp[i - 2];
        }
        return dp[len];
    }

}


/*
DFS

Time: O(n!)
Space: O(1)
*/
class Solution {
    public int numDecodings(String s) {
        return decode(s, 0);
    }
    private int decode(String s, int index){
        if(index == s.length()) return 1;
        if(s.charAt(index) - '0' == 0) return 0;
        
        int res = 0;
        if(s.charAt(index) - '0' > 0) res += decode(s, index + 1);
        if(index + 2 <= s.length() && Integer.parseInt(s.substring(index, index + 2)) <= 26){
            res += decode(s, index + 2);
        }
        return res;
    }
}


















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