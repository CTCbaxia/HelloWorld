/*
M
1190. Reverse Substrings Between Each Pair of Parentheses
*/
/*
Iteration, using Stack and Queue

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == ')'){
                Queue<Character> q = new LinkedList<>();
                while(!stack.isEmpty() && stack.peek() != '(') q.offer(stack.pop());
                if(!stack.isEmpty()) stack.pop();//pop '('
                while(!q.isEmpty()) stack.push(q.poll());
            }else{
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) sb.append(stack.pop());
        return sb.reverse().toString();
    }
    
}

/*
Recursion, set bracket level and treat bracket as a whole
Half of the brackets do not need to be reverse
Time: O(n^2)
Space: O(n)
*/
class Solution {
    public String reverseParentheses(String s) {
        return reverseLevel(s, 0);
    }
    private String reverseLevel(String s, int level){
        StringBuilder sb = new StringBuilder();
        if(level % 2 == 0){
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) != '(' && s.charAt(i) != ')') sb.append(s.charAt(i));
                else if(s.charAt(i) == '('){
                    int start = i + 1;
                    int count = 0;
                    while(i < s.length()){
                        if(s.charAt(i) == '(') count++;
                        else if(s.charAt(i) == ')'){
                            count--;
                            if(count == 0) break;
                        }
                        i++;
                    } 
                    int end = i;
                    String nextLevel = reverseLevel(s.substring(start, end), level + 1);
                    sb.append(nextLevel);
                }
            }
        }else{
            Stack<String> stack = new Stack<>();
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) != '(' && s.charAt(i) != ')') stack.push(""+s.charAt(i));
                else if(s.charAt(i) == '('){
                    int start = i + 1;
                    int count = 0;
                    while(i < s.length()){
                        if(s.charAt(i) == '(') count++;
                        else if(s.charAt(i) == ')'){
                            count--;
                            if(count == 0) break;
                        }
                        i++;
                    }
                    int end = i;
                    String nextLevel = reverseLevel(s.substring(start, end), level + 1);
                    stack.push(nextLevel);
                }
            }
            while(!stack.isEmpty()) sb.append(stack.pop());
        }
        return sb.toString();
    }
}
