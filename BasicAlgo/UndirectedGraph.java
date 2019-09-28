/*
How to detect a cycle in u-graph (undirected graph)

1. Union Find

2. DFS (backtracking)
https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
try every path, maintain a boolean[] visited for that path, and record current node's parent
loop all its connected nodes but skip the parent, do backtracking for each neighbors
if visited[next] is true, there is a cycle (because next cannot be its parent)

*/
/*
Union Find + detect cycle

Time: O(n)//because we do compression, so we won't always go through the parent list
Space: O(n)
*/
class Solution {
    public boolean findCycle(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        //initial parent 
        for(int i = 1; i <= n; i++) parent[i] = i;
        for(int[] e : edges){
            int p1 = find(e[0], parent);
            int p2 = find(e[1], parent);
            if(p1 == p2) return true;
            else parent[p2] = p1;
        }
        return false;
    }
    private int find(int n, int[] parent){
        if(parent[n] == n) return n;
        parent[n] = find(parent[n], parent);//compress the parent relationship
        return parent[n];
    }
}


/*
DFS + detect cycle

Time: O(n)
Space: O(n)
*/
class Solution {
    public boolean findCycle(int[][] edges) {
        int n = edges.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        //build graph
        for(int i = 0; i < edges.length; i++) map.put(i + 1, new ArrayList<>());
        for(int[] e : edges){
            map.get(e[0]).add(e[1]);
            map.get(e[1]).add(e[0]);
        }
        List<Integer> path = new ArrayList<>();//just for record the path that contains cycle, useless in this prob
        backtracking(map, 0, 1, new boolean[n + 1], path);//get circle
        return true;
    }
    private boolean backtracking(Map<Integer, List<Integer>> map, int pre, int cur, boolean[] visited, List<Integer> path){
        path.add(cur);
        if(visited[cur]) return true;//find circle in this path
        
        visited[cur] = true;//mark
        List<Integer> nei = map.get(cur);
        for(int n : nei){
            if(n == pre) continue;//don't go back to its parent
            if(backtracking(map, cur, n, visited, path)) return true;
        }
        path.remove(path.size() - 1);
        visited[cur] = false;
        return false;
    }
}