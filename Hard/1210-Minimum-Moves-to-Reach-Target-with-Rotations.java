/*
H
1210. Minimum Moves to Reach Target with Rotations
*/
/*
BFS
**用Queue<String> q = new LinkedList<>(); 不好，因为我们想要 int 运算
Time: O(mn * mn)
Space: O(mn * mn)
*/
class Solution {
    int N;
    public int minimumMoves(int[][] grid) {
        N = grid.length;
        int steps = 0;
        List<Integer> start = Arrays.asList(0,0,0,1);
        List<Integer> end = Arrays.asList(N - 1, N - 2, N - 1, N - 1);
        Set<List<Integer>> visited = new HashSet<>();
        Queue<List<Integer>> q = new LinkedList<>();
        q.offer(start);
        visited.add(start);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                List<Integer> cur = q.poll();
                if(cur.equals(end)) return steps;
                
                int r1 = cur.get(0), c1 = cur.get(1), r2 = cur.get(2), c2 = cur.get(3);
                List<List<Integer>> nexts = new ArrayList<>();
                nexts.add(right(grid, r1, c1, r2, c2));
                nexts.add(down(grid, r1, c1, r2, c2));
                nexts.add(clockwise(grid, r1, c1, r2, c2));
                nexts.add(counterclockwise(grid, r1, c1, r2, c2));
                for(List<Integer> next : nexts){
                    if(next != null && !visited.contains(next)){
                        visited.add(next);
                        q.offer(next);
                    }
                }
            }
            steps++;
        }
        return -1;
    }
    private List<Integer> right(int[][] grid, int r1, int c1, int r2, int c2){
        if(r1 == r2){//水平
            if(c2 + 1 < N && grid[r2][c2 + 1] == 0) return Arrays.asList(r2,c2,r2,c2 + 1);
        }else{
            if(c1 + 1 < N && grid[r1][c1 + 1] == 0 && grid[r2][c2 + 1] == 0) return Arrays.asList(r1,c1 + 1,r2,c2 + 1);
        }
        return null;
    }
    private List<Integer> down(int[][] grid, int r1, int c1, int r2, int c2){
        if(r1 == r2){//水平
            if(r1 + 1 < N && grid[r1 + 1][c1] == 0 && grid[r2 + 1][c2] == 0) return Arrays.asList(r1 + 1,c1,r2 + 1,c2);
        }else{
            if(r2 + 1 < N && grid[r2 + 1][c2] == 0) return Arrays.asList(r2,c2,r2 + 1,c2);
        }
        return null;
    }
    private List<Integer> clockwise(int[][] grid, int r1, int c1, int r2, int c2){
        if(r1 == r2){//水平
            if(r1 + 1 < N && grid[r1 + 1][c1] == 0 && grid[r2 + 1][c2] == 0) return Arrays.asList(r1,c1,r1 + 1,c1);
        }
        return null;
    }
    private List<Integer> counterclockwise(int[][] grid, int r1, int c1, int r2, int c2){
        if(c1 == c2){//水平
            if(c1 + 1 < N && grid[r1][c1 + 1] == 0 && grid[r2][c2 + 1] == 0) return Arrays.asList(r1,c1,r1,c1 + 1);
        }
        return null;
    }
    
}



/*
DFS + MEMO - 不能做 DFS + memo，因为你不知道前一步和后一步，哪一个是离终点更近的点
Time: O(mn * mn)
Space: O(mn * mn)
*/
class Solution {
    int N;
    public int minimumMoves(int[][] grid) {
        N = grid.length;
        Map<String, Integer> memo = new HashMap<>();
        int[] p1 = {0,0}, p2 = {0,1};
        int[] t1 = {N - 1, N - 2}, t2 = {N - 1, N - 1};
        int res = dfs(grid, p1, p2, t1, t2, memo);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    //return minStep from p1,p2 to t1,t2
    private int dfs(int[][] grid, int[] p1, int[] p2, int[] t1, int[] t2, Map<String, Integer> memo){
        if(p1[0] >= N || p1[1] >= N || p2[0] >= N || p2[1] >= N) return Integer.MAX_VALUE;
        if(grid[p1[0]][p1[1]] != 0 || grid[p2[0]][p2[1]] != 0) return Integer.MAX_VALUE;
        
        if(samePos(p1, p2, t1, t2)){
            return 0;
        }
        
        String state = p1[0] + "," + p1[1] + "-" + p2[0] + "," + p2[1];
        if(memo.containsKey(state)){
            return memo.get(state);
        }
        
        memo.put(state, Integer.MAX_VALUE);//先附上最大的值，代表这是之前的步子，不要回溯，因为这样肯定更慢
        
        int steps = Integer.MAX_VALUE;
        if(p1[0] == p2[0]){//水平
            //right
            if(p2[1] + 1 < N && grid[p2[0]][p2[1] + 1] == 0){
                int[] p3 = {p2[0], p2[1] + 1};
                steps = Math.min(steps, dfs(grid, p2, p3, t1, t2, memo));
            }
            //down
            if(p1[0] + 1 < N && grid[p1[0] + 1][p1[1]] == 0 && grid[p2[0] + 1][p2[1]] == 0){
                int[] p3 = {p1[0] + 1, p1[1]};
                int[] p4 = {p2[0] + 1, p2[1]};
                steps = Math.min(steps, dfs(grid, p3, p4, t1, t2, memo));
            }
            //clockwise
            if(p1[0] + 1 < N && grid[p1[0] + 1][p1[1]] == 0 && grid[p2[0] + 1][p2[1]] == 0){
                int[] p3 = {p1[0] + 1, p1[1]};
                steps = Math.min(steps, dfs(grid, p1, p3, t1, t2, memo));
            }
        }else{//垂直
            //right
            if(p1[1] + 1 < N && grid[p1[0]][p1[1] + 1] == 0 && grid[p2[0]][p2[1] + 1] == 0){
                int[] p3 = {p1[0], p1[1] + 1};
                int[] p4 = {p2[0], p2[1] + 1};
                steps = Math.min(steps, dfs(grid, p3, p4, t1, t2, memo));
            }
            //down
            if(p2[0] + 1 < N && grid[p2[0] + 1][p2[1]] == 0){
                int[] p3 = {p2[0] + 1, p2[1]};
                steps = Math.min(steps, dfs(grid, p2, p3, t1, t2, memo));
            }
            //counterclockwise
            if(p1[1] + 1 < N && grid[p1[0]][p1[1] + 1] == 0 &&  grid[p2[0]][p2[1] + 1] == 0){
                int[] p3 = {p1[0], p1[1] + 1};
                steps = Math.min(steps, dfs(grid, p1, p3, t1, t2, memo));
            }
        }
        steps = steps == Integer.MAX_VALUE ? Integer.MAX_VALUE : steps + 1;
        memo.put(state, steps);       
        return steps;    
    }
    private boolean samePos(int[] p1, int[] p2, int[] p3, int[] p4){
        if(p1[0] == p3[0] && p1[1] == p3[1] && p2[0] == p4[0] && p2[1] == p4[1]) return true;
        else return false;
    }
}