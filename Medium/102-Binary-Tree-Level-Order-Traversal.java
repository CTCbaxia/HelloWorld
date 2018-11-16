/*
MEDIUM
102. Binary Tree Level Order Traversal
https://leetcode.com/problems/binary-tree-level-order-traversal/description/
TIME: 0719 - 30min
RESULT: 100% - 0ms

METHOD:
1. recursive
2. queue

NOTE:
1. 对于 tree 来说，不要把它看成一个数组类的东西，要看成一个 ListNode 类的东西

*/
//-----3 ROUND FOR FB---------------------------------------------------------------------------------
/*
DFS + Level

Time: O(n)
Space: O(1)
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, result, 0);
        return result;
    }
    private void dfs(TreeNode node, List<List<Integer>> result, int level){
        if(node == null) return;
        if(result.size() == level) result.add(new ArrayList<>());
        
        result.get(level).add(node.val);
        dfs(node.left, result, level + 1);
        dfs(node.right, result, level + 1);
    }
}



/*
BFS + Queue

Time: O(n)
Space: O(n)
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        List<List<Integer>> result = new ArrayList<>();
        
        if(root != null) q.offer(root);
        
        while(!q.isEmpty()){
            Queue<TreeNode> newQ = new LinkedList<TreeNode>();
            List<Integer> res = new ArrayList<>();
            while(!q.isEmpty()){
                TreeNode node = q.poll();
                res.add(node.val);
                if(node.left != null) newQ.offer(node.left);
                if(node.right != null) newQ.offer(node.right);
            }
            result.add(res);
            q = newQ;
        }
        return result;
    }
}









//-----2 ROUND FOR MS-----------------------------------------------------------------
//BFS
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null) return result;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        
        while(!q.isEmpty()){
            List<Integer> level = new ArrayList<Integer>();
            Queue<TreeNode> nextQ = new LinkedList<TreeNode>();
            while(!q.isEmpty()){
                TreeNode node = q.poll();
                level.add(node.val);
                if(node.left != null) nextQ.add(node.left);
                if(node.right != null) nextQ.add(node.right);
            }
            q = nextQ;
            result.add(level);
        }
        return result;
    }
}
//DFS
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs(root, result, 0);
        return result;
    }
    private void dfs(TreeNode node, List<List<Integer>> result, int level){
        if(node == null) return;
        if(result.size() <= level) result.add(new ArrayList<Integer>());
        result.get(level).add(node.val);
        dfs(node.left, result, level + 1);
        dfs(node.right, result, level + 1);
    }
}








/*
SOLUTION 0: wow!!
TIME: 0719 - 30min
RESULT: 100% - 0ms
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        nextLevel(root, result, 0);
        return result;
    }
    private void nextLevel(TreeNode node, List<List<Integer>> result, int level){
        if(node == null) return;
        if(result.size() <= level) result.add(new ArrayList<Integer>());
        result.get(level).add(node.val);
        nextLevel(node.left, result, level + 1);
        nextLevel(node.right, result, level + 1);
        return;
    }
}

/*
SOLUTION 1: 
用 queue 来存每层的东西，思路不错

TIME: 0719 - 30min
RESULT: 86.7% - 1ms
*/

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root == null) return result;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<Integer>();
            for(int i = 0; i < size; i++){
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                level.add(queue.poll().val);
            }
            result.add(level);
        }
        return result;
    }

}
