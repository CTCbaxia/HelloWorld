/*
M
1066. Campus Bikes II
*/
/*
DFS (backtracking): Try every person with every bike -- O(M^N) 
Since 1 <= M, N <= 10, it can be acceptable

How to prun?
We only want the minimum value, so just stop when getting a value larger than the current min

Time: O(M^N) + pruning may make it faster
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
