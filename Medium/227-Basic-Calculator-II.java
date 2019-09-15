/*
MEDIUM
227. Basic Calculator II

TIME: 
RESULT: 17% - 46ms
NOTES:
计算式子要用 stack
保留 + - 之间的内容，及时合并 "括号内" 或 " * / " 这类优先级较高的部分 (利用 push，pop)
*/
/*
Mark lastNum (withour using Stack):
lastNum: 记录上一个数字
sign: 记录上一个符号
res: 记录当前结果

Time: O(n)
Space: O(1)
*/
class Solution {
    public int calculate(String s) {
        int res = 0;//记录当前结果
        int lastNum = 0;//记录上一个数字 (带符号)
        char sign = '+';//记录上一个符号，因为有 */ 所以用 char
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                int curNum = 0;
                while(i < s.length() && Character.isDigit(s.charAt(i))){
                    curNum = curNum * 10 + (s.charAt(i++) - '0');
                }
                if(sign == '+'){
                    res += curNum;
                    lastNum = curNum;
                } 
                else if(sign == '-'){
                    res -= curNum;
                    lastNum = -curNum;
                } 
                else if(sign == '*'){
                    res = res - lastNum + lastNum * curNum;
                    lastNum = lastNum * curNum;
                } 
                else if(sign == '/'){
                    res = res - lastNum + lastNum / curNum;
                    lastNum = lastNum / curNum;
                } 
                
                i--;//one step back
            }else if(c != ' '){
                sign = c;
            }
        }

        return res;
    }
}


/*
Stack：因为乘除有更高的优先级，所以永远直接计算乘除。在stack里面只留加减级别的正负数，不做加减运算
sign: 记录上一个符号

Time: O(n)
Space: O(n)
*/
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char sign = '+';//记录上一个符号，因为有 */ 所以用 char
        // int curNum = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                int curNum = 0;
                while(i < s.length() && Character.isDigit(s.charAt(i))){
                    curNum = curNum * 10 + (s.charAt(i++) - '0');
                }
                if(sign == '+') stack.push(curNum);
                else if(sign == '-') stack.push(-curNum);
                else if(sign == '*') stack.push(stack.pop() * curNum);
                else if(sign == '/') stack.push(stack.pop() / curNum);
                
                i--;//one step back
            }else if(c != ' '){
                sign = c;
            }
        }
        //now stack contains a series of numbers that can be simply added
        int res = 0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }
        return res;
    }
}



/*
Stack: 因为上一个结果有用
sign ： 保存上一个符号
num ： 收集整个数字

Time: O(n)
Space: O(n)
*/
class Solution {
    public int calculate(String s) {
        char sign = '+';//记录上一个符号
        int num = 0;
        Stack<Integer> stack = new Stack<>();//里面只存最后需要相加的数
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){//先看是不是 num，要累计 num
                num = num * 10 + (c - '0');
            }
            /*
            当得到符号，说明数字收集完毕
            只能写 if，因为如果 i 即是数字，又是最后一个char，就需要再次计算
            */
            if(c == '+' || c == '-' || c == '*' || c == '/' || i == s.length() - 1){
                if(sign == '+') stack.push(num);
                else if(sign == '-') stack.push(-num);
                else if(sign == '*') stack.push(stack.pop() * num);
                else if(sign == '/') stack.push(stack.pop() / num);
                num = 0;
                sign = c;
            } 
        }
        int res = 0;
        while(!stack.isEmpty()) res += stack.pop();
        return res;
    }
}





class Solution {
    public int calculate(String s) {
        if(s.length() == 0) return 0;
        
        int result = 0;
        int num = 0;
        char sign = '+';
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < s.length(); i++){
            if(Character.isDigit(s.charAt(i))){
                num = num * 10 + (s.charAt(i) - '0');
            }
            if(!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() - 1){
            	//只留 + - 之间的结果，滞后一轮用 sign
                if(sign == '+') stack.push(num);
                else if(sign == '-') stack.push(-num);
                else if(sign == '*') stack.push(stack.pop()*num);
                else if(sign == '/') stack.push(stack.pop()/num);
                sign = s.charAt(i);
                num = 0;  
            }
        }
        while(!stack.isEmpty()) result += stack.pop();
        return result;
    }
}



class Solution {
    public int calculate(String s) {
        int result = 0;
        if(s.length() == 0) return result;
        
        if(s.contains("+")){
            String[] str = s.split("\\+");
            result = calculate(str[0]);
            for(int i = 1; i < str.length; i++) result += calculate(str[i]);
        }else if(s.contains("-")){
            String[] str = s.split("-");
            result = calculate(str[0]);
            for(int i = 1; i < str.length; i++) result -= calculate(str[i]);            
        }else if(s.contains("/") || s.contains("*")){
            int index = 0;
            char sign = '+';
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '*' || s.charAt(i) == '/'){
                    int num = Integer.parseInt(s.substring(index, i).trim());
                    if(sign == '*') result *= num;
                    else if(sign == '/') result /= num;
                    else result = num;
                    sign = s.charAt(i);
                    index = i + 1;
                }
                
            }
            int num = Integer.parseInt(s.substring(index, s.length()).trim());
            if(sign == '*') result *= num;
            else if(sign == '/') result /= num;
            
        }else{
            result = Integer.parseInt(s.trim());
        }
        return result;
    }
}




class Solution {
    public int calculate(String s) {
        int result = 0;
        if(s.length() == 0) return result;
        
        if(s.contains("+")){
            String[] str = s.split("\\+");
            result = calculate(str[0]);
            for(int i = 1; i < str.length; i++) result += calculate(str[i]);
        }else if(s.contains("-")){
            String[] str = s.split("-");
            result = calculate(str[0]);
            for(int i = 1; i < str.length; i++) result -= calculate(str[i]);            
        }else if(s.contains("/") || s.contains("*")){
            Queue<String> num = new LinkedList<String>();
            Queue<Character> op = new LinkedList<Character>();
            int index = 0;
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '*' || s.charAt(i) == '/'){
                    op.offer(s.charAt(i));
                    num.offer(s.substring(index, i));
                    index = i+1;
                }
            }
            num.offer(s.substring(index, s.length()));
            result = Integer.parseInt(num.poll().trim());
            while(!op.isEmpty()){
                char ops = op.poll();
                String nums = num.poll().trim();
                if(ops == '*') result *= Integer.parseInt(nums);
                else if(ops == '/') result /= Integer.parseInt(nums);
            }
        }else{
            result = Integer.parseInt(s.trim());
        }
        return result;
    }
}