/*
MEDIUM
684. Redundant Connection
https://leetcode.com/problems/redundant-connection/description/

TIME: 0901 - 2h
RESULT: 97% - 3ms
METHOD: 
	1. DFS 找环
	2. Union-Find
*/
/*
Union Find: 对每条边，更新连通图的情况

初始化：所有点对应的parent都为自己
对于每条edge上的两个点，检查他的parent（所属连通图），

如果不一样，合并他们
如果一样，因为连通图内部所有的点都可以到任意一个点，说明他们早就被连通了，增加的这个edge会造成成环

核心思想：每次都要让 edge 上的两条 node 的parent 合并成一个，以代表这整个连通图

Time: O(n)
Space: O(n)
*/

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        for(int i = 0; i <= n; i++) parent[i] = i;//初始化所有的 parent 都为自己
        for(int[] edge : edges){
            int p1 = find(edge[0], parent);
            int p2 = find(edge[1], parent);
            if(p2 != p1){//如果 parent 不一样，右边向左合并
                parent[p2] = p1;//union
            }else{//如果 parent 一样，找到冗余
                return edge;
            }
        }
        return null;
    }
    private int find(int p, int[] parent){
        if(parent[p] != p) return find(parent[p], parent);
        else return p;
    }
}

/*
[[1,2], [3,4], [4,5],[1,5],[5,6],[4,6]] ：每次union 的对象应为 parent，而不是当前的点
*/




/*
DFS: Build Graph and Find Cycle
there is only one cycle in this graph

先构建双向graph
再从任意一个点遍历（因为这肯定是一个连通图，任意一个点都能到达所有的点）
用 visit 来记录是否已经遍历完毕，是否正在遍历
用 path 来记录遍历过的所有点（按顺序）
用 pre， cur 来记录来时的上一个点，以防双向图直接遍历回去并认为找到了环

思路就是 DFS 遍历图，直到找到了 circle。
然后根据 circle 里面所有的点，从后往前遍历 edges，找到第一个两个端点都在 circle 里面的边

1. 为什么要构建双向 graph？
因为如果只构建单项，无法保证能够在每一点都能走完所有路径(题目说是无向图)


Time: O(V + E) = O(N)
Space: O(n)

Time 分析：
对于图来说，worst case 就是所有点之间都有edge，这里 E = V^2 = N^2
对于 N-ary Tree 来说，只会有 N - 1 个 Edge
这个题有 N 个 Edge

Result: 
Time: 11ms
*/

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        //build graph
        for(int i = 0; i < edges.length; i++) map.put(i + 1, new ArrayList<Integer>());
        for(int[] edge : edges){
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        
        //DFS to find cycle
        int[] visit = new int[edges.length + 1];
        List<Integer> path = new ArrayList<>();
        if(findCycle(0, 1, visit, path, map)){
            //path : 5 - 1 - 2 - 3 - 4 - 1, need remove last [] in 2D array, should be 1 - 4
            //get circle list
            int dul = path.get(path.size() - 1);
            int first = path.indexOf(dul);
            List<Integer> circle = path.subList(first, path.size() - 1);
            
            for(int i = edges.length - 1; i >= 0; i--){
                if(circle.contains(edges[i][0]) && circle.contains(edges[i][1])){
                    return edges[i];
                }
            }
        }
        return null;
        
    }
    private boolean findCycle(int pre, int cur, int[] visit, List<Integer> path, Map<Integer, List<Integer>> map){
        path.add(cur);
        
        if(visit[cur] == 1) return true;//if in the path, we find a point again
        visit[cur] = 1;//visiting
        
        List<Integer> list = map.get(cur);
        for(int i = 0; i < list.size(); i++){
            int next = list.get(i);
            if(visit[next] == -1 || next == pre) continue;//don't go back
            if(findCycle(cur, next, visit, path, map)) return true;
        }
        
        visit[cur] = -1;//visited
        path.remove(path.size() - 1);//remove p (backtracking)
        return false;
    }
    
}




