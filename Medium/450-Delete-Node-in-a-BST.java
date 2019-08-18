/*
M
450. Delete Node in a BST
*/
/*
Recursion
recursion to find the target, 
Find the smaller node (X) and assign the value to the target,
recursion to delete the new target (the smaller node X)

Time: O(logn)
Space: O(logn)
*/
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val < key){
            root.right = deleteNode(root.right, key);
        }else if(root.val > key){
            root.left = deleteNode(root.left, key);
        }else{
            //find the target
            TreeNode small = findSmaller(root.left);
            
            if(small == null) return root.right;
            else{
                root.val = small.val;
                root.left = deleteNode(root.left, small.val);
                //不能直接 small = deleteNode(small, small.val), 这样会直接重新给 small 赋值，而不是引用
            }
        }
        return root;
        
    }
    private TreeNode findSmaller(TreeNode node){
        if(node == null) return null;
        
        while(node.right != null){
            node = node.right;
        }
        return node;
    }
}
