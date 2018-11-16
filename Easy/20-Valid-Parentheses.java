/*
20. Valid Parentheses

TIME: 1008 - 1ms
RESULT: 7ms

*/
/*
Stack:
Different type (need position)
once right, pop stack and check type, false if not match
at the end, stack should be empty
*/
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else{//should be )]}
                if(stack.isEmpty()) return false;
                char left = stack.pop();
                if(left == '(' && c != ')' || left == '[' && c != ']' || left == '{' && c != '}'){
                    return false;
                } 
            }
        }
        return stack.isEmpty();
    }
}















class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(char c : s.toCharArray()){
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else if(c == ')' || c == ']' || c == '}'){
                if(stack.isEmpty()) return false; //记得凡是要 pop 的，都要先判断是否为空
                char left = stack.pop();
                if(left == '(' && c != ')' || left == '[' && c != ']' || left == '{' && c != '}') return false; 
            }
        }
        if(!stack.isEmpty()) return false;//记得不光是走完，还要所有都匹配
        else return true;
    }
}
/*
 Solution 1: 用新字符串存所有左边的括号，一旦遇到右边的括号，立马与还未匹配的最后一个tmp里面的左边括号匹配
 
 0.这种题的边界情况一定要考虑好
 1.一定要考虑string=""的情况，这个时候s.charAt()失效
 2.deleteCharAt() 方法不识别
 3.left_tmp.substring()会生成一个新的string，并不会改变现有的left_tmp，所以需要把这个值重新赋给left_tmp
 */

class Solution {
    public boolean isValid(String s) {
        if(s.length()==0){
            return true;
        }else if(s.length()%2==1 ||s.charAt(0) ==')' ||s.charAt(0) ==']' ||s.charAt(0) =='}'){
            return false;
        }
        int left=0;
        String left_tmp="";
        for (int i=0; i<s.length();i++){
            if(s.charAt(i) =='(' ||s.charAt(i) =='[' ||s.charAt(i) =='{'){
                left++;
                left_tmp=left_tmp+s.charAt(i);
            }
            if(s.charAt(i)==')'){
                if(left>0 && left_tmp.charAt(left-1)=='('){
                    left_tmp=left_tmp.substring(0,left-1);
                    left--;
                }else{
                    return false;
                }
            }
            if(s.charAt(i)==']'){
                if(left>0 && left_tmp.charAt(left-1)=='['){
                    left_tmp=left_tmp.substring(0,left-1);
                    left--;
                }else{
                    return false;
                }
            }
            if(s.charAt(i)=='}'){
                if(left>0 && left_tmp.charAt(left-1)=='{'){
                    left_tmp=left_tmp.substring(0,left-1);
                    left--;
                }else{
                    return false;
                }
            }
        }
        if(left==0){
            return true;
        }else{
            return false;
        }
        
    }
}

"[(())(())]"




"[{(())}[()]]"
"[{(())}()]"
"[{(())}(())]"



/*
 不好解决啊...这题必须得用到stack啊？？？
 */


class Solution {
    public boolean isValid(String s) {
        int match=0;
        if(s.length()==0){
            return true;
        }else if(s.length()%2==1 ||s.charAt(0) ==')' ||s.charAt(0) ==']' ||s.charAt(0) =='}'){
            return false;
        }
        int left=1;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==')'){
                if(s.charAt(i-1)=='(' || s.charAt(i-match*2-1)=='('){
                    match ++;
                    left--;
                }else{
                    return false;
                }
            }
            if(s.charAt(i)==']'){
                if(s.charAt(i-1)=='[' || s.charAt(i-match*2-1)=='['){
                    match ++;
                    left--;
                }else{
                    return false;
                }
            }
            if(s.charAt(i)=='}'){
                if(s.charAt(i-1)=='{' || s.charAt(i-match*2-1)=='{'){
                    match ++;
                    left--;
                }else{
                    return false;
                }
            }
            if(s.charAt(i) =='(' ||s.charAt(i) =='[' ||s.charAt(i) =='{'){
                left++;
            }
        }
        if(left==0){
            return true;
        }else{
            return false;
        }
        
    }
}



/*
 "[({(())}[()])]"
 "]"
 "]["
 ""
 "(((()"
 */

class Solution {
    public boolean isValid(String s) {
        int match=0;
        
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==')'){
                if(s.charAt(i-1)=='(' || s.charAt(i-match*2-1)=='('){
                    match ++;
                }else{
                    return false;
                }
            }
            if(s.charAt(i)==']'){
                if(s.charAt(i-1)=='[' || s.charAt(i-match*2-1)=='['){
                    match ++;
                }else{
                    return false;
                }
            }
            if(s.charAt(i)=='}'){
                if(s.charAt(i-1)=='{' || s.charAt(i-match*2-1)=='{'){
                    match ++;
                }else{
                    return false;
                }
            }
            
        }
        return true;
    }
}
