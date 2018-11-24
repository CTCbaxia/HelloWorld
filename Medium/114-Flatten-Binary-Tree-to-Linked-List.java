/*
MEDIUM
114. Flatten Binary Tree to Linked List
https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/

TIME: 0827 - 1h
RESULT: 8% - 14ms
NOTES:
1. 题目没有返回值，就是要的这个 root 地址的值
2. 注意 DFS + recursive 的特质，他会一直走到头。比如向左，会一直走到左边的头部，这样就很难从左边开始顺序 flatten 链接上去
3. 这种题要学会利用题目的这个函数，用 recursive 来带。
*/
/*
Recursive:
Helper function: flatten 且 return last Node 以减少每次都要loop left node 的操作

Time: O(n) -- 和结果完全相反的情况
Space: O(h) due to recursive program stack, where h is tree height.
*/
class Solution {
    public void flatten(TreeNode root) {
        flattenHelper(root);
        return;
    }
    public TreeNode flattenHelper(TreeNode root){//flatten and return last node 
        if(root == null) return root;
        TreeNode leftLast = flattenHelper(root.left);
        TreeNode rightLast = flattenHelper(root.right);
        
        if(leftLast != null){//如果 root 有左子树
            leftLast.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        //如果右边有点，返回右边；如果没有，如果左边有点，返回左边；如果都没有，返回root
        return rightLast != null ? rightLast : (leftLast != null ? leftLast : root);
    }
}








/*
Recursive

Time: O(n^2) -- 和结果完全相反的情况
Space: O(h) due to recursive program stack, where h is tree height.
*/
class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;
        flatten(root.left);
        flatten(root.right);
        
        TreeNode node = root.left;//找到左边list的最后一个点
        if(node == null) return;//左边为null就直接结束了
        while(node.right != null){
            node = node.right;
        }
        
        node.right = root.right;//右边搭到左边的结尾
        root.right = root.left;
        root.left = null;
        return;
    }
}






/*
Preorder
Flatten left
Flatten right
link left and right

Time: O(n)
Space: O(1)
*/
class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        
        if(left != null){
            root.right = left;
            while(left.right != null){
                left = left.right;
            }
            left.right = right;
        }else{
            root.right = right;
        }
        return;
    }
}














/*
SOLUTION REFERENCE: post order 的倒着遍历法，很巧妙，但是对于我不 intuitive
TIME: 0827 - 30min
RESULT: 21% - 12ms

THOUGHTS:
likely postorder traversal
flatten from the very right node and save the node that has been flatted to the prev
*/
class Solution {

    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if(root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
/*
SOLUTION 0: 
TIME: 0827 - 1h
RESULT: 8% - 14ms

THOUGHTS:
flatten through preorder traversal
the test is based on the initial root address
*/

class Solution {
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        preorder(root, list);
        if(root != null) root.left = null;
        for(int i = 1; i < list.size(); i++){
            TreeNode next = new TreeNode(list.get(i).val);
            root.right = next;
            root = root.right;
        }
        return;
    }
    private void preorder(TreeNode root, List<TreeNode> list){
        if(root == null) return;
        
        list.add(root);
        
        preorder(root.left, list);
        preorder(root.right, list);

        return;
    }
}


/*
Wrong Code that help you correct your address convey method

*/
class Solution {
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        preorder(root, list);
        
        TreeNode res = new TreeNode(0);
        root = res;//这个时候你已经丢失了题目要测试的 root 地址，所以不过如何操作，题目的返回值都只会是最初值
        for(TreeNode t : list){
            TreeNode next = new TreeNode(t.val);
            root.right = next;
            root = root.right;
        } 

        root = res.right;

        return;
    }
    private void preorder(TreeNode root, List<TreeNode> list){
        if(root == null) return;
        
        list.add(root);
        
        preorder(root.left, list);
        preorder(root.right, list);

        return;
    }
}


[1,2,5,3,4,null,6]



//flatten through preorder traversal
//the test is based on the initial root address
class Solution {


    public void flatten(TreeNode root) {
        TreeNode next = new TreeNode(0);
        TreeNode newroot = next;
        preorder(root, next);
        for(int i = 0; i < 6; i++){
            newroot = newroot.right;
            System.out.println(newroot.val);
        }
        root.right = newroot.right;
        root.left = null;
        return;
    }
    private void preorder(TreeNode root, TreeNode next){
        if(root == null) return;
        
        TreeNode node = new TreeNode(root.val);
        System.out.println("pre: "+next.val);
        next.right = node;
        next = next.right;
        System.out.println(next.val);//不是很懂为什么这里 next 遍历出问题 -----这里学问大了我告诉你！！！！看末尾
        preorder(root.left, next);
        preorder(root.right, next);

        return;
    }
}
先遍历一个 root 的左子树，
然后遍历右子树。
虽然你在遍历左子树的时候， next 已经走到左子树的最后一个遍历点
但是对于跳回到 root 的时候，接下来的右子树的传参 next 仍然为该方法里面的 next 位置，而不是前面遍历左子树到最后的位置。
所以遍历到 4 的时候，会浪费掉之前的遍历结果的，直接由 2 链接到 4.
以此类推，遍历到 1 的右子树的时候，next 会回归原位，浪费掉之前遍历的 1 左字数的结果。
最后得到的结果应为 1 - 5 - 6.
要避免这个问题，就要找一个外部变量，每次都是最新的节点。

/*
SOLUTION 1: 
TIME: 0827 - 30min
RESULT: 0% - 36ms

THOUGHTS:
inorder 遍历，用外部变量存一个 linknote，最后把 root.right 链接过去
*/
class Solution {

    private TreeNode next = new TreeNode(0);
    private TreeNode newRoot = next;//注意最后 next 就跑到最后一位了，所以要找个 treenode 把开头盯住
    public void flatten(TreeNode root) {
        if(root == null) return;
        preorder(root);

        root.right = newRoot.right.right;
        root.left = null;
        return;
    }
    private void preorder(TreeNode root){
        if(root == null) return;
        
        TreeNode node = new TreeNode(root.val);
        next.right = node;
        next = next.right;
        System.out.println(next.val);
        preorder(root.left);
        preorder(root.right);
        return;
    }
}
