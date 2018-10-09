/*
MEDIUM
701. Insert into a Binary Search Tree

TIME: 1009 - 40min
RESULT: 100% - 2ms
思路：
每次比较一个node，
如果大于，且node.right == null，直接插入右边。否则继续比较和右边的关系
如果小于，且node.left == null，直接插入左边。否则继续比较和左边的关系
*/
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = root;
        while(node != null){
            if(val > node.val){
                if(node.right != null) node = node.right;
                else{
                    node.right = new TreeNode(val);
                    return root;
                }
            } 
            else if(val < node.val){
                if(node.left != null) node = node.left;
                else{
                    node.left = new TreeNode(val);
                    return root;
                }
            }
        }
        return new TreeNode(val);

    }
}