/*
MEDIUM
241. Different Ways to Add Parentheses
https://leetcode.com/problems/different-ways-to-add-parentheses/description/

TIME: 
RESULT: 
METHOD:

NOTES:

*/
/*
(*(-(*)))
((*)-(*))
((*(-))*)
(*((-)*))
(((*)-)*)


((*(-)*))


((*))-(*)

(*((-))*)

*/
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        int par = (input.length() - 1)/2;
        Queue<Integer> number = new LinkedList<Integer>();
        String operation = "";
        //Queue<Character> operation = new LinkedList<Character>();
        List<String> diffOper = new ArrayList<String>();
        List<Integer> result = new ArrayList<Integer>();
        
        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            if(ch == '+' || ch == '-' || ch == '*') operation = operation + ch;
            else number.add(ch - '0');
        }
        diffOperHelper(diffOper, "(", operation, 0, par - 1, 1);
        return result;
    }
    private void diffOperHelper(List<String> diffOper, String pre, String operation, int pointer, int par, int leftpar){
        if(pointer == operation.length()){
            if( par == 0){
                while(leftpar > 0){
                    pre = pre + ")";
                    leftpar--;
                }
                if(pre.charAt(pre.length() - 1) != '-' && pre.charAt(pre.length() - 1) != '+' && pre.charAt(pre.length() - 1) != '*'){
                System.out.println(pre);
                diffOper.add(pre);         
                }         
            }
            return;
        } 
        if(par > 0 && pre.charAt(pre.length() - 1) != ')'){
            diffOperHelper(diffOper, pre + "(" , operation, pointer, par - 1, leftpar + 1);
        }
        if(pre.charAt(pre.length() - 1) != '-' && pre.charAt(pre.length() - 1) != '+' && pre.charAt(pre.length() - 1) != '*'){
            diffOperHelper(diffOper, pre + operation.charAt(pointer), operation, pointer + 1, par, leftpar);
        }
        if(leftpar > 0 && pre.charAt(pre.length() - 1) != '(' && pre.length() > 2){
            diffOperHelper(diffOper, pre + ")", operation, pointer, par, leftpar - 1);
        }
 
    }
    
}