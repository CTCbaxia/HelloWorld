import java.io.*;
import java.util.*;
import java.math.*;
/*
Find the route from leftup most point to rightdown most point in a matrix
The route should have smallest sum


Solution:
Dynamic Programming: 找到到达每一个点的最短路径

*/
class Facebook_Route_In_Matrix_With_Least_Sum {
	public static void main(String[] args) {
		int[][] matrix = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
		System.out.println(optimalRoute(matrix));
	}
	public static int optimalRoute(int[][] matrix){
	    int m = matrix.length;
	    int n = matrix[0].length;
	    int[][] dp = new int[m][n];
	    for(int i = 0; i < m; i++){
	        for(int j = 0; j < n; j++){
	        	if(i - 1 >= 0 && j - 1 >= 0) dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
	        	else if(i - 1 >= 0) dp[i][j] = dp[i - 1][j] + matrix[i][j];
	        	else if(j - 1 >= 0) dp[i][j] = dp[i][j - 1] + matrix[i][j];
	        	else dp[i][j] = matrix[i][j];
	        }
	    }
	    return dp[m -1][n - 1];
	}



}