/*
MEDIUM
659. Split Array into Consecutive 

*/
/*
BFS: 
Hint:
example:
q = [
        [3],
        [2,3],
        [1,2,3]
    ]

1. 每个 subarray 内部数字要连续，则 BFS 的时候只要外部断层，所有的 subarray 都失效了。所以每次都会对所有 subarray 是否能够继续有一个结论
2. 既然每次都会有结论，q 内部所有的 subarray 都是以同一个数字结尾。所以只需要放该 subarray 的长度就好。所以转化为 q = [1, 2, 3]
3. queue 顺序应该按 subarray 长度排序。对于新的尾巴，优先加在最短的 subarray 上。


每一层遍历同一个数字，每一个 queue 存同一个结尾数
deque里存现在还在继续增加的 subarray

Time: O(n) 只会从头到尾遍历一遍，清空之前 q 的操作 worst case = O(n)
Space: O(n)
*/
class Solution {
    public boolean isPossible(int[] nums) {
        if(nums.length == 0) return false;
        Deque<Integer> q = new LinkedList<>();//Deque<len of the subarrays>
        int i = 0;
        int pre = nums[0];
        while(i < nums.length){
            Deque<Integer> newQ = new LinkedList<>();
            int n = nums[i];
            while(i < nums.length && nums[i] == n){
                if(n > pre + 1 || q.isEmpty()) newQ.offerFirst(1);//出现断点
                else{
                    newQ.offerLast(q.pollFirst() + 1);
                }
                i++;
            }
            //check all stopped subarray
            while(!q.isEmpty()){
                if(q.pollFirst() < 3) return false;
            }
            q = newQ;//更新 q
            pre = n;//更新上一层数字
        }
        while(!q.isEmpty()){
                if(q.pollFirst() < 3) return false;
        }
        return true;
    }
}







/*
Deque:
题目已经是有序的，所以解答中应该是没有任何排序相关复杂度消耗的。

q 里应该维持有序，随着len长度递增排序
如果是新增的一个subarray，则应该放到最前面 -- 用deque

**!!**
每次对于一组相同数字n，更新完该数的 newQ 之后（将 n 链接到之前 subarray 的末尾），
剩余没有得到更新的 subarray 都应该无法延续，因为断点了。所以扔掉所有 q 里面的数字，如果 len < 3 就有失败的


Time: O(n)
Space: O(frequency)
*/
/*
Deque:
题目已经是有序的，所以解答中应该是没有任何排序相关复杂度消耗的。

q 里应该维持有序，随着len长度递增排序
如果是新增的一个subarray，则应该放到最前面 -- 用deque

**!!**
每次对于一组相同数字n，更新完该数的 newQ 之后（将 n 链接到之前 subarray 的末尾），
剩余没有得到更新的 subarray 都应该无法延续，因为断点了。所以扔掉所有 q 里面的数字，如果 len < 3 就有失败的


Time: O(n)
Space: O(frequency)
*/
class Solution {
    public boolean isPossible(int[] nums) {
        Deque<subArray> q = new LinkedList<>();
        int i = 0;
        while(i < nums.length){
            Deque<subArray> newQ = new LinkedList<>();
            int n = nums[i];
            while(i < nums.length && nums[i] == n){
                if(q.isEmpty()) newQ.offerFirst(new subArray(n, 1));//add new subarray to the front
                else{
                    subArray sa = q.pollFirst();
                    if(sa.last < n - 1){
                        if(sa.len < 3) return false;
                        else newQ.offerFirst(new subArray(n, 1));
                    }else{
                        sa.last = n;
                        sa.len += 1;
                        newQ.offerLast(sa);                        
                    }

                }
                i++;
            }
            while(!q.isEmpty()){
                if(q.pollFirst().len < 3) return false;
            }
            q = newQ;
        }
        while(!q.isEmpty())
            if(q.pollFirst().len < 3) return false;
        
        return true;
    }
    public class subArray{
        int last;
        int len;
        public subArray(int _last, int _len){
            last = _last;
            len = _len;
        }
    }
}


/*
Deque:
题目已经是有序的，所以解答中应该是没有任何排序相关复杂度消耗的。

q 里应该维持有序，随着len长度递增排序
如果是新增的一个subarray，则应该放到最前面 -- 用deque

**!!**
每次对于一组相同数字n，更新完该数的 newQ 之后（将 n 链接到之前 subarray 的末尾），
剩余没有得到更新的 subarray 都应该无法延续，因为断点了。所以扔掉所有 q 里面的数字，如果 len < 3 就有失败的


Time: O(n)
Space: O(frequency)
*/
class Solution {
    public boolean isPossible(int[] nums) {
        Deque<subArray> q = new LinkedList<>();
        int i = 0;
        while(i < nums.length){
            Deque<subArray> newQ = new LinkedList<>();
            int n = nums[i];
            while(i < nums.length && nums[i] == n){
                if(q.isEmpty()) newQ.offerFirst(new subArray(n, 1));
                else{
                    subArray sa = q.pollFirst();
                    if(sa.last < n - 1) return false;
                    sa.last = n;
                    sa.len += 1;
                    newQ.offerLast(sa);
                }
                i++;
            }
            while(!q.isEmpty()){
                if(q.pollFirst().len < 3) return false;
            }
            q = newQ;
        }
        while(!q.isEmpty())
            if(q.pollFirst().len < 3) return false;
        
        return true;
    }
    public class subArray{
        int last;
        int len;
        public subArray(int _last, int _len){
            last = _last;
            len = _len;
        }
    }
}




/*
PriorityQueue ---其实已经排序了就不需要了

Time: O(nlogn)
*/
