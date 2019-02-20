/*
417. Pacific Atlantic Water Flow
*/
/*
真正的 BFS：从起始点【起始区域】一直往下走，最终能走到哪
Time: O(mn)
Space: O(mn)
*/
class Solution {
    int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
    int m, n;
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;
        
        m = matrix.length;
        n = matrix[0].length;
        boolean[][] pac = new boolean[m][n];//mark if this point can reach pac
        boolean[][] atl = new boolean[m][n];
        Queue<int[]> pacQ = new LinkedList<>();//do bfs
        Queue<int[]> atlQ = new LinkedList<>();
        
        for(int i = 0; i < m; i++){//for each row, left and right
            pac[i][0] = true;
            atl[i][n - 1] = true;
            pacQ.offer(new int[]{i,0});
            atlQ.offer(new int[]{i,n - 1});
        }
        for(int j = 0; j < n; j++){//for each col, top and down
            pac[0][j] = true;
            atl[m - 1][j] = true;
            pacQ.offer(new int[]{0,j});
            atlQ.offer(new int[]{m - 1, j});
        }
        //bfs for two ocean, get the boolean map for points ocean can reach
        bfs(matrix, pac, pacQ);
        bfs(matrix, atl, atlQ);
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(pac[i][j] && atl[i][j]){
                    result.add(new int[]{i, j});
                }
            }
        }
        return result;
    }
    private void bfs(int[][] matrix, boolean[][] visited, Queue<int[]> q){
        while(!q.isEmpty()){
            int[] p = q.poll();
            for(int[] dir : directions){
                int row = p[0] + dir[0];
                int col = p[1] + dir[1];
                if(row < 0 || row >= m || col < 0 || col >= n) continue;
                if(visited[row][col]) continue;
                if(matrix[row][col] >= matrix[p[0]][p[1]]){
                    visited[row][col] = true;
                    q.offer(new int[]{row, col});
                }
            }            
        }
    }
    
}






/*
BFS for every node

Time: O(mn*mn)
Space: O(mn)
*/
class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        
        if(matrix.length == 0 || matrix[0].length == 0) return result;
        int m = matrix.length, n = matrix[0].length;
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                Queue<Integer> q = new LinkedList<>();
                boolean[][] visited = new boolean[m][n];
                q.offer(i * n + j);
                visited[i][j] = true;// otherwise may deadloop in same height
                boolean pac = false, atl = false;
                while(!q.isEmpty()){
                    int p = q.poll();
                    int row = p / n;
                    int col = p % n;
                    
                    for(int[] dir : directions){
                        int x = row + dir[0];
                        int y = col + dir[1];
                        if(x < 0 || y < 0) pac = true;
                        else if(x >= m || y >= n) atl = true;
                        else if(matrix[x][y] <= matrix[row][col] && !visited[x][y]){
                            q.offer(x * n + y);
                            visited[x][y] = true;
                        }  
                    }
                    if(pac && atl){
                        result.add(new int[]{i, j});
                        break;
                    }
                }
            }
        }
        return result;
    }
}