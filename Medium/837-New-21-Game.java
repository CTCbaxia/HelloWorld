/*
MEDIUM
837. New 21 Game

*/
/*
TLE
Dynamic Programming:
所有的点都是可以由前 W 个点 draw 一次到达
Important: 到达了 K 之后的点不会再有新的 draw，所以 dp 的时候 dp[i] 的来源不会是大于 k 的点
Termination: 所有点都超过或到达了 K
0 <= K <= N <= K - 1 + W （所有点都超过或到达了 K）

Time: O((K+W) * W)
Space: O(K+W)
*/
class Solution {
    public double new21Game(int N, int K, int W) {
        if(K == 0) return 1;
        int max = K - 1 + W;
        double[] dp = new double[max + 1];//从 1 开始，直到 max
        dp[0] = 1;
        
        double res = 0;
        for(int i = 1; i <= max; i++){
            for(int j = 1; j <= W; j++){
                if(i - j >= 0 && i - j < K){//超过 K 的点不会再生成新的 draw
                    dp[i] += dp[i - j]/W;
                }
            }
            
            if(i >= K && i <= N) res += dp[i];
        }
        return res;
    }
}

/*
Dynamic Programming:
可以直接 keep 一个大小 W 的 sliding window，记录前 W 个数的概率和。每个数都能 1/W 的概率到达这个数。

Important: 到达了 K 之后的点不会再有新的 draw，所以 dp 的时候 dp[i] 的来源不会是大于 k 的点
Termination: 所有点都超过或到达了 K
0 <= K <= N <= K - 1 + W （所有点都超过或到达了 K）

Time: O(N)
Space: O(N)
*/
class Solution {
    public double new21Game(int N, int K, int W) {
        if(K == 0) return 1;
        double[] dp = new double[N + 1];//从 1 开始，直到 max
        dp[0] = 1;//initialization should be 1, before everything????
        double proSum = 1;
        double res = 0;
        for(int i = 1; i <= N; i++){//when N < i < max(K - 1 + W), 计算没有意义
            dp[i] = proSum / W;
            
            if(i < K){
                proSum += dp[i];
            }else{
                res += dp[i];
            }
            if(i - W >= 0) proSum -= dp[i - W];
        }
        return res;
    }
}


/*
❌ 不可以用发生事件的总数来算概率。因为在到达 K 之后，有的事件不会再生成后续事件。应该用概率直接算
Important: 到达了 K 之后的点不会再有新的 draw，所以 dp 的时候 dp[i] 的来源不会是大于 k 的点
Termination: 所有点都超过或到达了 K
0 <= K <= N <= K - 1 + W （所有点都超过或到达了 K）
*/
class Solution {
    public double new21Game(int N, int K, int W) {
        int max = K - 1 + W;
        double[] dp = new double[max + 1];//从 1 开始，直到 max
        dp[0] = 1;
        
        double res = 0;
        for(int i = 1; i <= max; i++){
            for(int j = 1; j <= W; j++){
                if(i - j >= 0 && i - j < K){
                    dp[i] += dp[i - j];
                }
            }
            
            if(i >= K && i <= N) res += dp[i];
        }
        System.out.println(res);
        double total = Math.pow(W, K);
        return (double)res/total;
    }
}