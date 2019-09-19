/*
H
639. Decode Ways II
*/
/*
dynamic programming - 这题 dfs + memo 需要的 stack call 太多了，会 memory exceed

Time:  O(n)
Space: O(n)

So we need  memory
*/
class Solution {
    int mod = 1000000007;
    public int numDecodings(String s) {
        long[] dp = new long[s.length() + 1];// ""
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' 
            ? 0 
            : s.charAt(0) == '*'
                ? 9
                : 1;
        if(dp[1] == 0) return 0;
        
        for(int i = 2; i < dp.length; i++){
            char first = s.charAt(i-2);//组成双字符的左边
            char second = s.charAt(i-1);//组成单字符 / 双字符右边
            
            //for 单字符
            if(second == '*'){
                dp[i] += 9 * dp[i - 1];
            }else if(second >'0'){
                dp[i] += dp[i - 1];
            }
            
            //for 双字符
            if(first == '*'){
                if(second == '*'){
                    dp[i] += 15 * dp[i - 2];//combination for 9 + 6
                }else if(second <= '6'){
                    dp[i] += 2 * dp[i - 2];
                }else{
                    dp[i] += dp[i - 2];
                }
            }else if(first == '1'){
                if(second == '*'){
                    dp[i] += 9 * dp[i - 2];
                }else{
                    dp[i] += dp[i - 2];
                }
            }else if(first == '2'){
                if(second == '*'){
                    dp[i] += 6 * dp[i - 2];
                }else if(second <= '6'){
                    dp[i] += dp[i - 2];
                }
            }

            dp[i] %= mod;
        }
        return (int) dp[s.length()];
    }
        
}








//不知道哪里错了
/*
recursion + Memory (kind of like dp)

Time:  O(n)
Space: O(1) - stack O(n)

So we need  memory
*/
class Solution {
    int mod = 1000000007;
    public int numDecodings(String s) {
        int[] mem = new int[s.length()];
        Arrays.fill(mem, -1);
        return s.length() == 0 ? 0 : helper(0, s, mem);
    }
    private int helper(int index, String s, int[] mem){
        if(index == s.length()) return 1;
        if(mem[index] > -1) return mem[index];
        
        char c1 = s.charAt(index);
        if(c1 == '0') return 0;//impossible
        int res = ((c1 == '*' ? 9 : 1 ) * helper(index + 1, s, mem)) % mod;
        
        if(index + 2 <= s.length()){
            char c2 = s.charAt(index + 1);
            if(c1 != '*'){
                if(c1 == '1') res += ((c2 == '*' ? 9 : 1 ) * helper(index + 2, s, mem)) % mod;
                else if(c1 == '2') res += ((c2 == '*' ? 6 : ((c2 <= '6') ? 1 : 0 )) * helper(index + 2, s, mem)) % mod;
                res %= mod;
            }else{
                res += ((c2 == '*' ? 9 : 1 ) * helper(index + 2, s, mem)) % mod;
                res %= mod;
                res += ((c2 == '*' ? 6 : ((c2 <= '6') ? 1 : 0 )) * helper(index + 2, s, mem)) % mod;
                res %= mod;
            }
                
        } 
        mem[index] = res;
        return res;
    }
}


