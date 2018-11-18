/*
EASY
270. Closest Binary Search Tree Value

RESULT: 
NOTES: 
*/
/*
BST
For every node we pass, store the min difference and the corresponding node

Time: O(logn)
Space: O(1)
 */
class Solution {
    public int closestValue(TreeNode root, double target) {
        int res = root.val;
        double diff = Double.MAX_VALUE;//remember to use Double.MAX_VALUE
        
        while(root != null){
            double d = Math.abs(root.val - target);
            if(d < diff){
                diff = d;
                res = root.val;
            }
            if(root.val < target) root = root.right;
            else root = root.left;
        }
        return res;
    }
}