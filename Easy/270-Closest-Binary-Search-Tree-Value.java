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
        double min = Double.MAX_VALUE;//remember to use Double.MAX_VALUE
        
        while(root != null){
            if(min > Math.abs(target - (double)root.val)){
                min = Math.abs(target - (double)root.val);
                res = root.val;
            }
            
            if(target > root.val) root = root.right;
            else root = root.left;
        }
        return res;
    }
}