/*
MEDIUM
22. Generate Parentheses
https://leetcode.com/problems/generate-parentheses/description/

TIME: 0823 - 1h
RESULT: 83% - 2ms
NOTES:
1. 记得要把 stack 的内容补回来，保持其他情况的路径不会受影响
*/
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