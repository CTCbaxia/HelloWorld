/*
MEDIUM
1091. Shortest Path in Binary Matrix
*/
/*
BFS (to find the shortest path -- always go further 永远向前看)
直接更改 grid 的值，每次遇到 grid = 0 的时候才更新步数并放入 queue

Time: O(n^2) evert empty(0) position will be put into the Queue no more than once
Space: O(n^2)
*/
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return -1;//invalid input
        
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;//impossible case
        
        int[][] directions = new int[][]{{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        int res = 1;

        while(!q.isEmpty()){
            int size = q.size();
            res++;
            for(int i = 0; i < size; i++){
                int[] pos = q.poll();
                for(int[] dir : directions){
                    int x = pos[0] + dir[0];
                    int y = pos[1] + dir[1];
                    if(x < 0 || y < 0 || x >= n || y >= n) continue;
                    if(grid[x][y] == 0){
                        grid[x][y] = res;
                        if(x == n - 1 && y == n - 1) return res;//reach the destination
                        q.offer(new int[]{x,y});
                    }
                }
            }
        }
        return -1;

    }
}




/*
BFS (to find the shortest path -- always go further 永远向前看)
**其实可以直接 modify 这个 grid

Time: O(n^2) evert empty(0) position will be put into the Queue no more than once
Space: O(n^2)
*/
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return -1;//invalid input
        
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;//impossible case
        
        int[][] directions = new int[][]{{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
        int[][] dp = new int[n][n];
        dp[0][0] = 1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        int res = 1;

        while(!q.isEmpty()){
            int size = q.size();
            res++;
            for(int i = 0; i < size; i++){
                int[] pos = q.poll();
                for(int[] dir : directions){
                    int x = pos[0] + dir[0];
                    int y = pos[1] + dir[1];
                    if(x < 0 || y < 0 || x >= n || y >= n) continue;
                    if(grid[x][y] == 1) continue;
                    if(dp[x][y] == 0){//Note: here is dp[x][y] == 0 rather than grid[x][y] == 0. reach a NEW empty pos (always go further rather than go back)
                        dp[x][y] = res;
                        if(x == n - 1 && y == n - 1) return res;//reach the destination
                        q.offer(new int[]{x,y});
                    }
                }
            }
        }
        return -1;

    }
}