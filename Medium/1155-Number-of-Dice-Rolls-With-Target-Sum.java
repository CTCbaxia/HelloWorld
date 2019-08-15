/*
M
1155. Number of Dice Rolls With Target Sum
*/
/*
Dynamic Programing + kind of BFS
For every dice, updtae the can-arrive-position with how many ways to arrive at them

Time: O(d * f * target)
Space: O(target) // we only need to compute till target
*/
class Solution {
    public int numRollsToTarget(int d, int f, int target) {
        if(target > d * f) return 0;
        int[] dp = new int[target + 1];
        for(int i = 1; i <= f && i <= target; i++){
            dp[i] = 1;
        }
        int low = 1, high = f;
        for(int i = 2; i <= d; i++){
            int[] next = new int[target + 1];// build a new array for this dice
            for(int k = 1; k <= target; k++){// for every previously arrived number
                for(int j = 1; j <= f && j <= target - k; j++){// the number we get for this dice
                    next[k + j] = (next[k + j] + dp[k]) % 1000000007;
                }
            }
            //assign temp to be dp
            dp = next;

        }
        return dp[target];
    }
}
