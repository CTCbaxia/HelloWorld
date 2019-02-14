/*
EASY
sort the stack using only top/pop/push

*/
class Solution {
    public Stack<Integer> sortStack(Stack<Integer> s){
        Stack<Integer> res = new Stack<>();
        int len = s.size();
        for(int i = 0; i < len; i++){
            int count = 0;
            int num = s.pop();
            while(!res.isEmpty() && res.peek() > num){
                s.push(res.pop());
                count++;
            }
            res.push(num);
            while(count-- > 0){
                res.push(s.pop());
            }
        }
        return res;
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