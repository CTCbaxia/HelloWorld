/*
EASY
653. Two Sum IV - Input is a BST

*/
/*
Inorder (没用到BST)

Time: O(n)
Space: O(n)
*/
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        return inorder(root, k, new HashSet<>());
    }
    private boolean inorder(TreeNode node, int k, Set<Integer> set){
        if(node == null) return false;
        if(inorder(node.left, k, set)) return true;
        
        if(set.contains(k - node.val)) return true;
        set.add(node.val);
        
        if(inorder(node.right, k, set)) return true;
        return false;
    }
}



/*
Inorder Stack -- BST 顺序性质

Time: O(n)
Space: O(n ~ logn) -- logn in avg, n in worst case 八字形
*/
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        Stack<TreeNode> left = new Stack<>();
        Stack<TreeNode> right = new Stack<>();
        addNode(root, left, "left");
        addNode(root, right, "right");
        while(left.peek() != right.peek()){
            int n = left.peek().val + right.peek().val;
            if(n == k){
                return true;
            }else if(n < k){//left pop
                TreeNode node = left.pop();
                node = node.right;
                addNode(node, left, "left");
            }else{//right pop
                TreeNode node = right.pop();
                node = node.left;
                addNode(node, right, "right");                
            }
        }
        return false;
    }
    private void addNode(TreeNode node, Stack<TreeNode> stack, String dir){
        while(node != null){
            stack.push(node);
            if(dir.equals("left")) node = node.left;
            else node = node.right;
        }
        return;
    }
}