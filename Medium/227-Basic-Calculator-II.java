/*
MEDIUM
227. Basic Calculator II

TIME: 
RESULT: 17% - 46ms
NOTES:
计算式子要用 stack
保留 + - 之间的内容，及时合并 "括号内" 或 " * / " 这类优先级较高的部分 (利用 push，pop)
*/
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