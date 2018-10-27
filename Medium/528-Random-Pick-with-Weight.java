/*
MEDIUM
528. Random Pick with Weight

TIME: 
RESULT: 
NOTES:
***** 如果要你从 O(n) 再降，如果题目涉及查找 ---- 二分法 logn
*/
/*
根据 ran 落在的区间找对应的位置

Time: 
	O(n) for initialization
	O(logn) for pick
Space: O(n)
*/
class Solution {
    Random r;
    int[] sum;
    public Solution(int[] w) {
        r = new Random();
        sum = new int[w.length];
        sum[0] = w[0];
        for(int i = 1; i < sum.length; i++){
            sum[i] = sum[i - 1] + w[i];
        }
    }
    
    public int pickIndex() {
        int ran = r.nextInt(sum[sum.length - 1]) + 1;
        int lo = 0; 
        int hi = sum.length - 1;
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(ran == sum[mid]) return mid;
            else if(ran < sum[mid]) hi = mid;
            else lo = mid + 1;
        }
        return hi;
    }
}



/*
根据 ran 落在的区间找对应的位置

Time: 
	O(n) for initialization
	O(n) for pick
Space: O(n)
*/
class Solution {
    Random r;
    int[] sum;
    public Solution(int[] w) {
        r = new Random();
        sum = new int[w.length];
        sum[0] = w[0];
        for(int i = 1; i < sum.length; i++){
            sum[i] = sum[i - 1] + w[i];
        }
    }
    
    public int pickIndex() {
        int ran = r.nextInt(sum[sum.length - 1]);
        for(int i = 0; i < sum.length; i++){
            if(ran < sum[i]) return i;
        }
        return 0;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
