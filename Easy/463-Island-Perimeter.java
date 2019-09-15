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
for lake, check for 4 sides of the lake, if finds a neighbor is island, res++;
for island, check whether it is the bound, one bound got res++

Time: O(mn)
Space: O(1)
*/
class Solution {
    public int islandPerimeter(int[][] grid) {
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        int res = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){//只要岛岛挨到边，就 + 1
                    if(i == 0) res++;
                    if(i == grid.length - 1) res++;
                    if(j == 0) res++;
                    if(j == grid[0].length - 1) res++;
                }else{//meet a lake
                    for(int[] dir : directions){
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if(x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1){
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }
}
/*
高票答案
顺序规律：4 * island - 2 * neighbors edge
from top to down, left to right, 
we only need to check the right and down side
left and above has been covered

Find a island part, add 4 line
Find a neighbor, remove 2 line

Time: O(mn)
Space: O(1)
*/
class Solution {
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){//只要找到一个岛岛，就 + 4，然后看他的右/下邻居
                    res += 4;
                    if(i + 1 < grid.length && grid[i + 1][j] == 1) res -= 2;//只要看一个邻居
                    if(j + 1 < grid[0].length && grid[i][j + 1] == 1) res -= 2;
                    
                }
            }
        }
        return res;
    }
}
/*
Facebook 变种
1. number of lake (surronded by land) 
Only count the points facing the sea(lake), do not count the land inside. 
For example,   land A is surrounded by land B, C, D, E (up, down, left, right). land A will not be counted in the final result.
The question is how many lake-facing lands the island have.

Time: O(mn)
Space: O(1)
*/
class Solution {
    public int islandPerimeter(int[][] grid) {
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        int res = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    if(i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1){
                        res++;
                        grid[i][j] = -1;
                    } 
                }else if(grid[i][j] == 0){//meet a lake, which is 0 (there are 1, -1, 0 for all)
                    for(int[] dir : directions){
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if(x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1){
                            res++;
                            grid[x][y] = -1;
                        }
                    }
                }
            }
        }
        return res;
    }
}
















/*
顺序规律：4 * island - 2 * neighbors edge
from top to down, left to right, 
we only need to check the right and down side
left and above has been covered

Find a island part, add 4 line
Find a neighbor, remove 2 line

Time: O(mn)
Space: O(1)
*/
class Solution {
    public int islandPerimeter(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int result = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    result += 4;
                    if(j + 1 < grid[0].length && grid[i][j + 1] == 1) result -= 2;
                    if(i + 1 < grid.length && grid[i + 1][j] == 1) result -= 2;
                }
            }
        }
        return result;
    }
}



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