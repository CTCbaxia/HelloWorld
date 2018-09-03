/*
MEDIUM
275. H-Index II
https://leetcode.com/problems/h-index-ii/description/

TIME: 0903 - 20min
RESULT: 0% - 1034ms

*/


/*
SOLUTION 0: 二分法
TIME: 0903 - 20min
RESULT: 27% - 11ms
THOUGHTS: 
如果一个 mid 点满足不了 h-index， 那么它之后的点也都满足不了
*/
class Solution {
    public int hIndex(int[] citations) {
        int total = citations.length;
        int lo = 0, hi = total - 1;
        int result = 0;
        while(lo <= hi){
            int mid = (lo + hi) / 2;
            if(citations[mid] >= (total - mid)){
                result = total - mid;
                hi = mid - 1;
            } 
            else lo = mid + 1;
        }
        return result;
    }
}
/*
SOLUTION 0: Sequential Search
TIME: 0903 - 20min
RESULT: 0% - 1034ms
THOUGHTS:
h - index 指的是 paper 数量。
倒着数现在有几篇 paper 了，目前的这个 citations[i] 是不是大于现在有的 paper 数量，
如果是，说明现有 paper 均满足条件，下一个循环。
如果不是，则截止上一个满足条件的 paper 数量
*/
class Solution {
    public int hIndex(int[] citations) {
        int total = citations.length;
        for(int i = total - 1; i >= 0; i--){
            System.out.println(citations[i] + " " + i);
            if(citations[i] >= total - i) continue;
            else return total - i - 1;
        }
        return total;
    }
}