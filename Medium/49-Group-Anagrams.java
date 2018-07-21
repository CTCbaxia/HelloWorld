/*
MEDIUM
49. Group Anagrams
https://leetcode.com/problems/group-anagrams/description/

TIME: 0721 - 1h
RESULT: 95% - 15ms
NOTES:
1. String s = String.valueOf(ch); 直接把 char[] 转成字符串 
2. Map<String,List<String>>
3. 直接添加一个新建的内容： 比如 result.add(new ArrayList<String>()); 还比如： map.put(s, new ArrayList<String>());
4. 直接返回一个新建的内容： return new ArrayList<List<String>>(map.values());
5. new 对象的时候可以直接带入 collection
*/

/*
SOLUTION REFERENCE:
注意简洁的写法也语法
*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<String, List<String>>();
        
        for(int i = 0; i < strs.length; i++){
            char[] ch = strs[i].toCharArray();
            Arrays.sort(ch);
            String s = String.valueOf(ch);
            if(!map.containsKey(s)) map.put(s, new ArrayList<String>());
            map.get(s).add(strs[i]);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
/*
SOLUTION 0:
思路：
1. anagram 的一组内容肯定是当做一个 key，所以用 map 最好。
2. 每个 anagram 要分组，所以 map 的 value 最好能存多个内容
3. output 是 List<List<String>>，所以 <List<String> 很合适
4. 注意这里用 String 来存放每个 anagram
*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        Map<String,List<String>> map = new HashMap<String, List<String>>();
        
        for(int i = 0; i < strs.length; i++){
            char[] ch = strs[i].toCharArray();
            Arrays.sort(ch);
            String s = String.valueOf(ch);
            if(map.containsKey(s)) map.get(s).add(strs[i]);
            else{
                result.add(new ArrayList<String>());
                result.get(result.size() - 1).add(strs[i]);
                map.put(s,result.get(result.size() - 1));
            } 
        }
        return result;
    }
}

