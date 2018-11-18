/*
MEDIUM
98. Validate Binary Search Tree
https://leetcode.com/problems/validate-binary-search-tree/description/

TIME: 0826 - 4h
RESULT: 43% - 1ms
NOTES:
1. BST: 左小右大，并且左边的所有 subtree 都要小于 root，右边的所有 subtree 都要大于 root
2. 花花酱视频： https://www.youtube.com/watch?v=Jq0Wk9xeQ0U

METHODS:
1. Range Check + Recursive
2. Inorder Traversal

*/
/*
Range check:

Time: O(n)
Space: O(d)
*/
class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean helper(TreeNode node, long min, long max){
        if(node == null) return true;
        if(node.val > min && node.val < max){
            return helper(node.left, min, node.val) && helper(node.right, node.val, max);
        }else{
            return false;
        }
    }
}

/*
Inorder Traverse: DFS
pre < cur

Time: O(n)
Space: O(d)
*/
class Solution {
    TreeNode pre = null;//save pre node
    public boolean isValidBST(TreeNode root) {
        return isValid(root);
    }
    private boolean isValid(TreeNode node){
        if(node == null) return true;
        //left
        if(!isValid(node.left)) return false;
        
        //node
        
        if(pre != null && pre.val >= node.val) return false;
        pre = node;
        
        //right
        if(!isValid(node.right)) return false;
        return true;
    }

}


/*
Inorder Traverse:
pre < cur

Time: O(n)
Space: O(logn)
*/
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            if(pre != null && pre.val >= node.val) return false;
            pre = node;
            root = node.right;
        }
        return true;
    }

}





/*
SOLUTION REFERENCE 1: Inorder Traversal
If inorder traverse the BST, the value should be sorted. 
So just need to check if the previous value is smaller than the current value

TIME: 0826 - 30min
RESULT: 43% - 1ms
*/
class Solution {
    public boolean isValidBST(TreeNode root) {
        List<TreeNode> inorder = new ArrayList<TreeNode>();
        return inorder(root, inorder);
    }    
    private boolean inorder(TreeNode root, List<TreeNode> inorder){
        if(root == null) return true;
        if(!inorder(root.left, inorder)) return false;

        if(inorder.size() == 0) inorder.add(root);
        else if(inorder.size() > 0){
            if(inorder.get(inorder.size() - 1).val < root.val){
                inorder.add(root);
            }else{
                return false;
            }
        }

        if(!inorder(root.right, inorder)) return false;
        
        return true;
    }
}

/*
SOLUTION REFERENCE 1: Inorder Traversal

TIME: 0826 - 30min
RESULT: 6.8% - 4ms
*/
class Solution{
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode pre = null;
        while(root != null || !stack.empty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            if(pre != null && pre.val >= node.val) return false;
            pre = node;
            root = node.right;
        }
        return true;
    }    
}



/*
SOLUTION REFERENCE 0: Recursive
- Limit the value range for the subtrees

TIME: 0826 - 3h
RESULT: 43% - 1ms
TC: O(n)
SC: O(1)

THOUGHTS:
直接找到每个 root 的取值范围，DFS 层层传递。
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean isValid(TreeNode root, long min, long max){
        if(root == null) return true;
        else{
            if(root.val <= min || root.val >= max) return false;
            else return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
        }
    }    
}


/*
SOLUTION 0:

THOUGHTS:
DFS 思路不清晰
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        boolean res = true;
        if(root == null) return true;
        else{
            if(root.left == null){}
            else if(root.left != null && root.left.val < root.val)
                res = res && validLeft(root.left, root.val, 0);
            else return false;
            
            System.out.println(res);
            if(root.right == null){}
            else if(root.right != null && root.right.val > root.val)
                res = res && validRight(root.right, root.val, 1);
            else return false;
            System.out.println(res);
        }
        return res;
    }
    private boolean validLeft(TreeNode root, int parentVal, int dir){
        System.out.println("root.val:" + root.val + "  parentVal:" + parentVal + "  dir:" + dir );
        if(root.left == null) return true;
        else if (root.left != null && root.left.val < root.val){
            if(dir == 0 || dir == 1 && root.left.val > parentVal){
                return validLeft(root.left, root.val, 0) && validRight(root.right, root.val, 1);
            }
        }else{
            return false;
        }
        return false;
    }           
    private boolean validRight(TreeNode root, int parentVal, int dir){
        System.out.println("root.val:" + root.val + "  parentVal:" + parentVal + "  dir:" + dir );
        if(root.right == null) return true;
        else if (root.right != null && root.right.val > root.val){
            System.out.println("inside     root.right.val:" + root.right.val + "  root.val:" + root.val + "  dir:" + dir );
            if(dir == 1 || dir == 0 && root.right.val < parentVal){
                return validLeft(root.left, root.val, 0) && validRight(root.right, root.val, 1);
            }
        }else{
            return false;
        }
        return false;
    }    
}