/*
EASY
703. Kth Largest Element in a Stream

RESULT: 
NOTES: 
*/
/*
PriorityQueue: 

Time: O(logk)
Space: O(k)
*/
class KthLargest {
    public int k;
    public PriorityQueue<Integer> pq;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<Integer>();
        for(int i = 0; i < nums.length; i++){
            pq.offer(nums[i]);
            if(pq.size() > k) pq.poll();
        }
    }
    
    public int add(int val) {
        pq.offer(val);
        if(pq.size() > k) pq.poll();
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */