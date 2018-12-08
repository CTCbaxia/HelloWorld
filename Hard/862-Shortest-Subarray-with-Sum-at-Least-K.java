/*
HARD
862. Shortest Subarray with Sum at Least K

TIME: 
RESULT: 
*/
/*
Sliding Window
计算 preSum + 用 deque 维持一个pending index 序列，是的里面对应的 presum 递增，每次移除不可能使用的index
****核心思想：*****
1）要保持一个递增的 pending match queue：如果这个 queue 里面有递减的presum (i < j && presum[i] > presum[j]),那么肯定是不会选 i 的，因为如果i满足，后面的j肯定也满足，且index - j的距离更小

2）用过的left index 肯定不会再用了（不可能得到更优解了）： 在 right index 一直往后移的过程中，如果对于 right index 来说有一个最大 left index 满足条件，那么  right index 之后的元素就不可能在 pick 那个 left index了，因为得到的res肯定距离更大


We can rephrase this as a problem about the prefix sums of A. Let P[i] = A[0] + A[1] + ... + A[i-1]. We want the smallest y-x such that y > x and P[y] - P[x] >= K.

Motivated by that equation, let opt(y) be the largest x such that P[x] <= P[y] - K. We need two key observations:

1) If x1 < x2 and P[x2] <= P[x1], then opt(y) can never be x1, as if P[x1] <= P[y] - K, then P[x2] <= P[x1] <= P[y] - K but y - x2 is smaller. This implies that our candidates x for opt(y) will have increasing values of P[x].

2) If opt(y1) = x, then we do not need to consider this x again. For if we find some y2 > y1 with opt(y2) = x, then it represents an answer of y2 - x which is worse (larger) than y1 - x.


Time: O(n)
Space: O(n)
*/
class Solution {
    public int shortestSubarray(int[] A, int K){
        int[] sums = new int[A.length + 1];
        for(int i = 1; i < sums.length; i++){
            sums[i] = sums[i - 1] + A[i - 1];
        }
        int res = A.length + 1;// A.length + 1 is impossible
        Deque<Integer> mono = new LinkedList<>();
        for(int i = 0; i < sums.length; i++){
            //make it a queue of index with increasing mono presum (从后往前移除前面比presum更小的部分)
            while(!mono.isEmpty() && sums[i] <= sums[mono.getLast()]){
                mono.pollLast();
            }
            //从前往后移除能够满足K结果的index
            while(!mono.isEmpty() && sums[i] - sums[mono.getFirst()] >= K){
                res = Math.min(res, i - mono.pollFirst());
            }
            mono.offerLast(i);
        }
        return res == A.length + 1 ? -1 : res;
    }                 
               
}





/*
PreSum + PriorityQueue: int[sum, index]

Time: O(n) + O(nlogn) + O(nlogn)
Space: O(n)
*/
class Solution {
    public int shortestSubarray(int[] A, int K){
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2){
                return i1[0] - i2[0];
            }
        });
        int[] sums = new int[A.length + 1];
        pq.offer(new int[]{0, 0});
        for(int i = 1; i < sums.length; i++){
            sums[i] = sums[i - 1] + A[i - 1];
            pq.offer(new int[]{sums[i], i});//i 相减就是要的len
        }
        if(pq.size() <= 1) return -1;

        int res = sums.length + 1;
        for(int i = 1; i < sums.length; i++){
            Queue<int[]> q = new LinkedList<>();
            int[] tmp = pq.poll();
            while(sums[i] - tmp[0] >= K){//要把pq里面所有成立的都 poll 出来
                if(tmp[1] < i){//tmp[1] < i 要注意这里不要poll到 index 之后的 sums 了
                    res = Math.min(res, i - tmp[1]);
                }
                q.offer(tmp);
                if(!pq.isEmpty()) tmp = pq.poll();
                else break;
            }
            pq.offer(tmp);
            while(!q.isEmpty()){
                pq.offer(q.poll());
            }
        }
        return res == sums.length + 1 ? -1 : res;
    }  


}


/*
PreSum + Two Pointers

Time: O(n^2)
Space: O(n)
*/
public int shortestSubarray(int[] A, int K){
    int[] sums = new int[A.length];
    int sum = 0;
    for(int i = 0; i < A.length; i++){
        sum += A[i];
        sums[i] = sum;
    }
    int res = sums.length + 1;
    for(int i = 0; i < sums.length; i++){
        for(int j = i; j < sums.length; j++){
            if(sums[j] - sums[i] + A[i] >= K){
                res = Math.min(res, j - i + 1);
            }
        }
    }
    return res == sums.length + 1 ? -1 : res;
}  