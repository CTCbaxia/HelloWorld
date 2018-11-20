/*
MEDIUM
548. Split Array with Equal Sum

TIME: 
RESULT: 
NOTES:

*/
/*
Presum + 中间隔断 + set
三段式 -- 中间先隔断（divide and conquer），然后对比两边是不是有相等的parts

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public boolean splitArray(int[] nums) {
        int[] sums = new int[nums.length];
        int sum = 0;
        for(int i = 0; i < sums.length; i++){
            sum += nums[i];
            sums[i] = sum;
        }
        for(int m = 3; m < sums.length - 3; m++){//find the middle first
            Set<Integer> set = new HashSet<>();
            for(int l = 1; l < m - 1; l++){//find equal parts in left
                if(sums[l - 1] == sums[m - 1] - sums[l]){
                    set.add(sums[l - 1]);
                }
            }
            for(int r = m + 1; r < sums.length - 1; r++){//check equal parts in right
                if(sums[sums.length - 1] - sums[r] == sums[r - 1] - sums[m]){
                    if(set.contains(sums[r - 1] - sums[m])){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}





/*
用 j 来做隔断
先找 i j 部分可能的相等 intervals1
再找 j k 部分可能的相等 intervals2，且如果等于 intervals1， 返回 true

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public boolean splitArray(int[] nums) {
        if(nums.length < 7) return false;
        int len = nums.length;
        int[] sum = new int[len];
        sum[0] = nums[0];
        for(int i = 1; i < len; i++){
            sum[i] = sum[i - 1] + nums[i];
        }
        
        for(int j = 3; j < len - 3; j++){
            Set<Integer> intervals = new HashSet<Integer>();
            for(int i = 1; i < j - 1; i++){//find all possible intervals between i and j
                if(sum[i - 1] == sum[j - 1] - sum[i]){
                    intervals.add(sum[i - 1]);
                }
            }
            for(int k = j + 2; k < len - 1; k++){
                if(sum[len - 1] - sum[k] == sum[k - 1] - sum[j] && intervals.contains(sum[len - 1] - sum[k])){
                    return true;
                }
            }
        }
        return false;
    }
}
/*
先找 i 的位置，然后找 k 的位置使两端 interval 相等
对于每个（i，k），找之间的 j

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public boolean splitArray(int[] nums) {
        if(nums.length < 7) return false;
        int[] sum = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(i == 0){
                sum[i] = nums[i];
                continue;
            } 
            sum[i] = sum[i - 1] + nums[i];
        }
        int lo = 1; 
        int hi = nums.length - 2;
        while(lo <= nums.length - 6){
            int interval = sum[lo - 1];
            while(hi - 3 > lo && sum[sum.length - 1] - sum[hi] != interval) hi--;
            
            for(int i = lo + 2; i <= hi - 2; i++){
                if(sum[i - 1] - sum[lo] == interval && sum[hi - 1] - sum[i] == interval) return true;
            }
            if(lo < hi - 3) hi--;//对这个 lo 进行下一轮 hi 匹配
            else if(lo >= hi - 3){//如果没有合适的，lo++，hi 重置
                lo++;
                hi = nums.length - 2;
            }

        }
        return false;
    }
}