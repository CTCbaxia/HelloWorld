/*
E
1047. Remove All Adjacent Duplicates In String
*/
/*
Recursion + index

Time: O(n)
Space: O(1)
*/
class Solution {
    public String removeDuplicates(String S) {
        return removeHelper(S, 0);
    }
    private String removeHelper(String s, int start){//checking from the i
        char pre = 'A';//S consists only of English lowercase letters, so here choose anything impossible
        for(int i = start; i < s.length(); i++){
            if(s.charAt(i) == pre){
                // should include the i-2 to recheck this part after removal. but if i - 2 < 0, start from 0
                return removeHelper(s.substring(0, i - 1) + s.substring(i + 1), (i - 2 < 0 ? 0 : i - 2));
            } 
            pre = s.charAt(i);
        }
        return s;
    }
}



/*
Recursion

Time: O(n^2)
Space: O(1)
*/
class Solution {
    public String removeDuplicates(String S) {
        char pre = 'A';//S consists only of English lowercase letters.
        for(int i = 0; i < S.length(); i++){
            if(S.charAt(i) == pre) return removeDuplicates(S.substring(0, i - 1) + S.substring(i + 1));
            pre = S.charAt(i);
        }
        return S;
    }
}
