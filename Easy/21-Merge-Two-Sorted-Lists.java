/*
21. Merge Two Sorted Lists
*/          
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode node = result;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                node.next = l1;
                l1 = l1.next;
            }else if(l1.val > l2.val){
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
            
        }
        if(l1 != null){
            node.next = l1;
        }else if(l2 != null){
            node.next = l2;
        }
        return result.next;  
           
    }
}
 






/*
 Solution 1: 结果还不错啊
 基本是中等水平
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

Definition for singly-linked list.
public class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }

     
class Solution {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l0 = new ListNode(0);
        ListNode lresult = l0;
        while(l1 != null && l2 != null){
            if(l2.val <= l1.val){
                l0.val = l2.val;
                l2 = l2.next;
            }else if(l2.val > l1.val){
                l0.val = l1.val;
                l1 = l1.next;
            }
            l0.next = new ListNode(0);
            l0 = l0.next;
        }
        if(l1 != null){
            l0.val = l1.val;
            l0.next = l1.next;
        }else if(l2 != null){
            l0.val = l2.val;
            l0.next = l2.next;
        }else{
            return l1;
        }
        return lresult;
    }
}




/*
 JAVA visualizer
 */
public class ListNode { //若不想写成专门的Class，则直接命名这个class，在里面写构造函数，方法，执行函数。且ClassName需要写成构造函数的同名
    
    public int val;
    public ListNode next;
    
    public ListNode(int v, ListNode n){//构造函数
        val = v;
        next = n;
        
    }
    
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l0 = new ListNode(0,null);
        ListNode lresult = l0;
        while(l1 != null && l2 != null){
            if(l2.val <= l1.val){
                l0.val = l2.val;
                l2 = l2.next;
            }else if(l2.val > l1.val){
                l0.val = l1.val;
                l1 = l1.next;
            }
            l0.next = new ListNode(0,null);
            l0 = l0.next;
        }
        if(l2 == null ){
            l0.val = l1.val;
            l0.next = l1.next;
        }else{
            l0.val = l2.val;
            l0.next = l2.next;
        }
        return lresult;
    }
    
    public static void main(String[] args) {
        ListNode L1 = new ListNode(4,null);
        L1 = new ListNode(2, L1);
        L1 = new ListNode(1, L1);
        
        ListNode L2 = new ListNode(4,null);
        L2 = new ListNode(3, L2);
        L2 = new ListNode(1, L2);
        
        ListNode L_result = mergeTwoLists(L1,L2);
        System.out.println(L_result);
        
    }
    
}



/*为什么不行*/
public class ClassNameHere {
    
    //定义一个类，包含对象的特质 + 动作
    public class ListNode {
        public int val;
        public ListNode next;
        //构造器
        public ListNode(int x) {
            val = x;
        }
    }
    
    
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(4);
        
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(4);
        
        mergeTwoLists(l1,l2);
        
        
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l0 = new ListNode(0);
        ListNode lresult = l0;
        while(l1 != null && l2 != null){
            if(l2.val <= l1.val){
                l0.val = l2.val;
                l2 = l2.next;
            }else if(l2.val > l1.val){
                l0.val = l1.val;
                l1 = l1.next;
            }
            l0.next = new ListNode(0);
            l0 = l0.next;
        }
        if(l1 != null){
            l0.val = l1.val;
            l0.next = l1.next;
        }else if(l2 != null){
            l0.val = l2.val;
            l0.next = l2.next;
        }else{
            return l1;
        }
        return lresult;
    }
    
}



public class Try {
    
    
    public static class ListNode{ //如果想在内部专门定义一个Class，则要写成static，否则public static void main(String[] args) 无法调用构造函数
        public int val;
        public ListNode next;
        public ListNode(int v, ListNode n) {
            val = v;
            next = n;
        }
        
        public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode l0 = new ListNode(0,null);
            ListNode lresult = l0;
            while(l1 != null && l2 != null){
                if(l2.val <= l1.val){
                    l0.val = l2.val;
                    l2 = l2.next;
                }else if(l2.val > l1.val){
                    l0.val = l1.val;
                    l1 = l1.next;
                }
                l0.next = new ListNode(0,null);
                l0 = l0.next;
            }
            if(l1 != null){
                l0.val = l1.val;
                l0.next = l1.next;
            }else if(l2 != null){
                l0.val = l2.val;
                l0.next = l2.next;
            }else{
                return l1;
            }
            return lresult;
        }
    }
    
    
    public static void main(String[] args) {
        ListNode l1 = new ListNode(4,null);
        l1 = new ListNode(3,l1);
        l1 = new ListNode(1,l1);
        
        ListNode l2 = new ListNode(4,null);
        l2 = new ListNode(2,l2);
        l2 = new ListNode(1,l2);
        ListNode L_result = ListNode.mergeTwoLists(l1,l2);
        System.out.println(L_result);
        
    }
    
}

