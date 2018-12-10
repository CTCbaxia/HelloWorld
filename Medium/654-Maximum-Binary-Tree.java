/*
654. Maximum Binary Tree
https://leetcode.com/problems/maximum-binary-tree/description/

TIME: 0708
RESULT: 99%, 8ms
NOTE:

*/

/*
Stack(keep MONO desc stack)

loop the array to build node one by one, every time we build a node:
1) pop stack if peek is smaller than this node, assign to its left child
2) assign this node as right child of the stack.peek() if stack is not empty
3) add this node into stack(stack is still desc)
stack是很好的保持 mono object的东西

return final node in stack

Time: O(n) every node go into stack once
Space: O(n)
*/
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums){
        Stack<TreeNode> stack = new Stack<TreeNode>();//keep mono desc stack
        for(int i = 0; i < nums.length; i++){
            TreeNode node = new TreeNode(nums[i]);
            //the previous node that is smaller than node, will not have children any more
            while(!stack.isEmpty() && stack.peek().val < node.val){
                node.left = stack.pop();//assign node smaller than node but it the largest smaller one
            }
            if(!stack.isEmpty()) stack.peek().right = node;
            stack.push(node);//push node because we don't know what's next
        }
        while(stack.size() > 1) stack.pop();
        return stack.size() == 0 ? null : stack.pop();
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
范围内找大值

Time: O(n^2)
Space: O(1)
*/
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums){
        return build(nums, 0, nums.length - 1);
    }
    private TreeNode build(int[] nums, int lo, int hi){
        if(lo > hi) return null;
        int max = nums[lo];
        int index = lo;
        for(int i = lo; i <= hi; i++){
            if(nums[i] > max){
                max = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);
        return root;
    } 
}


/*
Stack 逐渐构建 tree （太 tricky 了）

Time: O(n)
Space: O(1)
*/
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        for(int n : nums){
            TreeNode node = new TreeNode(n);
            while(!stack.isEmpty() && stack.peek().val < node.val){//构建一个倒序的stack
                node.left = stack.pop();
            }
            if(!stack.isEmpty()){
                //如果有比 node 更大的 element，就让最近的比他大的 element 的 right tree 等于 node（这个是可能更新的，直到右边的最大值位置）
                stack.peek().right = node;
            }
            stack.push(node);
        }
        TreeNode result = null;
        while(!stack.isEmpty()) result = stack.pop();
        return result;
    }
}


/*
Recursive

Time: 
1. worst O(n^2) 每次都没怎么减少 T(n) = T(n - 1) + n
2. avg 
    假设每次能把array对半分
    T(n) = 2T(n/2)+o(n)
    T(n) = o(nlogn)
Space: O(1)
*/
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }
    private TreeNode buildTree(int[]nums, int l, int r){
        if(l > r) return null;
        
        int max = nums[l];
        int index = l;
        for(int i = l; i <= r; i++){
            if(nums[i] > max){
                max = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = buildTree(nums, l, index - 1);
        root.right = buildTree(nums, index + 1, r);
        return root;
    }
}









/**
1.注意树在接左右分支的时候不能让 result = result.left; 然后再导入子方法。因为这样会让处理右分支的时候直接以左分支为起点了
正确方法：subtree(result.left, nums, start, index - 1);
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