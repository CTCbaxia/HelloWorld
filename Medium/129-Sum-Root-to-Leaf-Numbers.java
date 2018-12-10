/*
MEDIUM
129. Sum Root to Leaf Numbers
https://leetcode.com/problems/sum-root-to-leaf-numbers/description/

TIME: 0831 - 30min
RESULT: 100% - 0ms
*/
/*
DFS 记录每条 path 当前 curSum

Time: O(n)
Space: O(logn)
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
    public int sumNumbers(TreeNode root){
        return dfs(root, 0);
    }
    private int dfs(TreeNode node, int curSum){
        if(node == null) return 0;//for if there is no this path

        curSum = curSum * 10 + node.val;
        if(node.left == null && node.right == null) return curSum;

        int left = dfs(node.left, curSum);
        int right = dfs(node.right, curSum);

        return left + right;
    }
}








/*
SOLUTION 0: 
TIME: 0831 - 10min
RESULT: 32% - 1ms
METHOD: DFS + Recursive + use external sum
*/

class Solution {
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        sumPath(root, sum);
        return sum;
    }
    private void sumPath(TreeNode root, int pre){
        if(root == null) return;
        if(root.left == null && root.right == null){
            sum += pre * 10 + root.val;
            return;
        }
        if(root.left != null) sumPath(root.left, pre * 10 + root.val);
        if(root.right != null) sumPath(root.right, pre * 10 + root.val); 
    }
    
}
/*
SOLUTION 1: 
TIME: 0831 - 10min
RESULT: 100% - 0ms
METHOD: DFS + Recursive + 传参
*/

class Solution {
    public int sumNumbers(TreeNode root) {
        return sumPath(root, 0);
    }
    private int sumPath(TreeNode root, int pre){
        
        if(root == null) return pre;
        
        pre = pre * 10 + root.val;//到当前点的现有 pre 
        int sum = 0;//包含了这个点的所有分支的 sum
        if(root.left == null && root.right == null) return pre;
        if(root.left != null) sum += sumPath(root.left, pre);
        if(root.right != null) sum += sumPath(root.right, pre); 
        return sum;
    }
    
}
/*
SOLUTION REFERENCE: 更简洁
TIME: 0831 - 10min
RESULT: 100% - 0ms
METHOD: DFS + Recursive + 传参
*/
class Solution {
	public int sumNumbers(TreeNode root) {
		return sum(root, 0);
	}

	public int sum(TreeNode n, int s){
		if (n == null) return 0;
		if (n.right == null && n.left == null) return s*10 + n.val;
		return sum(n.left, s*10 + n.val) + sum(n.right, s*10 + n.val);
	}
}