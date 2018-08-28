/*
EASY
101. Symmetric Tree
https://leetcode.com/problems/symmetric-tree/description/

TIME: 0828 - 1h
RESULT: 20% - 11ms （足够了，高票和我没区别）
NOTES: 

*/
/*
SOLUTION 0: Recursive( recursive 一般需要一个辅助函数？)
TIME: 0828 - 1h
RESULT: 20% - 11ms
*/
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return subSym(root.left, root.right);
    }
    private boolean subSym(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null) return false;
        if(root1.val != root2.val) return false;
        
        return subSym(root1.left, root2.right) && subSym(root1.right, root2.left);
    }
}


/*
Wrong code helps you learn:
这里不能用遍历的方法，这样遍历出来的结果顺序会掩盖实际的不对称
比如： [1,2,3,3,null,2,null]
*/
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        List<TreeNode> left = new ArrayList<TreeNode>();
        List<TreeNode> right = new ArrayList<TreeNode>();
        inorder(root.left, left);
        reinorder(root.right, right);
        if(left.size() != right.size()) return false;
        for(int i = 0; i < left.size(); i++){
            if(left.get(i).val != right.get(i).val) return false;
            System.out.println(left.get(i).val + " " + right.get(i).val);
        }
        return true;
    }
    private void inorder(TreeNode root, List<TreeNode> list){
        if(root == null) return;
        inorder(root.left, list);
        list.add(root);
        inorder(root.right, list);
    }
    
    private void reinorder(TreeNode root, List<TreeNode> list){
        if(root == null) return;
        reinorder(root.right, list);
        list.add(root);
        reinorder(root.left, list);
    }    
}