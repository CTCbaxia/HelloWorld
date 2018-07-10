/*
212. Word Search II
https://leetcode.com/problems/word-search-ii/description/

TIME: 0710
RESULT: 13%, 501ms
NOTES:to be improved
*/
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