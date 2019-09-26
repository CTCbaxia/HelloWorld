/*
MEDIUM
300. Longest Increasing Subsequence

TIME: 
RESULT: 99% - 1ms
NOTES:
binary search 的总结
*/
/*
Binary Search: find the large or equal num | can also use treemap's ceiling, O(nlogn)
i: length
end[i]: min end num for that len, update upper bound to give more flexibility for latter elements

do binary search to find the right position to put the current number, 
update dp[i] to make it smallest so far, but still larger than left ,smaller than right

end[i] should be increasing

Time: O(nlogn)
Space: O(n)
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = 0;
        int[] end = new int[nums.length + 1];//int[] end = new int[nums.length];//不会到达 length
        for(int i = 0; i < nums.length; i++){
            int l = 0, r = len;
            while(l < r){//find the large or equal num, update that larger value to nums[i]
                int m = l + (r - l)/2;
                if(end[m] < nums[i]) l = m + 1;
                else r = m;
            }//m can never be the original r(len)
            end[r] = nums[i];
            if(r == len) len++;
        }
        return len;
    }
}
/*
DP
dp[i]: longest increasing subsequence till i-th index(inclusive)
update result after every dp[i] calculated

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        int res = 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}









//Internship ================================================================================

/*
Binary Search: LargerOrEqual
for upper bound, update upper bound to give more flexibility for latter elements

Time: O(nlogn)
Space: O(n)
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] bound = new int[nums.length];//the upper bound[i] for LIS == i + 1
        int len = 0;//初始化这里会直接把array第一个值更新到len里面
        for(int i = 0; i < nums.length; i++){
            int target = nums[i];//找到第一个大于等于 target 的index
            int lo = 0, hi = len;
            while(lo < hi){//find larger or equal
                int mid = lo + (hi - lo)/2;//其实这里的 mid 是永远不可能等于 hi 的
                if(bound[mid] < target) lo = mid + 1;
                else hi = mid;
            }
            //这里找到的 hi 是顺序里第一个大于或等于 nums[i] 的数，或者就是现有数的 len
            bound[hi] = nums[i];//将第一个大于等于 target 的值更新为nums[i],减小边界
            if(hi == len) len++;
        }
        return len;
    }
}


/*
Dynamic Programming
dp[i]: longest increasing subsequence till i-th index(inclusive)
update result after every dp[i] calculated

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int result = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);//important! base case应该都是 1， 不然后面迭代就不对        
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}


/*
Binary Search: keep longest increasing subsequence's upper bound

len[k] 就是这个长度为 k 的 subsequence 里面的最大的数（后面的数想要接这个path，得比这个上边界更大），
len 一定是从小到大的
对于每个 nums[i]，找到 len[k] 里面第一个大于等于 nums[i]的数，然后更新这个数，这样使得这条 path 后面的空间更大
如果这个 nums[i] 比前面所有的 path 的最大值都大，就会更新 len[size] 的值，而扩大最长substring 的 size

Time: O(nlogn)
Space: O(n)
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] len = new int[nums.length];
        int size = 0;//初始化这里会直接把array第一个值更新到len里面
        for(int i = 0; i < nums.length; i++){
            int lo = 0;
            int hi = size;
            //其实这里的 mid 是永远不可能等于 hi 的
            while(lo < hi){
                int mid = lo + (hi - lo)/2;
                if(len[mid] < nums[i]) lo = mid + 1;
                else if(len[mid] >= nums[i]) hi = mid;
            }
            //这里找到的 hi 是顺序里第一个大于或等于 nums[i] 的数，或者就是现有数的 size
            len[hi] = nums[i];//将第一个大于等于 target 的值更新为nums[i],减小边界
            if(hi == size) size++;
            
        }
        return size;
    }
}


/*
Binary Search
方法不是很优化

Time: O(nlogn)
Space: O(n)
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] len = new int[nums.length + 1];
        int size = 0;
        for(int i = 0; i < nums.length; i++){
            int lo = 0;
            int hi = size;
            while(lo < hi){
                int mid = lo + (hi - lo)/2;
                if(len[mid] < nums[i]) lo = mid + 1;
                else if(len[mid] > nums[i]) hi = mid;
                else break;
            }
            
            if(len[hi] <= nums[i]){
                if(hi == size) size++;
                len[hi + 1] = nums[i];
            }else if(len[hi] > nums[i]){
                len[hi] = nums[i];
            }
        }
        return size;
    }
}



/*
DP

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] len = new int[nums.length];
        len[0] = 1;
        int result = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]) len[i] = Math.max(len[i], len[j] + 1);
                else len[i] = Math.max(len[i], 1);
            }
            result = Math.max(result, len[i]);
        }
        return result;
    }
}



/*
For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
Note that there may be more than one LIS combination, it is only necessary for you to return the length.
*/

class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0){
            return 0;
        }
        
        int[] dp = new int[prices.length];
        
        for(int i = prices.length - 1; i >= 0; i--){
            if( i == prices.length - 1){
                dp[i] = 0;
            }else{
                int choose_i = 0;
                int max_choose_i = 0;
                int not_choose_i = 0;
                //choose i
                for(int j = i + 1; j < prices.length; j++){
                    if(prices[j] > prices[i] ){
                        if(j + 1 < prices.length){
                            choose_i = prices[j] - prices[i] + dp[j + 1];
                            max_choose_i = Math.max(max_choose_i, choose_i);
                        }else{
                            choose_i = prices[j] - prices[i];
                            max_choose_i = Math.max(max_choose_i, choose_i);
                        }
                    }
                }
                
                //not choose i
                not_choose_i = dp[i + 1];
                dp[i] = Math.max(max_choose_i, not_choose_i);
            }
        }
        return dp[0];
    }
    
}
