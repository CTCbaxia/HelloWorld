/*
MEDIUM
647. Palindromic Substrings

TIME: 
RESULT: 
NOTES:

*/
/*
Two Pointers: Center extend

Odd and even extend check
for every element i, 
count palindrom substrings with i as center
count palindrom substrings with i, i + 1 as center

Time: O(n^2) for every element i, we may go through the whole string 
Space: O(1)
*/
class Solution {
    public int countSubstrings(String s) {
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            res += count(s, i, i);//odd palindrom with i as center
            res += count(s, i, i + 1);//even palindrom with i, i+1 as center
        }
        return res;
    }
    private int count(String s, int left, int right){//find palindrom with left and right as center
        int count = 0;
        while(left >= 0 && right < s.length()){
            if(s.charAt(left--) == s.charAt(right++)) count++;
            else return count;
        }
        return count;
    }
}