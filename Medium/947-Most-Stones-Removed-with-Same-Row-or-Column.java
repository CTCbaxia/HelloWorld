/*
MEDIUM
947. Most Stones Removed with Same Row or Column

*/
/*
Graph + DFS
将相邻（在一条线上）的关系构建成 graph
对于 graph，找到所有的连通路径
numOfIsland = 连通路径的总和
每个连通岛都会剩下一个点无法被 remove

result = # all island - numOfIsland

Time: O(n^2) 加了 visited 之后，只会把 graph 遍历一遍
Space: O(n^2)
*/
class Solution {
    public int removeStones(int[][] stones) {    
        Set<Integer> visited = new HashSet<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        //build map
        for(int i = 0; i < stones.length; i++)
            map.put(i, new ArrayList<>());
            
        for(int i = 0; i < stones.length; i++){
            for(int j = i + 1; j < stones.length; j++){
                if(stones[j][0] == stones[i][0] || stones[j][1] == stones[i][1]){
                    map.get(i).add(j);
                    map.get(j).add(i);
                }
            }
        }
        //dfs to find 连通路径
        int numOfIsland = 0;
        for(int i = 0; i < stones.length; i++){
            if(!visited.contains(i)){
                numOfIsland++;
                dfs(map, stones, i, visited);
            }
        }
        return stones.length - numOfIsland;
    }
    private void dfs(Map<Integer, List<Integer>> map, int[][] stones, int p, Set<Integer> visited){
        if(visited.contains(p)) return;
        visited.add(p);
        
        List<Integer> neighbors = map.get(p);
        for(int neighbor : neighbors){
            dfs(map, stones, neighbor, visited);
        }
        return;
    }
}