/*
MEDIUM
852. Peak Index in a Mountain Array

TIME: 
RESULT: 
NOTES:

*/
/*
Binary Search: climbing the mountain
lo < hi --> mid will always have one element to the right

Time: O(logn)
Space: O(1)
*/
class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int lo = 0, hi = A.length - 1;
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(A[mid] < A[mid + 1]) lo = mid + 1;
            else hi = mid;
        }
        return hi;
    }
}