/*
139. Word Break
https://leetcode.com/problems/word-break/description/

*/
//-------2 ROUND FOR MS ------------------------------------------
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean result = false;
        if(s.length() == 0) return true;
        for(String word : wordDict){
            int i = 0;
            while(i < s.length() && i < word.length()){
                if(word.charAt(i) != s.charAt(i)) break;
                i++;
            }
            if(i == word.length()){
                //matched
                boolean res = true;
                res = res && wordBreak(s.substring(i, s.length()), wordDict);
                result = result || res;
            }
        }
        return result;
    }

}

//DP REFERENCE
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];//到s的每一个 index 是否有匹配
        dp[0] = true;
        for(int j = 1; j <= s.length(); j++){
            for(int i = 0; i < j; i++){
                //如果可以从头一直走到j,dp[j] = true; 这取决于能不能 dp[i] 以及可以从 i - j
                //任何满足条件就行，所以只有有满足条件的，就true
                if(dp[i] && wordDict.contains(s.substring(i, j))){
                    dp[j] = true;
                }
                
            }
        }
        return dp[s.length()];
    }

}

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];//到s的每一个 index 是否有匹配
        dp[0] = true;
        for(int j = 1; j <= s.length(); j++){
            for(int i = 0; i < j; i++){
                //可以简化，其实和我 recursive 思路一样
                dp[j] = dp[j] || dp[i] && wordDict.contains(s.substring(i, j));
                
            }
        }
        return dp[s.length()];
    }

}





//-------1 ROUND------------------------------------------

//Time Limited Exceed
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean result = false;
        result = match(s, wordDict, 0);
        return result;
    }
    
    //要返回匹配的 N 个结果
    private boolean match(String s, List<String> wordDict, int index){
        boolean result = false;
        //loop every words in the wordDict
        for(int i = 0; i < wordDict.size(); i++){

            //start from the first char and if matched
            if(wordDict.get(i).charAt(0) == s.charAt(index)){
                int j = 1;
                while(j < wordDict.get(i).length() && index + j < s.length()){
                    if(wordDict.get(i).charAt(j) != s.charAt(index + j)){
                        break;
                    }
                    j++;
                }
                
                //check if the word in Dic was matched
                if(j == wordDict.get(i).length()){
                    if(index + j == s.length()){
                        //所有单词匹配成功
                        return true;
                    }else {
                        result = match(s, wordDict, index + j);
                        if(result == true){
                            return result;
                        }
                    }     
                }     
            }

        } 

        return result;

    }
}



/*
1. 有传参问题，boolean的传参到别的方法里面，只会改变被赋值的那个新变量的值，不会改变原有变量的值
2. 注意：一旦匹配成功，就要 return，不要继续运行了。因为继续运行会 1）耗时；2）将原本 true 的结果重新赋值为 false。参考以下情况：
"abcd"
["a","abc","b","cd"]
*/




//java visualizer

import java.util.*;
public class ClassNameHere {
   public static void main(String[] args) {
       
      String s = "leetcode";
      List<String> wordDict = new ArrayList<String>();
      
      wordDict.add("leet");
      wordDict.add("code");

        boolean result = false;
        result = match(s, wordDict, 0);
        System.out.println(result);
        return;

   }
    private boolean match(String s, List<String> wordDict, int index){
        boolean result = false;
        //loop every words in the wordDict
        for(int i = 0; i < wordDict.size(); i++){

            //start from the first char and if matched
            if(wordDict.get(i).charAt(0) == s.charAt(index)){
                int j = 1;
                while(j < wordDict.get(i).length() && index + j < s.length()){
                    if(wordDict.get(i).charAt(j) != s.charAt(index + j)){
                        break;
                    }
                    j++;
                }
                System.out.println("index:" + index);
                System.out.println("j:" + j);
                //check if the word in Dic was matched
                if(j == wordDict.get(i).length()){
                    if(index + j == s.length()){
                        //所有单词匹配成功
                        
                        return true;
                    }else {
                        result = match(s, wordDict, index + j);
                        
                    }     
                }     
            }

        } 

        return result;

    }
}