/*
617. Merge Two Binary Trees

*/
/*
Recursive

Time: O(n)
Space: O(logn - n)
*/
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null) return null;
        else if(t1 == null) return t2;//只要有一个是 null，只用把有内容的 subtree 接上去就好
        else if(t2 == null) return t1;
        else{
            TreeNode node = new TreeNode(t1.val + t2.val);
            node.left = mergeTrees(t1.left, t2.left);
            node.right = mergeTrees(t1.right, t2.right);
            return node;
        }
    }
}