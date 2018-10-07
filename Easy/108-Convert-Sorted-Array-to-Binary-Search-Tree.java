/*
EASY
108. Convert Sorted Array to Binary Search Tree
https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/

TIME: 0901 - 15min
RESULT: 100% - 0ms
NOTES: 

*/
//------2 ROUND FOR MS-----------------------------
//TIME: 1007 - 5min
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortHelper(nums, 0, nums.length - 1);
    }
    private TreeNode sortHelper(int[] nums, int left, int right){
        if(left > right) return null;
        int middle = (left + right)/2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = sortHelper(nums, left, middle - 1);
        root.right = sortHelper(nums, middle + 1, right);
        return root;
    }
}


//------1 ROUND-----------------------------
//find the middle part of the array
//then find the middle part of the subarray
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return subTree(0, nums.length - 1, nums);
    }
    private TreeNode subTree(int left, int right, int[] nums){
        if(left > right) return null;
        
        int mid = (left + right)/2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = subTree(left, mid - 1, nums);
        root.right = subTree(mid + 1, right, nums);
        return root;
    }
}