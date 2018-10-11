/*
MEDIUM
54. Spiral Matrix
https://leetcode.com/problems/spiral-matrix/description/

TIME: 0720 - 2h
RESULT: 100% - 1ms
NOTES:
1. matrix : 矩阵  |   spiral order ： 旋风螺旋顺序
2. 注意尽量找规律，也要考虑到只有一行的避免重复
*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if(matrix.length == 0 || matrix[0].length == 0) return result;
        
        int rowStart = 0, rowEnd = matrix.length - 1, colStart = 0, colEnd = matrix[0].length - 1;
        while(rowStart <= rowEnd && colStart <= colEnd){
            int i = rowStart;
            int j = colStart;
            //left to right
            while(j <= colEnd){
                result.add(matrix[i][j]);
                j++;
            }
            j--;i++;
            rowStart++;
            System.out.println(result);
            while(i <= rowEnd){
                result.add(matrix[i][j]);
                i++;
            }
            i--;j--;
            colEnd--;
            while(rowStart <= rowEnd && j >= colStart){
                result.add(matrix[i][j]);
                j--;
            }
            j++;i--;
            rowEnd--;
            while(colStart <= colEnd && i >= rowStart){
                result.add(matrix[i][j]);
                i--;
            }
            i++;j++;
            colStart++;
            
            
        }
        return result;
    }
}
/*
SOLUTION REFERENCE: 
TIME: 0720 - 30min
RESULT: 100% - 1ms

思路：对于每个方向设置一个 begin 和 end 来控制其垂直列的起止位置
当 begin 和 end 均相遇，完成。
*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if(matrix.length == 0) return result;
        
        int rowBegin = 0; 
        int rowEnd = matrix.length - 1;
        int colBegin = 0; 
        int colEnd = matrix[0].length - 1;

        while(rowBegin <= rowEnd && colBegin <= colEnd){
            for(int j = colBegin; j <= colEnd; j++){
                result.add(matrix[rowBegin][j]);
            }
            rowBegin ++;

            for(int i = rowBegin; i <= rowEnd; i++){
                result.add(matrix[i][colEnd]);
            }
            colEnd --;

            if(rowBegin <= rowEnd){
                for(int j = colEnd; j >= colBegin; j--){
                    result.add(matrix[rowEnd][j]);
                }
                rowEnd --;                    
            }

            if(colBegin <= colEnd){
                for(int i = rowEnd; i >= rowBegin; i--){
                    result.add(matrix[i][colBegin]);
                }
                colBegin ++;  
            }
        }
        return result;
    }
}
/*
SOLUTION 1: 
TIME: 0720 - 2h
RESULT: 100% - 1ms
*/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if(matrix.length == 0) return result;
        
        int min = 0;
        if(matrix.length < matrix[0].length) min = matrix.length;
        else min = matrix[0].length;
        
        int c_num = (min % 2 == 0)? min/2 : min/2 + 1;
        for(int k = 0; k < c_num; k++){
            circle(k, k, matrix, result);
        }
        return result;
    }
    private void circle(int m, int n, int[][] matrix, List<Integer> result){
        int lenm = matrix[m].length - n*2 - 1;
        int lenn = matrix.length - m*2 - 1;
        if(lenm == 0){
            for(int j = 0; j <= lenn; j++) result.add(matrix[m + j][n]);
        }else if(lenn == 0){
            for(int i = 0; i <= lenm; i++) result.add(matrix[m][n + i]);
        }else{
            for(int i = 0; i < lenm; i++) result.add(matrix[m][n + i]);
            for(int j = 0; j < lenn; j++) if(n + lenm < matrix[0].length - m) result.add(matrix[m + j][n + lenm]);
            for(int i = 0; i < lenm; i++) if(m + lenn < matrix.length - n && n + lenm < matrix[0].length - m) result.add(matrix[m + lenn][n + lenm - i]);
            for(int j = 0; j < lenn ; j++) if(m + lenn < matrix.length - n)result.add(matrix[m + lenn - j][n]);          
        }
    }
}