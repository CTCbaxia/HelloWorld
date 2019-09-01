/*
MEDIUM
127. Word Ladder
https://leetcode.com/problems/word-ladder/description/
*/
/*
Bidirectional BFS

Time: O(k) answer = k
Space: O(n)
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int res = 1;
        Set<String> words = new HashSet<>();
        Set<String> visited = new HashSet<>();
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();
        
        for(String s : wordList) words.add(s);
        if(!words.contains(endWord)) return 0;
        
        beginSet.add(beginWord);
        endSet.add(endWord);
        while(!beginSet.isEmpty() && !endSet.isEmpty()){
            res++;
            if(beginSet.size() > endSet.size()){//always choose smaller set to try
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            Set<String> nextSet = new HashSet<>();
            for(String s : beginSet){
                for(int i = 0; i < s.length(); i++){
                    char[] ch = s.toCharArray();
                    for(char c = 'a'; c <= 'z'; c++){
                        if(ch[i] == c) continue;
                        ch[i] = c;
                        String next = new String(ch);
                        if(endSet.contains(next)) return res;//need to check if it is in words
                        if(words.contains(next) && visited.add(next))//add to visited
                            nextSet.add(next);
                    }
                }
            }
            beginSet = nextSet;
        }
        return 0;
    }
}


/*
Normal BFS

Time: O(k) answer = k
Space: O(n)
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int res = 1;
        Set<String> words = new HashSet<>();
        Set<String> visited = new HashSet<>();
        for(String s : wordList) words.add(s);
        if(!words.contains(endWord)) return 0;
        
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        while(!q.isEmpty()){
            int size = q.size();
            res++;
            for(int k = 0; k < size; k++){
                String s = q.poll();
                for(int i = 0; i < s.length(); i++){
                    char[] ch = s.toCharArray();
                    for(char c = 'a'; c <= 'z'; c++){
                        if(ch[i] == c) continue;
                        ch[i] = c;
                        String next = new String(ch);
                        if(next.equals(endWord)) return res;//need to check if it is in words
                        if(words.contains(next) && visited.add(next))
                            q.offer(next);
                    }
                }
            }
        }
        return 0;
    }
}



/*
BFS:

每一层都更新能匹配的单词，直到找到单词

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int res = 1;
        q.offer(beginWord);
        while(!q.isEmpty()){
            res++;//匹配的是下一层
            Queue<String> newQ = new LinkedList<>();//next level
            while(!q.isEmpty()){
                String word = q.poll();
                for(String s : wordList){
                    if(visited.contains(s)) continue;
                    if(isMatch(word, s)){
                        if(s.equals(endWord)) return res;
                        newQ.offer(s);
                        visited.add(s);
                    }
                }
            }
            q = newQ;
        }
        return 0;
    }
    private boolean isMatch(String s1, String s2){
        //word have same length
        int count = 0;
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)) count++;
            if(count > 1) return false;
        }
        return count == 1;
    }
}

















/*
TIME: 0712 - 5h
RESULT: TIME LIMIT EXCEED
NOTES:
1. 字符串的等于要用 string1.equals(string2)，而非 string1 == string2。因为是 String 引用类型，所以得到的比较其实可能是比较的 address，而非 address 对应的 content
2. wordListRemain 的移除方法可能会简化比较（减少重复比较），但是问题是会使得 wordListRemain.add(s) 的结果和还未比完的 wordListRemain 不一样
   比如上一个比较是 [a,b,c] 操作一番下来却是 [a,c,b]。
   因为 b 匹配而暂时移除了 b，进行下一轮比较，但是若退回上一层， wordListRemain.add(s) 之后
   wordListRemain 的顺序已经改变，原本该比较的第三位的 c，会变成 b，结果又比较了一遍 b 而跳过了 c。
3. 用 List 来传参的时候注意 List 的永久有效性。如果只想让 List 存每一条路径的结果作为最终的过度，就该在该路径结束的 list.clear(); - 错，这样会把所有的结果都清空的...
   想要各自路径自己保留，就应该用原始类型...比如 String 这种不会被其他路径影响的。如果仍然要保留原始结果，设一个临时变量暂存已经不被需要的数，然后在存回来。
4. 谨慎改变引用变量（add，remove），因为这样会使得变量的位置改变，从而循环被有序性被破坏。

---

5. 这题 DFS 明显就 TLE 了，最好用 BFS。【题目要求最短路径，第一反应应该是 BFS】
*/

