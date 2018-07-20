/*
MEDIUM
289. Game of Life
https://leetcode.com/problems/game-of-life/description/

TIME: 0720 - 30min
RESULT: 61% - 2ms
NOTES:
1. itertor / next 等还要了解


QUESTION:
0. 为什么 board = result 直接相等不行？？ JAVA VISUALIZER 里面是可以让两个数组同时指向一个结果的啊？
1. 为什么用 change.get(i).iterator().next(); 遍历的时候，会有遍历出错的情况？
        //update
        for(int i = 0; i < board.length; i++){
            int size = change.get(i).size();
            while(size > 0){
                int j = change.get(i).iterator().next();
                if(board[i][j] == 0) board[i][j] = 1;
                else board[i][j] = 0;
                size--;
            }
        }
TEST CASE: [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
*/

/*
TIME: 0720 - 30min
RESULT: 61% - 2ms
 
METHOD: 用 List<Set<Integer>> change 来记录需要改变状态的点，in-place?？
*/
class Solution {
    public void gameOfLife(int[][] board) {
        if(board.length == 0) return;
        List<Set<Integer>> change = new ArrayList<Set<Integer>>();
        int[][] result = new int[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            change.add(new HashSet<Integer>());
            for(int j = 0; j < board[0].length; j++){
                checkNeighbors(i, j, board, change.get(i));
            }
        }
        //update
        for(int i = 0; i < board.length; i++){
            int size = change.get(i).size();
            Iterator<Integer> it = change.get(i).iterator();
            while(it.hasNext()){
                int j = it.next();
                if(board[i][j] == 0) board[i][j] = 1;
                else board[i][j] = 0;
            }
        }
        return;
    }
    private void checkNeighbors(int i, int j, int[][] board, Set<Integer> change){
        int nums = 0;
        if(j > 0 && i > 0 && board[i - 1][j - 1] == 1) nums++;
        if(j > 0 && board[i][j - 1] == 1) nums++;
        if(j > 0 && i < board.length - 1 && board[i + 1][j - 1] == 1) nums++;
        
        if(i > 0 && board[i - 1][j] == 1) nums++;
        if(i < board.length - 1 && board[i + 1][j] == 1) nums++;
        
        if(j < board[0].length - 1 && i > 0 && board[i - 1][j + 1] == 1) nums++;
        if(j < board[0].length - 1 && board[i][j + 1] == 1) nums++;
        if(j < board[0].length - 1 && i < board.length - 1 && board[i + 1][j + 1] == 1) nums++;        
        if(board[i][j] == 1){
            if(nums < 2 || nums > 3) change.add(j);
        }else{
            if(nums == 3) change.add(j);
        }
        return;
    }
}

/*
TIME: 0720 - 30min
RESULT: 61% - 2ms
 
METHOD: 两个数组
*/

class Solution {
    public void gameOfLife(int[][] board) {
        if(board.length == 0) return;
        int[][] result = new int[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                checkNeighbors(i, j, board, result);
            }
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
               board[i][j] = result[i][j];
            }
        }
        return;
    }
    private void checkNeighbors(int i, int j, int[][] board, int[][] result){
        int nums = 0;
        if(j > 0 && i > 0 && board[i - 1][j - 1] == 1) nums++;
        if(j > 0 && board[i][j - 1] == 1) nums++;
        if(j > 0 && i < board.length - 1 && board[i + 1][j - 1] == 1) nums++;
        
        if(i > 0 && board[i - 1][j] == 1) nums++;
        if(i < board.length - 1 && board[i + 1][j] == 1) nums++;
        
        if(j < board[0].length - 1 && i > 0 && board[i - 1][j + 1] == 1) nums++;
        if(j < board[0].length - 1 && board[i][j + 1] == 1) nums++;
        if(j < board[0].length - 1 && i < board.length - 1 && board[i + 1][j + 1] == 1) nums++;        
        if(board[i][j] == 1) {
            if(nums >= 2 && nums <= 3) result[i][j] = 1;
        }else{
            if(nums == 3) result[i][j] = 1;
        }
        return;
    }
}





https://leetcode.com/problems/game-of-life/discuss/73366/Clean-O(1)-space-O(mn)-time-Java-Solution