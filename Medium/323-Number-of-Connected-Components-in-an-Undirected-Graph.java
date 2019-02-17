/*
323. Number of Connected Components in an Undirected Graph
*/
/*
Build Graph + DFS

Time: O(V + E)
Space: O(V + E)
*/
class Solution {
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> list = new ArrayList<>();
        //build graph
        for(int i = 0; i < n; i++) list.add(new ArrayList<>());
        for(int[] e : edges){
            list.get(e[0]).add(e[1]);
            list.get(e[1]).add(e[0]);
        }
        int count = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                count++;
                dfs(list, i, visited);
            }
        }
        return count;
        
    }
    private void dfs(List<List<Integer>> list, int node, boolean[] visited){
        visited[node] = true;
        
        List<Integer> neighbors = list.get(node);
        for(int i : neighbors){
            if(visited[i]) continue;
            dfs(list, i, visited);
        }
        return;
    }
}



/*
Build Graph + BFS

One suggestion to your code (BFS): mark the node as visited when you add it to the queue, not when it's popped. Otherwise you might add nodes multiple times to the queue. The result will still be OK but it's unnecessary computation.

Time: O(V + E)
Space: O(V + E)
*/
class Solution {
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> list = new ArrayList<>();
        //build graph
        for(int i = 0; i < n; i++) list.add(new ArrayList<>());
        for(int[] e : edges){
            list.get(e[0]).add(e[1]);
            list.get(e[1]).add(e[0]);
        }
        int count = 0;
        boolean[] visited = new boolean[n];
        
        for(int i = 0; i < n; i++){
            if(visited[i]) continue;
            count++;
            
            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            visited[i] = true;
            
            while(!q.isEmpty()){
                int node = q.poll();
                for(int neighbors : list.get(node)){
                    if(!visited[neighbors]){
                        q.offer(neighbors);
                        visited[neighbors] = true;
                    } 
                }
            }
        }
        return count;
        
    }
    
}



/*
Build Graph + Union Find 好一些
Time: O(V + E)
Space: O(V + E)
*/
class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i;
        for(int[] e : edges){
            int p1 = find(parent, e[0]);
            int p2 = find(parent, e[1]);
            
            if(p1 != p2){//union
                parent[p1] = p2;
                n--;//合并了一个 component
            }
        }
        
        return n;
    }
    private int find(int[] parent, int i){//其实这里不需要
        if(parent[i] == i) return i;
        else return find(parent, parent[i]);
    }
}





/*
Build Graph + Union Find 一般
Time: O(V + E)
Space: O(V + E)
*/
class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i;
        for(int[] e : edges){
            int p1 = parent[e[0]];//find
            int p2 = parent[e[1]];
            
            if(p1 == p2) continue;
            
            for(int i = 0; i < n; i++){//每次都把 parent 扁平化
                if(parent[i] == p2) parent[i] = p1;
            }
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++){
            set.add(parent[i]);
        }
        return set.size();
    }
    
    
}
