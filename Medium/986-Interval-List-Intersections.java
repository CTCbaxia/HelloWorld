/*
MEDIUM
986. Interval List Intersections
注意！！！题目要求的是 interval

*/
/*
Two Pointers: 有序 + interval

Time: O(m + n)     m = A.length, n = B.length
Space:O(1)
*/
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if(A == null || A.length == 0 || B == null || B.length == 0) return new int[0][0];//return []

        List<int[]> res = new ArrayList<>();
        int i = 0, j = 0;
        while(i < A.length && j < B.length){
            int start = Math.max(A[i][0], B[j][0]);
            int end = Math.min(A[i][1], B[j][1]);
            if(start <= end){ // if there is an interval for the current pairs
                res.add(new int[]{start, end});
            } 
            // pass the one that has already reach its end
            if(end == A[i][1]) i++;
            if(end == B[j][1]) j++;
        }
        
        int[][] result = new int[res.size()][2];
        for(int k = 0; k < result.length; k++){
            result[k] = res.get(k);
        }
        return result;
    }

}