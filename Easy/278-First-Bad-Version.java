/*
EASY
278. First Bad Version

RESULT: 99% - 12ms
NOTES: 

*/

/* 
这个题是要找第一个出现的 target，所以要用 high hold住 target，不断往前逼近找到的 target

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