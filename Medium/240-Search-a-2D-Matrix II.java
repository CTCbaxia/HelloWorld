/*
MEDIUM
240. Search a 2D Matrix II
https://leetcode.com/problems/search-a-2d-matrix-ii/description/

TIME: 0720 - 1h+
RESULT: 
NOTES:


*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        return subSearch(0, 0, matrix, target);
        
    }
    private boolean subSearch(int row, int col, int[][] matrix, int target){
        int lenrow = matrix.length - row;
        int lencol = matrix[0].length - col;
        int min = 0;
        if(lenrow <= lencol) min = lenrow;
        else min = lencol;
        int aim = 0;
        for(int i = 0; i < min; i++){
            if(matrix[row + i][col + i] >= target){
                aim = i;
                break;
            }
            if(i == min - 1 && matrix[row + i][row + i] < target) aim = min;
        }
        System.out.println(aim);
        if(aim < min){
            for(int i = 0; i <= aim; i++){
                if(matrix[aim][i] == target || matrix[i][aim] == target) return true;
            }
            return false;
        }else{
            if(min == lenrow) return subSearch(row, col + min, matrix, target);
            else return subSearch(row + min, col, matrix, target);            
        }
    }
}