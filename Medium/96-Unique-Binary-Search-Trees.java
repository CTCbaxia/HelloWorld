/*
MEDIUM
96. Unique Binary Search Trees
https://leetcode.com/problems/unique-binary-search-trees/description/

TIME: 0826 - 1h
RESULT: 100% - 0ms
NOTES:
这其实就是个数学问题：每个遍历的 min 和 max 值不会影响结果，会影响的只是每次遍历的时候的 max - min 的可选节点数量
*/

/*
SOLUTION 0: DP
TIME: 0826 - 1h
RESULT: 100% - 0ms
THOUGHTS:
算以每个为 root 之后，其左右分支的不同 subtree 组合数，然后左右相乘
其实每个遍历的 min 和 max 值不会影响结果，会影响的只是每次遍历的时候的 max - min 的可选节点数量
这样可以只用一维 dp[]
*/
class Solution {
    public int numTrees(int n){
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < i; j++){
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

}