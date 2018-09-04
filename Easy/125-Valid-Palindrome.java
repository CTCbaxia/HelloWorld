/*
EASY
125. Valid Palindrome
https://leetcode.com/problems/valid-palindrome/description/

TIME: 0903 - 30min
RESULT: 79% - 5ms

*/
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while(left < right){//when left == right, the value doesn't matter
            char l = Character.toLowerCase(s.charAt(left));
            char r = Character.toLowerCase(s.charAt(right));
            
            while(left < right && !(l >= 'a' && l <= 'z' || l >= '0' && l <= '9')){
                l = Character.toLowerCase(s.charAt(++left));;
            }
            while(right > left && !(r >= 'a' && r <= 'z' || r >= '0' && r <= '9')){
                r = Character.toLowerCase(s.charAt(--right));
            }
            if(l != r) return false;
            left++;
            right--;
        }
        return true;
    }

}
//正则表达式
public class Solution {
    public boolean isPalindrome(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
}