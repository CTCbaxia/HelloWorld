/*
Bucket Sort | also PQ can work

Time: O(n)
Space: O(n)
*/
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // buckets
        List<Character>[] bucket = new ArrayList[s.length() + 1];
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            int freq = entry.getValue();
            if(bucket[freq] == null) bucket[freq] = new ArrayList<>();
            bucket[freq].add(entry.getKey());
        }
        StringBuilder sb = new StringBuilder();
        for(int i = bucket.length - 1; i >= 0; i--){
            if(bucket[i] != null){
                for(char c : bucket[i]){
                    for(int k = 0; k < i; k++)
                        sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}