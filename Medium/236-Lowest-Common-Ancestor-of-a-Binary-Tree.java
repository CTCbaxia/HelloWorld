/*
MEDIUM
236. Lowest Common Ancestor of a Binary Tree

TIME: 
RESULT: 65% - 8ms
NOTES:

*/
/*
Recursive:
如果我在左右分支的一边找到了match，那么 LCA 一定是第一个匹配点
如果我在两边都找到了match，那么root一定是返回值

Time: O(n)
Space: O(1)
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;//return once we find a target
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null) return root; //if left and right both have one, then root
        return left != null ? left : right;
        

    }

}
//test case

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4

6 8
root: 5
left: 6
right: null
return: 6

root: 1
left: null
right: 8
return: 8

root: 3
left: 6
right: 8
return: 3


/*
Recursive + Find
1. 看是不是有一个等于root，等于的话直接返回root
2. 看是不是都在同一个 subtree 中，
    在的话 recursively check subtree； 
    不在的话返回 root
3. find(node, p, q)找这个node是否存在 p 或 q
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;
        //check for the root
        if(root.val == p.val) return p;
        if(root.val == q.val) return q;

        //check for left and right
        boolean left = find(root.left, p, q);
        boolean right = find(root.right, p, q);
        
        if(left && right) return root;
        else if(left) return lowestCommonAncestor(root.left, p, q);
        else if(right) return lowestCommonAncestor(root.right, p, q);
        else return root;
    }
    private boolean find(TreeNode node, TreeNode p, TreeNode q){
        if(node == null) return false;
        if(node.val == p.val || node.val == q.val) return true;
        return find(node.left, p, q) || find(node.right, p, q);
    }
}




/*
DFS:
 一旦匹配一点，就 flag = 匹配点。tmpFlag = 匹配点的 parent （因为结果只可能在第一个匹配点的上面）
flag 记录如果继续向下查询得到匹配，该返回的点，
一旦 pop 的点超过了 flag，到达了 tmpFlag，就把 flag = tmpFlag

Time: O(n)
Space: O(n)
RESULT: 47% - 9ms
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p == q) return p;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode flag = null;
        TreeNode tmpFlag = null;
        TreeNode node = root;
        int matches = 0;
        while(!stack.isEmpty() || node != null){
            while(node != null){
                
                if(node == p || node == q){
                    matches++;
                    if(matches == 2) return flag;
                    else{
                        flag = node;
                        tmpFlag = stack.isEmpty() ? null : stack.peek();
                    }
                } 
                stack.push(node);
                node = node.left;
            }        
            
            TreeNode popnode = stack.pop();
            if(popnode == tmpFlag){
                flag = tmpFlag;
                if(flag == root) return flag;
                tmpFlag = stack.isEmpty() ? null : stack.peek();
            } 
            node = popnode.right;
        }
        return flag;
    }
}


