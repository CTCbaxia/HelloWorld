/*
0705

73. Set Matrix Zeroes

RESULT: 93%
*/

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