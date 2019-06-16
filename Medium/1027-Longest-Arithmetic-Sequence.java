/*
MEDIUM
1027. Longest Arithmetic Sequence
*/
/*
DP + Map<Integer, Integer>[] dp
Map<distance, len>
map 的好处是将查找时间变为了 1
用 map 来对每个数记录他能提供的可选间隔（distance），以及该间隔目前为止的长度（len）

Time: O(n^2) = use map to make finding at O(1)
Space: O(n^2)

*/
class Solution {
    public int longestArithSeqLength(int[] A) {
        Map<Integer, Integer>[] dp = new HashMap[A.length];
        int res = 0;
        for(int i = 0; i < A.length; i++){
            dp[i] = new HashMap<Integer, Integer>();
            for(int j = 0; j < i; j++){
                int dis = A[i] - A[j];
                int len = dp[j].getOrDefault(dis, 1) + 1;
                dp[i].put(dis, len);
                res = Math.max(res, len);
            }
        }
        return res;
    }

}


/*
DP + List of Map
Map<distance, len>


Time: O(n^2) = use map to make finding at O(1)
Space: O(n^2)

*/
class Solution {
    public int longestArithSeqLength(int[] A) {
        List<Map<Integer, Integer>> list = new ArrayList<>();
        int res = 0;
        for(int i = 0; i < A.length; i++){
            list.add(new HashMap<Integer, Integer>());
            for(int j = 0; j < i; j++){
                int dis = A[i] - A[j];
                int len = list.get(j).containsKey(dis) ? list.get(j).get(dis) : 1;
                list.get(i).put(dis, len + 1);
                res = Math.max(res, len + 1);
            }
        }
        return res;
    }

}



/*
DP?
Time: O(n^3) = n^2 * findAS[O(n)]
Space: O(n^2)

*/
class Solution {
    public int longestArithSeqLength(int[] A) {
        int n = A.length;
        int[][] dis = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                dis[i][j] = A[j] - A[i];
            }
        }
        int res = 0;
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                res = Math.max(res, findAS(dis, j, dis[i][j], 2));
            }
        }
        return res;
    }
    private int findAS(int[][] dis, int i, int d, int len){
        //d: the distance between each num
        //len: len of the subsequence
        for(int j = i + 1; j < dis[0].length; j++){
            if(dis[i][j] == d){
                return findAS(dis, j, d, len + 1);
            }
        }
        return len;
        
    }
}