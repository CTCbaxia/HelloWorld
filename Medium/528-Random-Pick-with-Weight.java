/*
MEDIUM
528. Random Pick with Weight

TIME: 
RESULT: 
NOTES:
***** 如果要你从 O(n) 再降，如果题目涉及查找 ---- 二分法 logn
*/
/*
直接看 range 落在哪里了，weight 越大，range也越大，随机数落在这个范围的可能性也越大

Time: 
    O(n) for initialization
    O(logn) for pick (O(n)也可以，直接loop一遍) 因为 presum 是单调递增的，我们只是需要在里面寻找 target 的范围
Space: O(n)
*/
class Solution {
    Random rand;
    int[] sum;
    public Solution(int[] w) {
        rand = new Random();
        sum = new int[w.length];
        int tmp = 0;
        for(int i = 0; i < w.length; i++){
            tmp += w[i];
            sum[i] = tmp;
        }
    }
    
    public int pickIndex() {
        int target = rand.nextInt(sum[sum.length - 1]) + 1;//随机数是 0~sum-1,但是sum里面的数是 1~sum
        //want to find larger or equal
        int lo = 0, hi = sum.length - 1;
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(sum[mid] == target) return mid;
            else if(sum[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return hi;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */


/*
直接看 range 落在哪里了，weight 越大，range也越大，随机数落在这个范围的可能性也越大

Time: 
    O(n) for initialization
    O(n) for pick
Space: O(n)
*/
class Solution {
    Random rand;
    int[] sum;
    public Solution(int[] w) {
        rand = new Random();
        sum = new int[w.length];
        int tmp = 0;
        for(int i = 0; i < w.length; i++){
            tmp += w[i];
            sum[i] = tmp;
        }
    }
    
    public int pickIndex() {
        int r = rand.nextInt(sum[sum.length - 1]);
        for(int i = 0; i < sum.length; i++){
            if(r < sum[i]) return i;
        }
        return 0;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */

//借鉴其他题的方法，太慢，这题可以直接求一次，然后看落在哪里

class Solution {
    Random rand;
    int[] weights;
    public Solution(int[] w) {
        weights = w;
        rand = new Random();
    }
    
    public int pickIndex() {
        int total = 0;
        int pending = 0;
        for(int i = 0; i < weights.length; i++){
            total +=weights[i];
            int r = rand.nextInt(total);
            if(r >= 0 && r < weights[i]) pending = i;
        }
        return pending;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
