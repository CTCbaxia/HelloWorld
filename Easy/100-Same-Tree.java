/*
EASY
100. Same Tree
https://leetcode.com/problems/same-tree/description/

TIME: 0826 - 10min
RESULT: 20% - 4ms
NOTES: 
高票满分回答没区别
*/

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        else if(p != null && q != null && p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        else return false;
        
    }
}


class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        boolean res = false;
        if(p == null && q == null) return true;
        else if(p != null && q != null){
            if(p.val == q.val){
                res = isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
        }
        return res;
    }
}