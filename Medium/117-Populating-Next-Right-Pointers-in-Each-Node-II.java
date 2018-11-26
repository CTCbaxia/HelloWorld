/*
MEDIUM
117. Populating Next Right Pointers in Each Node II
https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/

TIME: 0828 - 30min
RESULT: 87% - 1ms

*/
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
/*
BFS and use a pre node 

Time: O(n)
Space: O(1)
*/
//更 intuitive
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode head = root;
        TreeLinkNode node = root;
        while(head != null){
            node = head;
            TreeLinkNode pre = null;
            while(node != null){//loop for this level
                if(node.left != null){//left child
                    if(pre != null) pre.next = node.left;
                    pre = node.left;
                }
                
                if(node.right != null){//right child
                    if(pre != null) pre.next = node.right;
                    pre = node.right;
                }

                node = node.next;//loop this level
            }
            //找到下一个层的起始点：in this level, find the head that has a child
            while(head != null && head.left == null && head.right == null){
                head = head.next;
            }
          if(head == null) return;//if no node has a child
          head = head.left != null ? head.left : head.right;
        }
    }
}
//更简洁
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode head = root;
        TreeLinkNode node = root;
        while(head != null){
            node = head;
            head = null;//除非这一层的节点有child，才会更新head，才会有下一个loop
            TreeLinkNode pre = null;
            while(node != null){//loop for this level
                if(node.left != null){//left child
                    if(pre == null) head = node.left;//first child from this level
                    if(pre != null) pre.next = node.left;
                    pre = node.left;
                }
                
                if(node.right != null){//right child
                    if(pre == null) head = node.right;//first child from this level
                    if(pre != null) pre.next = node.right;
                    pre = node.right;
                }
                node = node.next;//loop this level
            }
        }
    }
}



public class Solution {
    public void connect(TreeLinkNode root) {
        while(root != null){
            TreeLinkNode theLeft = root;
            TreeLinkNode pre = null;
            while(root != null){
                pre = nextLevel(root, pre);
                root = root.next;
            }
            
            while(theLeft != null){
                if(theLeft.left != null){
                    root = theLeft.left;
                    theLeft = null;
                }else if(theLeft.right != null){
                    root = theLeft.right;  
                    theLeft = null;
                }else{
                    theLeft = theLeft.next;
                }
            }
        }
        return;
    }
    private TreeLinkNode nextLevel(TreeLinkNode root, TreeLinkNode pre){

        if(root.left == null && root.right == null) return pre;
        
        if(pre != null) pre.next = (root.left != null) ? root.left : root.right;
        
        if(root.left != null && root.right != null) root.left.next = root.right;
        
        if(root.right != null) pre = root.right;
        else if(root.left != null) pre = root.left;
        return pre;
    }
}

/*
SOLUTION 1: 不用辅助函数，用了个外部变量 pre 其实一样
TIME: 0828 - 30min
RESULT: 33% - 2ms
*/

public class Solution {
    public void connect(TreeLinkNode root) {
        while(root != null){//for next each level
            
            TreeLinkNode theLeft = root;
            TreeLinkNode pre = null;
            while(root != null){
                if(root.left == null && root.right == null){
                    root = root.next;
                    continue;
                }
                if(pre != null) pre.next = (root.left != null) ? root.left : root.right;
                
                if(root.left != null && root.right != null) root.left.next = root.right;
                
                if(root.right != null) pre = root.right;
                else if(root.left != null) pre = root.left;
                
                root = root.next;
            }
            
            while(theLeft != null){
                if(theLeft.left != null){
                    root = theLeft.left;
                    theLeft = null;
                }else if(theLeft.right != null){
                    root = theLeft.right;  
                    theLeft = null;
                }else{
                    theLeft = theLeft.next;
                }
            }
        }
        return;
    }

}