/*
HARD
301. Remove Invalid Parentheses

TIME: 
RESULT: 
NOTES: 
*/
//time complexity: O(nk) k valid output, 构造每个答案需要O(n)

/*
DFS: remove first in 连续的，remember last removing position
一遇到invalid，就在之前的部分last_j to i 移除多余的 ），然后dfs剩余的部分。

go through the string, remove when invalid
what to remove: any ) in the prefix, if 连续), remove the first time it shows up
then, dfs the remaining string, removing from the last removing position

And to check left, reverse the string and do the same

last_i: before are all valid
last_j: last position remove )

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