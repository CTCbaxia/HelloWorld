/*
M
1048. Longest String Chain
*/
/*
Bucket sort the strings, dp the result level by level and update result on the way
        Arrays.sort(words, new Comparator<String>(){
            public int compare(String s1, String s2){
                return s1.length() - s2.length();
            }
        });
Time: O(n^2)
Space: O(n)
*/
class Solution {
    public int longestStrChain(String[] words) {
        if(words.length == 0) return 0;//if there is no words
        // bucket sort the strings according to its len
        int minLen = Integer.MAX_VALUE;
        Map<Integer, List<String>> lenMap = new TreeMap<>();
        for(String s : words){
            int len = s.length();
            if(!lenMap.containsKey(len)){
                lenMap.put(len, new ArrayList<>());
                minLen = Math.min(minLen, len);//record the min length
            }
            lenMap.get(len).add(s);
        }
        // loop the treeMap
        // initialization should be 1, otherwise if there is no update inside, 
        // res should be at least right
        int res = 1;
        Map<String, Integer> map = new HashMap<>();
        for(Map.Entry<Integer, List<String>> entry : lenMap.entrySet()){
            int len = entry.getKey();
            List<String> list = entry.getValue();
            if(!lenMap.containsKey(len - 1)){// if no previous list of string, set 1 by default
                for(String s : list){
                    map.put(s, 1);
                }                
            }else{
                List<String> preList = lenMap.get(len - 1);
                for(String next : list){
                    int longest = 0;
                    for(String pre : preList){
                        if(isPredecessor(next, pre)){
                            longest = Math.max(longest, map.get(pre));// longest pre length
                        }else{
                            
                        }
                    }
                    map.put(next, longest + 1);
                    res = Math.max(res, longest + 1);
                }
            }

        }
        return res;
        
    }
    private boolean isPredecessor(String next, String pre){// check whether pre and next is predecessor
        int i = 0, j = 0, count = 0;
        while(i < next.length() && j < pre.length()){
            if(next.charAt(i) != pre.charAt(j)){
                count++;
                j--;// stay j for next comparison
            }
            if(count > 1) return false;
            i++;
            j++;
        }
        return true;
    }
}