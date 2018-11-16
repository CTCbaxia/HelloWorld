/*
MEDIUM
36. Valid Sudoku

TIME: 
RESULT: 
NOTES:

*/
/*
One Pass: Find the right set to check

Time: O(n)
Space: O(n)
*/
class Solution {
    public boolean isValidSudoku(char[][] board) {
        List<Set<Character>> subboards = new ArrayList<>();
        List<Set<Character>> cols = new ArrayList<>();
        //initialization
        for(int i = 0; i < board[0].length; i++){
            subboards.add(new HashSet<Character>());
            cols.add(new HashSet<Character>());
        }
        for(int i = 0; i < board.length; i++){
            Set<Character> r = new HashSet<Character>();
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.') continue;
                
                //check row
                if(!r.add(board[i][j])) return false;
                
                //check col
                if(!cols.get(j).add(board[i][j])) return false;
                
                //check board
                int rboard = i / 3;
                int cboard = j / 3;
                int b = rboard * 3 + cboard;
                if(!subboards.get(b).add(board[i][j])) return false;
            }
        }
        return true;
    }
}