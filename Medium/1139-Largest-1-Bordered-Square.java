/*
M
1139. Largest 1-Bordered Square
*/
/*
**A better way to save extra step**
Preprocess the grid using one pass and Record the longest top and left length with continuous 1
Then check for each cell to find the largest possible square

Time: O(n^3)
Space: O(n^2)
*/
class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] left = new int[m][n];
        int[][] top = new int[m][n];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    left[i][j] = j > 0 ? left[i][j - 1] + 1 : 1;
                    top[i][j] = i > 0 ? top[i - 1][j] + 1 : 1;
                }
            }
        }
        int maxLen = 0;
        // can also go from topleft to downright
        // but better to always find the largest possible square first
        for(int i = m - 1; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                int len = Math.min(left[i][j], top[i][j]);
                while(len > maxLen){//pruning, no need to loop for this cell if it cannot exceed max
                    int diff = len - 1;
                    int topX = i - diff;
                    int leftY = j - diff;
                    if(left[topX][j] >= len && top[i][leftY] >= len) maxLen = len;//won't go into next loop
                    else len--;
                }
            }
        }
        return maxLen * maxLen;
    }
}


/*
Preprocess the grid using one pass and Record the longest top and left length with continuous 1
Then check for each cell to find the largest possible square

Time: O(n^3)
Space: O(n^2)
*/
class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] left = new int[m][n];
        int[][] top = new int[m][n];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    left[i][j] = j > 0 ? left[i][j - 1] + 1 : 1;
                    top[i][j] = i > 0 ? top[i - 1][j] + 1 : 1;
                }
            }
        }
        int maxLen = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int len = Math.min(left[i][j], top[i][j]);
                if(len <= maxLen) continue;//pruning, no need to loop for this cell if it cannot exceed max
                for(int k = 0; k < len; k++){
                    int topX = i - k;
                    int leftY = j - k;
                    if(left[topX][j] >= k + 1 && top[i][leftY] >= k + 1) maxLen = Math.max(maxLen, (k + 1));
                }
            }
        }
        return maxLen * maxLen;
    }
}

//Better way to check (no influence on Time Complexity on worst case)
