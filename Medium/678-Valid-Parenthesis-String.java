/*
MEDIUM
678. Valid Parenthesis String

TIME: 1031
RESULT: 
NOTES:

*/
/*
bound evaluation: how many possibility we got for every * 

lo, hi are possible bounds for the number of (
they must be >= 0 
if lo < 0, that path is not possible
if hi < 0, all paths are not possible

也就是用 lo 和 hi 来衡量可能的剩余 ( 的数量

Time: O(n)
Space: O(1)

*/
class Solution {
    public boolean checkValidString(String s) {
        int lo = 0; 
        int hi = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '('){
                lo++;
                hi++;
            }else if(c == ')'){
                if(lo > 0) lo--;
                hi--;
            }else{ //*
                if(lo > 0) lo--;
                hi++;
            }
            if(hi < 0) return false;
        }
        return lo == 0;
    }
}


/*
DFS: we assign 3 possible value for every * we meet (,), 'empty' 
and do dfs

Time: o(3^n) n is the length of the string, could be very high if all input is *
Space: O(1)
*/
class Solution {
    public boolean checkValidString(String s) {
        return check(s, 0, 0);
    }
    private boolean check(String s, int index, int left){
        if(left < 0) return false;
        
        for(int i = index; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(') left++;
            else if(c == ')'){
                if(left > 0) left--;
                else return false;
            }else if(c == '*'){
                return check(s, i + 1, left + 1) || check(s, i + 1, left) || check(s, i + 1, left - 1);
            }
        }
        
        return left == 0;
    }
}