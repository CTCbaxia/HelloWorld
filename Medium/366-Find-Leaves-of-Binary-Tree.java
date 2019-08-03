/*
M
366. Find Leaves of Binary Tree
*/
/*
Recursion find level, add list on the way

Time: O(n)
Space: O(1)
*/
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        findLevel(root, res);
        return res;
    }
    private int findLevel(TreeNode node, List<List<Integer>> list){
        if(node == null) return 0;
        int left = findLevel(node.left, list);
        int right = findLevel(node.right, list);
        int nodeLevel = Math.max(left, right) + 1;
        
        //update res
        if(list.size() < nodeLevel) list.add(new ArrayList<Integer>());
        list.get(nodeLevel - 1).add(node.val);
        
        return nodeLevel;
    }
}
