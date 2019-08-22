/*
E
821. Shortest Distance to a Character
*/
/*
See left and right

Time: O(n)
Space: O(n)
*/
class Solution {
    public int[] shortestToChar(String S, char C) {
        int n = S.length();
        
        int indexOfC = -1;
        int[] left = new int[n];
        int[] right = new int[n];
        for(int i = 0; i < n; i++){
            char c = S.charAt(i);
            if(c == C) indexOfC = i;
            left[i] = indexOfC == -1 ? n : i - indexOfC;
        }
        indexOfC = n;
        for(int i = n - 1; i >= 0; i--){
            char c = S.charAt(i);
            if(c == C) indexOfC = i;
            right[i] = indexOfC == n ? n : indexOfC - i;
        }
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            res[i] = Math.min(left[i], right[i]);
        }
        return res;
    }
}
