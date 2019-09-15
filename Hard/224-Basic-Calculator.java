/*
HARD
224. Basic Calculator
https://leetcode.com/problems/basic-calculator/description/

TIME: 0716 - 4h
RESULT: 62% - 13ms
NOTES:
1. string 里面大于 10 的数字有多个 charAt() 的位数
2. sign 的用法

METHOD:
0. 用 sign 表示前一个符号
1. 每个左括号前只留一个 number，连同它们之间的符号一起存到 stack 里面。
2. 数字直接等它出现完全
3. 若出现左括号，则等待括号内 result 都计算完全（右括号出现），再将之前左括号左边的数和括号内的计算结果加和
*/
/*
Using Stack

Time: O(n)
Space: O(n)
*/
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        int curRes = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){//get the whole number
                int num = 0;
                while(i < s.length() && Character.isDigit(s.charAt(i))){
                    num = num * 10 + (s.charAt(i++) - '0');//damn! forget to increment
                }
                i--;//damn! forget to go one step back
                curRes += sign * num;
            }else if(c == '('){
                stack.push(curRes);
                stack.push(sign);//为什么要单独存一个 sign，因为这个sign对应的数字还不知道是多少
                curRes = 0;//reset
                sign = 1;//reset
            }else if(c == ')'){
                int curSign = stack.pop();
                int oparand = stack.pop();
                curRes = oparand + curSign * curRes;
            }else if(c == '-'){
                sign = -1;
            }else if(c == '+'){
                sign = 1;
            }
        }
        return curRes;
    }
}

"3+66-2-(2-3-1)-(3+(3-5)+(88-(21-(65+6))))"
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int tmpNum = 0;
        int result = 0;
        int sign = 1;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                tmpNum = c - '0';
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))){
                    tmpNum = tmpNum * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                result += sign * tmpNum;
            }else if(c == '('){
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            }else if(c == '+'){
                sign = 1;
            }else if(c == '-'){
                sign = -1;
            }else if(c == ')'){
                result = stack.pop() * result + stack.pop();
            }
        }
        return result;
    }
}


/*
TIME: 0716 - 3h
RESULT: 25% - 33ms

METHODS:
1. 数字：等到数字加和结束
2. ' ' ：无操作
3. + ：数字结束，可以把之前的数加和起来
4. - ：数字结束，可以把之前的数加和起来
5. ) ：数字结束，可以把之前的数加和起来。再 pop 上一个 (， 再把截止到上一个 ( 的内容加和起来。将数字存到 tmpNum 中。
6. ( ：输入到 operation 中
*/
class Solution {
    public int calculate(String s) {
        Stack<Integer> number = new Stack<Integer>();
        Stack<Character> operations = new Stack<Character>();
        int tmpNum = 0;
        int result = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ' ') continue;
            else if(c == '(') operations.push(c);
            else if(c == '+' || c == '-' || c == ')'){
                if(number.size() == 0){
                    number.push(tmpNum);
                }else{
                    if(operations.size() != 0){
                        if(operations.peek() == '(') number.push(tmpNum);
                        else number.push(calHelper(number,operations,tmpNum));             
                    }
                }
                tmpNum = 0;
                if(c == ')'){
                    operations.pop();//remove '('
                    if(operations.size() != 0){
                        tmpNum = number.pop();
                        tmpNum = calHelper(number,operations,tmpNum);
                    }
                }else operations.push(c);  
                
            }else{
                tmpNum = tmpNum * 10 + Character.getNumericValue(c);
            }
        }
        if(operations.size() != 0){
            result = calHelper(number,operations,tmpNum);          
        }else result = (number.size() == 0) ? tmpNum : number.pop();

        return result;
    }
    private int calHelper(Stack<Integer> number, Stack<Character> operations, int tmpNum){
        int val = number.pop();
        char ops = operations.pop(); 
        if(ops == '+') tmpNum = val + tmpNum;
        else if(ops == '-') tmpNum = val - tmpNum;
        return tmpNum;
    }
}





class Solution {
    public int calculate(String s) {
        Stack<Integer> number = new Stack<Integer>();
        Stack<Character> operations = new Stack<Character>();
        int tmpNum = 0;
        int result = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ' ') continue;
            else if(c == '(') operations.push(c);
            else if(c == '+' || c == '-' || c == ')'){
                if(number.size() == 0){
                    number.push(tmpNum);
                }else{
                    if(operations.size() != 0){
                        if(operations.peek() == '('){
                            number.push(tmpNum);
                        }else{
                            int val = number.pop();
                            char ops = operations.pop();
                            if(ops == '+') number.push(val + tmpNum);
                            else if(ops == '-') number.push(val - tmpNum); 
                        }                       
                    }
                }
                tmpNum = 0;
                if(c == ')'){
                    operations.pop();//remove '('
                    if(operations.size() != 0){
                        int val = number.pop();
                        char ops = operations.pop();
                        if(ops == '+') tmpNum = number.pop() + val;
                        else if(ops == '-') tmpNum = number.pop() - val;
                    }
                }else operations.push(c);  
                
            }else{
                // number
                tmpNum = tmpNum * 10 + Character.getNumericValue(c);
            }
        }
        if(operations.size() != 0){
            int val = number.pop();
            char ops = operations.pop();
            if(ops == '+') result = val + tmpNum;
            else if(ops == '-') result = val - tmpNum;             
        }else result = (number.size() == 0) ? tmpNum : number.pop();

        return result;
    }
}