/*
SOLUTION 3: BiBFS
TIME: 0830 - 1h
RESULT: 37.49% - 126ms
THOUGHTS:
双向 BFS。每次对较短的 beginSet 遍历，
先看看里面的每个单词是不是 endSet 里面单词的 ladder，如果有匹配，则返回 result + 1
再看看里面的每个单词在 wordlist 里面的 ladder，找到下一层的 reachword
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        Set<String> beginSet = new HashSet<String>();
        Set<String> endSet = new HashSet<String>();
        
        beginSet.add(beginWord);
        endSet.add(endWord);
        
        int result = 1;
        boolean match = false;
        int len = wordList.size();
        while(!match && result <= len + 1){

            //switch the set to everytime loop the smaller set to compare
            if(beginSet.size() > endSet.size()){
                Set<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }

            Set<String> visited = new HashSet<String>();
            for(String s : beginSet){
                for(String s_end : endSet){
                    if(isLadder(s, s_end)) return result + 1;
                }
                for(String s_match : wordList){
                    if(isLadder(s, s_match)) visited.add(s_match);
                }
            }
            beginSet = visited;
            for(String s : visited) wordList.remove(s);
            
            result ++;
        }
        return 0;
    }

    private boolean isLadder(String begin, String cur){
        int diff = 0;
        for(int i = 0; i < begin.length(); i++){
            if(begin.charAt(i) != cur.charAt(i)) diff++;
            if(diff > 1) return false;
        }
        if(diff == 0) return false;
        else return true;
    }
}


class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<String>();
        Set<String> endSet = new HashSet<String>();
        
        beginSet.add(beginWord);
        endSet.add(endWord);
        
        int result = 1;
        boolean match = false;
        int len = wordList.size();
        System.out.println(len);
        while(!match && result <= len + 1){
            System.out.println("beginSet:"+beginSet);
            System.out.println("endSet:"+endSet);
            match = nextLadder(wordList, beginSet, endSet);
            result ++;
        }
        return result;
    }
    //BiBFS 
    private boolean nextLadder(List<String> wordList, Set<String> beginSet, Set<String> endSet){
        //switch the set to everytime loop the smaller set to compare
        if(beginSet.size() > endSet.size()){
            Set<String> tmp = beginSet;
            beginSet = endSet;
            endSet = tmp;
        }
        Set<String> visited = new HashSet<String>();
        for(String s : beginSet){
            for(String s_match : wordList){
                if(isLadder(s, s_match)){
                    System.out.println("s_match:"+s_match);
                    if(endSet.contains(s_match)) return true;
                    visited.add(s_match);
                    
                }
            }
        }
        System.out.println("visited:"+visited);
        beginSet = visited;//【Wrong Code】这时候只是把里面传参的 beginSet 改变了，没有改变外部的 beginSet
        System.out.println("beginSet:"+beginSet);
        for(String s : visited) wordList.remove(s);
        return false;
        
    }
    private boolean isLadder(String begin, String cur){
        int diff = 0;
        for(int i = 0; i < begin.length(); i++){
            if(begin.charAt(i) != cur.charAt(i)) diff++;
            if(diff > 1) return false;
        }
        if(diff == 0) return false;
        else return true;
    }
}

/*
SOLUTION 4: BiBFS
TIME: 0830 - 1h
RESULT: 1% - 1163ms
THOUGHTS:
同样的双向，但是每次都查 26 * 单词长度 的数量的 loop，高票答案，但是慢很多啊？？？
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        Set<String> beginSet = new HashSet<String>();
        Set<String> endSet = new HashSet<String>();
        
        beginSet.add(beginWord);
        endSet.add(endWord);
        
        int result = 1;
        boolean match = false;
        int len = wordList.size();
        while(!match && result <= len + 1){

            //switch the set to everytime loop the smaller set to compare
            if(beginSet.size() > endSet.size()){
                Set<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }

            Set<String> visited = new HashSet<String>();
            for(String s : beginSet){
                char[] ch = s.toCharArray();
                for(int i = 0; i < ch.length; i++){
                    //to change each character to its 26 different char
                    for(char c = 'a'; c <= 'z'; c++){
                        char replace = ch[i];
                        ch[i] = c;
                        String target = String.valueOf(ch);
                        
                        if(endSet.contains(target)) return result + 1;
                        if(wordList.contains(target)){
                            visited.add(target);
                            
                        }
                        ch[i] = replace;
                        
                    }
                }
            }
            beginSet = visited;
            for(String s : visited) wordList.remove(s);
            
            result ++;
        }
        return 0;
    }

}


/*
SOLUTION 3: BFS
TIME: 0829 - 20min
RESULT: 20% - 394ms
THOUGHTS:
每次都更新剩余 wordList 里面的 word，这样会减少后续的遍历时间。
注意不要一边遍历一边 remove，最好每个周期 remove 一次。
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> nextMatch = new HashSet<String>();
        nextMatch.add(beginWord);
        int result = 1;
        int len = wordList.size();
        while(!nextMatch.contains(endWord) && result <= len + 1){
            nextMatch = nextLadder(endWord, wordList, nextMatch);
            result ++;
        }
        return nextMatch.contains(endWord) ? result : 0;
    }
    //the number of ways from cur to end 
    private Set<String> nextLadder(String end, List<String> wordList, Set<String> set){
        Set<String> nextMatch = new HashSet<String>();
        Set<String> visited = new HashSet<String>();
        for(String s : set){
            for(String cur: wordList){
                if(isLadder(s, cur)){
                    if(cur.equals(end)){
                        nextMatch.add(cur);
                        return nextMatch;
                    }else{
                        nextMatch.add(cur);
                        visited.add(cur);
                    }
                }
            }            
        }
        for(String s: visited) wordList.remove(s);
        return nextMatch;
    }
    private boolean isLadder(String begin, String cur){
        int diff = 0;
        for(int i = 0; i < begin.length(); i++){
            if(begin.charAt(i) != cur.charAt(i)) diff++;
            if(diff > 1) return false;
        }
        if(diff == 0) return false;
        else return true;
    }
}
/*
SOLUTION 2: BFS
TIME: 0829 - 30min
RESULT: 7% - 924ms
THOUGHTS:
每次用 set 存储现有 能够 reach 到的 word 能够再 reach 远一级的 word，每次扩大范围，一直到达 reach 库里面有 endWord，或者到达能够 reach 到的最远 word 范围（如果所有单词都 reach 到了，仍然没有匹配 endWord，就跳出循环）
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> visited = new HashSet<String>();
        Set<String> nextMatch = new HashSet<String>();
        nextMatch.add(beginWord);
        int result = 1;
        while(!nextMatch.contains(endWord) && result <= wordList.size() + 1){
            nextMatch = nextLadder(endWord, wordList, visited, nextMatch);
            result ++;
        }
        return nextMatch.contains(endWord) ? result : 0;
    }
    //the number of ways from cur to end 
    private Set<String> nextLadder(String end, List<String> wordList, Set<String> visited, Set<String> set){
        Set<String> nextMatch = new HashSet<String>();
        for(String s : set){
            for(int i = 0; i < wordList.size(); i++){
                String cur = wordList.get(i);
                if(visited.contains(cur)) continue;
                if(isLadder(s, cur)){
                    if(cur.equals(end)){
                        nextMatch.add(cur);
                        return nextMatch;
                    }else{
                        nextMatch.add(cur);
                        visited.add(cur);
                    }
                }
            }            
        }

        return nextMatch;
    }
    private boolean isLadder(String begin, String cur){
        int diff = 0;
        for(int i = 0; i < begin.length(); i++){
            if(begin.charAt(i) != cur.charAt(i)) diff++;
            if(diff > 1) return false;
        }
        if(diff == 0) return false;
        else return true;
    }
}
/*
SOLUTION 1: DFS
TIME: 0829 - 30min
RESULT: TIME LIMIT EXCEED
THOUGHTS: 
用 DFS 找到所有的路径，然后返回最短的路径
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> visited = new HashSet<String>();
        return nextLadder(beginWord, endWord, wordList, 1, visited);
    }
    private int nextLadder(String begin, String end, List<String> wordList, int count, Set<String> visited){
        int res = 0;
        for(int i = 0; i < wordList.size(); i++){
            String cur = wordList.get(i);
            if(visited.contains(cur)) continue;
            if(isLadder(begin, cur)){
                if(cur.equals(end)) return count + 1;
                else{
                    visited.add(cur);
                    int tmp = nextLadder(cur, end, wordList, count + 1, visited);
                    if(tmp > 0) res = (res > 0) ? Math.min(res, tmp) : tmp;
                    visited.remove(cur);
                }
            }
        }
        return res;
    }
    private boolean isLadder(String begin, String cur){
        int diff = 0;
        for(int i = 0; i < begin.length(); i++){
            if(begin.charAt(i) != cur.charAt(i)) diff++;
            if(diff > 1) return false;
        }
        if(diff == 0) return false;
        else return true;
    }
}


"qa"
"sq"
["si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"]

/*
SOLUTION 0:
TIME: 0712 - 5h
RESULT: TIME LIMIT EXCEED
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int result = 0;
        List<Integer> length = new ArrayList<Integer>();
        for(int i = 0; i < wordList.size(); i++){
            if(wordList.get(i).equals(endWord)) result = 1;
        }
        if(result == 0) return 0;
        else result = 0;
        
        nextladder(beginWord, endWord, wordList, length, 1);
        
        if(length.size() == 0){
            return result;
        }else{
            result = length.get(0);
            for(int i = 0; i < length.size(); i++){
                result = Math.min(result, length.get(i));
            }
        }
        return result;
    }
    private void nextladder(String beginWord, String endWord, List<String> wordListRemain, List<Integer> length, int count){
        for(int i = 0; i < wordListRemain.size(); i++){
            if(wordListRemain.get(i) == "") continue;
            if(isladder(beginWord, wordListRemain.get(i))){  
                String s = wordListRemain.get(i);
                if(s.equals(endWord)){
                    if(!length.contains(count + 1)) length.add(count + 1);
                    return;
                }else{
                    wordListRemain.set(i, "");
                    nextladder(s, endWord, wordListRemain, length, count + 1);
                    wordListRemain.set(i,s);
                }
            }
        }
        return;
    }
    private boolean isladder(String word, String wordladder){
        int diff = 0;
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == wordladder.charAt(i)) continue;
            else diff ++;
            
            if(diff > 1) return false;
        }
        if(diff == 1) return true;
        else return false;
        
    }
}


/*
Wrong Code 1：
问题：改变了 List<String> wordList
NOTE: 谨慎改变引用变量（add，remove），因为这样会使得变量的位置改变，从而循环被有序性被破坏
*/  
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int result = 0;
        List<Integer> length = new ArrayList<Integer>();
        for(int i = 0; i < wordList.size(); i++){
            if(wordList.get(i).equals(endWord)) result = 1;
        }
        if(result == 0) return 0;
        else result = 0;
        
        nextladder(beginWord, endWord, wordList, length, 1);
        
        if(length.size() == 0){
            return result;
        }else{
            result = length.get(0);
            for(int i = 0; i < length.size(); i++){
                result = Math.min(result, length.get(i));
            }
        }
        return result;
    }
    private void nextladder(String beginWord, String endWord, List<String> wordListRemain, List<Integer> length, int count){
        for(int i = 0; i < wordListRemain.size(); i++){
            
            if(isladder(beginWord, wordListRemain.get(i))){  
                
                String s = wordListRemain.get(i);
                if(s.equals(endWord)){
                    if(!length.contains(count + 1)) length.add(count + 1);                    
                    return;
                }else{
                    wordListRemain.remove(i);//Wrong Part
                    nextladder(s, endWord, wordListRemain, length, count + 1);
                    wordListRemain.add(s);
                }
            }
        }
        return;
    }
    private boolean isladder(String word, String wordladder){
        int count = 0;
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == wordladder.charAt(i)) continue;
            else count ++;
            if(count > 1) return false;
        }
        return true;
    }
}


