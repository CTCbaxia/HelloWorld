/*
H
145. Binary Tree Postorder Traversal
*/
/*
Postorder - Interation - Stack
leftnode, rightnode, node
Has a marker to check if it's children has been traversed, put children in right, left order

Time: O(n)
Space: O(logn)
*/
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode last = root;//mark the last put-to-res node
        while(!stack.isEmpty()){
            TreeNode node = stack.peek();
            //check if its children is processed, may only have one child
            if(node.left == null && node.right == null || node.left == last || node.right == last){
                res.add(stack.pop().val);
                last = node;
            }else{
                if(node.right != null) stack.push(node.right);
                if(node.left != null) stack.push(node.left);
            }
        }
        return res;
    }
}
/*
Postorder - Recursion
leftnode, rightnode, node

Time: O(n)
Space: O(logn)
*/
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
    private void dfs(TreeNode node, List<Integer> res){
        if(node == null) return;
        dfs(node.left, res);
        dfs(node.right, res);
        res.add(node.val);
    }
}
