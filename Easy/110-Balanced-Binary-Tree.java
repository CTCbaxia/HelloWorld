/*
EASY
110. Balanced Binary Tree

TIME: 
RESULT: 
NOTES:

*/
/*
One Traverse: (返回的 depth 里面多存一个值，减少一遍 traverse)
Get the right and left depth, at the same time, return whether it is balanced

Time: O(n)
Space: O(d)
*/
class Solution {
    public boolean isBalanced(TreeNode root) {
        return depth(root, 0) == -1 ? false : true;
    }
    private int depth(TreeNode node, int len){
        if(node == null) return len;
        
        int left = depth(node.left, len + 1);//check if left is balanced and get depth
        if(left == -1) return -1;
        
        int right = depth(node.right, len + 1);//check if left is balanced and get depth
        if(right == -1) return -1;
        
        if(Math.abs(left - right) > 1) return -1;
        return Math.max(left, right);
    }
}

/*
Multiple Traverse:
Check for this node (right and left depth) + Check subtree(left and right subtree)

one to get the depth of the every node
one to check is every node is balance

Time: O(n^2)
Space: O(1)
 */
/*
求深度-1：每个 node 自己知道自己的深度（len)
到叶子节点的时候，返回深度int， 
node == null, return len

*/
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int left = depth(root.left, 0);
        int right = depth(root.right, 0);
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(left - right) <= 1;
    }
    private int depth(TreeNode node, int len){
        if(node == null) return len;//
        else return Math.max(depth(node.left, len + 1), depth(node.right, len + 1));
    }
}
/*
求深度-2：每个 node 不知道自己的深度（但是外面有人计算）
返回深度int， 
node == null, return 0

*/
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        if(Math.abs(depth(root.left) - depth(root.right)) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }
    private int depth(TreeNode node){
        if(node == null) return 0;
        int left = depth(node.left) + 1;
        int right = depth(node.right) + 1;
        return Math.max(left, right);
    }
}