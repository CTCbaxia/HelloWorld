/*
Recursion
Two Scan, when meets somewhere there is an extra parenthesis, remove it
LTR and RTL

Time: O(n)
Space: O(1) - stack: O(n)
*/
class Solution1{
    public static String removeInvalidParentheses(String s){
        //left to right
        String ltr = remove(s, 0, new char[]{'(', ')'});
        //right to left
        String reverse = new StringBuilder(ltr).reverse().toString();
        String rtl = remove(reverse, 0, new char[]{')','('});
        return new StringBuilder(rtl).reverse().toString();
    }
    private static String remove(String s, int start, char[] c){
        int count = 0;
        for(int i = start; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == c[0]) count++;
            else if(ch == c[1]) count--;

            if(count < 0){//have one more c[1]
                return remove(s.substring(0, i) + s.substring(i + 1), i, c);
            }
        }
        return s;
    }    
}
/*
Interation
Two Scan, when meets somewhere there is an extra parenthesis, remove it
LTR and RTL

Time: O(n)
Space: O(n)
*/
class Solution2{
    public static String removeInvalidParentheses(String s){
        //left to right
        String ltr = remove(s, new char[]{'(', ')'});
        //right to left
        String reverse = new StringBuilder(ltr).reverse().toString();
        String rtl = remove(reverse, new char[]{')','('});
        return new StringBuilder(rtl).reverse().toString();
    }
    private static String remove(String s, char[] c){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == c[0]) count++;
            else if(ch == c[1]) count--;

            if(count >= 0) sb.append(ch);
            else count = 0;//reset
        }
        return sb.toString();
    }
}


public class Facebook_Remove_Invalid_Parenthesis {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Solution1 s1 = new Solution1();
        System.out.println(s1.removeInvalidParentheses("()())()"));
        System.out.println(s1.removeInvalidParentheses("(a)())()"));
        System.out.println(s1.removeInvalidParentheses(")()))))()"));
        
        Solution2 s2 = new Solution2();
        System.out.println(s2.removeInvalidParentheses("()())()"));
        System.out.println(s2.removeInvalidParentheses("(a)())()"));
        System.out.println(s2.removeInvalidParentheses(")()))))()"));
    }
}

