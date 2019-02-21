/*
Amazon_Shortest_Path_in_Chessboard
象棋中骑士从a点到b点到最短距离。。详细的问了dfs和bfs
面试官的头从头摇到尾。。。。
向上下左右四个方向分别走 L型, 八个方向， 没有障碍
输入什么都是自己做决定，我就只输入了a点跟b点的二维坐标，一开始用array来存坐标，但面试官不满意，之后另外写了class来存

BFS:
Go from source node
bfs until find the destination


BFS: shortest path
DFS: all paths
*/
class SolutionM{
    public static class Point{
        int x;
        int y;
        public Point(int _x, int _y){
            x = _x;
            y = _y;
        }
    }
    public int Amazon_Shortest_Path_in_Chessboard(Point p1, Point p2, int N){
        boolean[][] visited = new boolean[N][N];
        int[][] directions = {{2,1},{-2,1},{2,-1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};

        int step = 0;
        Queue<Point> q = new LinkedList<>();
        q.offer(p1);
        visited[p1.x][p1.y] = true;
        while(!q.isEmpty()){
            int len = q.size();
            step++;
            for(int i = 0; i < len; i++){
                Point p = q.poll();
                for(int[] dir : directions){
                    int x = p.x + dir[0];
                    int y = p.y + dir[1];
                    if(x < 0 || x >= N || y < 0 || y >= N || visited[x][y]) continue;
                    if(x == p2.x && y == p2.y) return step;
                    q.offer(new Point(x, y));
                    visited[x][y] = true;
                }
            }
        }
        return -1;
    }
}

