/*
MEDIUM
208. Implement Trie (Prefix Tree)

TIME: 
RESULT: 
NOTES:
Happy Birthday!!! 20181104

*/
/*
Trie:
在外面搭构造函数


*/
public class TrieNode{
    public TrieNode[] children;
    public String word;
    public TrieNode(){
        children = new TrieNode[26];
        word = "";
    }
}

class Trie {
    public TrieNode root;    
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
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
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()){
            if(node.children[c - 'a'] == null) return false;
            else node = node.children[c - 'a'];
        }
        return node.word.equals(word);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(char c : prefix.toCharArray()){
            if(node.children[c - 'a'] == null) return false;
            else node = node.children[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */