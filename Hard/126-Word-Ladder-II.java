/*
H
126. Word Ladder II
*/
/*
BFS + DFS(backtracking)
BFS: Queue<String> build graph
DFS: find all paths

Time: 
    BFS: O(n) only try each string once, change every char is constant
    DFS: O(#result) only generate all results
Space: O(n)

**还可以 two end bfs：https://leetcode.com/problems/word-ladder-ii/discuss/40477/Super-fast-Java-solution-(two-end-BFS)
*/
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> graph = new HashMap<>();//<valid word, next neighbors to end>
        List<List<String>> result = new ArrayList<>();
        
        //put wordlist to set
        Map<String, Integer> degree = new HashMap<>();//help to avoid reuse a string again later
        for(String s : wordList) degree.put(s, wordList.size() + 1);
        if(!degree.containsKey(endWord)) return resilt;//impossible
        degree.put(beginWord, 0);
        
        bfs(graph, beginWord, endWord, degree);
        dfs(graph, beginWord, endWord, new ArrayList<String>(), result);
        
        return result;
    }
    private void bfs(Map<String, List<String>> graph, String beginWord, String endWord, Map<String, Integer> degree){
        int level = 0;
        boolean foundEnd = false;
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        while(!q.isEmpty() && !foundEnd){
            int size = q.size();
            for(int i = 0; i < size; i++){
                String s = q.poll();
                boolean found = false;//don't try more for this string
                graph.put(s, new ArrayList<>());
                for(int j = 0; j < s.length() && !found; j++){
                    for(char c = 'a'; c <= 'z' && !found; c++){
                        if(c == s.charAt(j)) continue;
                        String next = s.substring(0, j) + c + s.substring(j + 1);
                        if(next.equals(endWord)) found = true;
                        if(degree.containsKey(next) && degree.get(next) >= level){//not used in previous level
                            degree.put(next, level);
                            q.offer(next);
                            graph.get(s).add(next);//build next graph
                        } 
                    }
                }
                foundEnd = found;//this level is the last level
            }
            level++;
        }
    }
    private void dfs(Map<String, List<String>> graph, String word, String endWord, List<String> res, List<List<String>> result){
        res.add(word);
        if(word.equals(endWord)){
            result.add(new ArrayList<>(res));
        }else if(graph.containsKey(word)){
            List<String> nexts = graph.get(word);
            for(String next : nexts){
                dfs(graph, next, endWord, res, result);
            } 
        }
        res.remove(word);//backtracking
    }
    
}
