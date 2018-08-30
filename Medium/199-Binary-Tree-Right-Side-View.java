/*
MEDIUM
199. Binary Tree Right Side View
https://leetcode.com/problems/binary-tree-right-side-view/description/

TIME: 0830 - 15min
RESULT: 78% - 1ms
METHOD: BFS

*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        
        queue.add(root);
        while(!queue.isEmpty()){
            
            Queue<TreeNode> nextlevel = new LinkedList<TreeNode>();
            while(!queue.isEmpty()){
                TreeNode node = queue.poll();
                if(queue.size() == 0) result.add(node.val);
                if(node.left != null) nextlevel.add(node.left);
                if(node.right != null) nextlevel.add(node.right);
            }
            queue = nextlevel;
        }
        return result;
    }
}

//similar but use len
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        
        queue.add(root);
        while(!queue.isEmpty()){
            int len = queue.size();
            for(int i = 0; i < len; i++){
                TreeNode node = queue.poll();
                if(i == len - 1) result.add(node.val);
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
        }
        return result;
    }
}