/*
M
938. Range Sum of BST
*/
/*
DFS + Stack

Time: O(n)
Space: O(n)
*/
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        Stack<TreeNode> stack = new Stack<>();
        int res = 0;
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            
            TreeNode node = stack.pop();
            if(node.val > R) break;
            if(node.val >= L) res += node.val;
            root = node.right;
        }
        return res;
    }
}
