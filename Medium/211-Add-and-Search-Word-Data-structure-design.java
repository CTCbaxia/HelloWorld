/*
MEDIUM
211. Add and Search Word - Data structure design

TIME: 1021 - 3h
RESULT: 76% - 113ms
NOTES:
1. Brute Force 思路：对于每个 . 都 loop 一遍所有可能取值
2. map 思路：
	1. 储存 map<len, wordlist>，每次只用对比长度一致的
	2. BFS - 每次都用一层 queue 来存截止到当前 index，仍然匹配的 string
	3. 有 "." 的部分就跳过不查询
3. Trie : 匹配前缀最佳思路
    1. 存成 Trie: children 存下一个节点；word 存当前匹配的单词
    2. 遍历单词的时候，也向下遍历 TrieNode
*/


/*
Trie 存储 wordDict
每次从头找路径

Time: O(len of the word) - O(26^n)
Space: O(1)


Note:
Java 构造函数 和 类的定义，变量的声明
*/
class WordDictionary {
    public class TrieNode{
        TrieNode[] children;
        String word;
        public TrieNode(){
            children = new TrieNode[26];
            word = "";
        }
    }
    public TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        //go from the root
        TrieNode node = root;
        for(char c : word.toCharArray()){
            if(node.children[c - 'a'] == null){
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.word = word;
        return;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchTrie(word.toCharArray(), 0, root);
    }
    private boolean searchTrie(char[] ch, int index, TrieNode node){
        if(index == ch.length) return !node.word.equals("");//at the end of the word, is there a word?
        if(ch[index] != '.'){
            if(node.children[ch[index] - 'a'] != null){//continuous to check next letter
                return searchTrie(ch, index + 1, node.children[ch[index] - 'a']);
            }else{
                return false;
            }
        }else{
            for(int i = 0; i < node.children.length; i++){//loop for every possibility
                if(node.children[i] != null){//only continuous to search when there is a node
                    if(searchTrie(ch, index + 1, node.children[i]))
                       return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
















/*
Map 存储 wordDict
每次在 search word 长度匹配的子集里面找

RESULT: 25% - 164ms
*/
class WordDictionary {
    Map<Integer, List<String>> wordDict = new HashMap<Integer, List<String>>();
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        int len = word.length();
        if(!wordDict.containsKey(len)){
            wordDict.put(len, new ArrayList<String>());
        }
        wordDict.get(len).add(word);
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if(!wordDict.containsKey(word.length())) return false;
        
        List<String> pending = wordDict.get(word.length());
        if(word.indexOf(".") < 0) return pending.contains(word);
        Queue<String> q = new LinkedList<String>();
        for(String s : pending){
            char c = word.charAt(0);
            if(c == '.'){
                q.offer(s);
                continue;
            } 
            if(s.charAt(0) == c) q.offer(s);
        }
        
        int index = 1;
        while(!q.isEmpty() && index < word.length()){
            System.out.println(q);
            char c = word.charAt(index);
            if(c == '.'){
                index++;
                continue;
            } 
            Queue<String> newQ = new LinkedList<String>();
            while(!q.isEmpty()){
                String s = q.poll();
                if(index < s.length() && s.charAt(index) == c) newQ.offer(s);
            }
            q = newQ;
            index++;
        }
        if(q.isEmpty()) return false;
        else return true;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

/*
TLE 
Set 存储 wordDict
Queue BFS 每次记录匹配到目前的词

如果给一堆长度不匹配的 search，那每次都还要遍历一遍所有的 wordDict 效率太低
*/
class WordDictionary {
    List<String> wordDict = new ArrayList<String>();
    /** Initialize your data structure here. */
    public WordDictionary() {
        
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        wordDict.add(word);
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if(word.indexOf(".") < 0) return wordDict.contains(word);
        Queue<String> q = new LinkedList<String>();
        for(String s : wordDict){
            char c = word.charAt(0);
            if(c == '.' && s.length() == word.length()){
                q.offer(s);
                continue;
            } 
            if(s.charAt(0) == c && s.length() == word.length()) q.offer(s);
        }
        
        int index = 1;
        while(!q.isEmpty() && index < word.length()){
            System.out.println(q);
            char c = word.charAt(index);
            if(c == '.'){
                index++;
                continue;
            } 
            Queue<String> newQ = new LinkedList<String>();
            while(!q.isEmpty()){
                String s = q.poll();
                if(index < s.length() && s.charAt(index) == c && s.length() == word.length()) newQ.offer(s);
            }
            q = newQ;
            index++;
        }
        if(q.isEmpty()) return false;
        else return true;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

/*
TLE 
Set 存储

遇上"."就把该位置遍历一遍所有结果然后继续查找
每次匹配太困难了
*/
class WordDictionary {
    Set<String> wordDict = new HashSet<String>();
    /** Initialize your data structure here. */
    public WordDictionary() {
        
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        wordDict.add(word);
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if(word.indexOf(".") < 0) return wordDict.contains(word);
        else{
            for(char c = 'a'; c <= 'z'; c++){
                String tmp = word.replaceFirst("\\.", String.valueOf(c));
                if(search(tmp)) return true;
            }
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

