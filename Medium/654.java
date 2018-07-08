/*
654. Maximum Binary Tree
https://leetcode.com/problems/maximum-binary-tree/description/
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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode result = new TreeNode(0);
        int index = 0;
        result = subtree(result, nums, 0, nums.length);
        return result;
    }
    private void subtree(ListNode result, int[] nums, int start, int end){
        result.val = nums[start];
        int index = start;
        //the first Node
        for(int i = start + 1; i <= end; i++){
            if(nums[i] > nums[i - 1]){
                result.val = nums[i];
                index = i;
            }
        }
        if(start <= index - 1){
            subtree(result.left, nums, start, index - 1);
        }
        if(index + 1 <= end){
            subtree(result.right, nums, index + 1, end);
        }
        return;
    }
}