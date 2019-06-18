/*
249. Group Shifted Strings

 */
/*
Trie
using distance of each letter from the first letter to build Trie

Time: O(n*len) -- len = avg len of words, n = num of words
Space: O(n*len*26)
*/
class Solution {
    public class Trie{
        Trie[] children;
        List<String> strs;
        public Trie(){
            children = new Trie[26];
            strs = new ArrayList<>();
        }
    }
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        
        //generate Trie
        Trie root = new Trie();
        for(String s : strings){
            if(s.length() == 0){//for ""
                if(res.size() == 0) res.add(new ArrayList<>());
                res.get(0).add(s);
                continue;
            }
            char first = s.charAt(0);
            Trie node = root;
            for(int i = 1; i < s.length(); i++){
                int d = (s.charAt(i) - first + 26) % 26;//may yield -1, cannot be index
                if(node.children[d] == null) node.children[d] = new Trie();
                node = node.children[d];
            }
            node.strs.add(s);
        }
        
        //dfs the Trie
        dfs(root, res);
        return res;
        
    }
    private void dfs(Trie node, List<List<String>> res){
        if(node == null) return;
        if(node.strs.size() > 0) res.add(node.strs);
        
        for(int i = 0; i < 26; i++){
            dfs(node.children[i], res);
        }
        
    }
}