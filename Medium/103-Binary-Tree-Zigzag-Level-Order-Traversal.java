/*
MEDIUM
103. Binary Tree Zigzag Level Order Traversal
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/

TIME: 0828 - 30min
RESULT: 19% - 2ms
METHOD: BFS
*/
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