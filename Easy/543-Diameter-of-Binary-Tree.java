/*
EASY
543. Diameter of Binary Tree
https://leetcode.com/problems/diameter-of-binary-tree/description/

TIME: 0819 - 30min
RESULT: 25% - 7ms
NOTES:
1.不一定经过 root

THOUGHTS:
把每一个点都当做转折点, 在遍历每个点的时候都计算以他为 root 的最大枝节长度，并求以他为转折点的最长路径

Time Complexity: O(n)
Space Complexity: O(h) - 树的遍历要压栈，h 是树的最大高度

*/
/*

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
    int result = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        diaHelper(root);
        return result;
    }
    private int diaHelper(TreeNode root){
        if(root == null) return -1;
        int left = diaHelper(root.left);
        int right = diaHelper(root.right);
        result = Math.max(result, left + right + 2);
        return Math.max(left + 1, right + 1);
    }
}