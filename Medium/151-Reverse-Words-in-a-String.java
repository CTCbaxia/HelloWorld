/*
MEDIUM
151. Reverse Words in a String
https://leetcode.com/problems/reverse-words-in-a-string/description/

TIME: 10.6 - 30min
RESULT: 62% - 6ms

*/
public class Solution {
    public String reverseWords(String s) {
        Stack<String> stack = new Stack<String>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch != ' '){
                sb.append(ch);
            }else if(ch == ' ' && sb.length() != 0){
                stack.push(sb.toString());
                stack.push(" ");
                sb = new StringBuilder();
            }
        }
        if(sb.length() != 0) stack.push(sb.toString());
        if(!stack.isEmpty() && stack.peek() == " ") stack.pop();
        
        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty()) res.append(stack.pop());
        return res.toString();
    }
}
/*
SOLUTION REFERENCE:

String.trim(): copy a string with leading and trailing spaces omitted.
String.split(" +"): split with space (no matter how long the space is using " +" - regular expression)

*/

public class Solution {
    public String reverseWords(String s) {
        String[] parts = s.trim().split(" +");
        StringBuilder sb = new StringBuilder();
        for(int i = parts.length - 1; i >= 0; i--){
            sb.append(parts[i]);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}