/*
MEDIUM
1057. Campus Bikes

*/
/*
TreeMap + PriorityQueue
就是需要 Distance ASC, WorkerIndex ASC, Bike ASC

Time: O(mn*logmn)
Space: O(mn)
*/
class Solution {
    public class Pair{
        int worker;
        int bike;
        public Pair(int _worker, int _bike){
            worker = _worker;
            bike = _bike;
        }
    }
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        Map<Integer, PriorityQueue<Pair>> map = new TreeMap<>();
        for(int i = 0; i < workers.length; i++){
            for(int j = 0; j < bikes.length; j++){
                int dis = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                if(!map.containsKey(dis)){
                    map.put(dis, new PriorityQueue<Pair>(new Comparator<Pair>(){
                        public int compare(Pair p1, Pair p2){
                            if(p1.worker != p2.worker) return p1.worker - p2.worker;
                            else return p1.bike - p2.bike;
                        }
                    }));
                }
                map.get(dis).offer(new Pair(i,j));
            }
        }
        int[] res = new int[workers.length];
        Set<Integer> usedBikes = new HashSet<>();
        Arrays.fill(res, -1);
        for(Map.Entry<Integer, PriorityQueue<Pair>> entry : map.entrySet()){
            PriorityQueue<Pair> pq = entry.getValue();
            while(!pq.isEmpty()){
                Pair pair = pq.poll();
                if(res[pair.worker] == -1 && !usedBikes.contains(pair.bike)){
                    res[pair.worker] = pair.bike;
                    usedBikes.add(pair.bike);
                } 
            }
        }
        return res;
        
    }
}

/*
Fully PriorityQueue (三层对比)

Time: O(mn*logmn)
Space: O(mn)
*/
class Solution {
    public class DisPair{
        int dis;
        int worker;
        int bike;
        public DisPair(int _dis, int _worker, int _bike){
            dis = _dis;
            worker = _worker;
            bike = _bike;
        }
    }
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        // order by Distance ASC, WorkerIndex ASC, BikeIndex ASC
        PriorityQueue<DisPair> pq = new PriorityQueue<>(new Comparator<DisPair>(){
            public int compare(DisPair dp1, DisPair dp2){
                if(dp1.dis != dp2.dis) return dp1.dis - dp2.dis;
                else if(dp1.worker != dp2.worker) return dp1.worker - dp2.worker;
                else return dp1.bike - dp2.bike;
            }
        });
        
        for(int i = 0; i < workers.length; i++){
            for(int j = 0; j < bikes.length; j++){
               int dis = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
               pq.offer(new DisPair(dis, i,j));
            }
        }
        int[] res = new int[workers.length];
        Set<Integer> usedBikes = new HashSet<>();
        Arrays.fill(res, -1);
        while(!pq.isEmpty()){
            DisPair dp = pq.poll();
            if(res[dp.worker] == -1 && !usedBikes.contains(dp.bike)){
                    res[dp.worker] = dp.bike;
                    usedBikes.add(dp.bike);
            } 
        }
        return res;
        
    }
}

/*
Buket Sort
*/