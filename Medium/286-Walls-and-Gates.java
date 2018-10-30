/*
MEDIUM
286. Walls and Gates

TIME: 
RESULT: 
NOTES:
1. BFS
2. DFS
https://leetcode.com/problems/walls-and-gates/discuss/72748/Benchmarks-of-DFS-and-BFS
分析时间复杂度很好
*/
/*
DFS

Time: O(mn*mn)
Space: O(1)
*/
class Solution {
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length == 0 || rooms[0].length == 0) return;
        
        for(int i = 0; i < rooms.length; i++){
            for(int j = 0; j < rooms[0].length; j++){
                if(rooms[i][j] == 0) dfs(rooms, i, j, 0);
            }
        }
        return;
    }
    private void dfs(int[][] rooms, int i, int j, int step){
        if(i < 0 || j < 0 || i == rooms.length || j == rooms[0].length || rooms[i][j] < step) return;
        rooms[i][j] = step;
        dfs(rooms, i - 1, j, step + 1);
        dfs(rooms, i + 1, j, step + 1);
        dfs(rooms, i, j - 1, step + 1);
        dfs(rooms, i, j + 1, step + 1);
        return;
    }

}
/*
BFS + Queue<Integer> 用偏移量记录点位置 （优化空间）

Time: O(mn) + O(mn) - BFS最多就扫一遍所有
Space: O(mn)
*/
class Solution {
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length == 0 || rooms[0].length == 0) return;
        Queue<Integer> q = new LinkedList<Integer>();
        int col = rooms[0].length;
        
        for(int i = 0; i < rooms.length; i++){
            for(int j = 0; j < rooms[0].length; j++){
                if(rooms[i][j] == 0) q.offer(i * col + j);
            }
        }
        
        while(!q.isEmpty()){
            int point = q.poll();
            int i = point/col;
            int j = point%col;
            if(i > 0 && rooms[i - 1][j] == Integer.MAX_VALUE){
                rooms[i - 1][j] = rooms[i][j] + 1;
                q.offer((i - 1) * col + j);
            }
            if(j > 0 && rooms[i][j - 1] == Integer.MAX_VALUE){
                rooms[i][j - 1] = rooms[i][j] + 1;
                q.offer((i) * col + j - 1);
            } 
            if(i < rooms.length - 1 && rooms[i + 1][j] == Integer.MAX_VALUE){
                rooms[i + 1][j] = rooms[i][j] + 1;
                q.offer((i + 1) * col + j);
            } 
            if(j < rooms[0].length - 1 && rooms[i][j + 1] == Integer.MAX_VALUE){
                rooms[i][j + 1] = rooms[i][j] + 1;
                q.offer(i * col + j + 1);
            } 
        }
        return;
    }

}
//这个 BFS 其实不需要层次
class Solution {
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length == 0 || rooms[0].length == 0) return;
        Queue<Integer> q = new LinkedList<Integer>();
        int col = rooms[0].length;
        
        for(int i = 0; i < rooms.length; i++){
            for(int j = 0; j < rooms[0].length; j++){
                if(rooms[i][j] == 0) q.offer(i * col + j);
            }
        }
        
        while(!q.isEmpty()){
            Queue<Integer> newQ = new LinkedList<Integer>();
            while(!q.isEmpty()){
                int point = q.poll();
                int i = point/col;
                int j = point%col;
                if(i > 0 && rooms[i - 1][j] == Integer.MAX_VALUE){
                    rooms[i - 1][j] = rooms[i][j] + 1;
                    newQ.offer((i - 1) * col + j);
                }
                if(j > 0 && rooms[i][j - 1] == Integer.MAX_VALUE){
                    rooms[i][j - 1] = rooms[i][j] + 1;
                    newQ.offer((i) * col + j - 1);
                } 
                if(i < rooms.length - 1 && rooms[i + 1][j] == Integer.MAX_VALUE){
                    rooms[i + 1][j] = rooms[i][j] + 1;
                    newQ.offer((i + 1) * col + j);
                } 
                if(j < rooms[0].length - 1 && rooms[i][j + 1] == Integer.MAX_VALUE){
                    rooms[i][j + 1] = rooms[i][j] + 1;
                    newQ.offer(i * col + j + 1);
                } 
            }
            q = newQ;
        }
        return;
    }

}


/*
BFS + Queue<int[]>

Time: O(mn) + O(mn) - BFS最多就扫一遍所有
Space: O(mn)
*/
class Solution {
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length == 0 || rooms[0].length == 0) return;
        Queue<int[]> q = new LinkedList<int[]>();
        for(int i = 0; i < rooms.length; i++){
            for(int j = 0; j < rooms[0].length; j++){
                if(rooms[i][j] == 0) q.offer(new int[]{i, j});
            }
        }
        
        while(!q.isEmpty()){
            Queue<int[]> newQ = new LinkedList<int[]>();
            while(!q.isEmpty()){
                int[] point = q.poll();
                int i = point[0];
                int j = point[1];
                if(i > 0 && rooms[i - 1][j] == Integer.MAX_VALUE){
                    rooms[i - 1][j] = rooms[i][j] + 1;
                    newQ.offer(new int[]{i - 1, j});
                }
                if(j > 0 && rooms[i][j - 1] == Integer.MAX_VALUE){
                    rooms[i][j - 1] = rooms[i][j] + 1;
                    newQ.offer(new int[]{i, j - 1});
                } 
                if(i < rooms.length - 1 && rooms[i + 1][j] == Integer.MAX_VALUE){
                    rooms[i + 1][j] = rooms[i][j] + 1;
                    newQ.offer(new int[]{i + 1, j});
                } 
                if(j < rooms[0].length - 1 && rooms[i][j + 1] == Integer.MAX_VALUE){
                    rooms[i][j + 1] = rooms[i][j] + 1;
                    newQ.offer(new int[]{i, j + 1});
                } 
            }
            q = newQ;
        }
        return;
    }

}