/*
HARD
32. Longest Valid Parentheses

TIME: 
RESULT: 
NOTES: 
*/
/*
Stack for index + recursive for remaining
每次 ）都计算当前匹配的长度，update res
    如果 stack 为空：0 - i 全部匹配，i + 1
    如果 stack 不为空：从 stack peek index 到 i 都匹配
如果 i 之前的substring不可能再匹配了(多余")")，就recursive查 remaining 的最大长度，返回大值

Time: O(n)
Space: O(n)
*/
class Solution {
    public int longestValidParentheses(String s) {
        int res = 0;//should be max
        Stack<Integer> stack = new Stack<>();//store index
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(') stack.push(i);
            else{
                if(stack.isEmpty()){//多余的 ) 不可能被匹配了。中断，不可能和后面连续了
                    return Math.max(res, longestValidParentheses(s.substring(i + 1)));
                }else{
                    stack.pop();//先匹配出去
                    //计算目前为止匹配的长度
                    //if nothing left, from 0 to i all matched
                    //if ( left, from pop index + 1 to i all matched
                    int curlen = stack.isEmpty() ? i + 1 : i - stack.peek();
                    res = Math.max(res, curlen);
                }
            }
        }
        return res;
    }
}