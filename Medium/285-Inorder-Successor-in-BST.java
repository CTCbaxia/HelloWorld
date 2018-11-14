/*
MEDIUM
285. Inorder Successor in BST

TIME: 
RESULT: 
NOTES:
*/
/*
Two conditions:
1. target has no right child --- should be its larger parent
2. target has right child --- should be its right smallest children (leftmost)

Keep the result updated while traverse the tree

parent: update before move down
children: update after move down but make sure it is not null

Time: O(logn)
Space: O(1)
 */
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null) return null;
        
        TreeNode res = null;
        TreeNode node = root;
        //find the target and keep track of pending result
        while(node != null && node.val != p.val){
            if(p.val < node.val){
                res = node;//update the parent that is larger than p (make closer)
                node = node.left;
            }else if(p.val > node.val){
                node = node.right;
            } 
        }
        
        //if node (target) has right child
        if(node.right != null){
            node = node.right;
            while(node != null){
                res = node;
                node = node.left;
            }
        }
        return res;
    }
}