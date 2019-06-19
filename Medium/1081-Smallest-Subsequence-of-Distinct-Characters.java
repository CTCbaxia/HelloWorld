/*
MEDIUM
1081. Smallest Subsequence of Distinct Characters
*/
/*
Mono Deque: maintain a increase deque (delet from end, skip when letter already in deque)
注意：
需要有 set 来记录 mono deque 里面有没有当前 c
    1）有。保持 mono deque 不更新
    2）没有。移除大于 c 的元素，加上 c，保持 mono 特性
    
加入 result 的时候只需加入 c 之前的字母。后面的不确定会不会有更好的排列

Time: O(n)
Space: O(n)
*/
class Solution {
    public String smallestSubsequence(String text) {
        Deque<Character> q = new LinkedList<>();
        Set<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        int[] charNum = new int[26];
        
        for(char c : text.toCharArray()){
            charNum[c - 'a']++;
        }
        
        for(char c : text.toCharArray()){
            if(charNum[c - 'a'] == 0) continue;//already added to result
            
            charNum[c - 'a']--;
            if(!set.contains(c)){//if c is already in mono deque, maintain it. Otherwise:
                while(!q.isEmpty() && q.peekLast() > c){//remove all letter larger than c
                    set.remove(q.pollLast());
                }
                q.offerLast(c);
                set.add(c);
            }
            if(charNum[c - 'a'] == 0){
                while(!q.isEmpty() && q.peekFirst() <= c){//input all letters before c (included)
                    char cur = q.pollFirst();
                    sb.append(cur);
                    set.remove(cur);
                    charNum[cur - 'a'] = 0;//mark it as already added
                }
                
            }

        }
        return sb.toString();
    }
}



