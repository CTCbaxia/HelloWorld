/*
M
938. Range Sum of BST
*/
/*
DFS + Recursion
利用 BST 的特质，省略一部分

Time: O(n)
Space: O(n)
*/
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null) return 0;
        if(root.val < L) return rangeSumBST(root.right, L, R);// left branch excluded.
        else if(root.val > R) return rangeSumBST(root.left, L, R);// right branch excluded.
        else{
            return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
        }
    }
}




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
