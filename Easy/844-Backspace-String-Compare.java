/*
EASY
844. Backspace String Compare

RESULT: 
NOTES: 
*/
/*
Back checking: check from the end, count for # and jump while count > 0
也可以用stack，先得出两个 string 的实际内容，然后比较

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean backspaceCompare(String S, String T){
          int i = S.length() - 1, j = T.length() - 1;
          int countS = 0, countT = 0;
          while(i >= 0 || j >= 0){//if one finished, one has not, the remainer should have # at right
                while(i >= 0 && (countS > 0 || S.charAt(i) == '#')){//skip all non-use character
                  if(S.charAt(i) == '#') countS++;
                  else countS--;

                  i--;
                }
                while(j >= 0 && (countT > 0 || T.charAt(j) == '#')){
                  if(T.charAt(j) == '#') countT++;
                  else countT--;

                  j--;
                }
                //end up in a letter or pointer = -1
                if(i >= 0 && j >= 0){
                    if(S.charAt(i) != T.charAt(j)) return false;
                    i--;//if we come here, it means matched, and we continue
                    j--;      
                }else if(i < 0 && j < 0){
                    return true;
                } else return false;
          }

          return i < 0 && j < 0;
    }
}
/*
Stack

Time: O(n)
Space: O(n)
*/
class Solution {
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> s = new Stack<Character>();
        Stack<Character> t = new Stack<Character>();
        for(char c : S.toCharArray()){
            if(c == '#'){
                if(!s.isEmpty())
                    s.pop();
            }else{
                s.push(c);
            }
        }
        for(char c : T.toCharArray()){
            if(c == '#'){
                if(!t.isEmpty())
                    t.pop();
            }else{
                t.push(c);
            }
        }
        while(!s.isEmpty() && !t.isEmpty()){
            if(s.pop() != t.pop()) return false;
        }
        return s.isEmpty() && t.isEmpty();
    }
}








/*
Back checking: check from the end, count for # and jump while count > 0

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int cs = 0, ct = 0;
        int ps = S.length() - 1, pt = T.length() - 1;
        while(ps >= 0 || pt >= 0){
            //while it is #, or it is letter but # > 0
            while(ps >= 0 && (S.charAt(ps) == '#'|| cs > 0)){//skip all non-use character
                if(S.charAt(ps) != '#') cs--;
                else cs++;
                ps--;
            }
            while(pt >= 0 && (T.charAt(pt) == '#'|| ct > 0)){
                if(T.charAt(pt) != '#') ct--;
                else ct++;
                pt--;
            }
            //end up in a letter or pointer = -1
            if(ps < 0 && pt < 0) return true;
            else if(ps >= 0 && pt >= 0){
                if(S.charAt(ps) != T.charAt(pt)) return false;
                //if we come here, it means matched, and we continue
                ps--;
                pt--;
            }else{
                return false;
            }
        }
        return ps < 0 && pt < 0;
    }
}

