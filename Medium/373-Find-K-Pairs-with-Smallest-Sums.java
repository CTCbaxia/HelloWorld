/*
373. Find K Pairs with Smallest Sums

*/
/*
PriorityQueue:
1. Top K 直接考虑 PQ， 重点是 Time Complexity 怎么优化
2. What if there is a tie

Time: O((k)log(k))
Space: O(k)
*/

class Solution {
    public class Pair{
        int i;
        int j;
        int sum;
        public Pair(int _i, int _j, int _sum){
            i = _i;
            j = _j;
            sum = _sum;
        }
    }
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>(){
            public int compare(Pair p1, Pair p2){
                return p1.sum - p2.sum;
            }
        });
        if(nums1.length == 0 || nums2.length == 0) return result;
        for(int i = 0; i < nums1.length && i < k; i++){
            pq.offer(new Pair(i, 0, nums1[i] + nums2[0]));
        }
        while(result.size() < k && pq.size() > 0){
            Pair p = pq.poll();
            int[] sPair = new int[]{nums1[p.i], nums2[p.j]};
            result.add(sPair);
            
            p.j++;
            if(p.j == nums2.length) continue;
            
            pq.offer(new Pair(p.i, p.j, nums1[p.i] + nums2[p.j]));
        }
        return result;
        
    }
}