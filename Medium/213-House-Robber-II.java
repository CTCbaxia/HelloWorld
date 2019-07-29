/*
M
213. House Robber II
*/
/*
Dynamic Programming + 滚轴运算（只需要两个以前的值）

NOTE!!!
1. check if index is the null pointer
2. 只要能够顺利初始化就好了，所以要找到可以初始化的点

Time: O(n)
Space: O(n)
*/
class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        else if(nums.length == 1) return nums[0];
        else if(nums.length == 2) return Math.max(nums[0], nums[1]);
        
        int first1 = nums[0];
        int second1 = Math.max(nums[0], nums[1]);
        int first2 = nums[1];
        int second2 = Math.max(nums[1], nums[2]);
        
        for(int i = 2; i < nums.length - 1; i++){
            int tmp1 = Math.max(second1, first1 + nums[i]);
            int tmp2 = Math.max(second2, first2 + nums[i + 1]);
            
            first1 = second1;
            second1 = tmp1;
            
            first2 = second2;
            second2 = tmp2;
        }
        return Math.max(second1, second2);
    }
}

/*
Dynamic Programming:
dp[i] = 
1) dp[i - 2] + nums[i]
2) dp[i - 1]

Using two dp, 
one exclude the last one, 
one exclude the first one

NOTE!!!
check if index is the null pointer


Time: O(n)
Space: O(1)
*/
class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        else if(nums.length == 1) return nums[0];
        
        int[] dp1 = new int[nums.length];//include the first one, exclude the last one
        int[] dp2 = new int[nums.length];//exclude the first one, include the last one
        dp1[0] = 0;
        dp1[1] = nums[0];
        dp2[0] = 0;
        dp2[1] = nums[1];
        for(int i = 2; i < nums.length; i++){
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i - 1]);
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i]);
        }
        return Math.max(dp1[nums.length - 1], dp2[nums.length - 1]);
    }
}


/*
Dynamic Programming:
dp[i] = 
1) dp[i - 2] + nums[i]
2) dp[i - 1]

NOTE!!!
1. check if index is the null pointer
2. 只要能够顺利初始化就好了，所以要找到可以初始化的点

Time: O(n)
Space: O(n)
*/
class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        else if(nums.length == 1) return nums[0];
        else if(nums.length == 2) return Math.max(nums[0], nums[1]);
        
        int[] dp1 = new int[nums.length - 1];//include the first one, exclude the last one
        int[] dp2 = new int[nums.length - 1];//exclude the first one, include the last one
        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0], nums[1]);
        dp2[0] = nums[1];
        dp2[1] = Math.max(nums[1], nums[2]);
        for(int i = 2; i < dp1.length; i++){
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i]);
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i + 1]);
        }
        return Math.max(dp1[dp1.length - 1], dp2[dp2.length - 1]);
    }
}
