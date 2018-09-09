/*
MEDIUM
223. Rectangle Area
https://leetcode.com/problems/rectangle-area/description/

TIME: 0909 - 30min
RESULT: 100% - 2ms

*/
/*
SOLUTION REFERENCE: 
TIME: 0909 - 30min
RESULT: 100% - 2ms
*/

class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int rec1 = Math.abs(A - C) * Math.abs(B - D);
        int rec2 = Math.abs(E - G) * Math.abs(F - H);
        
        int left = Math.max(A, E);
        int right = Math.min(C, G);
        int bottom = Math.max(B, F);
        int top = Math.min(D, H);
        int overlap = 0;
        if(right > left && top > bottom)
            overlap = (right - left) * (top - bottom);
        
        return rec1 + rec2 - overlap;
    }

}

/*
SOLUTION 0: 
TIME: 0909 - 30min
RESULT: 51% - 3ms
*/

class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int rec1 = Math.abs(A - C) * Math.abs(B - D);
        int rec2 = Math.abs(E - G) * Math.abs(F - H);
        int intersection = inter(A, C, E, G) * inter(B, D, F, H);
        return rec1 + rec2 - intersection;
    }
    private int inter(int n1, int n2, int n3, int n4){
        int max = 0;
        max = Math.max(Math.abs(n1 - n2), max);
        max = Math.max(Math.abs(n1 - n3), max);
        max = Math.max(Math.abs(n1 - n4), max);
        max = Math.max(Math.abs(n2 - n3), max);
        max = Math.max(Math.abs(n2 - n4), max);
        max = Math.max(Math.abs(n3 - n4), max);
        int res = Math.abs(n1 - n2) + Math.abs(n3 - n4) - max;
        return res >= 0 ? res : 0;
    }
}