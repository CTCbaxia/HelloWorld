/*
HARD
301. Remove Invalid Parentheses

*/
/*
Explanation:
We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.

To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix. However, if we remove any one, we will generate duplicate results, for example: s = ()), we can remove s[1] or s[2] but the result is the same (). Thus, we restrict ourself to remove the first ) in a series of concecutive )s.

After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string. However, we need to keep another information: the last removal position. If we do not have this position, we will generate duplicate by removing two ‘)’ in two steps only with a different order.
For this, we keep tracking the last removal position and only remove ‘)’ after that.

Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
The answer is: do the same from right to left.
However a cleverer idea is: reverse the string and reuse the code!

Time: O(nk), every recursion path is to generate one result, if we have k valid output, 构造每个答案需要O(n)
Space: O(1) - recursion stack : O(n) )))(((
*/
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        remove(0, 0, s, new char[]{'(',')'}, res);
        return res;
    }
    private void remove(int start, int lastRemoved, String s, char[] pair, List<String> res){
        int count = 0;
        for(int i = start; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == pair[0]) count++;
            else if(c == pair[1]) count--;
            //can also be letter, just skip
            
            if(count < 0){//need to remove one
                for(int j = lastRemoved; j <= i; j++){
                    if(s.charAt(j) == pair[1] && (j == lastRemoved || s.charAt(j - 1) != s.charAt(j))){//remove j
                        remove(i, j, s.substring(0, j) + s.substring(j + 1), pair, res);
                    }
                }
                return;
            }
        }
        
        //if we arrive here, the s is valid
        String reversed = new StringBuilder(s).reverse().toString();
        // check if left-to-right and right-to-left all finished
        // if yes, return result
        // if no, do right-to-left
        if(pair[0] == ')'){//finished
            res.add(reversed);
        }else{
            remove(0, 0, reversed, new char[]{pair[1], pair[0]}, res);
        }
    }
}




//time complexity: O(nk) k valid output, 构造每个答案需要O(n)

/*
DFS: remove first in 连续的，remember last removing position
一遇到invalid，就在之前的部分last_j to i 移除多余的 ），然后dfs剩余的部分。

go through the string, remove when invalid
what to remove: any ) in the prefix, if 连续), remove the first time it shows up
then, dfs the remaining string, removing from the last removing position

And to check left, reverse the string and do the same

last_i: before are all valid
last_j: last position remove ), to avoid duplicate result

The program only generates valid answers. Every path in the search generates one valid answer. 
Time: O(nk) for every res, we loop the string once
Space: O(1)

*/
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        dfs(s, 0, 0, result, '(', ')');
        return result;
    }
    private void dfs(String s, int last_i, int last_j, List<String> res, char lc, char rc){
        int left = 0;
        for(int i = last_i; i < s.length(); i++){
            if(s.charAt(i) == lc) left++;
            else if(s.charAt(i) == rc) left--;
            
            if(left < 0){//一旦发现不匹配，就删减，然后只用关注后面substring就好，不用继续i++, 所以这里return
                for(int j = last_j; j <= i; j++){// Try removing one at each position, skipping duplicates
                    if(s.charAt(j) == rc && (j == last_j || s.charAt(j - 1) != rc)){
                        dfs(s.substring(0, j) + s.substring(j + 1), i, j, res, lc, rc);
                    }
                }
                return;// Stop here. The recursive calls handle the rest of the string.
            }
        }

        // No invalid closed parenthesis detected. Now check opposite direction, or reverse back to original direction.
        String reverse = new StringBuilder(s).reverse().toString();//reverse string
        if(lc == '('){//if now we only check that no more ")", we need do so for "("
            dfs(reverse, 0, 0, res, rc, lc);
        }else{
            //如果已经是 reverse 过的，这样会 reverse 回来
            res.add(reverse);
        }
        return;
               
    }
}

/*
Time:
The whole search space is a tree with k leaves. The number of nodes in the tree is roughly O(k). But this is not always true, for example a degenerated tree.
To generate one node it requires O(n) time from the string concatenation among other things. 
So roughly O(nk). Accurately O(nm) where m is the total "number of recursive calls" or "nodes in the search tree". Then you need to relate m to n in the worst case.
*/


//简化版本 只返回一个结果
class Solution {
    public List<String> removeInvalidParentheses(String s){
        String res1 = validParenthese(s, 0, '(', ')');
        String reverse = new StringBuilder(res1).reverse().toString();
        String res2 = validParenthese(reverse, 0, ')', '(');
        String result = new StringBuilder(res2).reverse().toString();
        return new ArrayList<>();
    }   
    private String validParenthese(String s, int start, char lc, char rc){
        int left = 0;
        for(int i = start; i < s.length(); i++){
            if(s.charAt(i) == lc) left++;
            else if(s.charAt(i) == rc){
                left--;
                if(left < 0) return validParenthese(s.substring(0, i) + s.substring(i + 1), i, lc, rc);
            }
        }
        return s;
    }
}




