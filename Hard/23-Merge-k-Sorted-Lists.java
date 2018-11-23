/*
23. Merge k Sorted Lists
https://leetcode.com/problems/merge-k-sorted-lists/description/

TIME: 0706
RESULT: 10%
NOTES:to be improved
*/
/*
PriorityQueue:
put all first node into pq
connect in-place

Time: O(nlogk) - n is total number of nodes
Space: O(k) - k is the number of listnode
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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>(){
            public int compare(ListNode l1, ListNode l2){
                return l1.val - l2.val;
            }
        });
        for(ListNode l : lists){
            if(l != null) pq.offer(l);
        }
        ListNode result = new ListNode(0);
        ListNode node = result;
        while(!pq.isEmpty()){
            node.next = pq.poll();
            node = node.next;
            if(node.next != null) pq.offer(node.next);
        }
        return result.next;

    }
}




/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode l_tmp = new ListNode(0);
        ListNode l_result = l_tmp;
        
        int min = 0;
        int index = 0;
        int count = 0;
        for(ListNode k : lists){
            if(k == null){
                count ++;
            }else{
                min = k.val;
            }
        }
        if(count == lists.length){
            l_result = l_result.next;
        }

        while(count < lists.length){
            for(int i = 0; i < lists.length; i++){
                if(lists[i] == null){
                    continue;
                }
                if(lists[i].val <= min){
                    min = lists[i].val;
                    index = i;
                }
            }
            
            l_tmp.val = min;
            lists[index] = lists[index].next;  
            if(lists[index] == null){
                count ++;
                for(ListNode k : lists){
                    if(k != null){
                        min = k.val;
                        break;
                    }
                }

            }else{
                min = lists[index].val;
            }
            if(count < lists.length){   
                l_tmp.next = new ListNode(0);
                l_tmp = l_tmp.next;                    
            }

        }
        return l_result;

    }
}