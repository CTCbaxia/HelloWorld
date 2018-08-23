/*
MEDIUM
433. Minimum Genetic Mutation
https://leetcode.com/problems/minimum-genetic-mutation/description/

TIME: 0822 - 1h
RESULT: 100% - 1ms


和之前的一个字典匹配题很像
139 - word break (TLE)
127 - word ladder (TLE)


THOUGHTS:
1. DFS
for(words : group1)
    for(words: group)
        for...
    visited.add(key word in group1)


NOTES:
1. 要找最小路径， 所以应该所有都要遍历一遍
*/

/*
SOLUTION 0:
TIME: 0822 - 1h
RESULT: 100% - 1ms
*/
class Solution {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> visited = new HashSet<String>();
        int result = dfs(bank.length + 1, 0, start, end, visited, bank);
        if(result == bank.length + 1) return -1;
        else return result;
    }
    private int dfs(int min, int num, String start, String end, Set<String> visited, String[] bank){
        if(start.equals(end)) return num;//a depth result
        
        visited.add(start);
        for(int i = 0; i < bank.length; i++){
            String s = bank[i];
            if(visited.contains(s)) continue;
            if(isValid(start, s)){
                int tmp = -1;
                tmp = dfs(min, num + 1, s, end, visited, bank);
                if(tmp != -1) min = Math.min(min, tmp);
            } 
        }
        visited.remove(start);
        return min;//the final result
    }
    private boolean isValid(String start, String match){
        int count = 0;
        for(int i = 0; i < start.length(); i++){
            if(start.charAt(i) != match.charAt(i)) count++;
            if(count > 1) return false;
        }
        return true;
    }
        
}