/*
E
941. Valid Mountain Array
*/
/*
Two Pointers
left and right to move on slope and stop
check whether they meet

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean validMountainArray(int[] A) {
        if(A.length < 3) return false;
        int l = 0, r = A.length - 1;
        while(l < A.length - 1 && A[l + 1] > A[l]) l++;
        while(r > 0 && A[r - 1] > A[r]) r--;
        return l == r && l != A.length - 1 && r != 0;
    }
}
