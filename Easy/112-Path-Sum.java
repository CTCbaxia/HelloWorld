/*
EASY
112. Path Sum

*/
/*
DFS

Time: O(n)
Space: O(logn)
*/
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null) return sum == root.val;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}



/*
DFS

Time: O(n)
Space: O(logn)
*/
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, 0, sum);
    }
    private boolean dfs(TreeNode node, int preSum, int sum){
        if(node == null) return false;
        preSum += node.val;
        
        if(node.left == null && node.right == null) return preSum == sum;
        return dfs(node.left, preSum, sum) || dfs(node.right, preSum, sum);
    }
}