/*
79. Word Search
https://leetcode.com/problems/word-search/description/

TIME: 0710
RESULT: 99.5%, 8ms
NOTE:

*/
/*
DFS + backtracking (Set for visited)

Time: O(mn * 4^len)
Space: O(mn) + O(k) //recursive //can also modify the board to save space c -> '\0'
*/
class Solution {
    public boolean exist(char[][] board, String word){
        if(board.length == 0 || board[0].length == 0) return word.length() == 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    if(search(board, word, 1, i, j)) return true;
                }
            }
        }
        return false;
    }
    private boolean search(char[][] board, String word, int index, int i, int j){
        if(index == word.length()) return true;
        
        board[i][j] = '*';
        
        int[][] direction = new int[][]{{1, 0}, {-1, 0},{0, 1}, {0, -1}};
        for(int[] dir : direction){
            int row = i + dir[0];
            int col = j + dir[1];
            if(row >= 0 && row < board.length && col >= 0 && col < board[0].length){
                if(board[row][col] == word.charAt(index)){
                    if(search(board, word, index + 1, row, col)) return true;
                }
            }
        }
        board[i][j] = word.charAt(index - 1);
        return false;
    }
}










/*
DFS + backtracking (Set for visited)

Time: O(mn * 4^len)
Space: O(mn) + O(k) //recursive //can also modify the board to save space c -> '\0'
*/
class Solution {
    public boolean exist(char[][] board, String word){
        if(board.length == 0 || board[0].length == 0) return word.length() == 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    if(search(board, word, 1, i, j, new HashSet<Integer>())) return true;
                }
            }
        }
        return false;
    }
    private boolean search(char[][] board, String word, int index, int i, int j, Set<Integer> visited){
        if(index == word.length()) return true;
        
        visited.add(i * board[0].length + j);
        
        int[][] direction = new int[][]{{1, 0}, {-1, 0},{0, 1}, {0, -1}};
        for(int[] dir : direction){
            int row = i + dir[0];
            int col = j + dir[1];
            if(row >= 0 && row < board.length && col >= 0 && col < board[0].length){
                if(visited.contains(row * board[0].length + col)) continue;
                if(board[row][col] == word.charAt(index)){
                    if(search(board, word, index + 1, row, col, visited)) return true;
                }
            }
        }
        visited.remove(i * board[0].length + j);
        return false;
    }
}



/*
DFS + backtracking (Set for visited)

Time: O(mn * 4^len)
Space: O(k) //recursive
*/
class Solution {
    public boolean exist(char[][] board, String word){
        if(board.length == 0 || board[0].length == 0) return word.length() == 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    if(search(board, word, 1, i, j)) return true;
                }
            }
        }
        return false;
    }
    private boolean search(char[][] board, String word, int index, int i, int j){
        if(index == word.length()) return true;
        
        char c = board[i][j];
        board[i][j] = '\0';
        
        int[][] direction = new int[][]{{1, 0}, {-1, 0},{0, 1}, {0, -1}};
        for(int[] dir : direction){
            int row = i + dir[0];
            int col = j + dir[1];
            if(row >= 0 && row < board.length && col >= 0 && col < board[0].length){
                if(board[row][col] == word.charAt(index)){
                    if(search(board, word, index + 1, row, col)) return true;
                }
            }
        }
        board[i][j] = c;
        return false;
    }
}














/*
1. 让一个 char 数组为 空：board[row][column] = '\0';
2. 注意传参： 数组，List 都是传的地址（改变的是原始值）；而 boolean， string， int 是复制 value
3. 注意方法的执行时序，只需要保证在执行该方法的时候 '/0' 的限制有效就行，在执行方法之后可以还原 board
4. 注意在 loop 里面一旦得到 true 就要 return，不然可能会在之后的 loop 中覆盖

QUESTION:
不是很懂 private void 的写法为什么就那么慢？？
*/
class Solution {
    public boolean exist(char[][] board, String word) {
        
 
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){           
                    if(nextChar(board, word, 0, i, j)) return true;//careful
            }
        }
        return false;
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

