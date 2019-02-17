/*
EASY
70. Climbing Stairs

*/
/*
Dynamic Programming
2:
1 + 1
2

3:
(1 + 1) + 1
(2) + 1
(1) + 2

4:
(1 + 1 + 1) + 1
(2 + 1) + 1
(1 + 2) + 1
(1 + 1) + 2
(2) + 2

Time: O(n)
Space: O(n)
*/
class Solution {
    public int climbStairs(int n) {
        if(n < 0) return 0;
        else if(n == 1) return 1;
        
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i < dp.length; i++){
            dp[i] = dp[i - 1] + dp[i - 2];//（从 i - 1 走 1 步）+（从 i - 2 走 2 步）
        }
        return dp[n];
    }
}





/*
DFS (Recursive)

Time: O(num of ways)
Space: O(n)
*/
class Solution {
    public int climbStairs(int n) {
        if(n < 0) return 0;
        else if(n == 1) return 1;
        else if(n == 2) return 2;
        else{
            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }
}