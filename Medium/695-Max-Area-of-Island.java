/*
695. Max Area of Island
*/
/*
DFS + visited
Time: O(mn)
Space: O(mn)
*/
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int max = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j]) continue;
                if(grid[i][j] == 1){
                    max = Math.max(max, dfs(grid, visited, i, j));
                } 
            }
        }
        return max;
    }
    private int dfs(int[][] grid, boolean[][] visited, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return 0;
        if(visited[i][j] || grid[i][j] != 1) return 0;
        
        visited[i][j] = true;
        
        int sum = 1;//add itself
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];
            sum += dfs(grid, visited, x, y);
        }
        return sum;
    }
}