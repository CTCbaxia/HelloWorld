/*
MEDIUM
1004. Max Consecutive Ones III
*/
/*
Sliding Window + Two Pointers

Time: O(n)
Space: O(1)
*/
class Solution {
    public int longestOnes(int[] A, int K) {
        int k = K, res = 0;
        int l = 0, r = 0;
        while(r < A.length){
            if(A[r] == 0){
                k--;//if we got a 0, use remaining 1 to make up
                while(k < 0){//use up all
                    if(l < A.length && A[l] == 0) k++;
                    l++;
                }
            }
            
            res = Math.max(res, r - l + 1);
            r++;
        }
        return res;
    }
}