/*
M
1208. Get Equal Substrings Within Budget
*/
/*
Sliding Window
Time: O(n)
Space: O(1)
*/
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int l = 0, r = 0;//r mark the exclusive end, l mark the inclusive begin
        int len = s.length();
        int cur = 0;
        int maxLen = 0;
        while(r < len){
            cur += Math.abs(t.charAt(r) - s.charAt(r));
            r++;
            while(cur > maxCost){
                cur -= Math.abs(t.charAt(l) - s.charAt(l));
                l++;
            }
            maxLen = Math.max(maxLen, r - l);
        }
        return maxLen;
    }
}