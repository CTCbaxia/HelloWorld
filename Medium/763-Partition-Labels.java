/*
763. Partition Labels
*/
/*
Two Pointers:
Find minimum substrings with letters in it and only in it

get end index of every letter
use start and end to mark pending substring
i to loop, 
1) once i == end, find a substring, update result, update start
2) i != end, update max end for this pending substring

Time: O(n)
Space: O(1)
*/
class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] ends = new int[26];
        //get end index for each letter
        for(int i = 0; i < S.length(); i++){
            ends[S.charAt(i) - 'a'] = i;
        }
        List<Integer> result = new ArrayList<>();
        int start = 0, end = ends[S.charAt(0) - 'a'];
        for(int i = 0; i < S.length(); i++){
            end = Math.max(end, ends[S.charAt(i) - 'a']);
            if(i == end){//need to compare after update end
                result.add(end - start + 1);
                start = end + 1;
            }
        }
        return result;
    }
}



/*
Two Pointers:
minimum substring with letters in it only in it

map for start and end of the letter
find all end index in this substring

Time: O(n)
Space: O(1)
*/
class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        if(S.length() == 0) return result;
        
        Map<Character, Integer> map = new HashMap<>();
        //get the end index of each letter
        for(int i = 0; i < S.length(); i++){
            map.put(S.charAt(i), i);
        }
        
        int start = 0, end = map.get(S.charAt(start));
        int i = 0;
        while(end < S.length()){
            if(i == end){
                result.add(end - start + 1);
                start = end + 1;
                i = start;
                if(start == S.length()) break;
                end = map.get(S.charAt(start));
            }else{
                i++;
                end = Math.max(end, map.get(S.charAt(i)));
            }
        }
        return result;
    }
}