/*
DFS: 在构建 graph 的过程中检查是否已经有从 u 到 v 的路径

Time: O(n^2) = 1 + 2 + 3 +... + n : 对于每一个 node 都要DFS
Space: O(n)

Result:
Time: 30ms
*/

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        //build graph
        for(int i = 0; i < edges.length; i++) map.put(i + 1, new ArrayList<>());
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            //if there is already a rount from start to target
            Set<Integer> visited = new HashSet<>();
            if(findPath(u, v, map, visited)){
                return edge;
            }
            
            map.get(u).add(v);
            map.get(v).add(u);
        }
        return null;
        
    }
    private boolean findPath(int p, int target, Map<Integer, List<Integer>> map, Set<Integer> visited){
        if(p == target) return true;
        visited.add(p);
        
        List<Integer> list = map.get(p);
        for(int i : list){
            if(visited.contains(i)) continue;
            if(findPath(i, target, map, visited)) return true;
        }
        return false;
    }
    
}














/*
SOLUTION REFERENCE: Union-Find Algorithm (can be used to check whether an undirected graph contains cycle or not)
TIME: 0901 - 2h
RESULT: 97% - 3ms
NOTES:
1. Union-Find 的讲解不错： https://zxi.mytechroad.com/blog/tree/leetcode-684-redundant-connection/
2. Union-Find 的思路就是不断的合并两个 Disjoint Sets（把他俩中间的线连起来），直到发现有两个所谓 “Disjoint Sets” 其实都已经在一个 set 里面了，这个时候把他俩之间的线连起来就会构成环
因为有且仅有一个环，所以最后封上的那条线就是要 return 的值
*/

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[edges.length + 1];
        for(int i = 0; i < parent.length; i++) parent[i] = i;
        for(int i = 0; i < edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            if(find(parent, from) == find(parent, to)) return edges[i];//一直 union 直到找到环的时候，这个点就是环最后的一条边
            else parent[find(parent, from)] = parent[to];
        }
        return null;
    }
    //DFS 找到 from 点的最终 parent
    public int find(int[] parent, int point){
        if(parent[point] == point) return point;
        return find(parent, parent[point]); //再简化一点可以每次都更新一下 set 内部其他元素的 parent[]，使图尽量扁平
    }
}

/*
SOLUTION 0: 
TIME: 0901 - 1.5h
RESULT: 18% - 13ms
THOUGHTS:
去环 - DFS 遍历，从任意一个点开始 DFS + backtrack，一定能走到环里。保留环。
然后从 edges 的尾部开始遍历，如果一个 edges[i] 的两个点都在环里，则返回这个 edges[i]
*/

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        List<Set<Integer>> graph = new ArrayList<Set<Integer>>();
        List<Integer> preRoute = new ArrayList<Integer>();
        for(int i = 0; i <= edges.length; i++) graph.add(new HashSet<Integer>());
        for(int i = 0; i < edges.length; i++){
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
        if(findCircle(graph, 1, preRoute)){
            
            int duplicate = preRoute.get(preRoute.size() - 1);
            int first = preRoute.indexOf(duplicate);

            List<Integer> circle = preRoute.subList(first, preRoute.size() - 1);
            for(int i = edges.length - 1; i >= 0; i--){
                if(circle.contains(edges[i][0]) && circle.contains(edges[i][1])) return edges[i];
            }
        }
        return null;
    }
    //DFS 每到一个点，就删除该点 set 对应的 pre，如果该点没有后续连接，则返回。 如果该点的后续连接在 prelist 里面，则成环
    private boolean findCircle(List<Set<Integer>> graph, int start, List<Integer> preRoute){
        preRoute.add(start);
        Set<Integer> set = graph.get(start);
        Iterator<Integer> it = set.iterator();
        while(it.hasNext()){
            int next = it.next();
            if(preRoute.contains(next)){
                preRoute.add(next);
                return true;
            }
            graph.get(next).remove(start);
            if(findCircle(graph, next, preRoute)) return true;//if find the circle
            preRoute.remove(preRoute.size() - 1);
        }
        return false;
    }
}