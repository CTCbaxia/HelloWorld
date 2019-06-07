/*
977. Squares of a Sorted Array

*/
/*
Two Pointers: lo + hi
Time: O(n)
Space: O(1)
*/
class Solution {
    public int[] sortedSquares(int[] A) {
        if(A == null || A.length == 0) return A;
        int[] res = new int[A.length];
        
        int lo = 0, hi = A.length - 1;
        int index = A.length - 1;
        while(lo <= hi){
            int n1 = A[lo] * A[lo];
            int n2 = A[hi] * A[hi];
            
            if(lo == hi){//don't forget to change value of lo/hi
                res[index] = n1;
                break;
            }else{
                if(n1 <= n2){
                    res[index--] = n2;
                    hi--;
                }else{
                    res[index--] = n1;
                    lo++;
                }
            }
        }
        return res;
    }
}





/*
Two Pointers: lo + hi
Time: O(n)
Space: O(1)
*/
class Solution {
    public int[] sortedSquares(int[] A) {
        if(A == null || A.length == 0) return A;
        int[] res = new int[A.length];
        
        int lo = 0, hi = A.length - 1;
        int index = A.length - 1;
        while(lo <= hi){
            int n1 = A[lo] * A[lo];
            int n2 = A[hi] * A[hi];
            
            if(lo == hi) res[index] = n1;
            else{
                if(n1 <= n2){
                    res[index--] = n2;
                    res[index--] = n1;
                }else{
                    res[index--] = n1;
                    res[index--] = n2;
                }
            }
            lo++;
            hi--;
        }
        return res;
    }
}