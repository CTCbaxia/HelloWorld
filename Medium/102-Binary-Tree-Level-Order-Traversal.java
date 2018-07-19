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