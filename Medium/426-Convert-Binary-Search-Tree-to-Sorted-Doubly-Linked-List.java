/*
MEDIUM
426. Convert Binary Search Tree to Sorted Doubly Linked List


TIME: 
RESULT: 
NOTES:
Inorder Traversal
*/
/*
Helper Function DFS
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
Helper Function DFS
3ms - 92%
Time: O(n)
Space: O(1)
*/
class Solution {
    public Node treeToDoublyList(Node root) {
        if(root == null) return root;
        Stack<Node> stack = new Stack<Node>();
        Node head = new Node(0);
        Node pre = head;
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            
            Node cur = stack.pop();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
            root = cur.right;
        }
        pre.right = head.right;
        head.right.left = pre;
        return head.right;
    }
}