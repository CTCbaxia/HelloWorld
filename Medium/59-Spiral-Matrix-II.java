/*
59. Spiral Matrix II
*/
/*
四个边界控制 start end，注意 termination

Time: O(n^2)
Space: O(1)
*/

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int num = 1;
        int rowStart = 0, rowEnd = n - 1, colStart = 0, colEnd = n - 1;
        while(num <= n * n){
            for(int j = colStart; j <= colEnd; j++){
                res[rowStart][j] = num++;
            }
            rowStart++;
            
            for(int i = rowStart; i <= rowEnd; i++){
                res[i][colEnd] = num++;
            }
            colEnd--;
            
            for(int j = colEnd; j >= colStart; j--){
                res[rowEnd][j] = num++;
            }
            rowEnd--;
            
            for(int i = rowEnd; i >= rowStart; i--){
                res[i][colStart] = num++;
            }
            colStart++;
            
        }
        return res;
    }
}





/*
四个边界控制，注意 termination

Time: O(n^2)
Space: O(1)
*/

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int num = 1;
        int rowStart = 0, rowEnd = n - 1, colStart = 0, colEnd = n - 1;
        while(num <= n * n){
            for(int j = colStart; j < colEnd; j++){
                res[rowStart][j] = num++;
            }
            rowStart++;
            
            for(int i = rowStart; i < rowEnd; i++){
                System.out.println(i + " "+num);
                res[i][colEnd] = num++;
                System.out.println("here:" + (i+1));
            }
            colEnd--;
            
            for(int j = colEnd; j > colStart; j--){
                res[rowEnd][j] = num++;
            }
            rowEnd--;
            
            for(int i = rowEnd; i > rowStart; i--){
                res[i][colStart] = num++;
            }
            colStart++;
            
        }
        return res;
    }
}




/*
四个边界控制，注意 termination
square 里面最后一个点无法到达，得在外面赋值

Time: O(n^2)
Space: O(1)
*/

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int num = 1, i = 0, j = 0;
        int rowMin = 1, rowMax = n - 1, colMin = 0, colMax = n - 1;
        while(num < n * n){
            while(j < colMax){
                res[i][j++] = num++;
            }
            colMax--;
            
            while(i < rowMax){
                res[i++][j] = num++;
            }
            rowMax--;
            
            while(j > colMin){
                res[i][j--] = num++;
            }
            colMin++;
            
            while(i > rowMin){
                res[i--][j] = num++;
            }
            rowMin++;
        }
        res[i][j] = num;//最后一个在循环里是永远无法到达的
        return res;
    }
}