/*
MEDIUM
542. 01 Matrix
https://leetcode.com/problems/01-matrix/description/

TIME: 0831 - 1.5h
RESULT: 86% - 13ms
METHODS:
	- BFS
	- DP
NOTES: 
1. queue 里面加数组
2. 上下左右周围遍历的简化写法： int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
*/
/*
SOLUTION 0: BFS
TIME: 0831 - 40min
RESULT: 21% - 37ms
THOUGHTS:
把所有 1 设为 Integer.MAX_VALUE
先遍历所有 0，其四个方向上所有大于 dis 的点都应为 dis (1)
再遍历所有 1，其四个方向上所有大于 dis 的点都应为 dis (2)

*/
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        if(matrix.length == 0) return matrix;
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0) queue.offer(new int[]{i, j});
                else matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        
        int dis = 1;        
        while(!queue.isEmpty()){
            Queue<int[]> newQueue = new LinkedList<int[]>();
            
            while(!queue.isEmpty()){
                int[] point = queue.poll();
                int i = point[0], j = point[1];
                if(i > 0 && matrix[i - 1][j] > dis){
                    matrix[i - 1][j] = dis; 
                    newQueue.offer(new int[]{i - 1, j});
                }
                if(j > 0 && matrix[i][j - 1] > dis){
                    matrix[i][j - 1] = dis;
                    newQueue.offer(new int[]{i, j - 1});
                }
                if(i < m - 1 && matrix[i + 1][j] > dis){
                    matrix[i + 1][j] = dis;
                    newQueue.offer(new int[]{i + 1, j});
                }
                if(j < n - 1 && matrix[i][j + 1] > dis){
                    matrix[i][j + 1] = dis;
                    newQueue.offer(new int[]{i, j + 1});
                }
                
            }
            dis++;
            queue = newQueue;
        }
        
        return matrix;
    }
}
/*
SOLUTION 0: 方法同上，简化循环写法
TIME: 0831 - 5min
RESULT: 7% - 60ms
*/
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        if(matrix.length == 0) return matrix;
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0) queue.offer(new int[]{i, j});
                else matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        
        int dis = 1;        
        int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while(!queue.isEmpty()){
            Queue<int[]> newQueue = new LinkedList<int[]>();
            
            while(!queue.isEmpty()){
                int[] point = queue.poll();
                for(int[] dir : direction){ //go check 4 directions
                    int i = point[0] + dir[0];
                    int j = point[1] + dir[1];  
                    if(i < 0 || j < 0 || i >= m || j >= n) continue;
                    if(matrix[i][j] > dis){
                        matrix[i][j] = dis;
                        newQueue.offer(new int[]{i, j});
                    }
                }
            }
            dis++;
            queue = newQueue;
        }
        
        return matrix;
    }
}



/*
SOLUTION 1: DP + 双向对角线遍历
TIME: 0831 - 10min
RESULT: 86% - 13ms
THOUGHTS:
跟我之前想的一样，DP 的话只有左上有参考性。
这个思想采用双对角线遍历，反向又 check 了一遍。
但是稍微有点不 intuitive
*/
public class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        if(matrix.length == 0) return matrix;
        int m = matrix.length;
        int n = matrix[0].length;
        int range = m * n; //一个点的最大可能值（matrix 只有一个 0，且他俩在对角线）
        
        //from top-left to bottom-right
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0) continue;
                int left = (i - 1 >= 0) ? matrix[i - 1][j] : range;
                int up = (j - 1 >= 0) ? matrix[i][j - 1] : range;
                matrix[i][j] = Math.min(left, up) + 1;
            }
        }
        //from bottom-right to top-left
        for(int i = m - 1; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                if(matrix[i][j] == 0) continue;
                int right = (i + 1 < m) ? matrix[i + 1][j] : range;
                int down = (j + 1 < n) ? matrix[i][j + 1] : range;
                matrix[i][j] = Math.min(Math.min(right, down) + 1, matrix[i][j]);
            }
        }        
        return matrix;
    }
}