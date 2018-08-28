/*
MEDIUM
117. Populating Next Right Pointers in Each Node II
https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/

TIME: 0828 - 30min
RESULT: 87% - 1ms

*/
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