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
Binary Search

Time: O(n)
Space: O(logn)
*/
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        //if(root == null) return 0;
        int leftnum = countNodes(root.left);
        if(k <= leftnum) return kthSmallest(root.left, k);
        else if(k == leftnum + 1) return root.val;
        else return kthSmallest(root.right, k - leftnum - 1);// leftnum > k - 1
    }
    private int countNodes(TreeNode node){
        if(node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
}


/*
Inorder traverse
Stack

Time: O(n)
Space: O(n)
*/
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            k--;
            if(k == 0) return node.val;
            root = node.right;
        }
        return 0;
    }
}



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
