/*
MEDIUM
5086. Smallest Subsequence of Distinct Characters
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




/*

Input: "abbbba"
Output: "abbbb"
Expected: "ab"


Input: "cbacabcacaba"
                ^
                q = [a,c,b] 这个时候应该把 c 留着，先放 b，最后放 c
Output: "acb"
Expected: "abc"


"abefcbeefefecede"

"abefbfe" ⭐️为什么要set？因为要保证现在维持的 mono[bef] 不会因为 b 的再次出现而直接变成 [b]。
Output: "abfe"
Expected: "abef"
*/

class Solution {
    public String smallestSubsequence(String text) {
        Queue<Character> q = new LinkedList<>();
        Set<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        int[] charNum = new int[26];
        
        for(char c : text.toCharArray()){
            charNum[c - 'a']++;
        }
        
        for(char c : text.toCharArray()){
            if(charNum[c - 'a'] == 0) continue;//c is already in result
            charNum[c - 'a']--;
            
            while(!q.isEmpty() && q.peek() > c) set.remove(q.poll());//make sure the first is the smallest
            if(!set.contains(c)){
                q.offer(c);
                set.add(c);
            }
            
            if(charNum[c - 'a'] == 0){//if c appears for the last time
                while(!q.isEmpty()){
                    if(q.peek() < c){//put all chars smaller than c into result
                        char cur = q.poll();
                        set.remove(cur);
                        sb.append(cur);
                        charNum[cur - 'a'] = 0;
                    }else{//put all chars larger or equal than c into result ❌ 这个时候可能会浪费一个不错的（c之后字母的）排序
                        char cur = q.poll();
                        set.remove(cur);
                    }
                } 
                sb.append(c);//add this char into result
            }
        }
        return sb.toString();
    }
}





class Solution {
    public String smallestSubsequence(String text) {
        Queue<Character> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int[] charNum = new int[26];
        
        for(char c : text.toCharArray()){
            charNum[c - 'a']++;
        }
        
        for(char c : text.toCharArray()){
            if(charNum[c - 'a'] == 0) continue;//c is already in result
            while(!q.isEmpty() && c <= q.peek()) q.poll();//remove all chars (available later) larger than c
            q.offer(c);//❌ didn't check if c is already in queue
            charNum[c - 'a']--;
            if(charNum[c - 'a'] == 0){//if c appears for the last time
                while(!q.isEmpty()){
                    char cur = q.poll();
                    sb.append(cur);
                    charNum[cur - 'a'] = 0;
                } 
            }
        }
        return sb.toString();
    }
}





//❌
class Solution {
    public String smallestSubsequence(String text) {
        int[] letters = new int[26];
        for(char c : text.toCharArray()){
            letters[c - 'a']++;
        }
        int index = 0;
        while(index < letters.length && letters[index] == 0) index++;//find the smallest letter that is not added to the res
        String res = "";
        for(char c : text.toCharArray()){
            if(c == (char)(index + 'a') || letters[c - 'a'] == 1) {
                res += c;
                if(c == (char)(index + 'a')){
                    letters[index] = 0;
                    while(index < letters.length && letters[index] == 0) index++;
                }
            }
            letters[c - 'a']--;
        }
        return res;
    }
}