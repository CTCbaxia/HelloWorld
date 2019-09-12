/*
139. Word Break
https://leetcode.com/problems/word-break/description/

*/

/*
DFS + Dynamic Programming + memo:
dp[i] : from i to the end of dp, it is valid or not
    dp[i] = 0 : haven't visit, need to check
    dp[i] = 1 : valid
    dp[i] = -1 : invalid

Time: O(mn^2) n = s.length(), m = wordDict.size()
Space: O(n)
*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;//"" should be true
        return dfs(0, dp, s, wordDict);
    }
    private boolean dfs(int start, int[] dp, String s, List<String> wordDict){
        if(dp[start] != 0) return dp[start] == 1;//use memory
        
        boolean res = false;
        for(String w : wordDict){
            int i = s.indexOf(w, start);//if there is a w in s after start
            if(i == start){
                res |= dfs(start + w.length(), dp, s, wordDict);
            } 
        }
        dp[start] = res ? 1 : -1;
        return res;
    }

}


//变种 for fb：变种题，求最小合理分割次数
/*
DFS + Dynamic Programming + memo:
dp[i] : from i to the end of dp, the min num of words needed for valid(positive val) or not(-1)
    dp[i] = 0 : haven't visit, need to check
    dp[i] = positive : valid, and min num of words needed
    dp[i] = -1 : invalid

Time: O(mn^2) n = s.length(), m = wordDict.size()
Space: O(n)
*/
class Solution {
    public int wordBreak(String s, List<String> wordDict) {
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 0;//"" needs 0 word
        return dfs(0, dp, s, wordDict);
    }
    private int dfs(int start, int[] dp, String s, List<String> wordDict){//from start in s, the min words to make up s
        if(start == s.length()) return dp[s.length()];//reach end, can return 0
        if(dp[start] != 0) return dp[start];//use memory
        
        int res = Integer.MAX_VALUE;
        for(String w : wordDict){
            int i = s.indexOf(w, start);//if there is a w in s after start
            if(i == start){
                int num = 1 + dfs(start + w.length(), dp, s, wordDict);//include the w itself, if return -1, num should be 0
                if(num > 0) res = Math.min(res, num);//get smallest
            } 
        }
        dp[start] = res == Integer.MAX_VALUE ? -1 : res;
        return dp[start];
    }

}
/*
test case: 
"leetcode"
["leet","co","de","code","lee","tc","ode"]
return: 2
*/


//变种 for fb：变种题，求最小合理分割次数
/*
DP: 
boolean dp[i] = from 0 to i, if we can make it 
then try from 0 to each letter to see if 0 - that letter can be make up
dp[i] = dp[j] + 1
dp[i] = -1 : cannot make up
dp[i] >= 0 : min num of words to make up

Time: O(mn^2) n = s.length(), m = wordDict.size()
Space: O(n)
*/
class Solution {
    public int wordBreak(String s, List<String> wordDict) {
        int[] dp = new int[s.length() + 1];//dp[i] if choose first i letter, is there a match
        dp[0] = 0;//""
        for(int i = 1; i < dp.length; i++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < i; j++){//when j == 0, it is the whole word
                if(dp[j] != -1 && wordDict.contains(s.substring(j, i))){
                    min = Math.min(min, dp[j] + 1);
                }
            }
            dp[i] = min == Integer.MAX_VALUE ? -1 : min;
        }
        return dp[s.length()];
    }
}






/*
DP: 
boolean dp[i] = from 0 to i, if we can make it (dp[i] if choose first i letter, is there a match)
then try from 0 to each letter to see if 0 - that letter can be make up

构造 dp size = s.length() + 1 是为了方便 substring，以及初始化。总得有一个初始化的值为 true


Time: O(mn^2) n = s.length(), m = wordDict.size()
Space: O(n)
*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];//dp[i] if choose first i letter, is there a match
        dp[0] = true;//""
        for(int i = 1; i < dp.length; i++){
            for(int j = 0; j < i; j++){//when j == 0, it is the whole word
                dp[i] |= dp[j] && wordDict.contains(s.substring(j, i));
            }
        }
        return dp[s.length()];
    }
}



//dfs
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s.length() == 0) return true;
        for(String word : wordDict){
            int i = 0;
            while(i < s.length() && i < word.length() && s.charAt(i) == word.charAt(i)){
                i++;
            }
            if(i == word.length()){
                if(wordBreak(s.substring(i), wordDict)) return true;
            }
        }
        return false;
    }
}


















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