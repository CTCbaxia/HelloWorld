/*
EASY
543. Diameter of Binary Tree
https://leetcode.com/problems/diameter-of-binary-tree/description/

TIME: 0819 - 30min
RESULT:
Notes: 


*/
/*
左边的最远叶子和右边的最远叶子
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int left = 0, right = 0;
        left = diaHelper(root.left, 1);
        right = diaHelper(root.right, 1);
        System.out.println(left);
        System.out.println(right);
        return left + right;
        
    }
    private int diaHelper(TreeNode root, int len){
        if(root.left == null && root.right == null){
            System.out.println(len);
            return len;
        } 
        if(root.left != null){
            diaHelper(root.left, len + 1);
        }
        if(root.right != null){
            diaHelper(root.right, len + 1);
        }
        return len;
    }
}