/*
MEDIUM
647. Palindromic Substrings

TIME: 
RESULT: 
NOTES:

*/
/*
odd and even extend check:
for every element i, 
count palindrom substrings with i as center
count palindrom substrings with i, i + 1 as center

Time: O(n^2) for every element i, we may go through the whole string 
Space: O(1)
*/
class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            count += palindrom(s, i, i); //odd palindrom with i as center
            count += palindrom(s, i, i + 1); //even palindrom with i, i+1 as center
        }
        return count;
    }
    private int palindrom(String s, int left, int right){//find palindrom with left and right as center
        int res = 0;
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            res++;
            left--;
            right++;
        }
        return res;
    }
}