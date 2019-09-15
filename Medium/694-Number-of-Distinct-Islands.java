/*
MEDIUM
694. Number of Distinct Islands

TIME: 
RESULT: 
NOTES:

*/
/*
Use relative position

Time: O(mn)
Space: O(mn)
*/
class Solution {
    public int numDistinctIslands(int[][] grid) {
        Set<String> set = new HashSet<>();
        if(grid.length == 0 || grid[0].length == 0) return 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, i, j, sb);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }
    private void dfs(int[][] grid, int i, int j, int oi, int oj, StringBuilder sb){
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        sb.append((i - oi) + "" +(j - oj));//build island
        grid[i][j] = 0;//marker
        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];
            if(x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1){
                dfs(grid, x, y, oi, oj, sb);
            }
        }
    }
}






/*
DFS
We just need the relative position to the original point for an island
So we use StringBuilder to build the island parts


Time: O(mn)? main part is the loop of the grid
Space: O(mn) ? worst case: all 1
注意这题 offset 的方法不行，要用（i，j) 双坐标定位，下面的也会成立
0 1 1
1 1 1
0 0 0
1 1 1
1 1 0
*/
class Solution {
    public int numDistinctIslands(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        
        Set<String> result = new HashSet<String>();//avoid duplicate
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    StringBuilder sb = new StringBuilder();//for the whole island
                    find(grid, i, j, i, j, sb);
                    String s = sb.toString();
                    if(result.contains(s)) continue;
                    else result.add(s);
                }
            }
        }
        return result.size();
    }
    /*
    find the whole island, and make all 相对位置 to oi, oj into a string
    because for two same island, the traverse order should also be the same
    */
    private void find(int[][] grid, int i, int j, int oi, int oj, StringBuilder sb){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return;
        
        if(grid[i][j] == 1){
            sb.append(i - oi).append(j - oj);//relative position
            grid[i][j] = 0;//marked so we won't treat it as island again
        }else return;
        
        int[][] moves = {{1, 0},{-1, 0}, {0, 1},{0, -1}};
        for(int[] move : moves){
            find(grid, i + move[0], j + move[1], oi, oj, sb);
        }
        return;
    }
}

