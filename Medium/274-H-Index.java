/*
MEDIUM
274. H-Index
https://leetcode.com/problems/h-index/description/

TIME: 0906 - 1h
RESULT: 17% - 7ms
NOTES: Bucket Sort 要再体会
*/

//7ms
class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        for(int i = citations.length - 1; i >= 0; i--){
            if(citations[i] < citations.length - i)
                return citations.length - i - 1;
        }
        return citations.length;
    }
}
//8ms
class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int lo = 0, hi = citations.length - 1;
        while(lo <= hi){
            int mid = (lo + hi) / 2;
            if(citations[mid] == citations.length - mid) return citations.length - mid;
            else if(citations[mid] > citations.length - mid) hi = mid - 1;
            else lo = mid + 1;
        }
        return citations.length - (hi + 1);//hi 一定是在满足条件的 mid 之下一位的。如果没有任何值满足条件，这个方程会返回 0
    }
}
//44%
//https://leetcode.com/problems/h-index/discuss/70768/Java-bucket-sort-O(n)-solution-with-detail-explanation
//求累计值，Bucket Sort。这个方法太不 intuitive 了。
class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        
        int[] cit = new int[len + 1];//cit 对应的 index 为引用数，最后一位存了引用值大于文章总数的数量
        for(int c : citations){
            if(c > len) cit[len] += 1;
            else cit[c] += 1;//文章引用少于总文章数的文章，对应的引用数量，的对应的文章数
        }
        int total = 0;
        for(int i = len; i >= 0; i--){
            total += cit[i];
            if(total >= i) return i;
        }
        return 0;
    }
}