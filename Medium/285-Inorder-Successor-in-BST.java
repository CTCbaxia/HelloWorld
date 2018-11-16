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
2. target has right child --- should be its right child's leftmost child

Keep the result updated while traverse the tree

parent: update before move down
children: update after move down but make sure it is not null

Time: O(logn)
Space: O(1)
 */
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        
        //find the target
        while(root != null && root.val !=  p.val){
            if(root.val < p.val) root = root.right;
            else if(root.val > p.val){
                res = root;//keep track of parent of the pending target
                root = root.left;
            }
        }
        root = root.right;
        while(root != null){
            res = root;
            root = root.left;
        }
        return res;
    }
}