/*
545. Boundary of Binary Tree
discuss one pass: https://leetcode.com/problems/boundary-of-binary-tree/discuss/101285/Java-Preorder-Single-Pass-O(n)-Solution
*/
/*
DFS
preorder to get left
inorder to get the leaves
postorder to get right

Time: O(n)
Space: O(n)
 */
class Solution {
    
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        
        if(root.left == null) res.add(root.val);
        else lefts(root, res);
        
        if(root.left == null && root.right == null) return res;
        else leaves(root, res);
        
        if(root.right == null) return res;
        else rights(root.right, res, res.size());
        
        return res;
    }
    private void lefts(TreeNode node, List<Integer> res){
        if(node == null || node.left == null && node.right == null) return;//no leaves
        res.add(node.val);
        if(node.left != null) lefts(node.left, res);//make sure it is the left bound
        else lefts(node.right, res);
    }
    private void rights(TreeNode node, List<Integer> res, int len){
        if(node == null || node.left == null && node.right == null) return;
        res.add(len, node.val);
        if(node.right != null) rights(node.right, res, len);
        else rights(node.left, res, len);
    }
    private void leaves(TreeNode node, List<Integer> res){
        if(node == null) return;
        leaves(node.left, res);
        if(node.left == null && node.right == null) res.add(node.val);
        leaves(node.right, res);
    }
        
}






/*
DFS
preorder traverse to get left and leaves
postorder traverse to get right

Time: O(n)
Space: O(n)
 */
class Solution {
    boolean leftValid = true, leaveValid = false;
    TreeNode root;
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        this.root = root;
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        
        dfs(root, res);
        int len = res.size();
        goRight(root.right, res, len);
        return res;
    }
    private void dfs(TreeNode node, List<Integer> res){
        if(node == null) return;
        if(leftValid) res.add(node.val);
        
        if(node.left != null) dfs(node.left, res);
        if(node.left == null && (node == root || node.right == null)){//left finish if root don't have left child
            if(leaveValid) res.add(node.val);
            if(leftValid){
                leaveValid = true;
                leftValid = false;
            }
        }
        if(node.right != null) dfs(node.right, res);
    }
    private void goRight(TreeNode node, List<Integer> res, int len){
        if(node == null) return;
        if(node.right == null && node.left == null) return;
        res.add(len, node.val);
        if(node.right != null) goRight(node.right, res, len);
        if(node.right == null && node.left != null) goRight(node.left, res, len);
    }
        
}








/*
Using 3 lists - BFS
left: first node until first don't have children
leave: all no children nodes
right: last node until last don't have children
 */
class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if(root == null) return null;
        List<Integer> left = new ArrayList<>();
        List<Integer> leave = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        
        boolean leftValid = true, leaveValid = true, rightValid = true;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int len = q.size();
            for(int i = 0; i < len; i++){
                TreeNode node = q.poll();
                if(i == 0 && leftValid){
                    left.add(node.val);
                    if(node.left == null && node.right == null){
                        leftValid = false;
                    }
                }
                if(node.left == null && node.right == null){
                    leave.add(node.val);
                    
                }
            }
        }
    }
}





leftValid = true
1
res = [1]