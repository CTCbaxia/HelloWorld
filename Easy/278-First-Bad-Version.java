/*
EASY
278. First Bad Version

RESULT: 99% - 12ms
NOTES: 

*/
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
/*
Binary Search
Time: O(logn)
Space: O(1)
*/
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while(l < r){//find first occurence, use r to hold the bad version
            int m = l + (r - l)/2;
            if(isBadVersion(m)) r = m;//hold bad version
            else l = m + 1;
        }
        //l == r and should be first bad, because l - 1 is always good
        return r;
    }
}



/* 
这个题是要找第一个出现的 target，所以要用 high hold住 target，不断往前逼近找到的 target

Time: O(logn)
Space: O(1)
*/
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int lo = 1, hi = n;
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(isBadVersion(mid)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}