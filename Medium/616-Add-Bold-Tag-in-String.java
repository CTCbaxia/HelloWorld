/*
M
616. Add Bold Tag in String
*/
/*
Trie + Merge Interval
1) build Trie
2) find Interval that need to be bold and merge them
3) apply the bold

length of s : n
length of dic : num, avgLen
Time: (num * avgLen + n^2 + n)
Space: O(avgLen * 128)
*/
class Solution {
    public class Trie{
        Trie[] children;
        String text;
        public Trie(){
            children = new Trie[128];
            text = "";
        }
    }
    public String addBoldTag(String s, String[] dict) {
        //build Trie
        Trie root = new Trie();
        buildTrie(dict, root);
        
        //mark & merge bold
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            int start = i;
            int end = getEndChar(s, i, root);
            if(end != -1){
                if(list.size() > 0 && list.get(list.size() - 1)[1] >= start - 1){// 1-3, 4-6 should be merge
                    list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], end);//merge to largest end
                }else{
                    list.add(new int[]{start, end});
                }
            }
        }
        
        //apply bold
        String open = "<b>";
        String close = "</b>";
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for(int i = 0; i < list.size(); i++){
            int[] word = list.get(i);
            sb.append(s.substring(index, word[0]));//without bold
            sb.append(open).append(s.substring(word[0], word[1] + 1)).append(close);//with bold
            index = word[1] + 1;
        }
        sb.append(s.substring(index));
        return sb.toString();
    }
    private void buildTrie(String[] dict, Trie root){
         for(int i = 0; i < dict.length; i++){
            String word = dict[i];
            Trie node = root;
            for(char c : word.toCharArray()){
                if(node.children[c] == null) node.children[c] = new Trie();
                node = node.children[c];
            }
            node.text = word;
        }
    }
    private int getEndChar(String s, int index, Trie root){
        Trie node = root;
        int end = -1;
        while(index < s.length() && node.children[s.charAt(index)] != null){
            node = node.children[s.charAt(index)];
            if(!node.text.equals("")) end = index;
            index++;
        }
        return end;
    }
}
