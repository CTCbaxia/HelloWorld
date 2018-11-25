/*
419. Battleships in a Board
https://leetcode.com/problems/battleships-in-a-board/description/

TIME: 0710
RESULT: 100%, 2ms
*/
/*
only count for the head
be careful for the boundary

Time: O(n^2)
Space: O(1)
*/
class Solution {
    public int countBattleships(char[][] board) {
        int res = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == 'X'){
                    if(i > 0 && board[i - 1][j] != '.') continue;
                    if(j > 0 && board[i][j - 1] != '.') continue;
                    res++;
                }
            }
        }
        return res;
    }
}



/*
NOTE:
题解：船已经画好，只需要数船数量；board 中不会有十字交叉的船，也不会有平行相邻的船（没有船是互相挨着的）
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.

下图是 invalid 的：
X X X .
. X X X 
X . . .
X . . .

METHOD:
one pass 的思维很巧妙：船的数量靠数船头确定，左边和上边都没有水，就是船头；至于船身到底是横着还是竖着，有多长，无所谓

TODO:
如果跟之前理解错误的题一样，题目没有划分好船呢（invalid board）？
如果要自己划分有多少没有交叉的船？
*/
class Solution {
    public int countBattleships(char[][] board) {

        int count = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == '.'){ 
                    continue;
                }else{
                    if(i > 0 && board[i - 1][j] == 'X') continue;
                    if(j > 0 && board[i][j - 1] == 'X') continue;
                    count ++;
                }
                
            }
        }
        return count;
    }
}


//题目理解错误了，其实题目已经跟你把船躺好了，不需要你来确认船有没有交叉。这种就很复杂了...
class Solution {
    public int countBattleships(char[][] board) {
        int vertical_num = 0;
        for(int i = 0; i < board.length; i++){
            boolean vstart = true;
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == 'X'){
                    if(check_horizontal(board, i, j) && vstart){
                        if(check_vertical(board, i, j)){
                            vertical_num ++;
                        }else{
                            continue;
                        }
                    }else{
                        vstart = false;
                        continue;
                    }
                }else{
                   vstart = true; 
                }
                
            }
        }
    }
    private boolean check_vertical(char[][] board, int row, int column){//左右没有水
        //check vertical
        if(row >= 1 && row =< board.length - 1){
            if(board[row - 1][column] != '.' || board[row + 1][column]  != '.'){ return false;}
        }else if(row < 1){
            if(board[row + 1][column]  != '.'){ return false;}
        }else if(row > board.length - 1){
            if(board[row - 1][column] != '.'){ return false;}
        }
        return true;
    }
    private boolean check_horizontal(char[][] board, int row, int column){//上下没有水
        if(column >= 1 && column =< board[i].length - 1){
           if(board[row][column - 1] != '.' || board[row][column + 1] != '.'){ return false;}
        }else if(column < 1){
            if(board[row][column + 1] != '.'){ return false;}
        }else if(column > board[i].length - 1){
            if(board[row][column - 1] != '.'){ return false;}
        }
        return true;
    }
}
