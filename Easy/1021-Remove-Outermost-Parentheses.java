/*
E
1021. Remove Outermost Parentheses
*/
/*
Counter

Time: O(n)
Space: O(1)
*/
class Solution {
    public String removeOuterParentheses(String S) {
        int countLeft = -1;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            if(c == '(') countLeft++;
            else countLeft--;
            
            if(c == '(' && countLeft == 0 || c == ')' && countLeft == -1) continue;
            else sb.append(c);
        }
        return sb.toString();
    }
}
