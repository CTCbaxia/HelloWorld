/*
M
156. Binary Tree Upside Down
*/
/*
Recursion
upside down the smallest subtree, the root for the subtree is the root for the new tree

Time: O(n)
Space: O(1)
*/
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null || root.left == null) return root;
        
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }
}
