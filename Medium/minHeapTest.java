/*
Min Heap:
直接打印出来的是 heap 构造出来的 Tree 结构

   1
 4   2
8 5 3 7

只有 pq.poll 操作才是执行的 logn 的操作来得到最小值
 */
import java.util.*;
class minHeapTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        pq.offer(8);
        pq.offer(2);
        pq.offer(1);
        pq.offer(5);
        pq.offer(4);
        pq.offer(3);
        pq.offer(7);
        System.out.println("pq:"+pq);//pq:[1, 4, 2, 8, 5, 3, 7]
        list.addAll(pq);
        System.out.println("list:"+list);//list:[1, 4, 2, 8, 5, 3, 7]
         while(!pq.isEmpty()){
             System.out.println(pq.poll());//1,2,3,4,5,7,8
        }
    }
    
}