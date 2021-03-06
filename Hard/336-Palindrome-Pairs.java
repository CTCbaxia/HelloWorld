/*
HARD
336. Palindrome Pairs

TIME: 
RESULT: 
NOTES: 
*/
/*
Split the word and Check reverse word
对于 string 的每一个 i 做分割

Time: O(n*k^2) k is the length of the word
Space: O(n)

还有个 trie 的解法，但这个更 intuitive
*/
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        
        //build map
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i++) map.put(words[i], i);
        
        //search words
        for(int i = 0; i < words.length; i++){
            for(int j = 0; j <= words[i].length(); j++){//终止为 <=
                String s1 = words[i].substring(0, j);
                String s2 = words[i].substring(j);
                
                if(isPal(s1)){//如果 s1 自成回文，只需要找 s2 的 reverse 在不在 map
                    String reverse = new StringBuilder(s2).reverse().toString();
                    if(map.containsKey(reverse) && map.get(reverse) != i){//不能是自己
                        res.add(Arrays.asList(map.get(reverse), i));//顺序重要
                    }
                }
                if(s2.length() != 0 && isPal(s2)){//需要控制 s2 不再出现 空字符串，否则和 s1 空的时候一样
                    String reverse = new StringBuilder(s1).reverse().toString();
                    if(map.containsKey(reverse) && map.get(reverse) != i){//不能是自己
                        res.add(Arrays.asList(i, map.get(reverse)));
                    }
                }
            }
        }
        return res;
        
    }
    private boolean isPal(String s){
        int lo = 0, hi = s.length() - 1;
        while(lo < hi){
            if(s.charAt(lo++) != s.charAt(hi--)) return false;
        }
        return true;
    }
}




/*
Map + 对每个单词都分段匹配（看自身是否有回文，剩余部分的reverse是否就是其他单词）
1. 一长一短，短的部分和长的其中一部分回文，长的剩下一部分自成回文
2. 同样长，互相成回文

Time: O(n * len of words)
Space: O(n)
*/
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            map.put(words[i], i);
        }
        for(int i = 0; i < words.length; i++){
            for(int j = 0; j <= words[i].length(); j++){
                String p1 = words[i].substring(0, j);
                String p2 = words[i].substring(j);
                
                if(isPal(p1)){//p1 自成回文，看 p2 的 reverse 能不能在 map 里面匹配
                    String reverse = new StringBuilder(p2).reverse().toString();
                    if(map.containsKey(reverse) && map.get(reverse) != i){
                        List<Integer> res = new ArrayList<>();
                        res.add(map.get(reverse));
                        res.add(i);
                        result.add(res);    
                    }
                }
                if(p2.length() != 0 && isPal(p2)){//需要控制这里不再出现 空字符串，不然 abcd dcba 会匹配两次，结果多余
                    String reverse = new StringBuilder(p1).reverse().toString();
                    if(map.containsKey(reverse) && map.get(reverse) != i){
                        List<Integer> res = new ArrayList<>();
                        res.add(i);
                        res.add(map.get(reverse));
                        result.add(res);             
                    }
                }                
            }
        }
        return result;
    }
    private boolean isPal(String s){
        int lo = 0, hi = s.length() - 1;
        while(lo < hi){
            if(s.charAt(lo++) != s.charAt(hi--)) return false;
        }
        return true;
    }
}





/*
Concat String and Check

Time: O(n^2)
Space: O(1)
*/
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < words.length; i++){
            for(int j = i + 1; j < words.length; j++){
                String com1 = words[i].concat(words[j]);
                String com2 = words[j].concat(words[i]);
                if(isPal(com1)){
                    List<Integer> res = new ArrayList<>();
                    res.add(i);
                    res.add(j);
                    result.add(res);
                }
                if(isPal(com2)){
                    List<Integer> res = new ArrayList<>();
                    res.add(j);
                    res.add(i);
                    result.add(res);
                }                
            }
        }
        return result;
    }
    private boolean isPal(String s){
        int lo = 0, hi = s.length() - 1;
        while(lo < hi){
            if(s.charAt(lo++) != s.charAt(hi--)) return false;
        }
        return true;
    }
}