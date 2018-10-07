/*
MEDIUM
48. Rotate Image
https://leetcode.com/problems/rotate-image/description/

TIME: 1006 - 30min
RESULT: 46% - 2ms
思路：
在每一层做旋转，每次旋转一个单位。每一层要旋转的steps 数量等于该层数目 - 1
*/
class Solution {
    public void rotate(int[][] matrix) {
        for(int i = 0; i < matrix.length / 2; i++){
            rotateLevel(matrix, matrix.length - 2*i - 1, i);
        }
        return;
    }
    private void rotateLevel(int[][] matrix, int steps, int level){
        int n = matrix.length;
        while(steps > 0){
            int tmp = matrix[level][level];
            //down to up
            int row = level;
            int col = level;
            while(row + 1 < n - level){
                matrix[row][col] = matrix[++row][col];
            }
            while(col + 1 < n - level){
                matrix[row][col] = matrix[row][++col];
            }
            while(row - 1 >= level){
                matrix[row][col] = matrix[--row][col];
            }
            while(col - 1 > level){
                matrix[row][col] = matrix[row][--col];
            }
            matrix[row][col] = tmp;
            steps--;
            
        }
        return;
    }
}

/*
SOLUTION REFERENCE:
rotate a matrix is to first turn the matrix upside down
and then mirror the matrix using its diagonal

*/
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //turn the matrix up side down
        for(int i = 0; i < n / 2; i++){
            for(int j = 0; j < n; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = tmp;
            }
        }
        //turn the matrix diagnosely
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= i; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        return;
    }
}