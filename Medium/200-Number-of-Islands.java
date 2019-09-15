/*
200. Number of Islands
https://leetcode.com/problems/number-of-islands/description/

TIME: 0706
RESULT: 100%

*/
/*
DFS + mark inplace
Time: O(mn)
Space: O(1)
*/
class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;
        // if(grid.length == 0 || grid[0].length == 0) return res;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }
    private void dfs(char[][] grid, int i, int j){
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        grid[i][j] = '0';
        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];
            if(x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == '1'){
                dfs(grid, x, y);
            }
        }
    }
}






// modify the input
class Solution {
    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int res = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    res++;
                    dfs(grid, i, j);
                } 
            }
        }
        return res;
    }
    private void dfs(char[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0'){
            return;//important to check grid[i][j] == '0'
        }
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        grid[i][j] = '0';
        
        for(int[] dir : directions){
            dfs(grid, i + dir[0], j + dir[1]);
        }
        return;
    }
}








//------------for Facebook
/*
When we find one island, make the whole island 0

Time: O(mn)
Space: O(1)
*/
class Solution {
    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int res = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length;j++){
                if(grid[i][j] == '1'){
                    res++;
                    findIsland(grid, i, j);
                }
            }
        }
        return res;
    }
    private void findIsland(char[][] grid, int i, int j){
        int[][] directions = {{1, 0},{-1, 0},{0, 1},{0, -1}};
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return;
        if(grid[i][j] == '1'){
            grid[i][j] = '0';
            for(int[] dir : directions){
                findIsland(grid, i + dir[0], j + dir[1]);
            }
        }
        return;
    }
}






class Solution {
    public int numIslands(char[][] grid) {
        int number = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    number++;
                    mark_island(i,j,grid);
                }
            }
        }
        return number;
    }
    private void mark_island(int row, int column, char[][] grid){

        if(row >= grid.length || row < 0 || column >= grid[0].length || column < 0 || grid[row][column] == '0' || grid[row][column] == 'x'){
            return;
        }else{
            grid[row][column] = 'x';
        }
        mark_island(row - 1, column, grid);
        mark_island(row + 1, column, grid);
        mark_island(row, column - 1, grid);
        mark_island(row, column + 1, grid);
    }
}
