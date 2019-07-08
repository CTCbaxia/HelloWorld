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
/*
Mark boolean bold for every char

length of s : n
length of dic : num, avgLen
Time: (num * avgLen * n + n)
Space: O(n)
*/
public class Solution {
    public String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        for(String str : dict){
            int start = 0;
            while(start < s.length()){
                start = s.indexOf(str, start);//每次跨过之前匹配的
                if(start < 0) break;
                int end = start + str.length();
                for(int i = start; i < end; i++){
                    bold[i] = true;
                }
                start++;
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean inBold = false;
        for(int i = 0; i < s.length(); i++){
            if(!inBold && bold[i]){//first bold char
                inBold = true;
                sb.append("<b>");
            }else if(inBold && !bold[i]){//first char out of bold
                inBold = false;
                sb.append("</b>");
            } 
            sb.append(s.charAt(i));
        }
        if(inBold) sb.append("</b>");//🙅注意结尾要check
        return sb.toString();
    }
}






//---------------------trial
/*
Mark boolean bold for every char

length of s : n
length of dic : num, avgLen
Time: (num * avgLen * n + n)
Space: O(n)
*/
class Solution {
    public String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        for(String str : dict){
            int index = 0;
            int end = 0;//avoid duplicate assign boolean
            while(index < s.length()){
                int start = s.indexOf(str, index);
                if(start >= 0){//❌"zzz",["zz"] 会出现错误，因为不会再匹配最后一个 z
                    int preEnd = end == 0 ? start : end;//为了减少重复赋值
                    end = start + str.length();//🙅end is not included
                    for(int i = Math.max(preEnd,start); i < end; i++){//为了减少重复赋值
                        bold[i] = true;
                    }
                }
                index++;
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean inBold = false;
        for(int i = 0; i < s.length(); i++){
            if(!inBold && bold[i]){//first bold char
                inBold = true;
                sb.append("<b>");
            }else if(inBold && !bold[i]){//first char out of bold
                inBold = false;
                sb.append("</b>");
            } 
            sb.append(s.charAt(i));
        }
        if(inBold) sb.append("</b>");//🙅注意结尾要check
        return sb.toString();
    }
}




/*❌
Mark boolean bold for every char

length of s : n
length of dic : num, avgLen
Time: (num * avgLen * n + n)
Space: O(n)
*/
class Solution {
    public String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        for(String str : dict){
            int index = 0;
            int end = -1;//avoid duplicate assign boolean
            while(index < s.length()){
                int start = s.indexOf(str, index);
                //find a str in s that starts later than previous end, 
                //🙅 mind start: initial end value is -1, but start should always be >= 0 to mean find str
                //🙅 mind end: updated end value is 1 larger than the previous actual end
                if(start >= 0 && start >= end){//❌"zzz",["zz"] 会出现错误，因为不会再匹配最后一个 z
                    end = start + str.length();//🙅end is not included
                    for(int i = start; i < end; i++){
                        bold[i] = true;
                    }
                }
                index++;
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean inBold = false;
        for(int i = 0; i < s.length(); i++){
            if(!inBold && bold[i]){//first bold char
                inBold = true;
                sb.append("<b>");
            }else if(inBold && !bold[i]){//first char out of bold
                inBold = false;
                sb.append("</b>");
            } 
            sb.append(s.charAt(i));
        }
        if(inBold) sb.append("</b>");//🙅注意结尾要check
        return sb.toString();
    }
}




