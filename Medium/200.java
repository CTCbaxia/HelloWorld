/*
200. Number of Islands
https://leetcode.com/problems/number-of-islands/description/

TIME: 0706
RESULT: 100%
NOTE: 思路很重要
*/

class Solution {
    public int numIslands(char[][] grid) {
        int number = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    number++;
                    mark_island(i,j,grid);
                }
            }
        }
        return number;
    }
    private void mark_island(int row, int column, char[][] grid){

        if(row >= grid.length || row < 0 || column >= grid[0].length || column < 0 || grid[row][column] == '0' || grid[row][column] == 'x'){
            return;
        }else{
            grid[row][column] = 'x';
        }
        mark_island(row - 1, column, grid);
        mark_island(row + 1, column, grid);
        mark_island(row, column - 1, grid);
        mark_island(row, column + 1, grid);
    }
}
