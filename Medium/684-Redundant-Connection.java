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