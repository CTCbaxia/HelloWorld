/*
M
385. Mini Parser
*/
/*
Recursion

Time: O(n)
Space: O(1)  | O(n) for stack
*/
class Solution {
    int i;
    public NestedInteger deserialize(String s) {
        i = 0;
        return helper(s);
    }
    private NestedInteger helper(String s){
        //s is non-empty
        NestedInteger res = new NestedInteger();
        if(s.charAt(i) == '['){//this is a list
            i++;//skip '['
            while(i < s.length() && s.charAt(i) != ']'){
                if(s.charAt(i) == ','){
                    i++;//skip ','
                    continue;
                }
                NestedInteger ni = helper(s);
                res.add(ni);
            }
            i++;//skip ']'
        }else{//this is a number
            int num = 0, sign = 1;
            if(i < s.length() && (s.charAt(i) == '-')){
                i++;
                sign = -1;
            } 
            while(i < s.length() && (s.charAt(i) != ',' && s.charAt(i) != ']')){
                num = num * 10 + s.charAt(i++) - '0';
                
            }
            res.setInteger(num * sign);
        }
        return res;
    }
}

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */


/*
Iteration Using Stack
每次都把当前在用的 ni 放在栈口，并且在新建 ni 的时候就定好链接关系

Integer.parseInt(s) will consider sign

Time: O(n)
Space: O(n)  
*/
class Solution {
    public NestedInteger deserialize(String s) {
        Stack<NestedInteger> stack = new Stack<>();
        
        NestedInteger res = new NestedInteger();
        if(s.charAt(0) != '['){//edge case, cannot check whether s == "0"
            res.setInteger(Integer.parseInt(s));
            return res;
        } 
        stack.push(res);//fake list
        int start = 1;
        for(int i = 1; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '['){
                NestedInteger ni = new NestedInteger();
                stack.peek().add(ni);
                stack.push(ni);
                start = i + 1;// edge case: "[[]]"
            }else if(c == ']' || c == ','){
                if(i > start){// make sure there is a number before ] and ,
                    int val = Integer.parseInt(s.substring(start, i));
                    stack.peek().add(new NestedInteger(val));
                }
                if(c == ']'){
                    stack.pop();
                }
                start = i + 1;
            }
            
        }
        
        return res;
    }
    
}



