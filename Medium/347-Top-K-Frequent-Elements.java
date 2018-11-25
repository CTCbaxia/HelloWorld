/*
MEDIUM
347. Top K Frequent Elements

TIME: 
RESULT: 
NOTES:
只要是 frequency 这种数额少的，都考了 bucket
*/
/*
if top k with most frequency
Clarify: what if two element have same frequency?


Map + PriorityQueue

First build the map, then put into pq


Time: O(nlogk)
Space: O(#key)
*/
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(nums.length, new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2){
                return i1[1] - i2[1];//min heap, freq
            }
        });
        
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        for(int n : map.keySet()){
            pq.offer(new int[]{n, map.get(n)});
            if(pq.size() > k) pq.poll();
        }
        List<Integer> result = new ArrayList<>();
        while(!pq.isEmpty()) result.add(pq.poll()[0]);
        
        return result;
        
    }
}


/*
if top k most frequency, 以 freq 值排序，结果可能多于 k 个，有并列的
Map + Bucket Sort for the frequency

Use map for <n, freq>
Use List[] for bucket of frequency

Time: O(n)
Space: O(#key)

*/
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer>[] freq = new List[nums.length + 1];//freq[i] all nums with i frequence
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        for(int n : map.keySet()){
            int f = map.get(n);
            if(freq[f] == null) freq[f] = new ArrayList<Integer>();
            freq[f].add(n);
        }
        List<Integer> result = new ArrayList<>();
        int n = 0;
        int i = freq.length - 1;
        while(n < k){
            if(freq[i] != null){
                result.addAll(freq[i]);
                n++;
            }
            i--;
        }
        
        return result;
        
    }
}
















/*
不能有并列的
Map + Bucket Sort for the frequency

Use map for <n, freq>
Use List[] for bucket of frequency

Time: O(n)
Space: O(#key)

*/
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();//<n, freq>
        List<Integer>[] freq = new List[nums.length + 1];//the freq must be 1 - nums.length;
        List<Integer> result = new ArrayList<Integer>();
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        for(int n : map.keySet()){
            int f = map.get(n);
            if(freq[f] == null) freq[f] = new ArrayList<Integer>();
            freq[f].add(n);
        }
        int i = freq.length - 1;
        while(result.size() < k){
            if(freq[i] != null){
                result.addAll(freq[i]);
            }
            i--;
        }
        return result;
        
    }
}

