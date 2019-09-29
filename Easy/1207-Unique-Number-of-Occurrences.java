/*
E
1207. Unique Number of Occurrences
*/
/*
Map + Set
1. sort + count check with set
2. map count + set check

Time: O(n)
Space: O(n)
*/
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int n : arr){
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        Set<Integer> set = new HashSet<>();
        for(Map.Entry<Integer, Integer> entry : freq.entrySet()){
            if(set.contains(entry.getValue())) return false;
            set.add(entry.getValue());
        }
        return true;
    }
}