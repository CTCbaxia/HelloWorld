/*
M
1110. Delete Nodes And Return Forest
*/
/*
Recursion 
passing value (isRoot) in method and return value (should delete?) to the parent
***Very good way to remove children

Time: O(n)
Space: O(n)
*/
class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        
        //build set
        Set<Integer> deleted = new HashSet<>();
        for(int n : to_delete){
            deleted.add(n);
        }
        dfs(root, deleted, res, true);
        return res;
    }
    // if left and right root should be deleted, add subtree to res
    private boolean dfs(TreeNode node, Set<Integer> deleted, List<TreeNode> res, boolean isRoot){
        if(node == null) return false;
        boolean deleteCur = deleted.contains(node.val);
        if(!deleteCur && isRoot){//if it should not be deleted, and it is the new root
            res.add(node);
        }
        
        boolean deleteLeft = dfs(node.left, deleted, res, deleteCur);
        boolean deleteRight = dfs(node.right, deleted, res, deleteCur);
        
        if(deleteLeft) node.left = null;
        if(deleteRight) node.right = null;
        return deleteCur;
    }
    
}
