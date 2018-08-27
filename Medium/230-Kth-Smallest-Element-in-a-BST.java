/*
MEDIUM
230. Kth Smallest Element in a BST
https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/

TIME: 0826 - 30min
RESULT: 100% - 0ms
NOTES:
1. Inorder Traversal 的两种典型方法
	- List | Recursive 辅助函数 ：因为 list 的传递性，每次递归都能知道遍历到哪个节点了
	- Stack：一个主函数直接顺序得到遍历结果
*/


/*
SOLUTION 1: Inorder Traversal | List | Recursive 辅助函数
TIME: 0826 - 15min
RESULT: 100% - 0ms
*/

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<TreeNode> inorder = new ArrayList<TreeNode>();
        inorder(root, inorder, k);
        return inorder.get(inorder.size() - 1).val;
    }
    private void inorder(TreeNode root, List<TreeNode> inorder, int k){
        if(root == null) return;
        inorder(root.left, inorder, k);
        
        if(inorder.size() == k) return;
        inorder.add(root);
        
        inorder(root.right, inorder, k);
        return;
    }
}
/*
SOLUTION 0: Inorder Traversal | Stack
TIME: 0826 - 15min
RESULT: 55% - 1ms
*/
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while((root != null || !stack.empty()) && k > 0){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            if(k == 1) return node.val;
            k--;
            root = node.right;
        }
        return 0;
    }
}
