/*
M
911. Online Election
*/
/*
Binary Search
这题有一个重要的信息就是 times 是递增的

构造一个数组，对于每个时间更新 winner
对于每次 query 都时间做 binary search 找到 smallOrEqual 的对应点，返回改时间点的 winner

Time: 
    Build map: O(n)
    Operation q: O(logn)
Space: O(n)
*/
class TopVotedCandidate {
    int[] winners;
    int[] times;
    public TopVotedCandidate(int[] persons, int[] times) {
        int n = persons.length;
        
        this.times = times;
        winners = new int[n];
        
        int[] count = new int[n];
        int maxVote = -1, winner = 0;
        for(int i = 0; i < n; i++){
            //vote for this person
            count[persons[i]]++;
            
            //persons[i] gets most votes, update most recent when there is a tie
            if(count[persons[i]] >= maxVote){
                winner = persons[i];
                maxVote = count[persons[i]];//!!! update maxVote
            } 
            
            winners[i] = winner;//update the winner for this time index
        }
    }
    
    public int q(int t) {// binary search to find the time point
        int l = 0, r = times.length - 1;//index
        while(l < r){
            int m = l + (r - l)/2 + 1;//need offset
            if(times[m] > t) r = m - 1;
            else l = m;//l always hold the smallOrEqual point
        }
        //l == r
        return winners[l];
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
/*
TreeMap

Use Treemap to save the winner at each time of vote
return the winner corresponding to the floored key

Time: 
    Build map: O(nlogn)
    Operation q: O(1)
Space: O(n)
*/
class TopVotedCandidate {
    TreeMap<Integer, Integer> map;
    public TopVotedCandidate(int[] persons, int[] times) {
        map = new TreeMap<>();
        int n = persons.length;
        int maxVote = -1, winner = 0;
        int[] count = new int[n];
        for(int i = 0; i < n; i++){
            //vote for this person
            count[persons[i]]++;
            
            //persons[i] gets most votes, update most recent when there is a tie
            if(count[persons[i]] >= maxVote){
                winner = persons[i];
                maxVote = count[persons[i]];//!!! update maxVote
            } 
            
            map.put(times[i], winner);
        }
    }
    
    public int q(int t) {
        //Given that: TopVotedCandidate.q(int t) is always called with t >= times[0].
        return map.get(map.floorKey(t));
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
