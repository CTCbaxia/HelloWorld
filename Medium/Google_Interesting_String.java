/*
M
Google_Interesting_String
https://leetcode.com/discuss/interview-question/350312/Google-or-Onsite-or-Interesting-String

Given a string s consisting of digits 0-9 and lowercase English letters a-z.

A string is interesting if you can split it into one or multiple substrings, 
such that each substring starts with a number and this number represents the number of characters after it. 
Retrun true if string s is intersting, otherwise false.

Example 1:

Input: "4g12y6hunter"
Output: true
Explanation: We can split it into "4g12y" and "6hunter".

3abc1a
42sad1b
*/
/*
DFS + Memo
There can be duplicate cases: 108abcdefgh1a
either you try 1 or 10, you will end at 1a as the last interesting substring
so you don't want to do it again,
so using memo can help you save extra duplicate effort.

Time: O(n)
Space: O(n)
*/
class Solution{
  public boolean isInteresting(String s){
      if(s.length() == 0) return false;// check for edge case ""
      int[] memo = new int[s.length()];
      return dfs(s, 0, memo) == 1;
  }
  private int dfs(String s, int i, int[] memo){
      memo[i] = -1;//initialization as visited but false
      int num = 0;
      while(i < s.length() && Character.isDigit(s.charAt(i))){ // everytime you use s.charAt(i), mind the length
          num = num * 10 + s.charAt(i) - '0';
          int nextIndex = i + num + 1;
          if(nextIndex > s.length()){
              memo[i] = -1;
              break;
          }else if(nextIndex == s.length()){
              memo[i] = 1;
          }else{
              if(memo[nextIndex] != 0){
                memo[i] = memo[nextIndex];
              }else{
                memo[i] = dfs(s, nextIndex, memo);
              }            
          }

          if(memo[i] == 1) break;// if one try is true, no need to try more
          i++;
      }
      return memo[i];
  }
}
