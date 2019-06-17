/*
MEDIUM
1090. Largest Values From Labels
*/
/*
Map + PriorityQueue
1.对于每个 label 存最大的两个数的 map
2.把所有 map label 遍历一遍，取最大 num_wanted 的数

Time: n + nlogn
*/
class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        int n = values.length;
        int[][] pairs = new int[n][2];
        Map<Integer, Integer> map = new HashMap<>();//<label, count of used>
        //make pairs and initialize map
        for(int i = 0; i < n; i++){
            pairs[i][0] = values[i];
            pairs[i][1] = labels[i];
            if(!map.containsKey(labels[i])) map.put(labels[i], 0);
        }
        //希望第一个 poll 出来的数是最大的
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2){
                return i2[0] - i1[0];
            }
        });
        //put all numbers into pq
        for(int i = 0; i < n; i++){
            pq.offer(pairs[i]);
        }
        //compute res
        int res = 0;
        for(int i = 0; i < n && num_wanted > 0; i++){
            int[] cur = pq.poll();
            if(map.get(cur[1]) < use_limit){
                res += cur[0];
                num_wanted--;
                map.put(cur[1], map.get(cur[1]) + 1);
            } 
        }
        return res;
    }
}
/*
Map + PriorityQueue
1.对于每个 label 存最大的两个数的 map
2.把所有 map label 遍历一遍，取最大 num_wanted 的数

Time: O(nlog(use_limit) + #labels * #use_limit * log(num_wanted)) --> worst case >  nlogn
*/
class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        int n = values.length;
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            if(!map.containsKey(labels[i])) map.put(labels[i], new PriorityQueue<Integer>());
            
            PriorityQueue<Integer> pq = map.get(labels[i]);
            pq.offer(values[i]);
            if(pq.size() > use_limit) pq.poll();
        }
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        
        for(Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()){
            PriorityQueue<Integer> labelPQ = entry.getValue();
            while(labelPQ.size() > 0){
                pq.offer(labelPQ.poll());
                if(pq.size() > num_wanted) pq.poll();
            }
        }
        int res = 0;
        while(pq.size() > 0){
            res += pq.poll();
        }
        return res;
    }
}