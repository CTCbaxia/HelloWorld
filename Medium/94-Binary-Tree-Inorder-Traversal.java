/*
MEDIUM
94. Binary Tree Inorder Traversal
https://leetcode.com/problems/binary-tree-inorder-traversal/description/

TIME: 0826 - 1h
RESULT: 100% - 0ms
NOTES:
1. inorder 遍历的两种方法
	- 辅助函数遍历（SOLUTION REFERENCE 0）：每次都从左往右遍历
	- Stack inorder traversal: 一直左走走到头
*/
/*
Iteration using Stack

Time: O(n)
Space: O(n)
*/
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
            
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root != null){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                TreeNode node = stack.pop();
                res.add(node.val);
                root = node.right;
            }
        }
        return res;
    }
}


/*
DFS - Recursion

Time: O(n)
Space: O(logn)
*/
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        dfs(root, result);
        return result;
    }
    private void dfs(TreeNode node, List<Integer> result){
        if(node == null) return;
        
        dfs(node.left, result);
        result.add(node.val);
        dfs(node.right, result);
        return;
    }
}


/*
Iterative: Stack

Time: O(n)
Space: O(logn)
*/
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> result = new ArrayList<Integer>();
        while(!stack.isEmpty() || root != null){//if the tree only have right children
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            result.add(node.val);
            root = node.right;
        }
        return result;
    }
}

/*
5
 6
   7
*/









/*
SOLUTION REFERENCE 0: 辅助函数遍历
TIME: 0826 - 10min
RESULT: 100% - 0ms
*/
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        inorder(root, result);
        return result;
    }
    private void inorder(TreeNode root, List<Integer> result){
        if(root == null) return;
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
        return;
    }
}

/*
SOLUTION REFERENCE 1:更简洁
TIME: 0826 - 10min
RESULT: 56% - 1ms
*/
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while(root != null || !stack.empty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            result.add(node.val);
            root = node.right;
        }
        return result;
    }
}

/*
SOLUTION REFERENCE:更简洁
TIME: 0826 - 30min
RESULT: 56% - 1ms
*/
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root == null) return result;
        stack.push(root);
        while(!stack.empty()){
            while(root.left != null){
                stack.push(root.left);
                root = root.left;
            }
            TreeNode node = stack.pop();
            result.add(node.val);
            if(node.right != null){
                stack.push(node.right);
                root = node.right;
            }
        }
        return result;
    }
}