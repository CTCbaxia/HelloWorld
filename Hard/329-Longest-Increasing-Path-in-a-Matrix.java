/*
HARD
329. Longest Increasing Path in a Matrix

TIME: 
RESULT: 
*/
/*
DFS for every point + Cache for the calculated point

Time: O(mn * mn) for every dfs point, every element in the matrix can only be visited once
Space: O(mn)
*/    
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int result = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] maxlen = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                result = Math.max(result, dfs(matrix, i, j, maxlen));
            }
        }
        return result;
    }
    private int dfs(int[][] matrix, int i, int j, int[][] maxlen){//compute the longest increasing path from (i, j)
        if(maxlen[i][j] != 0) return maxlen[i][j];
        int[][] directions = new int[][]{{1, 0},{-1, 0},{0, 1},{0,-1}};
        int max = 1;
        for(int[] dir : directions){
            int row = i + dir[0];
            int col = j + dir[1];
            if(row >= 0 && col >= 0 && row < matrix.length && col < matrix[0].length){
                if(matrix[i][j] < matrix[row][col]){
                    max = Math.max(max, 1 + dfs(matrix, row, col, maxlen));
                }
            }
        }
        maxlen[i][j] = max;
        return max;
    }

}    





/*
BFS for every point to calculate the longest path


Time: O(mn * mn)
Space: O(mn)
*/
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int result = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                result = Math.max(result, bfs(matrix, i, j));
            }
        }
        return result;
    }
    private int bfs(int[][] matrix, int i, int j){
        int[][] directions = new int[][]{{1, 0},{-1, 0},{0, 1},{0,-1}};
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i, j});

        int len = 0;
        while(!q.isEmpty()){
            Queue<int[]> newQ = new LinkedList<>();
            while(!q.isEmpty()){
                int[] point = q.poll();
                for(int[] dir : directions){
                    int row = point[0] + dir[0];
                    int col = point[1] + dir[1];
                    if(row >= 0 && col >= 0 && row < matrix.length && col < matrix[0].length){
                        if(matrix[point[0]][point[1]] < matrix[row][col]){
                            newQ.offer(new int[]{row, col});
                        }
                    }
                }
            }
            q = newQ;
            len++;
        }
        return len;
    }

}