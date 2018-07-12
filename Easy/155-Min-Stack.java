
/*
155. Min Stack
https://leetcode.com/problems/min-stack/description/


TIME: 0711 - 2h
RESULT: 96% - 67ms
NOTE: 
1. 注意 class, method 的概念
2. 注意边界：stack 为空的时候不能用 stack.peek()
3. 注意Integer.MAX_VALUE。但是题目没有给超过 max 值的输入，暂时不考虑


思路：
也就是需要能够不管怎么变，都能用 getMin 的方式得到最小的值，而其他的部分不变
保留最小值 - min
push 的时候核查这个值是否是最小值，若是则改变 min
重要的是 pop 的时候可能会赶走最小值 - 需要在 pop 的时候核查这个值是不是 min，若是，在剩下的内容里面找到新的最小值

QUESTION: 如何保存 min 的值，而不改变 stack 内部的顺序？？
ANSWER: 可以用一个 HashSet 存放所有的值，如果 pop 出去了，就 remove。但是，你并不知道 stack 里面还有没有同样大小的一个值可以继续保持 min。

QUESTION: 可不可以就在 stack 内部倒腾
*/
class MinStack {
    Stack<Integer> stack = new Stack<Integer>();
    int min = 0;
    /** initialize your data structure here. */

    public void push(int x) {
        if(stack.empty()){
           min = x; 
        }else{
            if(min >= x){
                min = x;
            }
        }
        stack.push(x);
        //System.out.println(stack);//add this print and then time limit exceed
    }
    
    public void pop() {
        if(min == stack.peek()){//method 内部不能套用 method? 这里不能写 stack.top()
            stack.pop();
            if(!stack.empty()){
                min = stack.peek();
                for(Integer i : stack){
                    min = Math.min(i, min);
                }
            }
        }else{
            stack.pop();
        }
    }
    
    public int top() {  
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */




TEST CASE:
["MinStack","push","push","push","top","pop","getMin","pop","getMin","pop","push","top","getMin","push","top","getMin","pop","getMin"]
[[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]