/*
MEDIUM
1008. Construct Binary Search Tree from Preorder Traversal

*/
/*
Recursion + Range Limit => One Pass
use a global variable to record the index, and set the left - right range for every subTree

Time: O(n)
Space: O(n)
*/
class Solution {
    int i = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return buildTree(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private TreeNode buildTree(int[] preorder, int l, int r){
        if(i >= preorder.length) return null;
        if(preorder[i] < l || preorder[i] > r) return null;
        
        TreeNode node = new TreeNode(preorder[i]);
        i++;
        if(i < preorder.length){
            node.left = buildTree(preorder, l, node.val);
        } 
        if(i < preorder.length){
            node.right = buildTree(preorder, node.val, r);
        }
        return node;
    }
}