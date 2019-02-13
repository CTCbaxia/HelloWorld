/*
957. Prison Cells After N Days

*/
/*
Find Pattern / Cycle
一共就 8 个数，一万次的迭代肯定有回到某一个状态的loop (迭代数字过大，一定会有一个循环点)
然后就可以利用循环关系，
==> 直接 reduce N 循环

Time: O(2 * circle * len) circle <= 2^6 = 64
Space: O(len)
*/
class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        
        Set<String> set = new HashSet<>();
        while(N-- > 0){
            int[] cells2 = new int[cells.length];
            for(int i = 0; i < cells2.length; i++){
                if(i == 0 || i == cells2.length - 1) cells2[i] = 0;
                else cells2[i] = 1 ^ cells[i - 1] ^ cells[i + 1];//XOR 来判断前后是否一致
            }
            String s = Arrays.toString(cells2);
            if(!set.add(s)){//if set.contains(s)
                N = N % set.size();
            }  
            
            cells = cells2;
        }
        return cells;
        
    }
}





/*
一共就 8 个数，一万次的迭代肯定有回到某一个状态的loop (迭代数字过大，一定会有一个循环点)
然后就可以利用循环关系
==> 找到存在 map 里面的对应值

Note that cells.length = 8, and cells[0] and cells[7] will become 0.
In fact, cells have only 2 ^ 6 = 64 different states.
And there will be a loop.

We record all seen states.
Be careful,
we need transform array to string as the key,
otherwise it use the reference.

Time: O(N * len)
Space: O(len)
*/
class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        int[] res;
        int n = 0;
        List<int[]> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        while(n < N){
            res = new int[cells.length];
            for(int i = 0; i < res.length; i++){
                if(i == 0 || i == res.length - 1) res[i] = 0;
                else res[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            }
            //add memo for the 1st transform, with index starting from 0
            String s = Arrays.toString(res);
            if(!set.add(s)) break;
            list.add(res);
            
            cells = res;
            n++;
        }
        if(n == N) return cells;
        else return list.get((N - 1) % list.size());
        
    }
}




/*
TLE --
BFS
Time: O(N * len)
Space: O(len)
*/
class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        int[] res;
        while(N-- > 0){
            res = new int[cells.length];
            for(int i = 0; i < res.length; i++){
                if(i == 0 || i == res.length - 1 || cells[i - 1] != cells[i + 1]){
                    res[i] = 0;
                }else{
                    res[i] = 1;
                }
            }
            cells = res;
        }
        return cells;
    }
}
/*
TLE --
BFS  +Bit Manipulation
Time: O(N * len)
Space: O(len)
*/
class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        int[] res;
        while(N-- > 0){
            res = new int[cells.length];
            for(int i = 0; i < res.length; i++){
                if(i == 0 || i == res.length - 1) res[i] = 0;
                else res[i] = 1 ^ cells[i - 1] ^ cells[i + 1];//XOR 来判断前后是否一致
            }
            cells = res;
        }
        return cells;
    }
}