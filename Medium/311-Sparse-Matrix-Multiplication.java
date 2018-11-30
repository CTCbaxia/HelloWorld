/*
MEDIUM
311. Sparse Matrix Multiplication

TIME: 
RESULT: 78% - 50ms
NOTES:

*/
/*
Smart Solution for sparse matrix: 分次算完result[i][j]

Time:O(ml * (percentage of 0) * n)
Space: O(1)
*/
class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        if(A.length == 0 || A[0].length == 0) return new int[0][0];
        int m = A.length;
        int l = A[0].length;//Also B.length()
        int n = B[0].length;
        int[][] C = new int[m][n];

        for(int i = 0; i < m; i++){
            for(int k = 0; k < l; k++){
                if(A[i][k] != 0){//因为是 sparse matrix，A 里面有很多 0 元素不需要在 B 里面遍历一遍 n 次
                    for(int j = 0; j < n; j++){
                        if(B[k][j] != 0){//对于 B = 0 的情况也是，可以减少计算
                            C[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            }
        }
        return C;
    }
}


/*
Naive Solution: 一次性算完result[i][j]

Time:O(mnl)
Space: O(1)
*/
class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        if(A.length == 0 || A[0].length == 0 || B.length == 0 || B[0].length == 0) return new int[0][0];
        
        int m = A.length;
        int n = B[0].length;
        int l = A[0].length;//assume A[0].length == B.length
        int[][] result = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < l; k++){
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }
}