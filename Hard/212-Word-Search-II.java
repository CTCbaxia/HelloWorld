/*
212. Word Search II
https://leetcode.com/problems/word-search-ii/description/

TIME: 0710
RESULT: 13%, 501ms
NOTES:to be improved
*/
/*
TrieNode + DFS(Backtracking)

对于一系列单词建立 Trie
然后对于每一个搜索 path，都同步查看 Trie 是否有效，
1) 无效就返回
2) 如果查到一个单词，就输出到结果

Time: O(mn * wl * l) for every board[i][j], we search through the trie
Space: O(wl * l) l is number of words, wl is average len of word
*/ 
class Solution {
    public List<String> findWords(char[][] board, String[] words){
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                dfs(board, i, j, root.children[board[i][j] - 'a'], result);//找到对应node
            }
        }
        return result;
    }
    public void dfs(char[][] board, int i, int j, TrieNode node, List<String> result){
        if(node == null) return;//in this path, no matched char in words -- path fail
        if(node.word != ""){
            result.add(node.word);
            node.word = "";//remove duplicate
        }
        
        char tmp = board[i][j];
        board[i][j] = '#';
        
        int[][] directions = new int[][]{{1, 0},{-1, 0},{0, 1},{0, -1}};
        for(int[] dir : directions){
            int row = i + dir[0];
            int col = j + dir[1];
            if(row < 0 || col < 0 || row >= board.length || col >= board[0].length) continue;
            if(board[row][col] == '#') continue;
            
            char c = board[row][col];
            dfs(board, row, col, node.children[c - 'a'], result);
        }
        board[i][j] = tmp;//backtracking
        return;
    }
    public TrieNode buildTrie(String[] words){
        TrieNode root = new TrieNode();
        for(String s : words){
            TrieNode node = root;
            for(char c : s.toCharArray()){
                if(node.children[c - 'a'] == null){
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.word = s;
        }
        return root;
    }
    public class TrieNode{
        TrieNode[] children;
        String word;
        public TrieNode(){
            children = new TrieNode[26];
            word = "";
        }
    }
} 












/*
Backtracking
TLE

Time: O(num * mn * 4^len)
Space: O()
*/
class Solution {
    public List<String> findWords(char[][] board, String[] words){
        List<String> result = new ArrayList<>();
        boolean[][] used = new boolean[board.length][board[0].length];
        for(String s : words){
            if(result.contains(s)) continue;
            labelWord:
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[0].length; j++){
                    if(board[i][j] == s.charAt(0)){
                        if(find(board, s, 1, i, j, used)){
                            result.add(s);
                            break labelWord;//jump out to labelWord loop
                        }
                    }
                }
            }
        }
        return result;
    }
    private boolean find(char[][] board, String s, int index, int i, int j, boolean[][] used){
        if(index == s.length()) return true;

        used[i][j] = true;
        int[][] directions = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        boolean res = false;
        for(int[] dir : directions){
            int row = i + dir[0];
            int col = j + dir[1];

            if(row < 0 || col < 0 || row >= board.length || col >= board[0].length) continue;
            if(board[row][col] == s.charAt(index) && !used[row][col]){//注意检查没用过
                if(find(board, s, index + 1, row, col, used)){//因为要检查多个单词，所以要记住backtracking完成
                    res = true;
                    break;
                } 
            }
        }
        used[i][j] = false;
        return res;
    }
}











class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<String>();
        for(int k = 0; k < words.length; k++){

            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[i].length; j++){           
                        if(nextChar(board, words[k], 0, i, j)){
                            if(!result.contains(words[k])){
                                result.add(words[k]);
                            }
                        };
                }
            }
        }
        return result;
        
    }
    private boolean nextChar(char[][] board, String word, int index, int row, int column){
        if(row < 0 || row >= board.length || column < 0 || column >= board[0].length){
            return false;
        }
        if(board[row][column] == word.charAt(index)){
            if(index == word.length() - 1){
                return true;
            }
            char c = board[row][column];
            board[row][column] = '\0';
            boolean res = nextChar(board, word, index + 1, row - 1, column) || nextChar(board, word, index + 1, row + 1, column) || nextChar(board, word, index + 1, row, column - 1) || nextChar(board, word, index + 1, row, column + 1);
            board[row][column] = c;
            return res;
        }else{
            return false;
        }
    }
}