/*
MEDIUM
865. Smallest Subtree with all the Deepest Nodes

TIME: 1105
RESULT: 
NOTES:

*/
/*
Recursion:
We need return node that has all deepest node in its subtree
Use global variable for result and deepest
User Helper function findLevel to calculate the deepest level the current node can reach

Time: O(n)
Space: O(1)
*/
class Solution {
    public TreeNode result = null;
    public int deepest = 0;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        findLevel(root, 0);
        return result;
    }
    private int findLevel(TreeNode node, int level){
        if(node == null) return level;
        int left = findLevel(node.left, level + 1);
        int right = findLevel(node.right, level + 1);
        
        if(left == right && left >= deepest){//if the subtree's level is the deeper than current deepest
            result = node;//update the result
            deepest = left;
        } 
        return Math.max(left, right);
    }
}