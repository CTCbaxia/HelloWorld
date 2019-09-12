/*
MEDIUM
151. Reverse Words in a String
https://leetcode.com/problems/reverse-words-in-a-string/description/

TIME: 10.6 - 30min
RESULT: 62% - 6ms

*/
/*
Two Pointers
Time: O(n)
Space: O(n)
*/
class Solution {
    public String reverseWords(String s) {
        List<String> strs = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != ' '){
                int j = i;
                while(j < s.length() && s.charAt(j) != ' '){
                    j++;
                }
                strs.add(s.substring(i, j));
                i = j;//will increase in for loop
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = strs.size() - 1; i >= 0; i--){
            sb.append(strs.get(i)).append(" ");
        }
        if(sb.length() > 0) sb.deleteCharAt(sb.length() - 1);//check for ""
        return sb.toString();
    }
}




/*
Move empty to the end + Reverse Whole String + Reverse every letter

Time: O(n)
Space: O(1)   -ch is result
*/
public class Solution {
    public String reverseWords(String s){
        char[] ch = s.toCharArray();
        int end = moveEmpty(ch);
        //reverse whole string
        reverse(ch, 0, end);
        
        //reverse each letter
        int pre = 0;
        for(int i = 0; i <= end; i++){
            if(ch[i] == ' '){
                reverse(ch, pre, i - 1);
                pre = i + 1;
            }else if(i == end){
                reverse(ch, pre, i);
            }
        }
        return new String(ch).substring(0,end + 1);
    }
    private int moveEmpty(char[] ch){//move the empty char to the end, and save one space between letters
        int i = 0;//for valid
        int j = 0;//loop the array
        while(j < ch.length && ch[j] == ' ') j++;//skip pre spaces
        
        while(j < ch.length){
            while(j < ch.length && ch[j] != ' ') ch[i++] = ch[j++];
            while(j < ch.length && ch[j] == ' ') j++;//skip spaces
            if(j < ch.length) ch[i++] = ' ';//for a space
        }
        return i - 1;
    }
    private void reverse(char[] ch, int lo, int hi){
        while(lo < hi){
            char tmp = ch[lo];
            ch[lo] = ch[hi];
            ch[hi] = tmp;
            lo++;
            hi--;
        }
        return;
    }
}


/*
Stack + StringBuilder

Time: O(n)
Space: O(n)
*/
public class Solution {
    public String reverseWords(String s){
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while(index < s.length()){
            //skip empty
            while(index < s.length() && s.charAt(index) == ' '){
                index++;
            }
            //get String
            int start = index;
            if(start == s.length()) break;
            while(index < s.length() && s.charAt(index) != ' '){
                index++;
            }
            stack.push(s.substring(start, index));
        }
        while(!stack.isEmpty()) sb.append(stack.pop()).append(" ");
        if(sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
/*
利用split


Time: O(n)
Space: O(1)
*/
public class Solution {
    public String reverseWords(String s){
        String[] str = s.trim().split(" +");//regrx
        StringBuilder sb = new StringBuilder();
        for(int i = str.length - 1; i >= 0; i--){
            sb.append(str[i]).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}










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
对于单纯的 s.split(" ")，如果有两个连续的"a  b",会分出一个"a","","b"
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