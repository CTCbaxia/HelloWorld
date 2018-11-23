/*
HARD
317. Shortest Distance from All Buildings

TIME: 
RESULT: 
*/
/*
for all building, BFS
just modify the original grid, make step to all building as negative 累计 value
and count for total number of buildings, store how many building each empty space can reach

有可能你的结果reach不到某一个building
Time: O((mn)^2)
Space: O(mn)
*/
class Solution {
    public int shortestDistance(int[][] grid) {
        int res = Integer.MAX_VALUE;
        int buildingNum = 0;
        int[][] reach = new int[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    buildingNum++;
                    findDis(grid, i, j, reach);
                }
            }
        }
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(reach[i][j] == buildingNum){//can reach all buildings
                    res = Math.min(res, -grid[i][j]);//find the min distance value
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
        
    }
    private void findDis(int[][] grid, int i, int j, int[][] reach){
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        int[][] directions = new int[][]{{1, 0},{-1, 0},{0, 1},{0, -1}};
        int m = grid.length;
        int n = grid[0].length;
        int step = 1;
        
        q.offer(i * n +j);
        visited.add(i * n +j);
        while(!q.isEmpty()){
            Queue<Integer> newQ = new LinkedList<>();
            while(!q.isEmpty()){
                int p = q.poll();
                int row = p / n;
                int col = p % n;
                for(int[] dir : directions){
                    int row2 = row + dir[0];
                    int col2 = col + dir[1];
                    if(row2 >= 0 && col2 >= 0 && row2 < m && col2 < n){
                        if(grid[row2][col2] == 1 || grid[row2][col2] == 2) continue;
                        if(!visited.contains(row2 * n + col2)){
                            grid[row2][col2] -= step;//for this new building, minus step
                            reach[row2][col2]++;
                            newQ.offer(row2 * n + col2);
                            visited.add(row2 * n + col2);
                        }
                    }
                }
            }
            q = newQ;
            step++;
        }
        return;
    }
}