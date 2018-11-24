/*
MEDIUM
708. Insert into a Cyclic Sorted List

TIME: 1110
RESULT: 
NOTES:
*/
/*
分情况讨论：
一共三种插入情况：A M B
1. A <= M <= B 顺序情况
2. B <= A <= M 大于 turning point 的大值
3. M <= B <= A 小于 turning point 的小值

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
        while(node != null){//顺着环一直找，再回到原点之前肯定能找到出口
            if(node.val < node.next.val){
                if(insertVal >= node.val && insertVal <= node.next.val){
                    Node insert = new Node(insertVal, node.next);
                    node.next = insert;
                    return head;
                }
            }else{//turning point || only have one value || only have one node
                if(insertVal >= node.val || insertVal <= node.next.val){
                    Node insert = new Node(insertVal, node.next);
                    node.next = insert;
                    return head;                    
                }
            }     
            node = node.next;
        }
        //head == null
        Node res = new Node(insertVal, null);
        res.next = res;
        return res;
    }
}







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