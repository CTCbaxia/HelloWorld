/*
MEDIUM
240. Search a 2D Matrix II
https://leetcode.com/problems/search-a-2d-matrix-ii/description/

TIME: 0720 - 2h
RESULT: 100% - 7ms
NOTES:
1. 当你意识到算法太过复杂的时候，要换个思路
2. 横竖有序，一定要利用有序的规则，但是不要跳跃一个层次把问题弄复杂了

*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;
        int row = 0;
        int col = matrix[0].length - 1;
        while(row < matrix.length && col >= 0){
            if(matrix[row][col] == target) return true;
            else if(matrix[row][col] < target) row ++;
            else if(matrix[row][col] > target) col --;
        }
        return false;
    }
}
/*
Wrong Code
陷入到对角线关系中不能自拔。其实当对角线得划分那么多区域的时候，就该意识到肯定不是最优解。（尤其是复杂度不是 O(m + n) 的时候）
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