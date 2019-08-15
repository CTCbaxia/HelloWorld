/*
M
250. Count Univalue Subtrees
*/
/*
Recursion

Time: O(n)
Space: O(1) - stack: O(logn)
*/
class Solution {
    public class UniTree{
        int val;
        boolean isValid;
        public UniTree(int _val, boolean _isValid){
            val = _val;
            isValid = _isValid;
        }
    }
    int count;
    public int countUnivalSubtrees(TreeNode root) {
        count = 0;
        dfs(root);
        return count;
    }
    private UniTree dfs(TreeNode node){
        if(node == null) return null;
        UniTree left = dfs(node.left);
        UniTree right = dfs(node.right);
        boolean isValid = false;
        if(left == null && right == null) isValid = true;
        else if(left == null){
            if(right.isValid && right.val == node.val) isValid = true;
        }else if(right == null){
            if(left.isValid && left.val == node.val) isValid = true;
        }else{
            if(left.isValid && right.isValid && left.val == node.val && right.val == node.val){
                isValid = true;
            }
        }
        if(isValid) count++;
        return new UniTree(node.val, isValid);
    }
}
