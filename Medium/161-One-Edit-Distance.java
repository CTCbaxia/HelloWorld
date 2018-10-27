/*
MEDIUM
161. One Edit Distance

TIME: 
RESULT: 99% - 1ms
NOTES:
这种对比题肯定是横扫
*/
/*
Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int i = 0;
        int lens = s.length();
        int lent = t.length();
        if(Math.abs(lens - lent) > 1) return false;
        
        int count = 0;
        while(i < Math.min(lens, lent)){
            if(s.charAt(i) != t.charAt(i)){
                if(lens < lent) return s.substring(i).equals(t.substring(i + 1));
                else if(lens > lent) return s.substring(i + 1).equals(t.substring(i));
                else if(lens == lent) return s.substring(i + 1).equals(t.substring(i + 1));
            }
            i++;
        }
        return (lens != lent);//if no difference and same len, false; if no difference, diff len, true
    }
}
/*
Time: O(s + t)
Space: O(1)
*/
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int i = 0, j = 0;
        int lens = s.length();
        int lent = t.length();
        if(Math.abs(lens - lent) > 1) return false;
        
        int count = 0;
        while(i < lens && j < lent){
            if(s.charAt(i++) != t.charAt(j++)){
                count++;
                if(count > 1) return false;
                
                if(lens < lent) i--;
                else if(lens > lent) j--;
            }
        }
        if(count == 0 && lens == lent) return false;// if they are totally the same
        return true;
    }
}