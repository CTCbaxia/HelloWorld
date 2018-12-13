/*
MEDIUM
426. Convert Binary Search Tree to Sorted Doubly Linked List


TIME: 
RESULT: 
NOTES:

*/
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
/*
Inorder traversal + Helper Function inorder

Time: O(n)
Space: O(logn)
*/
class Solution {
    Node pre;
    public Node treeToDoublyList(Node root){
        if(root == null) return root;//头尾链接会出问题
        Node head = new Node();
        pre = head;

        inorder(root);
        //pre is now the last node, manage the last head and tail
        pre.right = head.right;//如果只有一个node，这里就在自成环
        head.right.left = pre;

        return pre.right;
    }
    private void inorder(Node node){
        if(node == null) return;

        inorder(node.left);
        
        pre.right = node;
        node.left = pre;
        pre = node;//move the node

        inorder(node.right);
        return;
    }

}



/*
Helper Function DFS + Inorder Traversal
3ms - 92%
Time: O(n)
Space: O(1)
*/
class Solution {
    Node pre = null;
    public Node treeToDoublyList(Node root) {
        if(root == null) return root;
        Node head = new Node(0);
        head.right = root;
        pre = head;
        inorder(root);

        //manage the last head and tail
        pre.right = head.right;
        head.right.left = pre;
        return head.right;
    }
    //do inorder traversal
    private void inorder(Node node){
        if(node == null) return;
        inorder(node.left);
        pre.right = node;
        node.left = pre;
        pre = node;
        inorder(node.right);
        return;
    }
}
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node() {}
    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/




/*
Inorder traversal + Stack

Time: O(n)
Space: O(n)
*/
class Solution {
    public Node treeToDoublyList(Node root) {
        if(root == null) return root;
        Stack<Node> stack = new Stack<>();
        Node head = new Node(0, null, null);
        Node pre = head;
        
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            Node node = stack.pop();
            //link with pre
            pre.right = node;
            node.left = pre;
            pre = node;
            
            root = node.right;
        }
        //pre is the last node
        pre.right = head.right;
        head.right.left = pre;
        
        return head.right;
    }
}

