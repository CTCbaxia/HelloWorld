/*
MEDIUM
490. The Maze

TIME: 
RESULT: 
NOTES:

*/
/*
BFS: mark the place (-1) that we have already visited
for every level, put all reachable points (that haven't been visited) to the queue
until we find the destination

improve: 
- we can use maze to mark visited: set the value to -1
- if not allowed to modify, we can have boolean[][] visited

Time: O(mn) worst case need to traverse all point
Space: O(mn)
*/
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];//mark visited ***!!! we can use maze value -1 to mark visited place
        int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};//4 direction

        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(start);
        while(!q.isEmpty()){
            int[] point = q.poll();
            for(int[] move : moves){
                int[] des = goDir(maze, point, move);
                if(des[0] == destination[0] && des[1] == destination[1]) return true;
                
                if(visited[des[0]][des[1]] == true) continue;
                else{
                    q.offer(des);
                    visited[des[0]][des[1]] = true;
                } 
            }
        }
        return false;
        
    }
    private int[] goDir(int[][] maze, int[] start, int[] dir){
        int i = start[0];
        int j = start[1];
        
        while(i >= 0 && j >= 0 && i < maze.length && j < maze[0].length && maze[i][j] != 1){
            i += dir[0];
            j += dir[1];
        }
        i -= dir[0];
        j -= dir[1];
        return new int[]{i, j};
    }
}




/*
DFS: 
make every move and finish one path, use set for visited point

Time: O((m * n)^4)
*/
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        Set<Integer> visited = new HashSet<Integer>();
        visited.add(start[0] * maze[0].length + start[1]);
        return dfs(maze, start, destination, visited);
    }
    private boolean dfs(int[][] maze, int[] cur, int[] des, Set<Integer> visited){
        if(cur[0] == des[0] && cur[1] == des[1]) return true;
        
        boolean res = false;
        int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int i = cur[0], j = cur[1];
        
        for(int[] move : moves){
            int[] arrives = goDir(maze, cur, move);
            int offset = arrives[0] * maze[0].length + arrives[1];
            if(visited.contains(offset)) continue;
            else{
                //System.out.println(cur[0] + " " + cur[1] + " -> " + arrives[0] + " " + arrives[1]);
                visited.add(offset);
                res |= dfs(maze, arrives, des, visited);
                //visited.remove(offset);//不需要 backtracking，这里相当于对于所有的 path，更新一遍之前 path已经尝试过的点
            }
        }
        return res;
    }
    private int[] goDir(int[][] maze, int[] start, int[] dir){
        int i = start[0];
        int j = start[1];
        
        while(i >= 0 && j >= 0 && i < maze.length && j < maze[0].length && maze[i][j] != 1){
            i += dir[0];
            j += dir[1];
        }
        i -= dir[0];
        j -= dir[1];
        return new int[]{i, j};
    }
}