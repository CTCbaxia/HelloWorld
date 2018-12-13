/*
EASY
543. Diameter of Binary Tree
https://leetcode.com/problems/diameter-of-binary-tree/description/

TIME: 0819 - 30min
RESULT: 25% - 7ms
NOTES:
1.不一定经过 root
*/
/*
SinglePath Helper
计算 SinglePath 返回值的同时，update result
SinglePath: 只选其中一个最大值
result: 选left + right 最大值

Time: O(n)
Space: O(1)
*/
class Solution {

    int maxLen = 0;
    public int diameterOfBinaryTree(TreeNode root){
        depth(root);
        return maxLen;
    }
    private int depth(TreeNode node){//no null
        if(node == null) return 0;
        int left = depth(node.left);
        int right = depth(node.right);
        maxLen = Math.max(maxLen, left + right);
        return Math.max(left, right) + 1;
    }


}






/*
SinglePath Helper
计算 SinglePath 返回值的同时，update result
SinglePath: 只选其中一个最大值
result: 选left + right 最大值

Time: O(n)
Space: O(1)
*/
class Solution {
    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        singlePath(root);
        return res;
    }
    private int singlePath(TreeNode node){
        if(node == null) return 0;
        int left = (node.left != null) ? 1 + singlePath(node.left) : 0;
        int right = (node.right != null) ? 1 + singlePath(node.right) : 0;
        res = Math.max(res, left + right);
        return Math.max(left, right);
    }
}