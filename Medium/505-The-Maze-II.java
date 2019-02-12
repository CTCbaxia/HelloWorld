/*
MEDIUM
505. The Maze II

*/
/*
BFS: PriorityQueue
把所有的点都更新为从 start 到该点最近的距离 (Min Spanning Tree - Dijstra)

Time: 
Space: 
*/
class Solution {
    public class Point{
        int x;
        int y; 
        int l;
        public Point(int _x, int _y, int _l){
            x = _x;
            y = _y;
            l = _l;
        }
    }
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if(maze.length == 0 || maze[0].length == 0) return 0;
        
        int res = 0;
        int m = maze.length, n = maze[0].length;
        int[][] len = new int[m][n];
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>(){
            public int compare(Point p1, Point p2){
                return p1.l - p2.l;
            }
        });
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                len[i][j] = Integer.MAX_VALUE;
            }
        }
        //initialization
        len[start[0]][start[1]] = 0;
        pq.offer(new Point(start[0], start[1], 0));
        
        while(!pq.isEmpty()){
            Point from = pq.poll();
            
            for(int[] dir : directions){
                int i = from.x;
                int j = from.y;
                int l = from.l;
                while(i >= 0 && i < m && j >= 0 && j < n && maze[i][j] != 1){
                    i += dir[0];
                    j += dir[1];
                    l++;
                }
                i -= dir[0];
                j -= dir[1];
                l--;
                
                if(len[i][j] <= l) continue;//如果已经在之前有过到达 (i,j) 更短的路径
                len[i][j] = l;//update all paths from this point (put into pq)
                pq.offer(new Point(i, j, l));
            }
        }
        
        return len[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : len[destination[0]][destination[1]];
    }
}