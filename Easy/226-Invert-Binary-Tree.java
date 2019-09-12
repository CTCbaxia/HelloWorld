/*
EASY
226. Invert Binary Tree

TIME: 
RESULT: 
NOTES:
*/
/*
Recursion

Time: O(n)
Space: O(logn)
*/
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);
        root.left = left;
        root.right = right;
        return root;
    }
}
// /*
// Iteration - BFS

// Time: O(n)
// Space: O(n)
// */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();//for each node, reverse children
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if(node.left != null) q.offer(node.left);
            if(node.right != null) q.offer(node.right);
        }
        return root;
    }
}