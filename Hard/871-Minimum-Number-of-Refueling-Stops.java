/*
H
871. Minimum Number of Refueling Stops
*/
/*
PriorityQueue
When we need to add fuel, we want to add the largest refuel, 
but we don't know whether this current refuel is the largest one
and we don't know if we need to refuel

--So we can save the fuel and add the previous largest fuel when needed


Time: O(nlogn)
Space: O(n)
*/
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        // pq descending order
        // (a, b) -> (b - a)
        // Comparator.reverseOrder()
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));//desc 
        int curFuel = startFuel;
        int preStop = 0;
        int count = 0;
        for(int i = 0; i < stations.length; i++){//arrive at every station
            curFuel -= stations[i][0] - preStop;
            while(curFuel < 0 && !pq.isEmpty()){//add fuel until we can arrive the cur station
                curFuel += pq.poll();
                count++;
            }
            pq.offer(stations[i][1]);//add current fuel after pq as this cannot be add before arrive here
            if(curFuel < 0) return -1;//impossible to arrive at this station
            preStop = stations[i][0];
        }
        // need to arrive at the target
        while(curFuel < target - preStop && !pq.isEmpty()){
            curFuel += pq.poll();
            count++;
        }
        return curFuel >= target - preStop ? count : -1;
    }
}
