/*
HARD
99. Recover Binary Search Tree

TIME: 
RESULT: 
NOTES: 
*/
/*
Inorder DFS:

有冲突一定是前面的大于后面。（前面有个大的，后面有个小的，所以得让first = 大的，second = 小的）
第一次遇到冲突，就把first = 前面，second = 后面
第二次遇到冲突，说明应该两块交换。
而 inorder traverse 遇到的第二次，一定是一群大值里面混入了一个小值。就把 second = 那个小值（也就是后面的）


Time: O(n)
Space: O(d) //recursive
*/
class Solution {
    TreeNode first = null;
    TreeNode second = null;
    TreeNode pre = new TreeNode(Integer.MIN_VALUE);
    
    public void recoverTree(TreeNode root) {
        inorder(root);//find two nodes
        
        //swap
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
        return;
    }
    private void inorder(TreeNode node){
        if(node == null) return;
        inorder(node.left);
        
        if(first == null && pre.val >= node.val){//first = larger value
            first = pre;
        }
        if(first != null && pre.val >= node.val){//second = smaller value
            second = node;
        }
        pre = node;//update pre node
        
        inorder(node.right);
        return;
    }
}

/*
if only one node not in the right position: swap with the conflix parent
if two node not in the right position: swap two nodes

n1, p1
n2, p2
*/
class Solution {
    TreeNode n1, n2, p1, p2;
    public void recoverTree(TreeNode root) {
        TreeNode min = new TreeNode(Integer.MIN_VALUE);
        TreeNode max = new TreeNode(Integer.MAX_VALUE);
        helper(root, min, max);
        if(n1 != null && n2 != null){
            if((p1.val - n1.val) * (p1.val - n2.val) > 0) swap(p1, n2);
            else if((p2.val - n2.val) * (p2.val - n1.val) > 0) swap(p2, n1);
            else swap(n1, n2);
        }else if(n1 != null){
            swap(n1, p1);
        }else{
            swap(n2, p2);
        }
        return;
    }
    private void helper(TreeNode node, TreeNode left, TreeNode right){
        if(node == null) return;
        if(node.val <= left.val){
            if(n1 == null){
                n1 = node;
                p1 = left;
            }else{
                n2 = node;
                p2 = left;
            }
            helper(node.left, node, left);
        }else{
            helper(node.left, left, node);
        }
        if(node.val >= right.val){
            if(n1 == null){
                n1 = node;
                p1 = right;
            }else{
                n2 = node;
                p2 = right;
            }     
            helper(node.right, right, node);
        }else{
            helper(node.right, node, right);
        }
        
        return;
    }
    private void swap(TreeNode node1, TreeNode node2){
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp; 
        return;
    }
}