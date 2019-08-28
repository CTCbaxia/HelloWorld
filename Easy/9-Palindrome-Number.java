/*
E
9. Palindrome Number
*/
/*
Revert the int

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        int revert = 0, num = x;
        while(num > 0){
            if(revert > Integer.MAX_VALUE / 10 || (revert == Integer.MAX_VALUE && num % 10 >= 8))
                return false;
            revert = revert * 10 + num % 10;
            num /= 10;
        }
        System.out.println(Integer.MAX_VALUE);
        return x == revert;
    }
}
