/*
M
1130. Minimum Cost Tree From Leaf Values
*/
/*
Find all possibility: Recursion + Dynamic Programming(save time)

Time: O(n^3)
Space: O(n^2)

dp[i][j] = from node i to j, get the smallest possible sumTree
dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + max[i][k] * max[k + 1][j]);
Space: 
*/
class Solution {

    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        int[][] max = new int[n][n];
        
        //find max num between i and j
        for(int i = 0; i < n; i++){
            int maxNum = arr[i];
            for(int j = i; j < n; j++){
                maxNum = Math.max(arr[j], maxNum);
                max[i][j] = maxNum;
            }
        }
        
    
        for(int i = n - 1; i >= 0; i--){
            for(int j = i; j < n; j++){
                dp[i][j] = Integer.MAX_VALUE;
                if(i == j) dp[i][j] = 0;//leat value = 0
                else{
                    for(int k = i; k < j; k++){
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + max[i][k] * max[k + 1][j]);
                    }                    
                }
            }
        }
        return dp[0][n - 1];
    }

}


/*
Find all possibility: Recursion + Dynamic Programming(save time)

Time:
T(n) = T(k) + T(n - k) = [T(1) + T(n - 1)] + [T(2) + T(n - 2)] + [T(3) + T(n - 3)] + ... + 2*T(n/2)
     = [T(1) + T(2) + T(3) + ... + T(n/2)] + [T(n/2) + ... + T(n - 2) + T(n - 1)]
     = T(1) + T(2) + ...+ T(n - 1)
     ???
Space: O(n^2)
*/
class Solution {
    public class SubTree{
        int nonLeafSum;
        int largestLeaf;
        public SubTree(int _nonLeafSum, int _largestLeaf){
            nonLeafSum = _nonLeafSum;
            largestLeaf = _largestLeaf;
        }
    }
    public int mctFromLeafValues(int[] arr) {
        SubTree[][] dp = new SubTree[arr.length][arr.length];
        SubTree res = buildSmallestSubTree(0, arr.length - 1, arr, dp);
        return res.nonLeafSum;
    }
    
    private SubTree buildSmallestSubTree(int start, int end, int[] arr, SubTree[][] dp){
        if(start == end) return new SubTree(0, arr[start]);
        
        int minNonLeafSum = Integer.MAX_VALUE;
        int maxLeaf = Integer.MIN_VALUE;
        for(int i = start; i <= end - 1; i++){
            if(dp[start][i] == null) dp[start][i] = buildSmallestSubTree(start, i, arr, dp);
            if(dp[i + 1][end] == null) dp[i + 1][end] = buildSmallestSubTree(i + 1, end, arr, dp);
            
            SubTree left = dp[start][i];
            SubTree right = dp[i + 1][end];
            int rootValue = left.largestLeaf * right.largestLeaf;
            int nonLeafSum = left.nonLeafSum + right.nonLeafSum + rootValue;
            
            minNonLeafSum = Math.min(minNonLeafSum, nonLeafSum);
            maxLeaf = Math.max(left.largestLeaf, right.largestLeaf);
        }
        return new SubTree(minNonLeafSum, maxLeaf);
    }
}



/*
TLE!!
Find all possibility: Recursion (Brute Force)

Time:
T(n) = T(k) + T(n - k) = [T(1) + T(n - 1)] + [T(2) + T(n - 2)] + [T(3) + T(n - 3)] + ... + 2*T(n/2)
     = [T(1) + T(2) + T(3) + ... + T(n/2)] + [T(n/2) + ... + T(n - 2) + T(n - 1)]
     = T(1) + T(2) + ...+ T(n - 1) 不知道怎么算
     
Space: O(1)
*/
class Solution {
    public class SubTree{
        int nonLeafSum;
        int largestLeaf;
        public SubTree(int _nonLeafSum, int _largestLeaf){
            nonLeafSum = _nonLeafSum;
            largestLeaf = _largestLeaf;
        }
    }
    public int mctFromLeafValues(int[] arr) {
        SubTree res = buildSmallestSubTree(0, arr.length - 1, arr);
        return res.nonLeafSum;
    }
    
    private SubTree buildSmallestSubTree(int start, int end, int[] arr){
        if(start == end) return new SubTree(0, arr[start]);
        
        int minNonLeafSum = Integer.MAX_VALUE;
        int maxLeaf = Integer.MIN_VALUE;
        for(int i = start; i <= end - 1; i++){
            SubTree left = buildSmallestSubTree(start, i, arr);
            SubTree right = buildSmallestSubTree(i + 1, end, arr);
            
            int rootValue = left.largestLeaf * right.largestLeaf;
            int nonLeafSum = left.nonLeafSum + right.nonLeafSum + rootValue;
            
            minNonLeafSum = Math.min(minNonLeafSum, nonLeafSum);
            maxLeaf = Math.max(left.largestLeaf, right.largestLeaf);
        }
        return new SubTree(minNonLeafSum, maxLeaf);
    }
}
