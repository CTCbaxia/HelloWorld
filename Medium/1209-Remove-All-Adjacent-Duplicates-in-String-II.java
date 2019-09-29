/*
M
1209. Remove All Adjacent Duplicates in String II
*/
/*
Stack
Time: O(n)
Space: O(n)
*/
class Solution {
    public class CharCount{
        char c;
        int count;
        public CharCount(char _c, int _count){
            c = _c;
            count = _count;
        }
    }
    public String removeDuplicates(String s, int k) {
        Stack<CharCount> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(!stack.isEmpty() && stack.peek().c == c){
                stack.peek().count++;
                if(stack.peek().count == k) stack.pop();
            }else{
                stack.push(new CharCount(c, 1));
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            CharCount cc = stack.pop();
            while(cc.count-- > 0) sb.append(cc.c);
        } 
        return sb.reverse().toString();
    }
}




/*
BFS
compute numbers for each char and mod k

Time: O(n^2) - O(n) for each level, there might be O(n) level
Space: O(n)
*/
class Solution {
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            int num = 0;
            while(i < s.length() && s.charAt(i) == c){
                num++;
                i++;
            }
            i--;
            
            num %= k;
            while(num-- > 0) sb.append(c);
        }
        return sb.length() == s.length() ? s : removeDuplicates(sb.toString(), k);//length equal - unchanged
    }
}