/*
MEDIUM
222. Count Complete Tree Nodes
https://leetcode.com/problems/count-complete-tree-nodes/description/

TIME: 0902 - 2h
RESULT: 65% - 72ms
NOTES: 
这种题明显是找规律。应该是会利用规律迭代求解，所以不要想着一个一个算最后一层的。要想想怎么把所有 complete tree 确认出来，然后用（1<<n）一步步累计
*/

/*
算 Depth + Find the full subtree and compute using 公式
recursively count the not full subtree

Time: O(logn^2) 
Since I halve the tree in every recursive step, I have O(log(n)) steps. Finding a height costs O(log(n)). So overall O(log(n)^2).
T(n) = T(n/2) + O(logn)? --> O(logn)

Space: O(1)
*/
class Solution {
    public int countNodes(TreeNode root){
        if(root == null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        if(Math.abs(left - right) == 0){
            //left is full
            return (1 << left) + countNodes(root.right);
        }else{
            //right is full
            return (1 << right) + countNodes(root.left);
        }
    }
    private int depth(TreeNode node){//null
        if(node == null) return 0;
        return 1 + depth(node.left);
    }        
}























/**
Find the full subtree and compute using 公式
recursively count the not full subtree


往左下数层数: 最需要往最左边走就知道这个 node 的height
如果左右两边相等，就知道左边一定是full，算出左边 + root，继续算右边
如果左右不相等，那么右边一定是full（按照他的高度），算出右边 + root，继续算左边

算出左子树（root.left）的高度
算出右子树（root.right）的高度
如果相等，说明左边一定是full
可以先算左子树
左子树的count: (1 << left) - 1
但是还要加上 root : (1 << left) - 1 + 1

然后继续算右子树


**位运算比加减运算第一个优先级，所以即使没有括号，也会先算加减

Time: O((logn)^2)
Space: O(1)
*/
//recursive
class Solution {
    public int countNodes(TreeNode root) {
          if(root == null) return 0;
          int left = height(root.left);
          int right = height(root.right);
          if(left == right){
              return (1 << left) + countNodes(root.right);
          }else{
              return (1 << right) + countNodes(root.left); 
          }
    }
    private int height(TreeNode node){
          if(node == null) return 0;
          return height(node.left) + 1;
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



/*
沿着左边path 和 沿着右边path走，如果height相等，说明整个tree都是完整的
否则，只算root一个，然后继续看root的左右字数个数

Time: O((logn)^2)
Space: O(1)

 */
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        
        int left = leftPath(root);
        int right = rightPath(root);
        if(left == right){
            return (1 << left) - 1;
        }else{
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
    private int leftPath(TreeNode node){
        int height = 0;
        while(node != null){
            height++;
            node = node.left;
        }
        return height;
    }
    private int rightPath(TreeNode node){
        int height = 0;
        while(node != null){
            height++;
            node = node.right;
        }
        return height;
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