/*
M
298. Binary Tree Longest Consecutive Sequence
*/
/*
Recursion + Passing value

Time: O(n)
Space: O(1)  |  O(logn) for stack
*/
class Solution {
    int res;
    public int longestConsecutive(TreeNode root) {
        res = 0;
        dfs(root, 0, null);
        return res;
    }
    private void dfs(TreeNode node, int curLen, TreeNode parent){
        if(node == null) return;
        if(parent == null || node.val != parent.val + 1){
            curLen = 1;
        }else{
            curLen++;
        }
        res = Math.max(res, curLen);
        dfs(node.left, curLen, node);
        dfs(node.right, curLen, node);
    }
    
}

/*
Recursion + Passing value

Time: O(n)
Space: O(1)  |  O(logn) for stack
*/
class Solution {
    int res;
    public int longestConsecutive(TreeNode root) {
        res = 0;
        if(root == null) return res;
        dfs(root, 0, root.val);
        return res;
    }
    private void dfs(TreeNode node, int curLen, int target){
        if(node == null) return;
        if(node.val == target){
            curLen++;
        }else{
            curLen = 1;
        }
        res = Math.max(res, curLen);
        dfs(node.left, curLen, node.val + 1);
        dfs(node.right, curLen, node.val + 1);
    }
    
}
