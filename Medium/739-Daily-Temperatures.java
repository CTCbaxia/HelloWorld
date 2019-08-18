/*
M
739. Daily Temperatures
*/
/*
MONO Deque, can use Stack for this

Time: O(n)
Space: O(n)
*/
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[n];
        for(int i = n - 1; i >= 0; i--){
            int days = 0;
            while(!stack.isEmpty() && T[stack.peek()] <= T[i]){//if equal, just need to be 1 more
                days += res[stack.pop()];
            }
            if(!stack.isEmpty()) days += 1;//add the larger day itself
            else days = 0;//can have no larger
            stack.push(i);
            res[i] = days;
        }
        return res;
    }
}
