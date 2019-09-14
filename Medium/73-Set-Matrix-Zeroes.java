/*
0705

73. Set Matrix Zeroes

TIME: 0705
RESULT: 93%
*/
/*
Mark row and col using the first element in that row and col
***be careful, use firstRow and firstCol to mark matrix[0][0]
***be careful, assign 0 for first row and col in the end after all other points. Otherwise, you change the value for the first line and will mislead you later

thinking process:
1. use hashset to store all 0s
2. use row array and col array to mark 0
3. use matrix[i][0] and matrix[0][j] to mark row and col

Time: O(mn)
Space: O(1)
*/
class Solution {
    public void setZeroes(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return;
        
        boolean firstRow = false, firstCol = false;
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    if(i == 0) firstRow = true;
                    else matrix[i][0] = 0;
                    
                    if(j == 0) firstCol = true;
                    else matrix[0][j] = 0;
                }
            }
        }
        //set zeroes
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){//check row and col at the same time
                    matrix[i][j] = 0;
                }
            }
        }

        //first row and col
        if(firstRow){
            for(int j = 0; j < n; j++) matrix[0][j] = 0;
        }
        if(firstCol){
            for(int i = 0; i < m; i++) matrix[i][0] = 0;
        }
        return;
    }
}

/*
Marker - intuitive 一些

Time: O(mn)
Space: O(1)
*/
class Solution {
    public void setZeroes(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return;
        
        boolean firstRow = false, firstCol = false;
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    if(i == 0) firstRow = true;
                    else matrix[i][0] = 0;
                    
                    if(j == 0) firstCol = true;
                    else matrix[0][j] = 0;
                }
            }
        }
        //这块可以直接合并
        //set zeroes
        for(int i = 1; i < m; i++){
            if(matrix[i][0] == 0)
                for(int j = 1; j < n; j++){
                    matrix[i][j] = 0;
                }
        }
        //set zeroes
        for(int j = 1; j < n; j++){
            if(matrix[0][j] == 0)
                for(int i = 1; i < m; i++){
                    matrix[i][j] = 0;
                }
        }

        //first row and col
        if(firstRow){
            for(int j = 0; j < n; j++) matrix[0][j] = 0;
        }
        if(firstCol){
            for(int i = 0; i < m; i++) matrix[i][0] = 0;
        }
        return;
    }
}


/*
Two HashSet - 差一些的解法

Time: O(n^2)
Space: O(n + m)
*/
class Solution {
    public void setZeroes(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return;
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for(int i : rows){
            for(int j = 0; j < n; j++) matrix[i][j] = 0;
        }
        for(int j : cols){
            for(int i = 0; i < m; i++) matrix[i][j] = 0;
        }
        
        return;
    }
}


/*
Marker for row and col: for each row and col, mark if it should be all 0
1) standard: use extra boolean[] for each row and col
2) better: use first line of row and col as a mark: ex matrix[i][0] = 0; matrix[0][j] = 0;
也就是我们要将mark存在不会影响我们后续遍历的格子里

后续赋值的时候，要先对 first row and first col 之外赋值
Time: O(mn)
Space: O(1)
*/
class Solution {
    public void setZeroes(int[][] matrix){
        if(matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;

        //mark for each row and col
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        //set for rows and cols except first line
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][0] == 0|| matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        //first line
        if(matrix[0][0] == 0){
            for(int i = 0; i < m; i++) matrix[i][0] = 0;
            for(int j = 0; j < n; j++) matrix[0][j] = 0;
        }
        return;
    }

}







/*
Marker for row and col: for each row and col, mark if it should be all 0
1) standard: use extra boolean[] for each row and col
2) better: use first line of row and col as a mark: ex matrix[i][0] = 0; matrix[0][j] = 0;
也就是我们要将mark存在不会影响我们后续遍历的格子里

**[0,0] 的双向性： 需要 firstCol 来区分first col, 
matirx[0,0] == 0 表示 i = 0 这一行都是 0
firstCol == 0，才表示 j = 0 这一列都是 0
后续赋值的时候，要先对 first row and first col 之外赋值,然后根据 matrix[0][0] 和 firstCol 来赋值首行首列

Time: O(mn)
Space: O(1)
*/
class Solution {
    public void setZeroes(int[][] matrix){
        if(matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        int firstCol = 1;//since [0,0] both represent row and line, we need another flag

        //mark for each row and col
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    if(j == 0) firstCol = 0;//如果 j = 0，要单独标记第一列
                    else matrix[0][j] = 0;
                }
            }
        }
        //set for rows and cols except first line
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][0] == 0|| matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        //first line
        if(matrix[0][0] == 0) for(int j = 0; j < n; j++) matrix[0][j] = 0;
        if(firstCol == 0) for(int i = 0; i < m; i++) matrix[i][0] = 0;
        return;
    }

}






class Solution {
    public void setZeroes(int[][] matrix) {

        HashSet<Integer> row = new HashSet<Integer>();
        HashSet<Integer> column = new HashSet<Integer>();
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    //make row = 0, log column
                    if(!row.contains(i)){
                        row.add(i);
                    }
                    if(!column.contains(j)){
                        column.add(j);
                    }
                }
            }
        }
        for(int i: row){
            for(int j = 0; j < matrix[0].length; j++){
                matrix[i][j] = 0;
            }
        }
        for(int j: column){
            for(int i = 0; i < matrix.length; i++ ){
                matrix[i][j] = 0;
            }
        }
    }
}