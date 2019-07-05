/*
HARD
632. Smallest Range


*/
/*
PriorityQueue
NumIndex(num, row, col) 来得到这个 num 是哪个 list 的第几个数(row, col)
保证 pq 里面所有 list 都有一个代表。如果某一个 list 到终点了，return res
每次 poll 最小值，更新 smallest range，取当前 num 所在 list 的下一个数加到 pq，并更新 max

Time: O(kn*logk) worst case 会遍历所有的值，并且为pq 大小为 logk
Space: O(k)
*/
class Solution {
    public class NumIndex{
        int num;
        int row;
        int col;
        public NumIndex(int _num, int _row, int _col){
            num = _num;//the number
            row = _row;
            col = _col;
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        int[] res = new int[2];
        if(nums.size() == 0) return res;
            
        PriorityQueue<NumIndex> pq = new PriorityQueue<>(new Comparator<NumIndex>(){
            public int compare(NumIndex ni1, NumIndex ni2){
                return ni1.num - ni2.num;
            }
        });
        
        int max = Integer.MIN_VALUE;
        //inialization
        for(int i = 0; i < nums.size(); i++){
            int num = nums.get(i).get(0);
            pq.offer(new NumIndex(num, i, 0));
            max = Math.max(max, num);//update max
        }
        
        res[0] = 0;
        res[1] = Integer.MAX_VALUE;//or 2 * 100000, the largest possible range
        
        while(pq.size() == nums.size()){//if pq contains element from all list
            NumIndex curMin = pq.poll();
            int min = curMin.num;
            int row = curMin.row;
            int col = curMin.col;
            if(max - min < res[1] - res[0]){//update smallest range
                res[0] = min;
                res[1] = max;
            }
            
            if(col + 1 < nums.get(row).size()){
                pq.offer(new NumIndex(nums.get(row).get(col + 1), row, col + 1));
                max = Math.max(max, nums.get(row).get(col + 1));//max can only get larger. update current max
            }
        }
        return res;
    }
}



/*
PriorityQueue
pq里面每个list只有一个值。offer 的时候 track最大值，poll 的时候得到最小值。
每次 poll 之后，在该数所在的 list 里面再放下一个值进入 pq
终点：pop出来的最小的值没有下一个值了 （pq.size() != nums.size()）

int[] indices: 每一个 list 目前访问到的 index
NumIndex: num = 当前的数字, index = 当前数字所在的那个 list

Time: O(kn*logk) worst case 会遍历所有的值，并且为pq 大小为 logk
Space: O(k)
*/
class Solution {
    public class NumIndex{
        int num;
        int index;
        public NumIndex(int _num, int _index){
            num = _num;//the number
            index = _index;//index of list where the num sit in
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        int[] res = new int[2];
        if(nums.size() == 0) return res;
            
        PriorityQueue<NumIndex> pq = new PriorityQueue<>(new Comparator<NumIndex>(){
            public int compare(NumIndex ni1, NumIndex ni2){
                return ni1.num - ni2.num;
            }
        });
        int[] indices = new int[nums.size()];//maintain index of each list of num (where it is now)
        int max = Integer.MIN_VALUE;
        //inialization
        for(int i = 0; i < indices.length; i++){
            int num = nums.get(i).get(indices[i]);
            pq.offer(new NumIndex(num, i));
            max = Math.max(max, num);//update max
        }
        
        res[1] = 2 * 100000 + 1;//the largest possible range
        
        while(true){
            NumIndex minNI = pq.poll();
            int min = minNI.num;
            int index = minNI.index;
            if(max - min < res[1] - res[0]){
                res[0] = min;
                res[1] = max;
            }
            indices[index]++;//move to the next num of that list
            
            if(indices[index] == nums.get(index).size()){//pop出来的最小的值没有下一个值了，不可能集齐了
                 return res;
            }
            //get the next num of the current poped num in its list
            int newNum = nums.get(index).get(indices[index]);
            max = Math.max(max, newNum);//update the max value in the pq
            
            pq.offer(new NumIndex(newNum, index));
        }
    }
}