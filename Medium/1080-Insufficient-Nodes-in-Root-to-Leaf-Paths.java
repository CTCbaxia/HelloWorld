/*
MEDIUM
1080. Insufficient Nodes in Root to Leaf Paths
*/
/**
Recursion
***need to also remove parent when its only child has been deleted
Time: O(n)
Space: O(n)
*/
class Solution {
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if(needDelete(root, 0, limit)) return null;
        else return root;
    }
    private boolean needDelete(TreeNode node, int curSum, int limit){
        if(node == null) return curSum < limit;
        
        boolean deleteLeft = needDelete(node.left, curSum + node.val, limit);
        boolean deleteRight = needDelete(node.right, curSum + node.val, limit);
        
        if(deleteLeft) node.left = null;
        if(deleteRight) node.right = null;
        
        if(deleteLeft && deleteRight) return true;//delete its parent, for it's child path is insufficient
        else if(deleteLeft && node.right == null) return true;//if the node has only one child, and it's detelted
        else if(deleteRight && node.left == null) return true;
        else return false;
    }
}



/**❌
Recursion

Time: O(n)
Space: O(n)
*/
class Solution {
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if(needDelete(root, 0, limit)) return null;
        else return root;
    }
    private boolean needDelete(TreeNode node, int curSum, int limit){
        if(node == null) return curSum < limit;
        
        boolean deleteLeft = needDelete(node.left, curSum + node.val, limit);
        boolean deleteRight = needDelete(node.right, curSum + node.val, limit);
        
        //❌这里如果只有一个 child，则 children 被删，他也得被删
        if(deleteLeft) node.left = null;
        if(deleteRight) node.right = null;
        if(deleteLeft && deleteRight) return true;//tell its parent, it's child path is all insufficient, it should be deleted
        else return false;
    }
}