/*
MEDIUM
518. Coin Change


TIME: 
RESULT: 
*/
/*
Dynamic Programming: 2D array

dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]]

Time: O(mn) m is amount, n is number of coins
Space: O(mn)
*/
class Solution {
    public int change(int amount, int[] coins) {
        if(coins.length == 0) return amount == 0 ? 1 : 0;
        int[][] dp = new int[coins.length][amount + 1];
        for(int i = 0; i < coins.length; i++){
            dp[i][0] = 1;
        }
        
        for(int i = 0; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                dp[i][j] = (i == 0) ? 0 : dp[i - 1][j];//if we only use the elements before the i, how many choices
                if(j >= coins[i]) dp[i][j] += dp[i][j - coins[i]];//if we want to use i, how many choices we got注意index范围
            }
        }
        return dp[coins.length - 1][amount];
    }
}




//DP 1D Array
class Solution {
    public int change(int amount, int[] coins) {
        if(coins.length == 0) return amount == 0 ? 1 : 0;
        
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        
        for(int i = 0; i < coins.length; i++){
            for(int j = coins[i]; j <= amount; j++){
                dp[j] += dp[j - coins[i]];//这里必须正着做因为每一轮你都会用到上一轮的结果，也就是 i - 1 的结果
            }
        }
        return dp[amount];
    }
}
//DP 2D Array
class Solution {
    public int change(int amount, int[] coins) {
        if(coins.length == 0) return amount == 0 ? 1 : 0;
        
        int[][] dp = new int[coins.length][amount + 1];
        for(int i = 0; i < coins.length; i++){
            dp[i][0] = 1;
        }
        
        for(int i = 0; i < coins.length; i++){
            for(int j = 1; j <= amount; j++){
                dp[i][j] = (i == 0) ? 0 : dp[i - 1][j]; //if we only use the elements before the i, how many choices
                if(j >= coins[i]){
                    dp[i][j] += dp[i][j - coins[i]];//if we want to use i, how many choices we got
                }
            }
        }
        return dp[coins.length-1][amount];
    }
}



/*
DFS: TLE
*/
class Solution {
    public int change(int amount, int[] coins) {
        return sumHelper(amount, coins, 0);
    }
    private int sumHelper(int target, int[] coins, int index){
        int count = 0;
        if(target == 0) return 1;
        if(target < 0) return 0;
        if(target > 0){
            for(int i = index; i < coins.length; i++){
                count += sumHelper(target - coins[i], coins, i);
            }
        }
        return count;
    }
}


/*来来
dynamic programming
dp[i] = dp[i] + dp[i-coin]
o(m*n) time, m is amount, n is number of coins
o(m) space
keep adding available coins
start from only coins[0] is available, then coins[0], coins[1] are available, ..., then coins[0, 1, ..., i] are available
when coins[0, 1, ..., i] are available, dp[j] means number of combination to make up j using coins[0, 1, ..., i]
dp[j] = dp[j](last round, not using coins[i]) + dp[j-coins[i]](using coins[i], ways to make up left, which is j - coins[i])
*/

class Solution {
public:
    int change(int amount, vector<int>& coins) {
        vector<int> dp(amount + 1, 0);
        dp[0] = 1; // ways to make up 0
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] = dp[j] + dp[j-coin]; // to get ways to make up j using coins[0-i], adding ways to make up j using coins[0-(i-1)] and ways to make up j-coin[i] using coins[0-i]
            }
        }
        return dp[amount];
    }
};
