/*
MEDIUM
222. Count Complete Tree Nodes
https://leetcode.com/problems/count-complete-tree-nodes/description/

TIME: 0902 - 2h
RESULT: 65% - 72ms
NOTES: 
这种题明显是找规律。应该是会利用规律迭代求解，所以不要想着一个一个算最后一层的。要想想怎么把所有 complete tree 确认出来，然后用（1<<n）一步步累计
*/
/**
SOLUTION REFERENCE: https://leetcode.com/problems/count-complete-tree-nodes/discuss/61958/Concise-Java-solutions-O(log(n)2)

往左下数层数。
如果一个 root 的 height(right subtree) 的值等于 height(root) - 1，说明 left subtree 是 complete tree，左边的值可以算出来，然后继续求右边的 subtree 的值
 */
//recursive
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int hleft = height(root.left);
        int hright = height(root.right);
        if( hleft == hright){//left subtree is complete tree
            return (1 << hleft + 1) + countNodes(root.right);
        }else{//right subtree(h-1) is complete tree
            return (1 << hright + 1) + countNodes(root.left);
        }
    }
    private int height(TreeNode root){
        return root == null ? -1 : 1 + height(root.left);
    }
}

//iterative
class Solution {
    public int countNodes(TreeNode root) {
        int result = 0;
        while(root != null){
            int h = height(root);
            if(height(root.right) == h - 1){//left subtree is complete tree
                result += (1 << h);
                root = root.right;
            }else{//right subtree(h-1) is complete tree
                result += (1 << h - 1);
                root = root.left;
            }
        }
        return result;
    }
    private int height(TreeNode root){
        return root == null ? -1 : 1 + height(root.left);
    }
}


/**
同时左边和右边向下
如果最右边没有左右 children，则目前值为 1 + 2 + 4 +...+ 2^h 还差最后一行；如果有左 children， 1 + 2 + 4 +...+ 2^(h+1) - 1。

 */
/*
SOLUTION 0: 
TIME: 0902 - 15min
RESULT: TLE
 */
class Solution {
    public int countNodes(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        int res = 0;
        if(root == null) return res;
        while(!q.isEmpty()){
            Queue<TreeNode> newQ = new LinkedList<TreeNode>();
            while(!q.isEmpty()){
                TreeNode node = q.poll();
                res++;
                if(node.left != null) newQ.offer(node.left);
                if(node.right != null) newQ.offer(node.right);
            }
            q = newQ;
        }
        return res;
    }
}
/**
同时左边和右边向下
如果最右边没有左右 children，则目前值为 1 + 2 + 4 +...+ 2^h 还差最后一行；如果有左 children， 1 + 2 + 4 +...+ 2^(h+1) - 1。

 */
class Solution {
    public int countNodes(TreeNode root) {
        TreeNode rootflag = root;
        double h = 0;
        while(root != null){
            h++;
            root = root.right;
        }
        double tree1 = Math.pow(2.0, h) - 1;//calculate the complete tree value
        
        double tree2 = -1, height = h;
        root = rootflag;
        while(tree2 == -1 && root != null){
            System.out.println(root.val);
            root = root.left;
            tree2 = findComplete(root, height--);
        }
        System.out.println(tree1);
        System.out.println(tree2);
        int res = (int)(tree1 + tree2 - (Math.pow(2.0, height) - 1));
        return res;
        
    }
    private double findComplete(TreeNode root, double count){
        while(root != null && count - 1 > 0){
            count--;
            root = root.right;
        }
        if(root == null) return Math.pow(2.0, count - 1);
        System.out.println(root.val);
        if(root.right != null) return Math.pow(2.0, count);
        else if(root.left != null) return Math.pow(2.0, count) - 1;
        else return -1;
    }
}