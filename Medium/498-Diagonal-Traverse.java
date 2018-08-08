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