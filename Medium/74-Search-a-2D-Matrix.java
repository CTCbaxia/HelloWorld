/*
MEDIUM
74. Search a 2D Matrix
https://leetcode.com/problems/search-a-2d-matrix/description/

TIME: 0902 - 40min
RESULT: 100% - 5ms
NOTES:
1. 注意范围：[[]] - matrix.length = 1 but matrix[0].length = 0
2. 按照线性顺序找到矩阵内的元素： matrix[midIndex / n][midIndex % n];(n = column number)
*/
/*
Binary Search:
in total in order, just need to find the corresponding position: matrix[mid / n][mid % n];

Time: O(logmn)
Space: O(1)

Notes:
while(lo <= hi)
    lo = mid + 1
    hi = mid - 1
适合找到某个 target
这个模式比较了所有可能的元素，如果有对应值就弹出
没有就直到最后
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target){
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length; 
        int n = matrix[0].length;
        int lo = 0, hi = m * n - 1;

        while(lo <= hi){
            int mid = lo + (hi - lo)/2;
            int x = mid / n;
            int y = mid % n;
            if(matrix[x][y] == target) return true;

            if(matrix[x][y] < target){
                lo = mid + 1;
            }else{
                hi = mid - 1;
            }
        }
        return false;
    }
}





/*
Binary Search:
in total in order, just need to find the corresponding position: matrix[mid / n][mid % n];

Time: O(logmn)
Space: O(1)

Notes:
while(lo <= hi)
    lo = mid + 1
    hi = mid - 1
适合找到某个 target
这个模式比较了所有可能的元素，如果有对应值就弹出
没有就直到最后
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int lo = 0;
        int hi = m * n - 1;
        
        while(lo <= hi){//if while(lo < hi)
            int mid = lo + (hi - lo)/2;
            int num = matrix[mid / n][mid % n];
            if(num < target) lo = mid + 1;
            else if(num > target) hi = mid - 1;
            else return true;
        }
        return false;//then here need to be return matrix[lo / n][lo % n] == target
    }
}










/*
SOLUTION 0: 双向二分法
TIME: 0902 - 30min
RESULT: 100% - 5ms
THOUGHTS:
先二分法找出区间范围（注意大小的判断有一点 tricky），然后在该 row 里面寻找 target。
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0) return false;
        int n = matrix[0].length;
        if(n == 0) return false;
        
        if(matrix[0][0] > target || matrix[m - 1][n - 1] < target) return false;
        
        //find the row
        int lo = 0, hi = m - 1;
        while(lo < hi){
            int mid = (lo + hi) / 2;
            if(matrix[mid][0] < target) lo = mid;
            else if(matrix[mid][0] > target) hi = mid - 1;
            else return true;
            
            if(hi - lo == 1){
                if(matrix[hi][0] > target)  hi--;
                else  lo++;
            }else mid = (lo + hi) / 2;
        }
        //check the target
        int left = 0, right = n - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(matrix[lo][mid] < target) left = mid + 1;
            else if(matrix[lo][mid] > target) right = mid - 1;
            else return true;
        }
        return false;
    }
}
/*
SOLUTION REFERENCE: 一维二分法
TIME: 0902 - 10min
RESULT: 95% - 6ms
THOUGHTS:
通过几何运算，把矩阵变换成一个一维顺序链表，然后二分
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0) return false;
        int n = matrix[0].length;
        if(n == 0) return false;
        
        if(matrix[0][0] > target || matrix[m - 1][n - 1] < target) return false;
        
        int lo = 0, hi = m * n - 1;
        while(lo <= hi){
            int midIndex = (lo + hi) / 2;
            int mid = matrix[midIndex / n][midIndex % n];

            if(target > mid) lo = midIndex + 1;
            else if(target < mid) hi = midIndex - 1;
            else return true;
        }
        return false;
    }
}