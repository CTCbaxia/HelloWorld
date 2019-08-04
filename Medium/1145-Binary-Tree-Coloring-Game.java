/*
M
1145. Binary Tree Coloring Game
*/
/*
Count the number of nodes for subtree of x (including x), let's set is as num, 
if num <= n/2, then you can win by just set y as x's parent
if num > n/2,
    1) if one of the x's subtree is larger than n/2, you can

*/
class Solution {
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        //find the x
        TreeNode nodeX = find(root, x);
        
        //count number
        int left = count(nodeX.left);
        int right = count(nodeX.right);
        int subTreeX = left + right + 1;
        
        if(subTreeX <= n/2) return true;
        else{
            if(left > n/2 || right > n/2) return true;
            else return false;
        }
        
        
        
    }
    private TreeNode find(TreeNode node, int x){
        if(node == null) return null;
        if(node.val == x) return node;
        
        TreeNode left = find(node.left, x);
        if(left != null) return left;
        
        TreeNode right = find(node.right, x);
        if(right != null) return right;
        
        return null;
    }
    private int count(TreeNode node){
        if(node == null) return 0;
        return 1 + count(node.left) + count(node.right);
    }
}
