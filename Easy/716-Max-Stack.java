/*
MEDIUM
716. Max Stack

TIME: 
RESULT: 
NOTES:

*/
/*
双重stack: stack + maxStack
maxStack 只保存比当前 peek 更大的值，见过更好的就再也不会回去
stack:    1 0 2 1 0 3 1 0 1
maxStack: 1 1 2 2 2 3 3 3 3

Time: O(n)
Space: O(n)
*/
class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack;
    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<Integer>();
        maxStack = new Stack<Integer>();
    }
    
    public void push(int x) {
        stack.push(x);
        //如果stack下面有更大的，那么 maxStack 在stack.pop()出最大值之前不会改变
        if(maxStack.isEmpty()) maxStack.push(x);
        else{
            maxStack.push((x > maxStack.peek() ? x : maxStack.peek()));
        }
    }
    
    public int pop() {
        maxStack.pop();
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }
    
    public int popMax() {
        //use a tmp to save elements until we find max
        Stack<Integer> tmp = new Stack<Integer>();
        int max = maxStack.peek();
        while(stack.peek() != max){
            tmp.push(stack.pop());
            maxStack.pop();
        }
        //pop max
        stack.pop();
        maxStack.pop();
        
        //add tmp back to stack
        while(!tmp.isEmpty()){
            int n = tmp.pop();
            stack.push(n);
            if(maxStack.isEmpty()) maxStack.push(n);
            else{
                maxStack.push((n > maxStack.peek() ? n : maxStack.peek()));
            }
        }
        return max;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */

//push max 的时候只push >= max.peek() 的写法
class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> max;
    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<>();
        max = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if(max.isEmpty() || max.peek() <= x) max.push(x);
    }
    
    public int pop() {
        if(stack.isEmpty()) return -1;
        int top = stack.pop();
        if(max.peek() == top) max.pop();
        return top;
    }
    
    public int top() {
        if(stack.isEmpty()) return -1;
        return stack.peek();
    }
    
    public int peekMax() {
        if(max.isEmpty()) return -1;
        return max.peek();
    }
    
    public int popMax() {
        if(max.isEmpty()) return -1;
        int maxNum = max.pop();
        Stack<Integer> tmp = new Stack<>();
        int num = stack.pop();
        while(!stack.isEmpty() && num != maxNum){
            tmp.push(num);
            num = stack.pop();
        }
        //find the position
        while(!tmp.isEmpty()){
            num = tmp.pop();
            if(max.isEmpty() || max.peek() <= num) max.push(num);
            stack.push(num);
        }
        return maxNum;
    }
}