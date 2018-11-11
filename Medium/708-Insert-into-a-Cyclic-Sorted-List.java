/*
MEDIUM
708. Insert into a Cyclic Sorted List

TIME: 1110
RESULT: 
NOTES:
*/
/*
Several Cases + Turning Point

Time: O(n)
Space: O(1)
*/


/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
};
*/
class Solution {
    public Node insert(Node head, int insertVal) {
        Node node = head;
        while(node != null){
            if(node.next.val <= node.val){//turning point
                if(insertVal <= node.next.val && insertVal <= node.val || insertVal >= node.next.val && insertVal >= node.val){
                    Node insert = new Node(insertVal, node.next);
                    node.next = insert;
                    return head;
                }
            }else if(insertVal >= node.val && insertVal <= node.next.val){
                Node insert = new Node(insertVal, node.next);
                node.next = insert;
                return head;
            }
            node = node.next;
        }
        Node res = new Node();
        res.val = insertVal;
        res.next = res;
        return res;
    }
}