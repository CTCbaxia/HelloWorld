/*
232. Implement Queue using Stacks
https://leetcode.com/problems/implement-queue-using-stacks/description/


TIME: 0712 - 1h
RESULT: 97% - 61ms
NOTE: 


思路：
主要就是改变 push 之后的结果
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


/*
只给了 stack 的基本用法，没给迭代，所以 foreach 行不通
*/
class MyQueue {
    Stack<Integer> myque = new Stack<Integer>();
    Stack<Integer> reverse = new Stack<Integer>();

    /** Initialize your data structure here. */
    public MyQueue() {
        
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        for(int i : reverse){
            myque.pop();
        }
        reverse.push(x);
        for(int j : reverse){
            myque.push(j);
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


/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */