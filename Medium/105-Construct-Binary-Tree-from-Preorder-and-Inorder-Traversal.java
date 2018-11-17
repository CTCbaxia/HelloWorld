/*
MEDIUM
105. Construct Binary Tree from Preorder and Inorder Traversal
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/

TIME: 0808 - 3h
RESULT: 95% - 3ms
NOTES: 
树的三种遍历方式：Depth First Traversals
(a) Inorder (Left, Root, Right) : 4 2 5 1 3
(b) Preorder (Root, Left, Right) : 1 2 4 5 3
(c) Postorder (Left, Right, Root) : 4 5 2 3 1

Breadth First or Level Order Traversal : 1 2 3 4 5


THOUGHTS:
preorder 的第一个值为最大的 root。
inorder 每次找到 sub 里面的 root，root 左边为 left 的分支，右边为 right 的分支。将左右的 subarray 带入 getChild 继续求解

依次迭代。
*/

/*
4 Pointers: find the left and right bound

Time: O(n) 
Space: O(1)
*/
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();//map for better search root
        for(int i = 0; i < len; i++){
            map.put(inorder[i], i);
        }
        
        return helper(preorder, inorder, 0, len - 1, 0, len - 1, map);
    }
    private TreeNode helper(int[] preorder, int[] inorder, int pl, int pr, int il, int ir, Map<Integer, Integer> map){
        
        if(pr - pl < 0) return null;
        int rootval = preorder[pl];
        TreeNode root = new TreeNode(rootval);
        if(pr - pl == 0) return root;
        
        int i = map.get(rootval);//find the root in inorder map
        int leftlen = i - il;//left subtree lenghth
        root.left = helper(preorder, inorder, pl + 1, pl + leftlen, il, i - 1, map);
        root.right = helper(preorder, inorder, pl + leftlen + 1, pr, i + 1, ir, map);
        return root;
    }
}








class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length; 
        return build(preorder, 0, len - 1, inorder, 0, len - 1);
    }
    private TreeNode build(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR){
        if(preR - preL < 0) return null;
        TreeNode root = new TreeNode(preorder[preL]);
        int index = inL;
        while(index <= inR && inorder[index] != preorder[preL]) index++;
        
        int len = index - inL;
        root.left = build(preorder, preL + 1, preL + len, inorder, inL, index - 1);
        root.right = build(preorder, preL + len + 1, preR, inorder, index + 1, inR);
        return root;
    }
}





//201808
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        if(len == 0) return null;
        
        Map<Integer, Integer> inmap = new HashMap<Integer, Integer>();
        for(int i = 0; i < len; i++){
            inmap.put(inorder[i],i);
        }
        
        return getChild(inmap, inorder, 0, len - 1, preorder, 0);
    }
    private TreeNode getChild(Map<Integer, Integer> inmap, int[] inorder, int inl, int inr, int[] preorder, int prel){
        if(inl > inr) return null;
        TreeNode root = new TreeNode(preorder[prel]);
        if(inl < inr){
            int inroot = inmap.get(preorder[prel]);
            root.left = getChild(inmap, inorder, inl, inroot - 1, preorder, prel + 1);
            root.right = getChild(inmap, inorder, inroot + 1, inr, preorder, prel + inroot - inl + 1);
        }
        return root;
    }
}
/*
SOLUTION 0: CTC
TIME: 0808 - 3h
RESULT: 73% - 4ms
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        if(len == 0) return null;
        
        TreeNode root = new TreeNode(preorder[0]);
        Map<Integer, Integer> premap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> inmap = new HashMap<Integer, Integer>();
        for(int i = 0; i < len; i++){
            premap.put(preorder[i],i);
            inmap.put(inorder[i],i);
        }
        
        int inroot = inmap.get(preorder[0]);
        if(0 <= inroot - 1){
            root.left = getChild(premap, inmap, inorder, 0, inroot - 1, preorder, 1, inroot);
        }
        if(inroot + 1 <= len - 1){
            root.right = getChild(premap, inmap, inorder, inroot + 1, len - 1, preorder, inroot + 1, len - 1);
        }
        return root;
    }
    private TreeNode getChild(Map<Integer, Integer> premap, Map<Integer, Integer> inmap, int[] inorder, int inl, int inr, int[] preorder, int prel, int prer){
        TreeNode root = new TreeNode(preorder[prel]);
        if(inl < inr){
            int inroot = inmap.get(preorder[prel]);
            if(inl <= inroot - 1){
                root.left = getChild(premap, inmap, inorder, inl, inroot - 1, preorder, prel + 1, prel + inroot - inl);
            }
            if(inroot + 1 <= inr){
                root.right = getChild(premap, inmap, inorder, inroot + 1, inr, preorder, prel + inroot - inl + 1, prer);
            }
        }
        return root;
    }

}