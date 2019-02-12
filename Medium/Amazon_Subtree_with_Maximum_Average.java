/*
5. Subtree: Maximum average node
Given a binary tree, find the subtree with maximum average. Return the root of the subtree.


!!!! -Double.MAX_VALUE;//double 里面 Double.MIN_VALUE 是正数
 */
//清晰一些
public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */
    Subtree res = null;
    public TreeNode findSubtree2(TreeNode root) {
        // write your code here
        if(root == null) return root;
        subtreeSum(root);
        return res.node;
    }
    private Subtree subtreeSum(TreeNode node){
        if(node == null) return new Subtree(null, 0, 0);
        
        Subtree left = subtreeSum(node.left);
        Subtree right = subtreeSum(node.right);
        
        int sum = node.val + left.sum + right.sum;
        int num = 1 + left.num + right.num;
        double avg = (double) sum / (double) num;

        if(res == null || avg > (double)res.sum / (double) res.num){
            res = new Subtree(node, sum, num);
        }
        return new Subtree(node, sum, num);
    } 
    private class Subtree{
        TreeNode node;
        int sum;
        int num;
        public Subtree(TreeNode _node, int _sum, int _num){
            node = _node;
            sum = _sum;
            num = _num;
        }
    }
}



//intuitive 一些
public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */
    Subtree res = null;
    public TreeNode findSubtree2(TreeNode root) {
        // write your code here
        subtreeSum(root);
        return res;
    }
    private Subtree subtreeSum(TreeNode node){
        if(node == null) return new Subtree(null, 0, 0);
        
        Subtree left = subtreeSum(node.left);
        Subtree right = subtreeSum(node.right);
        
        int sum = node.val + left.sum + right.sum;
        int num = 1 + left.num + right.num;
        double avg = (double) sum / (double) num;

        if(res == null || avg > max){
            res = new Subtree(node, sum, num);
        }
        return new Subtree(node, sum, num);
    } 
    private class Subtree{
        TreeNode node;
        int sum;
        int num;
        public Subtree(TreeNode _node, int _sum, int _num){
            node = _node;
            sum = _sum;
            num = _num;
        }
    }
}