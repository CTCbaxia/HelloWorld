/*
909. Snakes and Ladders

*/
/*
Write a function for getting to the right position
Then use BFS to find the shortest path

Time: O(n * n)
Space: O(n * n)
*/
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        
        q.offer(1);//The board square with number 1 has no snake or ladder.
        
        int res = 0;
        while(!q.isEmpty()){
            int len = q.size();
            
            for(int i = 0; i < len; i++){
                int cur = q.poll();
                if(!visited.add(cur)) continue;//visit只存起始的点
                if(cur == n * n) return res;
                
                for(int k = 1; k <= 6 && cur + k <= n * n; k++){
                    int next = cur + k;
                    int nextVal = getValue(board, next);
                    
                    if(nextVal != -1) next = nextVal;
                    if(visited.contains(next)) continue;//只 check 最终跳上去的点
                    q.offer(next);//q 只存最终跳上去的点
                }
            }
            res++;
        }
        return -1;
    }
    private int getValue(int[][] board, int num){
        num--;
        
        int n = board.length;
        int row = n - 1 - num / n;
        int col = ((num / n) % 2 == 0) ? num % n : (n - 1) - num % n;
        return board[row][col];
    }
}






/*
Write a function for getting to the right position
Then use BFS to find the shortest path
*/
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        
        q.offer(1);//The board square with number 1 has no snake or ladder.
        
        int res = 0;
        while(!q.isEmpty()){
            int len = q.size();
            
            System.out.println(q);
            for(int i = 0; i < len; i++){
                int cur = q.poll();
                if(!visited.add(cur)) continue;//visit只存起始的点
                if(cur == n * n) return res;
                for(int k = 1; k <= 6; k++){
                    int next = cur + k;
                    if(next > n * n) break;
                    int nextVal = getValue(board, next);
                    
                    //if(visited.contains(next)) continue;//因为不能连着跳，所以到达的这个点也许是以前人家跳上去的点，而里面的value并没有被用过，所以这里不能跳
                    if(visited.contains(nextVal)) continue;
                    
                    if(nextVal != -1) next = nextVal;
                    q.offer(next);//q 只存最终跳上去的点
                }
            }
            res++;
        }
        return -1;
    }
    private int getValue(int[][] board, int num){
        num--;
        
        int n = board.length;
        int row = n - 1 - num / n;
        int col = ((num / n) % 2 == 0) ? num % n : (n - 1) - num % n;
        return board[row][col];
    }
}