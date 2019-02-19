/*
787. Cheapest Flights Within K Stops
*/
/*
BFS + PriorityQueue => Shortest Cost Path (Dijkstra's algorithm)

对每一个 city 的所有到达路径做 price 的 pq
Time: O(V + NlogN)?
Space: O(N)

*/
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        //build flight map
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            map.put(i, new HashMap<>());
        }
        for(int[] f : flights){
            map.get(f[0]).put(f[1], f[2]);
        }
        
        //{pos, k, price} find lowest price and go to next stop
        //每一个 city ，该路径对应的话费，对应的 k
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2){
                return i1[2] - i2[2];
            }
        });
        
        pq.offer(new int[]{src, K + 1, 0});
        while(!pq.isEmpty()){
            int[] p = pq.poll();
            int pos = p[0];
            int k = p[1];
            int price = p[2];
            if(k < 0) continue;//超过 K
            if(pos == dst) return price;//we are at dest at lowest price
            
            Map<Integer, Integer> next = map.get(pos);
            for(int to : next.keySet()){
                pq.offer(new int[]{to, k - 1, price + next.get(to)});
            }
        }
        return -1;
        
    }
}