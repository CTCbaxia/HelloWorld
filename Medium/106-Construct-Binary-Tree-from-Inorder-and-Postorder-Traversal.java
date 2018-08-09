/*
MEDIUM
106. Construct Binary Tree from Inorder and Postorder Traversal
https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/

TIME: 0809 - 15min
RESULT: 91% - 3ms
NOTES: 
树的三种遍历方式：Depth First Traversals
(a) Inorder (Left, Root, Right) : 4 2 5 1 3
(b) Preorder (Root, Left, Right) : 1 2 4 5 3
(c) Postorder (Left, Right, Root) : 4 5 2 3 1

Breadth First or Level Order Traversal : 1 2 3 4 5


THOUGHTS:

*/
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        
        Map<Integer, Integer> inmap = new HashMap<Integer, Integer>();
        for(int i = 0; i < len; i++){
            inmap.put(inorder[i], i);
        }
        return getChild(inmap, inorder, postorder, 0, len - 1, len - 1);
    }
    private TreeNode getChild(Map<Integer, Integer> inmap, int[] inorder, int[] postorder, int inl, int inr, int postr){
        if(inl > inr) return null;
        TreeNode root = new TreeNode(postorder[postr]);
        if(inl < inr){
            int inroot = inmap.get(postorder[postr]);
            root.left = getChild(inmap, inorder, postorder, inl, inroot - 1, postr - inr + inroot - 1);//postr - (inr - (inroot + 1) + 1) - 1
            root.right = getChild(inmap, inorder, postorder, inroot + 1, inr, postr - 1);
        }
        return root;
    }
}