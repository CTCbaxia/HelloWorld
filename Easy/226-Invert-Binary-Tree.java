/*
EASY
226. Invert Binary Tree

TIME: 
RESULT: 
NOTES:
*/
/*
先recurively reverse left and right
再交换左右left and right subtree

Time: O(n) only traverse once
Space: O(1)
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}