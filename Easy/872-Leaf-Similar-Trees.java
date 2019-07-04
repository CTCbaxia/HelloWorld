/*
872. Leaf-Similar Trees
*/
/**
DFS (inorder traversal) when meet leaf, put into a sequence

Time: O(m + n)
Space: O(logm + logn)
*/
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        
        if(list1.size() != list2.size()) return false;
        for(int i = 0; i < list1.size(); i++){
            if(list1.get(i) != list2.get(i)) return false;
        }
        return true;
    }
    private void dfs(TreeNode root, List<Integer> list){
        if(root == null) return;
        
        dfs(root.left, list);
        
        if(root.left == null && root.right == null){//see if it is a leaf
            list.add(root.val);
            return;
        } 
        dfs(root.right, list);
    }
}