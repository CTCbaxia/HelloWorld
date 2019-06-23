/*
EASY
897. Increasing Order Search Tree

*/
/*
Recursion: 

Time: O(n)
Space: O(logn)
*/
class Solution {
    TreeNode parent;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummy = new TreeNode(0);//dummy root to held the parent of the result
        parent = dummy;
        buildTree(root);
        return dummy.right;
        
    }
    private void buildTree(TreeNode node){
        if(node == null) return;
        
        buildTree(node.left);
        
        parent.right = node;
        node.left = null;//clean the left node
        parent = node;
        
        buildTree(node.right);
        return;
    }
}