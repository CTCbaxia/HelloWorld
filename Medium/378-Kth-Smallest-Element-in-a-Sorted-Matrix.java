/*
MEDIUM
378. Kth Smallest Element in a Sorted Matrix

TIME: 
RESULT: 
NOTES:
*/

/*
binary search

Time: O(n*logX) X=max-min
first assign lo to be min, hi to be max, then we do binary search, total log(max - min) times

each time, count how many point is less or equal than current mid
since each row and column is sorted, we can do this in O(n) time by 
starting from left bottom corner
if val <= mid, done with this column, j++;(the count for this column is i + 1)
else i--;
until we reach right up corner, since we never go back, total step is O(n)

and back in the main method, we hold the pending point(mid) that satisfy the answer using hi = mid, 
but we still don't know whether the mid exist in the matrix
but it is clear that lo <= answer <= pending point(mid) <= hi
so we make lo and hi closer till meet lo == hi
return lo
*/

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        
        //get the range of the matrix
        int lo = matrix[0][0];
        int hi = matrix[matrix.length - 1][matrix[0].length - 1];
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            int count = getLessOrEqual(matrix, mid);//先 guess 一个可能的结果，然后 getLessOrEqual 看看这个是否满足条件
            if(count < k) lo = mid + 1;
            else hi = mid;//就相当于 hi 肯定是会 hold 住那个满足条件的 "可能的" 结果的, 然后就等 lo 慢慢逼近，直到他俩等于一个值
        }
        //lo <= answer for k <= mid <= hi (当 lo, hi 慢慢逼近，最终 answer for k 就是 mid)
        return lo;
    }
    private int getLessOrEqual(int[][] matrix, int target){//小于等于 pending target 的数量
        int res = 0;
        int n = matrix.length;
        int i = n - 1, j = 0;
        while(i >= 0 && j <= n - 1){
            if(matrix[i][j] > target){
                i--;
            }else if(matrix[i][j] <= target){
                res += i + 1;
                j++;
            }
        }
        return res;
    }
}



/*
PriorityQueue

Time: O(n + logk)
1. put the first line into pq and the elements in pq is the smallest one in each column

2. do the following
	and poll the smallest in pq (that would be the smallest for the matrix)
	and push the next element to the pop element in that column (pq still holds smallest for each column)

3. untill we pop (k - 1) smallest, we got the kth smallest on the top

this method only need to make sure the column is in order (rows don't have to be)
*/
//用构造函数实现
class Solution {
    public class pointVal{
        int x;
        int y; 
        int val;
        public pointVal(int x, int y, int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        PriorityQueue<pointVal> pq = new PriorityQueue<pointVal>(new Comparator<pointVal>(){
            public int compare(pointVal p1, pointVal p2){
                return p1.val - p2.val;
            }
        });

        for(int j = 0; j < matrix[0].length; j++){// put the smallest element for each column into pq
            pq.offer(new pointVal(0, j, matrix[0][j]));
        }
        
        //pop the smallest element in pq
        for(int i = 0; i < k - 1; i++){
            pointVal p = pq.poll();// smallest element in pq
            if(p.x == matrix.length - 1) continue;//if the end of that column, we don't add more
            else{
                pq.offer(new pointVal(p.x + 1, p.y, matrix[p.x + 1][p.y]));
            }
        }
        return pq.poll().val;
    }
}



//用int[]{x, y, matrix[x][y]} 实现
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] p1, int[] p2){
                return p1[2] - p2[2];
            }
        });
        for(int j = 0; j < matrix[0].length; j++){
            pq.offer(new int[]{0, j, matrix[0][j]});
        }
        
        //pop the smallest element in pq
        for(int i = 0; i < k - 1; i++){
            int[] point = pq.poll();// smallest element in pq
            if(point[0] == matrix.length - 1) continue;//if the end of that column, we don't add more
            else{
                int x = point[0];
                int y = point[1];
                pq.offer(new int[]{x + 1, y, matrix[x + 1][y]});
            }
        }
        return pq.poll()[2];
    }
}