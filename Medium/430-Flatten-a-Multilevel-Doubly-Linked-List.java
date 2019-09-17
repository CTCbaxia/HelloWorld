/*
MEDIUM
430. Flatten a Multilevel Doubly Linked List

TIME: 
RESULT: 
NOTES:

*/
/*
Return tail of the listnode

Time: O(n)
Space: O(1)
*/
class Solution {
    Node cur;
    public Node flatten(Node head) {
        connect(head);
        return head;
    }
    private Node connect(Node node){//
        if(node == null) return null;
        while(node != null){
            if(node.child != null){
                Node next = node.next;
                Node tail = connect(node.child);
                
                node.next = node.child;
                node.child.prev = node;
                node.child = null;  
                
                if(next != null){
                    tail.next = next;
                    next.prev = tail;
                    node = next;
                }else return tail;
                
            }else if(node.next == null){
                return node;//return the final valid node
            }else{
                node = node.next;
            }
        }
        return null;//we will not arrive here
    }
}



/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
/*
Recursion + return tail (not null unless the original input is null)

Time: O(n)
Space: O(1)
*/
class Solution {
    public Node flatten(Node head) {
        connect(head);
        return head;
    }
    private Node connect(Node node){//return the tail of this connection **!!! tail should NOT be null
        if(node == null) return node;//if the original input is null
        while(node != null){
            if(node.child != null){//current node have child, need to be flatten
                Node tail = connect(node.child);//recursively flatten child, get tail of child
                Node then = node.next;//save the node.next first

                node.next = node.child;//connect node and child
                node.child.prev = node;
                node.child = null;
                
                if(then != null){//current node is not the tail of this list
                    tail.next = then;
                    then.prev = tail;
                    
                    node = then;
                }else{
                    return tail;//return the tail of its child
                }

            }else if(node.next == null){//if node have no child and node.next == null
                return node;
            }else{
                node = node.next;
            }
            
        }
        return null;
    }


}







/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
//不是很懂为什么会有问题，id对不上，但是我觉得思路是对的
*/
class Solution {
    Node cur;
    public Node flatten(Node head) {
        Node res = new Node(0, null, null, null);
        cur = res;
        helper(head);
        return res.next;
    }
    private void helper(Node node){
        if(node == null) return;
        while(node != null){
            cur.next = node;
            node.prev = cur;
            System.out.println(cur.val + " " + node.val);
            cur = cur.next;
            
            Node next = node.next;//这里要 hold 住 next，不然 child 之后 node.next 其实已经指向 child
            if(node.child != null){
                helper(node.child);
                node.child = null;
            } 
            node = next;
        }
    }
}