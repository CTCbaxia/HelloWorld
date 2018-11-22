/*
MEDIUM
498. Diagonal Traverse
https://leetcode.com/problems/diagonal-traverse/description/

TIME: 0807 - 1h
RESULT: 98% - 4ms
NOTES: 
1. 矩阵的 row + column 对角线相等（间隔奇偶）
2. “以结果为对象遍历输入” 比 “以 matrix 为对象遍历输入” 思路更简单

*/
/*
这题关键点在于，每一条 line，i + j 都不变。
可以帮助判断方向。
甚至可以用来解题，因为一个变量不变(i + j) = k，所以改变 i, j = k - i，直到(i,j)hit the boundary

Time: O(m * n)
Space: O(1)
*/
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length == 0) return new int[0];
        int row = 0, column = 0, M = matrix.length, N = matrix[0].length, result[] = new int[M * N];
        for(int i = 0; i < result.length; i++){
            result[i] = matrix[row][column];
            if((row + column) % 2 == 0){//moving up
                if(column == N - 1) row++;//应该先于 row == 0 的常规边界判断，以为可能到达不了就要转向了
                else if(row == 0) column++;//常规边界
                else{ row--; column++; }
            }else{//moving down
                if(row == M - 1) column++;//if we are at down side, col++ to turn right
                else if(column == 0) row++;
                else{ row++; column--; }
            }
        }
        return result;
    }
}
/*
这题关键点在于，每一条 line，i + j 都不变。
可以帮助判断方向。
可以用来解题，因为一个变量不变(i + j) = k，所以改变 i, j = k - i，直到(i,j)hit the boundary
注意 boundary 处理 

Time: O(m * n)
Space: O(1)
*/
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length == 0) return new int[0];
        int row = 0, col = 0, M = matrix.length, N = matrix[0].length, result[] = new int[M * N];
        int k = 0;//i + j
        for(int i = 0; i < result.length; i++){
            result[i] = matrix[row][k - row];
            if(k % 2 == 0){//up
                row--;
                if(row < 0 || k - row >= N){//只要hit boundary，就k++，且特殊处理
                    k++;
                    row++;
                    if(k - row >= N){
                        row++;
                    }
                }
            }else if(k % 2 == 1){//down
                row++;
                if(row >= M || k - row < 0){//只要hit boundary，就k++，且特殊处理
                    k++;
                    if(row >= M){
                        row--;
                    }
                }
            }
            
            
        }
        return result;
    }
}





/*
SOLUTION REFERENCE:
TIME: 0807 - 1h
RESULT: 98% - 4ms
*/
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length == 0) return new int[0];
        int row = 0, column = 0, M = matrix.length, N = matrix[0].length, result[] = new int[M * N];
        for(int i = 0; i < result.length; i++){
            result[i] = matrix[row][column];
            if((row + column) % 2 == 0){//moving up
                if(column == N - 1) row++;
                else if(row == 0) column++;
                else{ row--; column++; }
            }else{//moving down
                if(row == M - 1) column++;
                else if(column == 0) row++;
                else{ row++; column--; }
            }
        }
        return result;
    }
}

/*
SOLUTION 0:
TIME: 0807 - 1h
RESULT: 59% - 5ms

普通思路也能做，cover 转向部分的不同 case就行
*/
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        int row = 0; 
        int column = 0;
        int M = matrix.length;
        if(M == 0) return new int[0];
        int N = matrix[0].length;
        int[] result = new int[M * N];
        int i = 0;
        while(row < M && column < N){
            while(row >= 0 && column < N) result[i++] = matrix[row--][column++];
            
            if(column < N) row++;
            else if(column >= N && row + 2 < M){
                row = row + 2;
                column --;
            }else break;
            
            while(column >= 0 && row < M) result[i++] = matrix[row++][column--];
            
            if(row < M) column++;
            else if(row >= M && column + 2 < N){
                column = column + 2;
                row--;                
            }else break;

        }
        return result;
    }
}