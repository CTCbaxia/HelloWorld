/*
MEDIUM
22. Generate Parentheses
https://leetcode.com/problems/generate-parentheses/description/

TIME: 1008 - 10min
RESULT: 100% - 1ms
NOTES:
1. 记得要把 stack 的内容补回来，保持其他情况的路径不会受影响
*/
//--------2 ROUND FOR MS------------------------------------
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        dfs(n, 0, result, new StringBuilder());
        return result;
    }
    private void dfs(int remain, int left, List<String> result, StringBuilder sb){
        if(left < 0 || remain < 0) return;//omit
        if(left == 0 && remain == 0){
            result.add(sb.toString());
            return;
        }
        if(left > 0){
            sb.append(')');
            dfs(remain, left - 1, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if(remain > 0){
            sb.append('(');
            dfs(remain - 1, left + 1, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        return;
    }
}



class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        dfs(n, 0, result, "");
        return result;
    }
    private void dfs(int remain, int left, List<String> result, String s){
        if(left < 0 || remain < 0) return;
        if(left == 0 && remain == 0) result.add(s);
        if(left > 0) dfs(remain, left - 1, result, s + ")");
        if(remain > 0) dfs(remain - 1, left + 1, result, s + "(");
        return;
    }
}

//--------1 ROUND------------------------------------
/*
SOLUTION REFERENCE:
TIME: 0823 - 2Omin
RESULT: 83% - 2ms
THOUGHTS:
直接可以用 left 和 right 的数量来算是否可以放右括号
*/
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        generate(result, n, n, "");  
        return result;
    }
    private void generate(List<String> result, int left, int right, String s){
        if(left == 0 && right == 0) result.add(s); 
        if(left > 0) generate(result, left - 1, right, s + "(");   
        if(right > 0 && right > left) generate(result, left, right - 1, s + ")");  
        return;
    }
}

/*
SOLUTION 0:
TIME: 0823 - 40min
RESULT: 20% - 4ms
THOUGHTS:
用一个 stack 来存储 (
*/

class Solution {
    public List<String> generateParenthesis(int n) {
        Stack<Character> leftstack = new Stack<Character>();
        List<String> result = new ArrayList<String>();
        generate(result, n, n, "", leftstack);  
        return result;
    }
    private void generate(List<String> result, int left, int right, String s, Stack<Character> leftstack){
        if(left == 0 && right == 0) result.add(s); 
        if(left < 0 || right < 0) return;
        if(left > 0){
            leftstack.push('(');
            generate(result, left - 1, right, s + "(", leftstack);     
            leftstack.pop();
        }
        if(right > 0 && !leftstack.empty()){
            leftstack.pop();
            generate(result, left, right - 1, s + ")", leftstack);  
            leftstack.push('(');
        }
        return;
    }
}