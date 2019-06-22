/*
MEDIUM
1008. Construct Binary Search Tree from Preorder Traversal

*/

/* good enough 的做法
Recursion + Range Limit => One Pass
use a global variable to record the index, and set the left - right range for every subTree

Time: O(n)
Space: O(n)
*/
class Solution {
    int i = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return buildTree(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private TreeNode buildTree(int[] preorder, int l, int r){
        if(i >= preorder.length) return null;
        if(preorder[i] < l || preorder[i] > r) return null;
        
        TreeNode node = new TreeNode(preorder[i]);
        i++;
        if(i < preorder.length){
            node.left = buildTree(preorder, l, node.val);
        } 
        if(i < preorder.length){
            node.right = buildTree(preorder, node.val, r);
        }
        return node;
    }
}

/*
优化： no need to check the lower bound (left)
因为一个 preorder traversal 的 order，左边的 value 一定都会满足的。
    如果我们一直在向左走，那么左边不用 check
    如果我们是在转折的地方，有左右 bound，那原始的 BST 就保证了不会在再出现比当前点的 parent 更小的点了。

*/

class Solution {
    int i = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return buildTree(preorder, Integer.MAX_VALUE);
    }
    private TreeNode buildTree(int[] preorder, int r){
        if(i >= preorder.length) return null;
        if(preorder[i] > r) return null;
        
        TreeNode node = new TreeNode(preorder[i]);
        i++;
        if(i < preorder.length){
            node.left = buildTree(preorder, node.val);
        } 
        if(i < preorder.length){
            node.right = buildTree(preorder, r);
        }
        return node;
    }
}
