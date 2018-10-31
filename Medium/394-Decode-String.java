/*
MEDIUM
394. Decode String

TIME: 
RESULT: 
NOTES:

*/
/*
Recursive + global i

Time: O(n)
Space: O(1)
*/
public class Solution {

    int i = 0;//全局变量，很重要

    public String decodeString(String s) {
        return decodeHelper(s);
    }
    private String decodeHelper(String s){//括号内的东西,最终的迭代小块就是一个单层[]
        StringBuilder sb = new StringBuilder();
        
        while(i < s.length() && s.charAt(i) != ']'){
            if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z' || s.charAt(i) >= 'A' && s.charAt(i) <= 'Z' ){
                sb.append(s.charAt(i++));
            }else{//digit
                int num = 0;
                while(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    num = 10 * num + s.charAt(i++) - '0';
                }
                i++; //'['
                String str = decodeHelper(s);
                i++; //']'
                while(num-- > 0) sb.append(str);
            }
        }
        return sb.toString();
    }
}
/*
stack

Time: O(n)
Space: O(1)
*/
class Solution {
    public String decodeString(String s) {
        Stack<String> stack = new Stack<String>();
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ']'){
                while(!stack.isEmpty() && !stack.peek().equals("[")){//we need [ to tell us it is the end of the abssdds
                    sb.insert(0,stack.pop());
                }
                stack.pop();//[
                String str = sb.toString();
                int num = Integer.parseInt(stack.pop());
                
                //build string
                StringBuilder tmp = new StringBuilder();
                while(num-- > 0) tmp.append(str);
                stack.push(tmp.toString());
            }else if(c >= '0' && c <= '9'){
                while(c >= '0' && c <= '9'){
                    sb.append(c);
                    i++;
                    if(i < s.length()) c = s.charAt(i);
                    else break;
                }
                i--;
                stack.push(sb.toString());
            }else{//letter or ’[‘
                stack.push(String.valueOf(c));
            }
            sb = new StringBuilder(); 
        }
        while(!stack.isEmpty()) sb.insert(0, stack.pop());
        
        return sb.toString();
    }
}



//其实字母的情况被最后一个 else 覆盖了
class Solution {
    public String decodeString(String s) {
        Stack<String> stack = new Stack<String>();
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ']'){
                while(!stack.isEmpty() && !stack.peek().equals("[")){//we need [ to tell us it is the end of the abssdds
                    sb.insert(0,stack.pop());
                }
                stack.pop();//[
                String str = sb.toString();
                int num = Integer.parseInt(stack.pop());
                
                //build string
                StringBuilder tmp = new StringBuilder();
                while(num-- > 0) tmp.append(str);
                stack.push(tmp.toString());
            }else if(c >= 'a' && c <= 'z'){
                while(c >= 'a' && c <= 'z'){
                    sb.append(c);
                    i++;
                    if(i < s.length()) c = s.charAt(i);
                    else break;
                }
                i--;
                stack.push(sb.toString());
            }else if(c >= '0' && c <= '9'){
                while(c >= '0' && c <= '9'){
                    sb.append(c);
                    i++;
                    if(i < s.length()) c = s.charAt(i);
                    else break;
                }
                i--;
                stack.push(sb.toString());
            }else{
                stack.push(String.valueOf(c));
            }
            sb = new StringBuilder(); 
        }
        while(!stack.isEmpty()) sb.insert(0, stack.pop());
        
        return sb.toString();
    }
}
