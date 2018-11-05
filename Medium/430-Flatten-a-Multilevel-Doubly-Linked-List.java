/*
MEDIUM
430. Flatten a Multilevel Doubly Linked List

TIME: 
RESULT: 
NOTES:

*/
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