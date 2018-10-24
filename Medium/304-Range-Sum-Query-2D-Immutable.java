/*
MEDIUM
304. Range Sum Query 2D - Immutable

TIME: 1024 - 30min
RESULT: 70% - 79ms
NOTES:
关于几何计算（长方形，直线）要想到累积关系。尤其是长方形，面积就那一个套路
*/
/*
Caching Recs

Time: O(1) per query, O(mn) per construction
Space: O(m*n), matrix[m + 1][n + 1]
*/
//用大 1 个维度的 sum 省去 0 的比较
class NumMatrix {
    int[][] sum;
    
    public NumMatrix(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return;
        sum = new int[matrix.length + 1][matrix[0].length + 1];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                sum[i + 1][j + 1] = matrix[i][j] + sum[i][j + 1] + sum[i + 1][j] - sum[i][j];
            }
        }
        return;
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2 + 1][col2 + 1] - sum[row2 + 1][col1] - sum[row1][col2 + 1] + sum[row1][col1];
    }
}


class NumMatrix {
    int[][] sum;
    public NumMatrix(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return;
        sum = matrix;
        //横向相加得到每行的累计
        for(int i = 0; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                sum[i][j] += sum[i][j - 1];
            }
        }
        //纵向相加得到每个rec的累计
        for(int j = 0; j < matrix[0].length; j++){
            for(int i = 1; i < matrix.length; i++){
                sum[i][j] += sum[i - 1][j];
            }            
        }
        return;
        
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int rec1 = (col1 - 1) >= 0 ? sum[row2][col1 - 1] : 0;
        int rec2 = (row1 - 1) >= 0 ? sum[row1 - 1][col2] : 0;
        return sum[row2][col2] - rec1 - rec2 + ((row1 - 1  >= 0 && col1 - 1 >= 0) ? sum[row1 - 1][col1 - 1] : 0);
    }
}




/*
Caching rows

Time: O(m) per query, O(mn) per construction
Space: O(m*n), matrix[m][n]
*/
class NumMatrix {
    int[][] sum;
    public NumMatrix(int[][] matrix) {
        sum = matrix;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                sum[i][j] += sum[i][j - 1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int region = 0;
        for(int i = row1; i <= row2; i++){
            region += sum[i][col2] - ((col1 - 1 >= 0) ? sum[i][col1 - 1] : 0);
        }
        return region;
    }
}