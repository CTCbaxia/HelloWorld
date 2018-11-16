/*
MEDIUM
103. Binary Tree Zigzag Level Order Traversal
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/

TIME: 0828 - 30min
RESULT: 19% - 2ms
METHOD: BFS
*/
/*
DFS + Level + List Insert
用 helper function 
1. level 控制应该加在哪一层，
2. 用level奇偶判断add顺序

Time: O(n)
Space: O(1)
*/
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, result, 0);
        return result;
    }
    private void dfs(TreeNode node, List<List<Integer>> result, int level){
        if(node == null) return;
        if(result.size() <= level) result.add(new ArrayList<Integer>());
        
        if(level % 2 == 0){//left to right
            result.get(level).add(node.val);
        }else{//right to the left
            result.get(level).add(0, node.val);
        }
        dfs(node.left, result, level + 1);
        dfs(node.right, result, level + 1);
    }
}



/*
BFS + Queue + List Insert

Time: O(n)
Space: O(n)
*/
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int sign = 1;
        
        if(root != null) q.offer(root);
        while(!q.isEmpty()){
            Queue<TreeNode> newQ = new LinkedList<TreeNode>();
            res = new ArrayList<>();
            while(!q.isEmpty()){
                TreeNode node = q.poll();
                if(sign == 1) res.add(node.val);
                else res.add(0, node.val);
                
                if(node.left != null) newQ.offer(node.left);
                if(node.right != null) newQ.offer(node.right);
            }
            q = newQ;
            result.add(res);
            sign = -sign;
        }
        return result;
    }
}








class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        stack.push(root);
        zigzag(stack, result, 1);
        return result;
    }
    private void zigzag(Stack<TreeNode> stack, List<List<Integer>> result, int flag){
        if(stack.size() == 0 || stack.peek() == null) return;
        
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> level = new Stack<TreeNode>();
        while(!stack.empty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            if(flag == 1){
                if(node.left != null) level.push(node.left);
                if(node.right != null) level.push(node.right);                
            }else if(flag == -1){
                if(node.right != null) level.push(node.right);
                if(node.left != null) level.push(node.left);
            }

        }
        if(res.size() != 0) result.add(res);
        zigzag(level, result, -1 * flag);
        return;
    }
}






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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Deque<TreeNode> q = new LinkedList<TreeNode>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        
        if(root != null) q.offerFirst(root);
        int sign = -1;
        while(!q.isEmpty()){
            Deque<TreeNode> newQ = new LinkedList<TreeNode>();
            res = new ArrayList<>();
            //q 和上一轮 res的顺序是相同的
            while(!q.isEmpty()){
                if(sign == -1){//build from right to left
                    TreeNode node = q.pollLast();
                    res.add(0, node.val);
                    if(node.right != null){
                        newQ.offerFirst(node.right);
                    }
                    if(node.left != null){
                        newQ.offerFirst(node.left);
                    }
                }else{
                    TreeNode node = q.pollFirst();
                    res.add(0, node.val);
                    if(node.left != null){
                        newQ.offerLast(node.left);
                    }
                    if(node.right != null){
                        newQ.offerLast(node.right);
                    }
                }
            }
            if(res.size() != 0) result.add(res);
            q = newQ;
            sign = -sign;
        }
        return result;
    }
}



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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Deque<TreeNode> q = new LinkedList<TreeNode>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        
        if(root != null){
            q.offerFirst(root);
            res.add(root.val);
            result.add(res);
        } 
        int sign = -1;
        while(!q.isEmpty()){
            Deque<TreeNode> newQ = new LinkedList<TreeNode>();
            res = new ArrayList<>();
            //q 和上一轮 res的顺序是相同的
            while(!q.isEmpty()){
                if(sign == -1){//build from right to left
                    TreeNode node = q.pollLast();
                    System.out.println(node.val);
                    if(node.right != null){
                        res.add(node.right.val);
                        newQ.offerFirst(node.right);
                    }
                    if(node.left != null){
                        res.add(node.left.val);
                        newQ.offerFirst(node.left);
                    }
                }else{
                    TreeNode node = q.pollFirst();
                    System.out.println(node.val);
                    if(node.left != null){
                        res.add(node.left.val);
                        newQ.offerLast(node.left);
                    }
                    if(node.right != null){
                        res.add(node.right.val);
                        newQ.offerLast(node.right);
                    }
                }
            }
            if(res.size() != 0) result.add(res);
            q = newQ;
            sign = -sign;
        }
        return result;
    }
}