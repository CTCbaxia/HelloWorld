/*
* 3. Count number of substrings with exactly k distinct characters
1. 所有 substring 都是正好 k 个元素
2. 所有 substring 长度要正好为 k
Time: O(n^2)
Space: O(n)
*/
class Solution3{
    public int numOfSubstringWithKDistinct(String s, int k) {
        int res = 0;
        Map<Character, Integer> map;
        for(int i = 0; i < s.length(); i++){
            map = new HashMap<>();

            for(int j = i; j < s.length(); j++){
                char c = s.charAt(j);
                map.put(c, map.getOrDefault(c, 0) + 1);

                if(map.size() == k) res++;
            }

        }
        return res;
    }
}
/*len k with k distinct
Time: O(n)
Space: O(n)
*/
class Solution3_1{
    public int numOfSubstringWithKDistinct(String s, int k) {
        if(s == null) return 0;
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0;
        for(; end < s.length(); end++){
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);

            if(end - start + 1 == k){//window reach size k
                if(map.size() == k) res++;

                //remove start
                char c2 = s.charAt(start++);
                map.put(c2, map.get(c2) - 1);
                if(map.get(c2) == 0) map.remove(c2);
            }

        }
        return res;
    }
}


/*
len k with k distinct: 如果需要 substring 去重
Time: O(n)
Space: O(n)
*/
class Solution3_1{
    public int numOfSubstringWithKDistinct(String s, int k) {
        if(s == null) return 0;
        Set<String> result = new HashSet<>();

        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0;
        for(; end < s.length(); end++){
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);

            if(end - start + 1 == k){//window reach size k
                if(map.size() == k){
                	result.add(s.substring(start, end + 1));
                }

                //remove start
                char c2 = s.charAt(start++);
                map.put(c2, map.get(c2) - 1);
                if(map.get(c2) == 0) map.remove(c2);
            }

        }
        return res.size();
    }
}


