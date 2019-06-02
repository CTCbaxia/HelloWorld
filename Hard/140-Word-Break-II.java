/*
HARD
140. Word Break II

*/
/*
DFS
with memory


Time: O()
Space: O()
*/
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, 0, wordDict);
    }
    Map<Integer, List<String>> map = new HashMap<>();
    private List<String> dfs(String s, int start, List<String> wordDict){
        if(map.containsKey(start)) return map.get(start);
        
        List<String> res = new ArrayList<>();
        if(start == s.length()){
            res.add("");
        } 
        
        for(int i = start + 1; i <= s.length(); i++){
            if(wordDict.contains(s.substring(start, i))){
                List<String> then = dfs(s, i, wordDict);
                for(String str : then){
                    res.add(s.substring(start, i) + (str.equals("")? "":" ") + str);
                }
            }
        }
        map.put(start, res);
        return res;
    }
}






/*
DFS

Time: O(n^n)
Space: O(n)
*/
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        dfs(s, 0, wordDict, "", result);
        return result;
    }
    private void dfs(String s, int start, List<String> wordDict, String pre, List<String> result){
        if(start == s.length()){
            result.add(pre.substring(1));
            return;
        } 
        
        for(int i = start; i <= s.length(); i++){
            if(wordDict.contains(s.substring(start, i))){
                dfs(s, i, wordDict, pre + " " + s.substring(start, i), result);
            }
        }
    }
}