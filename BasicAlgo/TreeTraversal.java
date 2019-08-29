/*
Tree Traversal - Iteration - Using Stack

Time: O(n)
Space: O(logn)
*/
class Solution {
    //Postorder - Interation - Stack
    //leftnode, rightnode, node
    //Has a marker to check if it's children has been traversed, put children in right, left order
    public List<Integer> postorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode last = root;//mark the last put-to-res node
        while(!stack.isEmpty()){
            TreeNode node = stack.peek();
            //check if its children is processed, may only have one child
            if(node.left == null && node.right == null || node.left == last || node.right == last){
                res.add(stack.pop().val);
                last = node;
            }else{
                if(node.right != null) stack.push(node.right);
                if(node.left != null) stack.push(node.left);
            }
        }
        return res;
    }
    
    //Pretorder - Interation - Stack
    //node, leftnode, rightnode, 
    //Go all the way to the left and add to res first
    public List<Integer> preorder(TreeNode root){//root-left-right
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root != null){
            if(root != null){
                res.add(root.val);//add to res first
                stack.push(root);
                root = root.left;
            }else{
                TreeNode node = stack.pop();
                root = node.right;
            }
        }
        return res;
    }

    //Inorder - Interation - Stack
    //leftnode, node, rightnode, 
    //Go all the way to the left, pop and add to res 
    public List<Integer> inorder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root != null){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                TreeNode node = stack.pop();
                res.add(node.val);//add to res here
                root = node.right;
            }
        }
        return res;
    }

}