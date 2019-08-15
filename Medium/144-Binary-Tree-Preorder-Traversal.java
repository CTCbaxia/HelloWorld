/*
M
144. Binary Tree Preorder Traversal
*/
/*
recursion
DFS

Time: O(n)
Space: O(n) - recursion stack
*/
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
    private void dfs(TreeNode node, List<Integer> res){
        if(node == null) return;
        res.add(node.val);
        dfs(node.left, res);
        dfs(node.right, res);
    }
}


/*
Iteration
DFS

Time: O(n)
Space: O(n)
*/
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                res.add(root.val);
                root = root.left;
            }
            TreeNode node = stack.pop();
            root = node.right;
        }
        return res;
    }

}
