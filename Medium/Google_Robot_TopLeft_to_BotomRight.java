/*
MEDIUM
Google_Robot_TopLeft_to_BotomRight
题目：
机器人从 matrix 的左上到右下
每个 point 可以走右上，右，右下
求路径数

*/
/*
Dynamic Programming: 2D Array

dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1] + dp[i + 1][j - 1]
Time: O(mn)
Space: O(mn)
*/
class Google_Robot_TopLeft_to_BotomRight {
    public int uniquePath(int m, int n) {

        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int j = 0; j < n; j++){
        	for(int i = 0; i < m; i++){
                if(j == 0) continue;

                if(i > 0) dp[i][j] += dp[i - 1][j - 1];
                dp[i][j] += dp[i][j - 1];
                if(i < m - 1) dp[i][j] += dp[i + 1][j - 1];
        	}
        }
        return dp[m - 1][n - 1];
    }
}

test case:
[
    1, 1, 2
    0, 1, 2
    0, 0, 1
]
m = 3
n = 3
i = 0
j = 2


/*
Follow up 1: 仍是从左上到右下，优化空间
Dynamic Programming: 1D Array

dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1] + dp[i + 1][j - 1]

其实只取决于左边一列的数据:

cur[i]=pre[i]+pre[i-1]+pre[i+1]

Time: O(mn)
Space: O(m)
*/
class Google_Robot_TopLeft_to_BotomRight {
    public int uniquePath1(int m, int n) {
        
        int[] pre = new int[m];//该列每一行的值
        pre[0] = 1;

        for(int j = 1; j < n; j++){
            int[] cur = new int[m];
            for(int i = 0; i < m; i++){
                if(i > 0) cur[i] += pre[i - 1];
                cur[i] += pre[i];
                if(i < m - 1) cur[i] += pre[i + 1];
            }
            pre = cur;//BFS 更新到最新列值
        }
        return pre[m - 1];
    }
}

/*
Follow up 2: 给定矩形里的三个点，判断是否存在遍历这三个点的路径

Solution 1:
遍历整个 matrix，在每一列 check 该列需要 reach 的点有没有可能被 reach 到

Time: O(mn)
Space: O(m + n)
*/
import java.util.*;
class Google_Robot_TopLeft_to_BotomRight {
    public static void main(String[] args) {
      int[][] points = {{2,1},{0,2}};
      System.out.println(uniquePath2(3, 3, points));
   }
    public static boolean uniquePath2(int m, int n, int[][] points) {
        int[] reach = new int[n];
        Arrays.fill(reach, -1);
        for(int[] point : points){
            int col = point[1];
            if(reach[col] != -1) return false;
            else reach[col] = point[0];
        }

        int[] pre = new int[m];//该列每一行的值
        pre[0] = 1;

        for(int j = 0; j < n; j++){
            int[] cur = new int[m];
            for(int i = 0; i < m; i++){
                if(j == 0) continue;

                if(i > 0) cur[i] += pre[i - 1];
                cur[i] += pre[i];
                if(i < m - 1) cur[i] += pre[i + 1];

            }
            if(reach[j] != -1){//there is point needed to be reached in this col
                int row = reach[j];
                if(cur[row] == 0) return false;
            }
            pre = cur;//BFS 更新到最新列值
        }
        return true;        
    }
}
/*
TEST:
input:
m = 3
n = 3
point = 
[
    [2, 1],
    [0, 2]
]

output:
reach = [-1, 2, 0]
pre = [1, 0, 0]
cur = [1, 0, 0]
j = 1
i = 0
row = 2
*/




/*
Follow up 2: 给定矩形里的三个点，判断是否存在遍历这三个点的路径

Solution 1:
只遍历给定的需要 reach 的点，看看这些点相互之间能不能 reach
判断一个点是否可以 reach 另一个点，就看另一个点在不在这个点的射程范围之内
所以将给定点按照列从小到大排序
如果有一列有多个需要 reach 的点，则 false

Time: O(nlogn) n is number of points
Space: O(n)
*/
class Google_Robot_TopLeft_to_BotomRight {
    public boolean uniquePath2_2(int[][] points) {
        List<int[]> list = new ArrayList<>();
        for(int[] point : points){
            list.add(point);
        }
        Collections.sort(list, new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2){
                return i1[1] - i2[1];
            }
        });

        //reach
        for(int i = 1; i < list.size(); i++){
            int[] pre = list.get(i - 1);
            int[] cur = list.get(i);
            if(pre[1] == cur[1]) return false;//more than 1 point in 1 col
            int distance = cur[1] - pre[1];
            if(pre[0] - distance <= cur[0] && pre[0] + distance >= cur[0]) continue;
            else return false;
        }
        return true;
    }
}


/*
Follow up 3: 给定矩形里的三个点，找到遍历这三个点的所有路径数量

对于每一列更新，如果该列有需要 reach 的点，则除了该点，所有的 point 值都为 0

Time: O(mn)
Space: O(m)

*/
import java.util.*;
class Google_Robot_TopLeft_to_BotomRight {
    public static void main(String[] args) {
      int[][] points = {{2,1},{0,2}};
      System.out.println(uniquePath2(3, 3, points));
   }
    public static int uniquePath3(int m, int n, int[][] points) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] point : points){
            if(map.containsKey(point[1])) return 0;
            else map.put(point[1], point[0]);
        }

        int res = 0;
        int[] pre = new int[n];
        for(int j = 1; j < n; j++){
            int[] cur = new int[n];
            for(int i = 0; i < m; i++){
                if(j == 0) continue;
                cur[i] += i - 1 >= 0 ? pre[i - 1] : 0;
                cur[i] += pre[i];
                cur[i] += i + 1 < m ? pre[i + 1] : 0;
            }
            if(map.containsKey(j)){//there is point in this col need to be reached
                int row = map.get(j);
                for(int k = 0; k < m; k++){
                    if(k != row) cur[k] = 0;
                    // else res = cur[k];
                }
            }
            pre = cur;
        }
        return pre[m - 1];
    }
}




/*
Follow up 4: 给定一个下界 (x == H)，找到能经过给定下界的所有从左上到右上的路径数量 (x >= H)

先找到矩阵里面所有左上 -> 右上的路径
再找到矩阵里 x > H 范围内所有左上 -> 右上的路径
*/

class Google_Robot_TopLeft_to_TopRight{
    public int uniquePath4(int m, int n, int h){
        int totalM = uniquePath1(m, n);
        int totalH = uniquePath1(h, n);
        return totalM - totalH;
    }
    public int uniquePath1(int m, int n) {
        
        int[] pre = new int[m];//该列每一行的值
        pre[0] = 1;

        for(int j = 1; j < n; j++){
            int[] cur = new int[m];
            for(int i = 0; i < m; i++){
                if(i > 0) cur[i] += pre[i - 1];
                cur[i] += pre[i];
                if(i < m - 1) cur[i] += pre[i + 1];
            }
            pre = cur;//BFS 更新到最新列值
        }
        return pre[0];
    }

}




