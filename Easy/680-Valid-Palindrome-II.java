/*
EASY
680. Valid Palindrome II

TIME: 1020 - 10min
RESULT: 42% - 28ms
NOTES: 

*/
/*
先比较，如果有不同，就进入错位后严格比较，有一个成功就可以
注意因为 lo++, hi--已经进入到不匹配的下一位，我们应该分别回归一位比较

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean validPalindrome(String s) {
        int lo = 0;
        int hi = s.length() - 1;
        while(lo < hi){
            if(s.charAt(lo++) != s.charAt(hi--)) return strictPal(s, lo - 1, hi) || strictPal(s, lo, hi +1);
        }
        return true;
    }
    private boolean strictPal(String s, int lo, int hi){
        while(lo < hi){
            if(s.charAt(lo++) != s.charAt(hi--)) return false;
        }
        return true;
    }
}


class Solution {
    int lo = 0;
    int hi = 0;
    public boolean validPalindrome(String s) {
        hi = s.length() - 1;
        if(strictPal(s)) return true;
        lo++;
        
        if(strictPal(s)) return true;
        lo--;
        hi--;
        
        if(strictPal(s)) return true;
        return false;
    }
    private boolean strictPal(String s){
        while(lo < hi){
            
            if(s.charAt(lo) != s.charAt(hi)) return false;
            lo++;
            hi--;
        }
        return true;
    }
}