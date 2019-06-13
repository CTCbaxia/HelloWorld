/*
MEDIUM
935. Knight Dialer
*/
/*
Dynamic Programming
for each button, calculate how many ways to arrive it at that step --> see n steps
** don't add for invalid position

Time: O(k * 3 * 4 * 8)
Space: O(1)
*/
class Solution {
    public int knightDialer(int N) {
        int MOD = 1000000007;
        int[][] directions = new int[][]{{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-2},{2,-1}};
        int[][] dp = new int[4][3];
        
        for(int i = 0; i < dp.length; i++){
                for(int j = 0; j < dp[0].length; j++){
                    dp[i][j] = 1;
                }
        }
        dp[3][0] = 0;
        dp[3][2] = 0;
        
        for(int k = 0; k < N - 1; k++){//each step
            int[][] curDp = new int[4][3];
            for(int i = 0; i < dp.length; i++){
                for(int j = 0; j < dp[0].length; j++){
                    //each button
                    if(isNotValid(i, j)) continue;
                    for(int[] dir : directions){
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if(isNotValid(x, y)) continue;
                        
                        curDp[x][y] = (curDp[x][y] + dp[i][j]) % MOD;
                    }
                }
            }
            dp = curDp;
        }
        //calculate the ways in total
        int res = 0;
        for(int i = 0; i < dp.length; i++){
                for(int j = 0; j < dp[0].length; j++){
                    System.out.println(i + " " + j + " " + dp[i][j]);
                    res = (res + dp[i][j]) % MOD;
                }
        }
        return res;
    }
    
    private boolean isNotValid(int i, int j){
        return (i == 3 && j ==0) || (i == 3 && j == 2) || i < 0 || i >= 4 || j < 0 || j >= 3;
    }
}