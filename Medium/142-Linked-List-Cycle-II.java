/*
MEDIUM
142. Linked List Cycle II
https://leetcode.com/problems/linked-list-cycle-ii/description/
TIME: 0717 - 60min
RESULT: 92.13% - 1ms
METHOD:
1. HashSet
2. Two Pointers(faster & slower)
slower 进入 circle 的时候，faster 早就在 circle 中了。
最慢的 catch 情况是 slower 比 faster 慢一步进入 circle。但是 faster 不会让 slower 跑完一遍 circle 就可以将其 catch。
L(非环长度) + tmp(未完成一环的距离) = S_slower
L + tmp + Circle * N = S_faster
S_slower = Circle * N

所以此时不能说 slower 走的距离就是环的长度。严谨来说还需要再求一次环长。
当两者都在环内部，环长等于下次相遇时 slower 走的长度。

3. loop and loop 
暴力破解法，就是每次往前走一个节点 nodex，然后另一个指针从 head 开始遍历直到 nodex 之前，判断是否会在真正到达距离之前就遇到 nodex

NOTES:
1. ListNode 的相关概念：two pointers(faster & slower), find the last n node
2. 要空间复杂度小，就用指针（注意运用现有知识 - 追击问题）；或者 hashset 总能暴力破解

QUESTION:
1. 为什么 hashset 的方法慢这么多？应该是遍历更少才对啊？ - 查找费时？？

思路：
如果不记住他的位置，怎么知道这就是起点？？
起点的特征：连接非环和环？？


*/

/*
SOLUTION 0:

TIME: 0717 - 60min
RESULT: 92.13% - 1ms
NOTES:


METHOD: Two Pointers

*/
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode faster = head;
        ListNode slower = head;
        ListNode tmp = head;
        boolean start = true;

        // check if there is a circle
        // if so, make the faster and slower meet while both in circle
        while(faster != slower || start){
            start = false;
            int i = 0;
            while(i < 2 && faster != null){
                faster = faster.next;
                i++;
            }
            if(faster == null) return null;
            slower = slower.next;
        }

        // calculate the length of the circle 
        // (V_f - V_s) * time = Circle && V_f = 2 V_s  -> V_s * time = Circle
        start = true;
        int c_length = 0;
        while(faster != slower || start){
            start = false;
            faster = faster.next.next;
            slower = slower.next;
            c_length ++;
        }

        // and then the question becomes find the last Circle_length node in a list ( S = C + L )
        faster = head;
        slower = head;
        while(c_length > 0){
            faster = faster.next;
            c_length--;
        } 
        while(faster != slower){
            faster = faster.next;
            slower = slower.next;
        }
        return faster;
        
    }
}


/*
SOLUTION 1:

TIME: 0717 - 10min
RESULT: 13% - 7ms
NOTES:

METHOD: HashSet
*/
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode tmp = head;
        Set<ListNode> allnode = new HashSet<ListNode>();
        while(tmp != null){
            if(!allnode.contains(tmp)) allnode.add(tmp);
            else return tmp;
            
            tmp = tmp.next;
        }
        return null;
    }
}

