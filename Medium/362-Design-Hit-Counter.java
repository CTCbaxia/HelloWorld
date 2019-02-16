/*
362. Design Hit Counter
*/
/*
循环数组
hits[300]: num of hits at this time
time[300]: timestamp for this hits

如果这个循环时间点对应的 timestamp 是在 300s 之内，加上 hits[i] 作为 300s 之内在该时间点的 hit

Time: O(1)
Space: O(1)
*/
class HitCounter {
    int[] hits;
    int[] times;
    /** Initialize your data structure here. */
    public HitCounter() {
        hits = new int[300];
        times = new int[300];
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int t = timestamp % 300;
        if(times[t] == timestamp){
            hits[t]++;
        }else{
            hits[t] = 1;
            times[t] = timestamp;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int count = 0;
        for(int i = 0; i < 300; i++){
            if(timestamp - times[i] < 300) count += hits[i];
        }
        return count;
    }
}



/*
Rate Limiter: Queue

Time:
hit: O(1)
getHit: O(s)

*/
class HitCounter {
    Queue<Integer> q;
    /** Initialize your data structure here. */
    public HitCounter() {
        q = new LinkedList<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        q.offer(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while(!q.isEmpty() && q.peek() <= timestamp - 300) q.poll();
        return q.size();
    }
    
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */