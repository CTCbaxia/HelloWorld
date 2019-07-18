/*
M
351. Android Unlock Patterns
*/
/*
DFS - backtracking: mark visited position and count path when m <= steps <= n
pruning: 
1) there are many symmetric cases, only need to compute for starting with 1, 2, 6
2) can use a jump to simplify the jump case

Time: O(3 * 9^9) 1-9 loop will be tried at most 9 times
Space:O(1)
*/
class Solution {
    public int numberOfPatterns(int m, int n) {
        int[][] jump = new int[10][10];//indicate the middle position for a jump
        jump[1][3] = jump[3][1] = 2; 
        jump[4][6] = jump[6][4] = 5;
        jump[7][9] = jump[9][7] = 8;
        jump[1][7] = jump[7][1] = 4;
        jump[2][8] = jump[8][2] = 5;
        jump[3][9] = jump[9][3] = 6;
        jump[1][9] = jump[9][1] = jump[3][7] = jump[7][3] = 5;
        
        int res = 0;
        boolean[]visited = new boolean[10];
        // because of the symmetrical, only need to try 3 position
        res += computePatterns(m, n, 1, 1, 0, jump, visited) * 4;//update count
        res += computePatterns(m, n, 2, 1, 0, jump, visited) * 4;//update count
        res += computePatterns(m, n, 5, 1, 0, jump, visited);//update count
        return res;
        
    }
    private int computePatterns(int m, int n, int cur, int curNumOfKey, int count, int[][] jump, boolean[] visited){
        if(curNumOfKey >= m) count++;
        if(curNumOfKey == n) return count;
        visited[cur] = true;
        
        for(int next = 1; next <= 9; next++){
            if(!visited[next] && (jump[cur][next] == 0 || visited[jump[cur][next]])){
                //if next position has not been visited and (there is no jump || jumped position has been visited)
                count = computePatterns(m, n, next, curNumOfKey + 1, count, jump, visited);//update count
            }
        }
        visited[cur] = false;//backtracking
        return count;
    }
}


/*
✅可行，但是可以优化
DFS - backtracking: mark visited position and count path when m <= steps <= n
pruning: there are many mirror cases, only need to compute for starting with 1, 2, 6

Time: O(9 * 24^9)
Space: O(9*9)
*/
class Solution {
    public int numberOfPatterns(int m, int n) {
        int res = 0;
        boolean[][] visited = new boolean[3][3];
        res += computePatterns(m, n, 1, new int[]{0, 0}, 0, visited) * 4;//symmetric
        res += computePatterns(m, n, 1, new int[]{0, 1}, 0, visited) * 4;
        res += computePatterns(m, n, 1, new int[]{1, 1}, 0, visited);
        return res;
        
    }
    private int computePatterns(int m, int n, int curNum, int[] curPos, int count, boolean[][] visited){
        if(curNum >= m) count++;//this path can work now
        if(curNum == n) return count;
        
        visited[curPos[0]][curPos[1]] = true;
        int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1},
                                         {2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2},
                                         {2,0},{-2,0},{0,2},{0,-2},{2,2},{2,-2},{-2,2},{-2,-2}};//24 ways to move in total
        for(int[] dir : directions){
            int x = curPos[0] + dir[0];
            int y = curPos[1] + dir[1];
            if(x >= 0 && y >= 0 && x < 3 && y < 3){//valid position
                if(Math.abs(dir[0]) == 2 && Math.abs(dir[1]) == 2 && !visited[curPos[0] + dir[0]/2][curPos[1] + dir[1]/2]) continue;
                if(Math.abs(dir[0]) == 2 && dir[1] == 0 && !visited[curPos[0] + dir[0]/2][curPos[1]]) continue;
                if(dir[0] == 0 && Math.abs(dir[1]) == 2 && !visited[curPos[0]][curPos[1] + dir[1]/2]) continue;
                if(!visited[x][y]) count = computePatterns(m, n, curNum + 1, new int[]{x, y}, count, visited);
            }
        }
        visited[curPos[0]][curPos[1]] = false;//backtracking
        return count;
    }
}

/*
❌
DFS - backtracking: mark visited position and count path when m <= steps <= n
pruning: there are many mirror cases, only need to compute for starting with 1, 2, 6

Time: O()
*/
class Solution {
    int res = 0;
    public int numberOfPatterns(int m, int n) {
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boolean[][] visited = new boolean[3][3];
                computePatterns(m, n, 1, new int[]{i, j}, visited);
                System.out.println(res);
            }
        }
        return res;
        
    }
    private void computePatterns(int m, int n, int curNum, int[] curPos, boolean[][] visited){
        if(curNum >= m) res++;//this path can work now
        if(curNum == n) return;
        
        int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1},
                                         {2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2},
                                         {2,0},{-2,0},{0,2},{-2,0},{2,2},{2,-2},{-2,2},{-2,-2}};
        for(int[] dir : directions){
            int x = curPos[0] + dir[0];
            int y = curPos[1] + dir[1];
            System.out.println("x:"+x+" y:"+y);
            if(x >= 0 && y >= 0 && x < 3 && y < 3){//valid position
                if(Math.abs(dir[0]) == 2 && Math.abs(dir[1]) == 2 && !visited[curPos[0] + dir[0]/2][curPos[1] + dir[1]/2]) continue;
                if(Math.abs(dir[0]) == 2 && dir[1] == 0 && !visited[curPos[0] + dir[0]/2][curPos[1]]) continue;
                if(dir[0] == 0 && Math.abs(dir[1]) == 2 && !visited[curPos[0]][curPos[1] + dir[1]/2]) continue;
                if(visited[x][y]){//❌不能回到之前到过的点，只能路过以前到达过的点。每一步只能走到新的点
                    computePatterns(m, n, curNum, new int[]{x, y}, visited);
                } 
                else{
                    visited[x][y] = true;//❌在这里标记会使得 curPos 这个初识点没有被true，比如 (0,0)->(0,1)-(0,0) 就不会走到上一个 if 里面
                    computePatterns(m, n, curNum + 1, new int[]{x, y}, visited);
                    visited[x][y] = false;//!!backtracking
                } 
            }
        }
        return;
    }
}
