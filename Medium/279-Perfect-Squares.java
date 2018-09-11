/*
MEDIUM
279. Perfect Squares
https://leetcode.com/problems/perfect-squares/description/

TIME: 0909 - 30min
RESULT: 32% - 50ms
METHOD: 
    - DP
    - BFS
*/
/*
SOLUTIONS REFERENCE: DP
TIME: 0909 - 30min
RESULT: 32% - 50ms
*/
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for(int i = 1; i <= n; i++){
            int min = Integer.MAX_VALUE;
            int j = 1;
            while(i - j*j >= 0){
                min = Math.min(min, dp[i - j*j] + 1);
                j++;
            }
            dp[i] = min;
        }
        return dp[n];
    }
}

/*
SOLUTIONS REFERENCE: BFS
TIME: 0909 - 30min
RESULT: 19% - 84ms
THOUGHTS: 
每一次都只存一排的结果(在上一层分别 minus 1，4，9，16...)之后。
最快得到结果的就是最短路径。
*/
class Solution {
    public int numSquares(int n) {
        if(n == 0) return 0;
        int k = 1;
        List<Integer> squares = new ArrayList<Integer>();
        while(k*k <= n){
            squares.add(k*k);
            k++;
        }
        Queue<Integer> q = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        int res = 0;
        q.offer(n);
        while(n > 0){

            int size = q.size();
            for(int i = 0; i < size; i++){
                int cur = q.poll();//get one in this level
                for(int j = 0; j < squares.size(); j++){
                    int num = cur - squares.get(j);
                    if(num < 0) break;
                    if(num == 0) return res + 1;
                    else if(num > 0 && !visited.contains(num)){
                        q.offer(num);
                        visited.add(num);
                    }
                }
                
            }
            res++;
        }
        return res;
    }
}