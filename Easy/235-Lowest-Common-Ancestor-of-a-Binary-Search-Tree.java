/*
EASY
235. Lowest Common Ancestor of a Binary Search Tree

TIME: 
RESULT: 
NOTES:
*/
/*
要找的结果必须夹在 p q 中间
min <= node result <= max

Time: O(logn)
Space: O(1)
 */
//recursive
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        else if(root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }
}


//iterative - mine
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //get min and max from pq
        int min = p.val;
        int max = q.val;
        if(p.val > q.val){
            min = q.val;
            max = p.val;
        }
        
        TreeNode node = root;
        while(node != null){
            if(node.val >= min && node.val <= max){
                return node;
            }
            if(node.val > max) node = node.left;
            else if(node.val < min) node = node.right;
        }
        return node;
    }
}