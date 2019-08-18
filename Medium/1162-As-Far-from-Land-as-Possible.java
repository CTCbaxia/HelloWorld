/*
M
1162. As Far from Land as Possible
*/
/*
BFS - shortest dis
Time: O(mn)
Space: O(1)
*/
class Solution {
    public int maxDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    q.offer(new int[]{i, j});
                }
            }
        }
        if(q.size() == 0 || q.size() == m * n) return -1;
        
        //bfs
        int[][] directions = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
        int step = 0;
        while(!q.isEmpty()){
            step--;
            int size = q.size();
            for(int i = 0; i < size; i++){
                int[] p = q.poll();
                for(int[] dir : directions){
                    int x = p[0] + dir[0];
                    int y = p[1] + dir[1];
                    if(x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 0){
                        grid[x][y] = step;//update next level
                        q.offer(new int[]{x, y});
                    }
                }
            }
            
        }
        return Math.abs(step + 1);//last level
    }
}
