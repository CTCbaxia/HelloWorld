/*
654. Maximum Binary Tree
https://leetcode.com/problems/maximum-binary-tree/description/

TIME: 0708
RESULT: 99%, 8ms
NOTE:
1.注意树在接左右分支的时候不能让 result = result.left; 然后再导入子方法。因为这样会让处理右分支的时候直接以左分支为起点了
正确方法：subtree(result.left, nums, start, index - 1);
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
        TreeNode tmp = new TreeNode(0);
        TreeNode result = tmp;
        if(nums.length > 0){
            int index = 0;
            subtree(tmp, nums, 0, nums.length - 1);
            
        }else{
            result = tmp.left;
            
        }
        return result;

    }
    private void subtree(TreeNode result, int[] nums, int start, int end){
        result.val = nums[start];
        int index = start;
        //the first Node
        for(int i = start + 1; i <= end; i++){
            if(nums[i] > nums[index]){
                result.val = nums[i];
                index = i;
            }
        }
        if(start <= index - 1){
            result.left = new TreeNode(0);
            
            subtree(result.left, nums, start, index - 1);
        }
        if(index + 1 <= end){
            result.right = new TreeNode(0);
            
            subtree(result.right, nums, index + 1, end);
        }
        return;
    }
}