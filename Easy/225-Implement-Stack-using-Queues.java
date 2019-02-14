/*
EASY
225. Implement Stack using Queues

*/
class MyStack {
    Queue<Integer> myStack;
    /** Initialize your data structure here. */
    public MyStack() {
        myStack = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        //need to put x in front of stack, with other order remains the same
        int len = myStack.size();
        myStack.offer(x);
        for(int i = 0;i < len; i++){
            myStack.offer(myStack.poll());
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return myStack.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return myStack.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return myStack.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */






class MyStack {
    Queue<Integer> myStack;
    /** Initialize your data structure here. */
    public MyStack() {
        myStack = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        //need to put x in front of stack, with other order remains the same
        Queue<Integer> helper = new LinkedList<>();
        while(!myStack.isEmpty()){
            helper.offer(myStack.poll());
        }
        myStack.offer(x);
        while(!helper.isEmpty()){
            myStack.offer(helper.poll());
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return myStack.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return myStack.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return myStack.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */