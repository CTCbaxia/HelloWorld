/*
M
152. Maximum Product Subarray
*/
/*
Dynamic Programming
previous product 都是重复的内容， dp 可以帮助省略计算
且因为必须是连续的，所以只需要看之前一个数就好
dp[i] = dp[i - 1] * nums[i] || nums[i]

因为要求极值，只需要让保存正反的极值就好, 下一个极值肯定是这个数 * 上一个极值

Time: O(n)
Space: O(n)
*/
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(nums.length == 0) return 0;
        
        int imax = nums[0];//largest continuous product include i
        int imin = nums[0];
        int res = nums[0];
        for(int i = 1; i < n; i++){
            if(nums[i] < 0){//swap numbers
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(nums[i], nums[i] * imax);
            imin = Math.min(nums[i], nums[i] * imin);
            res = Math.max(res, imax);
        }
        return res;
    }
    private void swap(int n1, int n2){

    }
    
}





/*
Dynamic Programming
previous product 都是重复的内容， dp 可以帮助省略计算
且因为必须是连续的，所以只需要看之前一个数就好
dp[i] = dp[i - 1] * nums[i] || nums[i]

因为要求极值，只需要让保存正反的极值就好

Time: O(n)
Space: O(n)
*/
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(nums.length == 0) return 0;
        
        int[] imax = new int[n];//largest continuous product include i
        int[] imin = new int[n];
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            imax[i] = i == 0 
                    ? nums[i] 
                    : Math.max(nums[i], nums[i] * (nums[i] > 0 ? imax[i - 1] : imin[i - 1]));
            imin[i] = i == 0 
                    ? nums[i] 
                    : Math.min(nums[i], nums[i] * (nums[i] > 0 ? imin[i - 1] : imax[i - 1]));
            res = Math.max(res, Math.max(imax[i], imin[i]));
        }
        return res;
    }
}





//严重❌
/*
Dynamic Programming
previous product 都是重复的内容， dp 可以帮助省略计算
且因为必须是连续的，所以只需要看之前一个数就好
dp[i] = dp[i - 1] * nums[i] || nums[i]

因为要求极值，只需要让保存正反的极值就好, 下一个极值肯定是这个数 * 上一个极值

Time: O(n)
Space: O(n)
*/
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(nums.length == 0) return 0;
        
        int imax = nums[0];//largest continuous product include i
        int imin = nums[0];
        int res = nums[0];
        for(int i = 1; i < n; i++){
            imax = Math.max(nums[i], nums[i] * (nums[i] > 0 ? imax : imin));
            imin = Math.min(nums[i], nums[i] * (nums[i] > 0 ? imin : imax));//❌如果nums[i]<0，这里用的imax就是更新过后的值了
            res = Math.max(res, imax);
        }
        return res;
    }
}
