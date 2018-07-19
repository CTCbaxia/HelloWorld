/*
463. Island Perimeter
https://leetcode.com/problems/island-perimeter/description/

TIME: 0719 - 1h
RESULT: 90% - 64ms
NOTES:
1. Perimeter: 周长 | diagonally: 斜的
METHOD:

*/
/*
SOLUTION 0:
TIME: 0719 - 10min
RESULT: 90% - 64ms

对于 SOLUTION 1 做了改进。
因为遍历是从左到右，从上到下的，所以其实对于 = 1 的岛，不需要 check 四个方向，只需要继续 check 右、下方向这部分没有被 loop 到的内容。
而因为这是的 num 是默认左上部分已经自动清零了，所以也不需要让 grid[] = 0
最后，如果辅助方法并没有很简化内容（没有迭代 / 复用），可以直接写在 mian 里，运行会快些
*/
class Solution {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        int[] adjs = {4, 2, 0, -2, -4}; 
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == 1){
                    int num = 0;
                    if(i < grid.length - 1 && grid[i + 1][j] == 1) num++;
                    if(j < grid[0].length - 1 && grid[i][j + 1] == 1) num++;
                    perimeter = perimeter + adjs[num];
                } 
            }
        }
        return perimeter;
    }
}

/*
SOLUTION 1:
TIME: 0719 - 1h
RESULT: 55% - 67ms


1. 对于每个岛块，查它上下左右有没有岛，如果有 n 个，那么它带有的边长为 4 - n
2. 每次遍历到一个岛块，消灭它，那么 perimeter = x + 剩余的perimeter，直到最后一个岛块的剩余的perimeter = 4
    四边没有相连   x = 4
    四边有一个相连 x = 2 
    四边有两个相连 x = 0
    四边有三个相连 x = -2
    四边有四个相连 x = -4
*/
class Solution {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == 1){
                    int n = adjacence(i, j, grid);
                    perimeter = perimeter + n;
                    grid[i][j] = 0; //useless : 只需要查右和下没有被遍历的
                } 
            }
        }
        return perimeter;
    }
    private int adjacence(int i, int j, int[][] grid){
        int[] adjs = {4, 2, 0, -2, -4}; 
        int num = 0;
        if(i >= 1 && grid[i - 1][j] == 1) num++; //useless : 其实左和上肯定被覆盖为 0 了
        if(i < grid.length - 1 && grid[i + 1][j] == 1) num++;
        if(j >= 1 && grid[i][j - 1] == 1) num++; //useless
        if(j < grid[0].length - 1 && grid[i][j + 1] == 1) num++;
        return adjs[num];
    }

}