/*
Wrong Code 2：
问题：
用 List 类型传参的时候注意一下这个里面的 value 是永远存在的，可以说是没有作用阈的问题
所以如果有不同的路径，每条路径都可能有一个结果，最好还是用一个引用类型记录所有最终结果，用原始类型来记录每条路一步一步的变化
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int result = 0;
        
        List<String> ladder = new ArrayList<String>();
        List<List<String>> allLadder = new ArrayList<List<String>>();
        
        //if no endWord
        for(int i = 0; i < wordList.size(); i++){
            if(wordList.get(i).equals(endWord)) result = 1;
        }
        if(result == 0) return 0;
        else result = 0;
        
        //start ladder
        nextladder(beginWord, endWord, wordList, allLadder, ladder);
        
        if(allLadder.size() == 0){
            return result;
        }else{
            result = allLadder.get(0).size();
            for(int i = 0; i < allLadder.size(); i++){
                result = Math.min(result, allLadder.get(i).size());
            }
        }
        return result;
    }
    private void nextladder(String beginWord, String endWord, List<String> wordList, List<List<String>> allLadder,List<String> ladder){
        
        for(int i = 0; i < wordList.size(); i++){
            if(isladder(beginWord, wordList.get(i))){
                String s = wordList.get(i);
                System.out.println(s);
                System.out.println(allLadder);
                
                if(!ladder.contains(s)){
                    ladder.add(s);//Wrong Part
                    if(s.equals(endWord)){
                        allLadder.add(ladder); 
                        return;
                    }else{
                        nextladder(s, endWord, wordList, allLadder, ladder);
                    }                    
                }else{
                    continue;
                }
            }
        }
        return;
    }
    private boolean isladder(String word, String wordladder){
        int count = 0;
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == wordladder.charAt(i)) continue;
            else count ++;
            
            if(count > 1) return false;
        }
        if(word.equals(wordladder)) return false;
        return true;
    }
}


/*
Wrong Code 3：
Problem：List<String> ladder 的永久适用性导致了一旦 clear()，所有支路暂存的结果都会被 clear().
Note: 谨慎清理 List（等引用变量）
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int result = 0;
        
        List<String> ladder = new ArrayList<String>();
        List<Integer> length = new ArrayList<Integer>();
        
        //if no endWord
        for(int i = 0; i < wordList.size(); i++){
            if(wordList.get(i).equals(endWord)) result = 1;
        }
        if(result == 0) return 0;
        else result = 0;
        
        //start ladder
        nextladder(beginWord, endWord, wordList, length, ladder);
        
        if(length.size() == 0){
            return result;
        }else{
            result = length.get(0);
            for(int i = 0; i < length.size(); i++){
                result = Math.min(result, length.get(i));
            }
        }
        return result;
    }
    private void nextladder(String beginWord, String endWord, List<String> wordList, List<Integer> length, List<String> ladder){
        
        for(int i = 0; i < wordList.size(); i++){
            if(isladder(beginWord, wordList.get(i))){
                String s = wordList.get(i);
                System.out.println("s:" + s);

                if(!ladder.contains(s)){
                    ladder.add(s);
                    if(s.equals(endWord)){
                        length.add(ladder.size() + 1);
                        ladder.clear();//Wrong Code
                        return;
                    }else{
                        nextladder(s, endWord, wordList, length, ladder);
                    }                 
                }else{
                    continue;
                }
            }
        }
        return;
    }
    private boolean isladder(String word, String wordladder){
        int count = 0;
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == wordladder.charAt(i)) continue;
            else count ++;
            
            if(count > 1) return false;
        }
        if(word.equals(wordladder)) return false;
        return true;
    }
}

//上面代码的死循环错误输出
s:hot
[hot]
s:dot
[hot, dot]
s:hot
s:dog
[hot, dot, dog]
s:dot
s:log
[hot, dot, dog, log]
s:dog
s:lot
[hot, dot, dog, log, lot]
s:hot
s:dot
s:log
s:cog
[hot, dot, dog, log, lot, cog]
7
ladder after clear: []
hereherehereherehereherehere
s:cog
[cog]
2
ladder after clear: []
hereherehereherehereherehere
s:lot
[lot]
s:hot
[lot, hot]
s:dot
[lot, hot, dot]
s:hot
s:dog
[lot, hot, dot, dog]
s:dot
s:log
[lot, hot, dot, dog, log]
s:dog
s:lot
s:cog
[lot, hot, dot, dog, log, cog]
7
ladder after clear: []
hereherehereherehereherehere
s:cog
[cog]
2
ladder after clear: []
hereherehereherehereherehere
s:lot
[lot]
s:hot
[lot, hot]
s:dot
[lot, hot, dot]
s:hot
s:dog
[lot, hot, dot, dog]
s:dot
s:log
[lot, hot, dot, dog, log]
s:dog
s:lot
s:cog
[lot, hot, dot, dog, log, cog]
7
ladder after clear: []
hereherehereherehereherehere
s:cog
[cog]
2
ladder after clear: []
hereherehereherehereherehere
s:lot
[lot]
s:hot
[lot, hot]
s:dot
[lot, hot, dot]
s:hot
s:dog
[lot, hot, dot, dog]
s:dot
s:log
[lot, hot, dot, dog, log]
s:dog
s:lot
s:cog
[lot, hot, dot, dog, log, cog]
7
ladder after clear: []
hereherehereherehereherehere
s:cog
[cog]
2
ladder after clear: []
hereherehereherehereherehere
s:lot
[lot]
... 86385 more lines