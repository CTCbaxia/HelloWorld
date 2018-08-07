/*
HARD
224. Basic Calculator
https://leetcode.com/problems/basic-calculator/description/

TIME: 0716
RESULT: 
NOTES:

*/
class Solution {
    public int calculate(String s) {
        Stack<Integer> number = new Stack<Integer>();
        Stack<Character> operations = new Stack<Character>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '+' || c == '-') operations.push(c);
            else if (c == ' ') continue;
            else if (c == ')'){
                int val = number.pop();
                char ops = operations.pop();
                if(ops == '+') number.push(number.pop() + val);
                else if(ops == '-') number.push(number.pop() - val);
            }else{ number.push(Character.getNumericValue(c));}
        }
        System.out.println(number);
        System.out.println(operations);
        return 0;
    }
}