/*
MEDIUM
285. Inorder Successor in BST

TIME: 
RESULT: 
NOTES:
*/
/*
(1. Inorder traversal - O(n), O(logn), but it does NOT take the advantage of BST)
2. When we turn left, we record the node, the result should be
    either the closet record to the target
    or the leftmost children of the p's right child
    
    To implement:
    - can use Stack
    - or better, to use TreeNode record the closet right parent

Time: O(logn)
Space: O(1)
*/
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while(root != null){
            if(root.val > p.val){
                res = root;
                root = root.left;
            }else if(root.val < p.val){
                root = root.right;
            }else break;
        }
        root = root.right;//check it p has right child
        while(root != null){//go all the way to the left
            res = root;
            root = root.left;
        }
        return res;
    }
}

/*
How about predecessor?
*/
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while(root != null){
            if(root.val > p.val){
                root = root.left;
            }else if(root.val < p.val){
                res = root;
                root = root.right;
            }else break;
        }
        root = root.left;//check it p has left child
        while(root != null){//go all the way to the right
            res = root;
            root = root.right;
        }
        return res;
    }
}

/*
Two conditions:
1. target has no right child --- should be its larger parent
2. target has right child --- should be its right child's leftmost child

Keep track of the larger parent, 
if target has right child, find all the way to the left

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