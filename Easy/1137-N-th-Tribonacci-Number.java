/*
E
1137. N-th Tribonacci Number
*/
/*
Optimized Solution

Time: O(n)
Space: O(1)
*/
class Solution {
    public int tribonacci(int n) {
        if(n == 0) return 0;
        else if(n <= 2) return 1;
        
        int first = 0, second = 1, third = 1;
        for(int i = 3; i <= n; i++){
            int tmp = first + second + third;
            
            first = second;
            second = third;
            third = tmp;
        }
        return third;
    }
}



// /*
// Naive Solution
// */
// class Solution {
//     public int tribonacci(int n) {
//         //WHAT IF n <= 2????
//         if(n == 0) return 0;
//         else if(n <= 2) return 1;
        
//         int[] dp = new int[n + 1];
//         dp[0] = 0;
//         dp[1] = 1;
//         dp[2] = 1;
//         for(int i = 3; i < dp.length; i++){
//             dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
//         }
//         return dp[n];
//     }
// }
