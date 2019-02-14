/*
232. Implement Queue using Stacks
https://leetcode.com/problems/implement-queue-using-stacks/description/


TIME: 0712 - 1h
RESULT: 97% - 61ms
NOTE: 
1. 注意题设条件
2. java.util.ConcurrentModificationException: 当一个线程在遍历一个 Collection 的时候，不能同时有另一个线程 modify 他

思路：
主要就是改变 push 之后的结果
*/
class MyQueue {
    Stack<Integer> myQ;
    /** Initialize your data structure here. */
    public MyQueue() {
        myQ = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        Stack<Integer> helper = new Stack<>();
        while(!myQ.isEmpty()){
            helper.push(myQ.pop());
        }
        myQ.push(x);
        while(!helper.isEmpty()){
            myQ.push(helper.pop());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return myQ.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return myQ.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return myQ.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */








 
class MyQueue {
    Stack<Integer> myque = new Stack<Integer>();
    Stack<Integer> reverse = new Stack<Integer>();

    /** Initialize your data structure here. */
    public MyQueue() {
        
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        while(!myque.empty()){
            reverse.push(myque.pop());
        }
        reverse.push(x);
        while(!reverse.empty()){
            myque.push(reverse.pop());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return myque.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return myque.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return myque.empty();
    }
}
