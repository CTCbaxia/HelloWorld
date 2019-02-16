/*
HARD
233. Number of Digit One

*/
/*
iterative

Time: O(n * log10(n))
Space: O(1)
*/
class Solution {
    public int countDigitOne(int n) {
        int count = 0;
        for(int i = 1; i <= n; i++){
            String s = String.valueOf(i);
            for(int j = 0; j < s.length(); j++){
                if(s.charAt(j) == '1') count++;
            }
        }
        return count;
    }
}