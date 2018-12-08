/*
MEDIUM
622. Design Circular Queue

TIME: 
RESULT: 
NOTES:

*/
/*
Array
限制大小的array，用 head, tail 来作为位移，指向头尾部分
deQueue: head = (head + 1) % q.length;
enQueue: tail = (tail + 1) % q.length;
用 num 来计数，看目前到底有几个 nums

Time: O(n)
Space: O(n)
*/
class MyCircularQueue {
    int[] q;
    int head;
    int tail;
    int num;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        q = new int[k];
        head = 0;
        tail = -1;
        num = 0;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull()) return false;
        
        tail = (tail + 1)%q.length;
        q[tail] = value;
        num++;
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty()) return false;
        
        head = (head + 1)%q.length;
        num--;
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if(isEmpty()) return -1;
        return q[head];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if(isEmpty()) return -1;
        return q[tail];
        
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return num == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return num == q.length;
    }
}




/*
Array
限制大小的array，用 front, rear 来处理最后的头尾部分
deQueue: front = (front + 1) % q.length;
enQueue: rear = (rear + 1) % q.length;
Time: O(n)
Space: O(n)
*/
class MyCircularQueue {
    public int front;
    public int rear;
    public int len;
    public int[] q;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        front = 0;
        rear = -1;//has to be -1 because we need to add 1 when enqueue
        len = 0;
        q = new int[k];
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull()) return false;
        rear = (rear + 1) % q.length;
        q[rear] = value;
        len++;
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty()) return false;
        front = (front + 1) % q.length;
        len--;
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if(isEmpty()) return -1;
        else return q[front];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if(isEmpty()) return -1;
        else return q[rear];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return len == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return len == q.length;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */