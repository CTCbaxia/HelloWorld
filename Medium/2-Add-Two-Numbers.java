/*
Solution 1: 7%
方法真的不够好，属于能够解决问题的笨办法
但是主动考虑到了有数到末尾之后的进位问题，不错。

想出了更好的方法。
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int add_one = 0;
        ListNode l_index = new ListNode(0);
        ListNode l_result = l_index;
        if(l1 == null && l2 == null ){
            return l1;
        }
        while(l1 != null && l2 != null){
            l_index.val = (l1.val + l2.val+ add_one) % 10;

            if(l1.val + l2.val+ add_one >= 10){
                add_one = 1;
            }else{
                add_one = 0;
            }   

            l1 = l1.next;
            l2 = l2.next;
            if(l1 == null && l2 == null && add_one == 0){
                break;
            }
            l_index.next = new ListNode(0);
            l_index = l_index.next;
        }
        while( l1 != null ){
            l_index.val = (l1.val + add_one) % 10;
            if(l1.val + add_one >= 10){ //如果大于等于 10 ，那么add_one 一定为 1 
                l_index.next = new ListNode(0);
                l_index = l_index.next;
                l1 = l1.next;
            }else{
                l_index.next = l1.next;
                add_one = 0;
                break;
            }
            
        }
        
        while( l2 != null ){
            l_index.val = (l2.val + add_one) % 10;
            if(l2.val + add_one >= 10){
                l_index.next = new ListNode(0);
                l_index = l_index.next;
                l2 = l2.next;
            }else{
                l_index.next = l2.next;
                add_one = 0;
                break;
            }
            
        }
        if(add_one == 1){
            l_index.val = 1;
        }
        return l_result;
    }
}


/*
Solution 2: 76%

直接把 l_result 并入 l1/l2中，如果一方到头了，直接并入 l_result
但是此时需要注意，两者此后变完全等价，改变一个的值，另一个一定会变
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }else if(l2 == null){
            return l1;
        }
        
        int add_one = 0;
        int test = 4;
        boolean l1_valid = true;
        boolean l2_valid = true;
        ListNode l_index = new ListNode(0);
        ListNode l_result = l_index;
        
        while(l1_valid || l2_valid ){
            
        //因为此时 l_index 和 l1/l2 等价，所以不能放在if前面，因为他会直接改变 l1/l2的值
        //使得 l1.val + l2.val+ add_one = 2 * l1.val
            if(l1.val + l2.val+ add_one >= 10){
                l_index.val = (l1.val + l2.val + add_one) % 10; 
                add_one = 1;
            }else{
                l_index.val = (l1.val + l2.val + add_one) % 10;
                add_one = 0;
            }   
            

            if(l1.next == null && l1_valid){
                l1 = l_index;
                l1_valid = false;
            }
            if(l2.next == null && l2_valid){
                l2 = l_index;
                l2_valid = false;
            }
               
            if((!l1_valid) && (!l2_valid) && add_one == 0){
                break;
                
            }
            l_index.next = new ListNode(0);
            l_index = l_index.next;
            l1 = l1.next; 
            l2 = l2.next;
            

        }

        if(add_one == 1){
            l_index.val = 1;
        }

        return l_result;
    }
}