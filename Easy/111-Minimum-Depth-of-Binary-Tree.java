/*
MEDIUM
111. Minimum Depth of Binary Tree

TIME: 
RESULT: 
NOTES:

*/
/*
BFS:
遇到没有left right child的叶子就 return result

Time: O(n)
Space: O(n)
*/

class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        
        q.offer(root);
        int result = 1;
        while(!q.isEmpty()){
            Queue<TreeNode> newQ = new LinkedList<TreeNode>();
            while(!q.isEmpty()){
                TreeNode node = q.poll();
                if(node.left == null && node.right == null) return result;
                if(node.left != null) newQ.offer(node.left);
                if(node.right != null) newQ.offer(node.right);
            }
            result++;
            q = newQ;
        }
        return result;
    }
}