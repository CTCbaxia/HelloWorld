/*
HARD
44. Wildcard Matching

TIME: 
RESULT: 
*/
/*
Two Pointers
when p meet a *, remember the * index using starIdx, and the current match index using matchIndex
continue to check if p and s index match
if not, reset p to previous starIdx, and s to previous matchIndex+1

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean isMatch(String s, String p) {
        int si = 0, pi = 0, checkIndex = -1, starIndex = -1;
        while(si < s.length()){
            if(pi < p.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?')){
                si++;
                pi++;
            }else if(pi < p.length() && p.charAt(pi) == '*'){//remember the index
                starIndex = pi;
                checkIndex = si;
                pi++;
            }else if(starIndex != -1){//if current checkIndex can not match pi
                pi = starIndex + 1;//reset pi
                si = ++checkIndex;//previous checkIndex is matched to *
            }else{
                return false;
            }
        }
        while(pi < p.length() && p.charAt(pi) == '*'){
            pi++;
        }
        return pi == p.length();
    }
}
/*
recursively

TLE
*/
class Solution {
    public boolean isMatch(String s, String p) {
        if(p.length() == 0) return s.length() == 0;
        if(s.length() == 0){
            int i = 0;
            while(i < p.length() && p.charAt(i) == '*'){
                i++;
            }
            if(i < p.length()) return false;
            else return true;
        }
        if(p.charAt(0) == '*'){
            return isMatch(s.substring(1), p) || isMatch(s, p.substring(1));
        }else if(p.charAt(0) == '?'){
            return isMatch(s.substring(1), p.substring(1));
        }else{
            if(s.charAt(0) == p.charAt(0)) return isMatch(s.substring(1), p.substring(1));
            else return false;
        }
    }
}
