/*
MEDIUM
173. Binary Search Tree Iterator

TIME: 
RESULT: 95% -3ms
NOTES:

*/
/*
用一个 stack 来存当前最小的 treenode，每次 next 就弹出来，然后把当前 treenode.right 的所有左边节点存进 stack

Time: O(1)
Space: O(h)
*/
public class BSTIterator {
    Stack<TreeNode> stack = new Stack<TreeNode>();
    public BSTIterator(TreeNode root) {
        while(root != null){
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return(!stack.isEmpty());
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        int res = node.val;
        node = node.right;
        while(node != null){
            stack.push(node);
            node = node.left;
        }
        return res;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */