/*
M
723. Candy Crush
*/
/*
Loop row and col + Mark can crushed cell to its -value
And use Math.abs(board[i][j]) to check if can crush ---> because the mark for row may change the value for a column

Then for each column, remove all negative number and filled with positive number in order,
fill the blank using 0
!!!DON'T check continuous count for 0

Time: O(mn)
Space: O(1)
*/
class Solution {
    int m;
    int n;
    public int[][] candyCrush(int[][] board) {
        m = board.length;
        n = board[0].length;
        Map<Integer, List<int[]>> colMap = new HashMap<>();
        
        //update and check if there can be at least a crush
        boolean canCrushRow = findRowCrush(board);
        boolean canCrushCol = findColCrush(board);
        if(!canCrushRow && !canCrushCol) return board;
        
        // for(int[] row : board) System.out.println(row);
        //if there can be at least a crush
        crush(board);
        return candyCrush(board);
    }
    private boolean findRowCrush(int[][] board){
        boolean canCrush = false;
        for(int i = 0; i < m; i++){
            int start = -1, preValue = -1, count = 1;
            for(int j = 0; j < n; j++){
                int value = Math.abs(board[i][j]);
                if(value != 0 && value == preValue){
                    count++;
                }else{
                    if(count >=3){
                        for(int k = start; k < j; k++) board[i][k] = -preValue;//mark can crush
                        canCrush = true;
                    }
                    preValue = value;
                    start = j;
                    count = 1;
                }
            }
            //till the end
            if(count >=3){
                for(int k = start; k < n; k++) board[i][k] = -preValue;//mark can crush
                canCrush = true;
            }
        }
        return canCrush;
    }
    private boolean findColCrush(int[][] board){
        boolean canCrush = false;
        for(int j = 0; j < n; j++){
            int start = -1, preValue = -1, count = 1;
            for(int i = 0; i < m; i++){
                int value = Math.abs(board[i][j]);
                if(value != 0 && value == preValue){
                    count++;
                }else{
                    if(count >= 3){
                        for(int k = start; k < i; k++) board[k][j] = -preValue;
                        canCrush = true;
                    }
                    preValue = value;
                    start = i;
                    count = 1;
                }
                
            }
            //till the end
            if(count >= 3){
                for(int k = start; k < m; k++) board[k][j] = -preValue;
                canCrush = true;
            }
        }
        return canCrush;
    }
    // remove and replace all negative value, use 0 to fill the front
    // two pointers
    private void crush(int[][] board){
        for(int j = 0; j < n; j++){
            int index = m - 1;
            for(int i = m - 1; i >= 0; i--){
                // if(j == 2) System.out.println(board[i][j]);
                if(board[i][j] < 0) continue;
                board[index--][j] = board[i][j];
                // if(j == 2) System.out.println(board[i][j]);
            }     
            for(; index >= 0; index--){
                board[index][j] = 0;
            }
        }
    }

}







/*
Loop row and col + Mark can crushed cell to its -value
And use Math.abs(board[i][j]) to check if can crush ---> because the mark for row may change the value for a column

Then for each column, remove all negative number and filled with positive number in order,
fill the blank using 0
!!!DON'T check continuous count for 0

Time: O(mn)
Space: O(1)
*/
class Solution {
    int m;
    int n;
    public int[][] candyCrush(int[][] board) {
        m = board.length;
        n = board[0].length;
        Map<Integer, List<int[]>> colMap = new HashMap<>();
        
        //update and check if there can be at least a crush
        //❌这里有一个极大的问题，在!findRowCrush(board)不可能之后，就不会去做 findColCrush 了
        //这样也不行 boolean canCrush = findRowCrush(board) || findColCrush(board);
        //这里需要保证两个 function 都会 run
        if(!findRowCrush(board) && !findColCrush(board)){
            return board;
        } 
        // for(int[] row : board) System.out.println(row);
        //if there can be at least a crush
        crush(board);
        return candyCrush(board);
    }
    private boolean findRowCrush(int[][] board){
        boolean canCrush = false;
        for(int i = 0; i < m; i++){
            int start = -1, preValue = -1, count = 1;
            for(int j = 0; j < n; j++){
                int value = Math.abs(board[i][j]);
                if(value != 0 && value == preValue){
                    count++;
                }else{
                    if(count >=3){
                        for(int k = start; k < j; k++) board[i][k] = -preValue;//mark can crush
                        canCrush = true;
                    }
                    preValue = value;
                    start = j;
                    count = 1;
                }
            }
            //till the end
            if(count >=3){
                for(int k = start; k < n; k++) board[i][k] = -preValue;//mark can crush
                canCrush = true;
            }
        }
        return canCrush;
    }
    private boolean findColCrush(int[][] board){
        boolean canCrush = false;
        for(int j = 0; j < n; j++){
            int start = -1, preValue = -1, count = 1;
            for(int i = 0; i < m; i++){
                int value = Math.abs(board[i][j]);
                if(value != 0 && value == preValue){
                    count++;
                }else{
                    if(count >= 3){
                        for(int k = start; k < i; k++) board[k][j] = -preValue;
                        canCrush = true;
                    }
                    preValue = value;
                    start = i;
                    count = 1;
                }
                
            }
            //till the end
            if(count >= 3){
                for(int k = start; k < m; k++) board[k][j] = -preValue;
                canCrush = true;
            }
        }
        return canCrush;
    }
    // remove and replace all negative value, use 0 to fill the front
    // two pointers
    private void crush(int[][] board){
        for(int j = 0; j < n; j++){
            int index = m - 1;
            for(int i = m - 1; i >= 0; i--){
                // if(j == 2) System.out.println(board[i][j]);
                if(board[i][j] < 0) continue;
                board[index--][j] = board[i][j];
                // if(j == 2) System.out.println(board[i][j]);
            }     
            for(; index >= 0; index--){
                board[index][j] = 0;
            }
        }
    }

}
//❌自己想的不好的解法
class Solution {
    public int[][] candyCrush(int[][] board) {
        Map<Integer, List<int[]>> colMap = new HashMap<>();
        
        findRowCrush(board, colMap);
        findColCrush(board, colMap);
        if(colMap.size() == 0) return board;
        for(int k : colMap.keySet()){
            for(int[] tmp : colMap.get(k)){
                System.out.println(k + " "+ tmp[0] + tmp[1]);
            }
        } 
       
        removeCol(board, colMap);
        return candyCrush(board);
    }
    private void findRowCrush(int[][] board, Map<Integer, List<int[]>> map){
        //find row crush
        for(int i = 0; i < board.length; i++){
            int start = -1, pre = -1, count = 1;
            boolean canCrush = false;
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == pre){
                    count++;
                    if(count >= 3) canCrush = true;
                }else{
                    if(canCrush){
                        for(int k = start; k < j; k++){
                            if(!map.containsKey(k)) map.put(k, new ArrayList<>());
                            map.get(k).add(new int[]{i, i});
                        }
                        canCrush = false;
                    }
                    pre = board[i][j];
                    start = j;
                    count = 1;
                }
            }
            if(canCrush){
                for(int k = start; k < board[0].length; k++){
                    if(!map.containsKey(k)) map.put(k, new ArrayList<>());
                    map.get(k).add(new int[]{i, i});
                }
            }
        }
    }
    private void findColCrush(int[][] board, Map<Integer, List<int[]>> map){
        //find col crush
        for(int j = 0; j < board[0].length; j++){
            int start = -1, pre = -1, count = 1;
            boolean canCrush = false;
            for(int i = 0; i < board.length; i++){
                if(board[i][j] == pre){
                    count++;
                    if(count >= 3) canCrush = true;
                }else{
                    if(canCrush){
                        if(!map.containsKey(j)) map.put(j, new ArrayList<>());
                        map.get(j).add(new int[]{start, i - 1});
                        canCrush = false;
                    }
                    pre = board[i][j];
                    start = i;
                    count = 1;
                }
            }
            if(canCrush){
                if(!map.containsKey(j)) map.put(j, new ArrayList<>());
                map.get(j).add(new int[]{start, board.length - 1});
            }
        }
    }
    //merge the interval
    private void merge(Map<Integer, List<int[]>> colMap){
        
        
    }
    private void removeCol(int[][] board, Map<Integer, List<int[]>> map){
        for(int j : map.keySet()){
            List<int[]> list = map.get(j);
            for(int[] crush : list){
                drop(board, crush[0], crush[1], j);
            }
        }
    }

    private void drop(int[][] board, int iStart, int iEnd, int j){//above start drop to end
        int pointer = iStart - 1;
        for(int index = iEnd; index >= 0; index--){
            if(pointer >= 0) board[index][j] = board[pointer--][j];
            else board[index][j] = 0;
        }
    }
}
