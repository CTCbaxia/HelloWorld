/*
M
1066. Campus Bikes II
*/
/*
DFS (backtracking): Try every person with every bike -- O(M^N) 
Since 1 <= M, N <= 10, it can be acceptable

How to prun?
We only want the minimum value, so just stop when getting a value larger than the current min

Time: O(numBikes * (numBikes - 1) * (numBikes - 2).... (numBikes - numWorkers)) + pruning may make it faster
Space: O(N)//stack 深度
*/
class Solution {
    int min = Integer.MAX_VALUE;
    public int assignBikes(int[][] workers, int[][] bikes) {
        dfs(workers, bikes, 0, new boolean[bikes.length], 0);
        return min;
    }
    private void dfs(int[][] workers, int[][] bikes, int p, boolean[] visited, int curDis){
        if(p == workers.length){
            min = Math.min(min, curDis);
            return;
        }
        if(curDis > min) return;//pruning
        
        for(int i = 0; i < bikes.length; i++){
            if(visited[i]) continue;
            
            visited[i] = true;
            dfs(workers, bikes, p + 1, visited, curDis + computeDis(workers[p], bikes[i]));
            visited[i] = false;//backtracking
        }
    }
    private int computeDis(int[] worker, int[] bike){
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}

/*
DFS (backtracking): Try every person with every bike -- O(M^N) 
Since 1 <= M, N <= 10, it can be acceptable

Time: O(M^N)
Space: O(N)//stack 深度
*/
class Solution {
    int min = Integer.MAX_VALUE;
    public int assignBikes(int[][] workers, int[][] bikes) {
        dfs(workers, bikes, 0, new boolean[bikes.length], 0);
        return min;
    }
    private void dfs(int[][] workers, int[][] bikes, int p, boolean[] visited, int curDis){
        if(p == workers.length){
            min = Math.min(min, curDis);
            return;
        }
        
        for(int i = 0; i < bikes.length; i++){
            if(visited[i]) continue;
            
            visited[i] = true;
            dfs(workers, bikes, p + 1, visited, curDis + computeDis(workers[p], bikes[i]));
            visited[i] = false;//backtracking
        }
    }
    private int computeDis(int[] worker, int[] bike){
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}




//dp + bitmask state
//看这个答案：https://leetcode.com/problems/campus-bikes-ii/discuss/305218/DFS-%2B-Pruning-And-DP-Solution
class Solution{
     public int assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        int[][] dp = new int[n + 1][1 << m];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {//这个 i 对应 worker 是要大 1 的
            for (int s = 1; s < (1 << m); s++) {
                for (int j = 0; j < m; j++) {
                    if ((s & (1 << j)) == 0) {//如果在这个 state 里面，自行车 j 没有被使用，skip
                        continue;
                    }
                    int prev = s ^ (1 << j);//找到在未使用这个 j 自行车时候的 prev state
                    dp[i][s] = Math.min(dp[i - 1][prev] + dis(workers[i - 1], bikes[j]), dp[i][s]) ;
                    if (i == n) {//最后一个人了，也就是 s 这里表示全部分配完了
                        min = Math.min(min, dp[i][s]);
                    }
                }
            }
        }
        return min;
    }
  
    public int dis(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}