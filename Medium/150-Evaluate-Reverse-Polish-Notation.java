/*
MEDIUM
150. Evaluate Reverse Polish Notation
https://leetcode.com/problems/evaluate-reverse-polish-notation/description/

TIME: 0809 - 30min
RESULT: 90%% - 6ms
NOTES: 
0. Reverse Polish notation (RPN) is a mathematical notation in which operators follow their operands, in contrast to Polish notation (PN), in which operators precede their operands. It does not need any parentheses as long as each operator has a fixed number of operands. 
1. Integer.parseInt(String) 可以把包含正负符号在内的部分都转化成对应的整数："-1" = -1; "+3" = 3
2. "==" vs ".equals()" : https://stackoverflow.com/questions/513832/how-do-i-compare-strings-in-java
    == tests for reference equality (whether they are the same object).
    .equals() tests for value equality (whether they are logically "equal").
3. how to read error code: https://stackoverflow.com/questions/39849984/what-is-a-numberformatexception-and-how-can-i-fix-it/39850130
4. 注意 +, * 无所谓顺序（可以直接pop）； -, / 要按照顺序（先存一波 pop）
THOUGHTS:

*/
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for(String s : tokens){
            if(s.equals("+")){
                stack.push(stack.pop() + stack.pop());
            }else if(s.equals("-")){
                int val = stack.pop();
                stack.push(stack.pop() - val);                
            }else if(s.equals("*")){
                stack.push(stack.pop() * stack.pop());                  
            }else if(s.equals("/")){
                int val = stack.pop();
                stack.push(stack.pop() / val);  
            }else{ //turns string into number
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }
}