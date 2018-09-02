/*
EASY
104. Maximum Depth of Binary Tree
https://leetcode.com/problems/maximum-depth-of-binary-tree/description/

TIME: 0901 - 10min
RESULT: 100% - 0ms
METHOD: DFS
*/
class Solution {
    public int maxDepth(TreeNode root) {
        return findPath(root, 0);
    }
    private int findPath(TreeNode root, int pre){
        if(root == null) return pre;
        return Math.max(findPath(root.left, pre + 1), findPath(root.right, pre + 1));
    }
}


/*
SOLUTION REFERENCE: 更简单的迭代思路
*/
class Solution {
	public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
	}
}