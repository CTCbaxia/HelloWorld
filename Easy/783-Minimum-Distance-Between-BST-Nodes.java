/*
EASY
783. Minimum Distance Between BST Nodes

*/
/*
Inorder traverse of the tree -- Stack

Time: O(n)
Space: O(logn)
 */
class Solution {
    public int minDiffInBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode last = null;
        int min = Integer.MAX_VALUE;
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            if(last != null) min = Math.min(min, node.val - last.val);
            last = node;
            
            root = node.right;    
        }
        return min;
    }
}






/*
Inorder traverse of the tree -- Recursion + 2 global var

Time: O(n)
Space: O(logn)
 */
class Solution {
    TreeNode last = null;
    int min = Integer.MAX_VALUE;
    public int minDiffInBST(TreeNode root) {
        if(root == null) return min;
        minDiffInBST(root.left);
        
        if(last != null) min = Math.min(min, root.val - last.val);
        last = root;
        
        minDiffInBST(root.right);
        return min;
    }
    
}


/*
Inorder traverse of the tree -- Recursion Helper + 2 global var

Time: O(n)
Space: O(logn)
 */
class Solution {
    TreeNode last = null;
    int min = Integer.MAX_VALUE;
    public int minDiffInBST(TreeNode root) {
        inorder(root);
        return min;
    }
    private void inorder(TreeNode node){
        if(node == null) return;
        
        inorder(node.left);
        
        if(last != null) min = Math.min(min, node.val - last.val);
        last = node;
        
        inorder(node.right);
        return;
    }
}



/*
Inorder traverse of the tree -- Recursion Helper + 1 global var

Time: O(n)
Space: O(logn)
 */
class Solution {
    TreeNode last = null;
    public int minDiffInBST(TreeNode root) {
        int min = Integer.MAX_VALUE;
        return inorder(root, min);
    }
    private int inorder(TreeNode node, int min){
        if(node == null) return min;
        
        min = inorder(node.left, min);
        
        if(last != null) min = Math.min(min, node.val - last.val);
        last = node;
        
        min = inorder(node.right, min);
        return min;
    }
}







/*
❌对于 BST 理解不够深入 --> 主要就是 inorder 思路
Recursion 

Time: O(n)
Space: O(logn) 递归深度
 */
class Solution {
    public int minDiffInBST(TreeNode root) {
        if(root == null) return Integer.MAX_VALUE;
        int left = root.left == null ? Integer.MAX_VALUE : root.val - root.left.val;
        int right = root.right == null ? Integer.MAX_VALUE : root.right.val - root.val;
        int minSub = Math.min(minDiffInBST(root.left), minDiffInBST(root.right));
        return Math.min(minSub, Math.min(left, right));
    }
}

