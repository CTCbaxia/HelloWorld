/*
MEDIUM
161. One Edit Distance

TIME: 
RESULT: 99% - 1ms
NOTES:
这种对比题肯定是横扫
*/
/*
Two Pointers + check the result part

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if(Math.abs(s.length() - t.length()) > 1) return false;
        
        int i = 0, j = 0;
        while(i < s.length() && j < t.length()){
            if(s.charAt(i) != t.charAt(j)){
                return s.substring(i + 1).equals(t.substring(i + 1))
                    || s.substring(i).equals(t.substring(i + 1))
                    || s.substring(i + 1).equals(t.substring(i));
            }
            i++;//need to check loop condition
            j++;
        }
        //all same so far, also need to check here
        return s.length() != t.length();//abs should be 1
    }
}
/*
Two Pointers + check the result part

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int i = 0, j = 0;
        while(i < s.length() && j < t.length()){
            if(s.charAt(i) != t.charAt(j)){
                return s.substring(i + 1).equals(t.substring(i + 1))
                    || s.substring(i).equals(t.substring(i + 1))
                    || s.substring(i + 1).equals(t.substring(i));
            }
            i++;//need to check loop condition
            j++;
        }
        //all same so far, also need to check here
        if(Math.abs(s.length() - t.length()) == 1) return true;
        else return false;
    }
}



/*
先pointer比较，后面完全相等的情况直接string比较

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
helper function to find the diff index, or -1 if totally the same

*/
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int index = isSame(s, t);
        if(index == -1) return false;//exactly the same
        
        int res = 0;
        if(s.length() > t.length()) res = isSame(s.substring(index + 1), t.substring(index));
        else if(s.length() < t.length()) res = isSame(s.substring(index), t.substring(index + 1));
        else if(s.length() == t.length()) res = isSame(s.substring(index + 1), t.substring(index + 1));
        return res == -1;
    }
    private int isSame(String s, String t){//if same, return -1, else return index of diff
        int i = 0;
        while(i < s.length() && i < t.length()){
            if(s.charAt(i) != t.charAt(i)) return i;
            i++;
        }
        return s.length() == t.length() ? -1 : i;
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