/*
EASY
278. First Bad Version

RESULT: 99% - 12ms
NOTES: 

*/

/* 
Time: O(logn)
Space: O(1)
*/

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int lo = 0; 
        int hi = n;
        while(lo < hi){
            int mid = lo + (hi - lo) / 2;
            if(isBadVersion(mid)) hi = mid;
            else lo = mid + 1;
        }
        return hi;
    }
}