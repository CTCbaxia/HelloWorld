/*
M
802. Find Eventual Safe States
*/
/*
Topological Sort
visited
circle

Time: O(V + E)
Space: O(n)
*/
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] degree = new int[n];
        Map<Integer, List<Integer>> reverse = new HashMap<>();
        for(int i = 0; i < n; i++) reverse.put(i, new ArrayList<>());
        for(int i = 0; i < n; i++){
            int[] to = graph[i];
            for(int j : to){
                reverse.get(j).add(i);
                degree[i]++;       
            }
            
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < degree.length; i++){
            if(degree[i] == 0) q.offer(i);
        }
        List<Integer> res = new ArrayList<>();
        while(!q.isEmpty()){
            int p = q.poll();
            res.add(p);
            List<Integer> froms = reverse.get(p);
            for(int f : froms){
                degree[f]--;
                if(degree[f] == 0) q.offer(f);
            }
        }
        Collections.sort(res);
        return res;
    }

}


/*
不知道为什么错了
visited
circle
*/
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> circle = new HashSet<>();
        int n = graph.length;
        for(int i = 0; i < n; i++){
            if(visited.contains(i)) continue;
            dfs(graph, i, new HashSet<>(), circle, visited);
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(!circle.contains(i)) res.add(i);
        }
        return res;
    }
    private void dfs(int[][] graph, int i, Set<Integer> path, Set<Integer> circle, Set<Integer> visited){
        visited.add(i);
        int[] nexts = graph[i];
        for(int next : nexts){
            if(path.contains(next)) circle.addAll(path);
            else if(visited.contains(next)) continue;
            else{
                path.add(next);
                dfs(graph, next, path, circle, visited);
                path.remove(path.size() - 1);
            }
            
        }
        return;
    }
}
