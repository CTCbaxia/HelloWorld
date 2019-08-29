/*
MEDIUM
785-Is-Graph-Bipartite

TIME: 0821 - 2.5h
RESULT: 7.5% - 16ms
NOTES: 
1. 题目没有说图是连通图，所以在主 numFriendRequests 里面要遍历所有的 node，而不是直接 return 以 0 为起点的递归
*/
/*
DFS + color array (visited and color)

Time: O(V + E)
Space: O(V)
*/
class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];//can also mark visited, 1 = color one, -1 = color two
        for(int i = 0; i < graph.length; i++){
            if(color[i] == 0){
                if(!dfs(graph, color, i, 1)) return false;
            }
        }
        return true;
    }
    private boolean dfs(int[][] graph, int[] color, int index, int c){
        color[index] = c;
        for(int next : graph[index]){
            if(color[next] == c) return false;
            if(color[next] == -c) continue;
            if(!dfs(graph, color, next, -c)) return false;
        }
        return true;
    }
}





/*
DFS + color 分类问题
assign color and check the link(path in dfs)
- 如果上过色：看颜色是否正确
- 如果没上色：上对应的颜色，然后接着遍历它的相邻节点

Time: O(n^2) all edges
Space: O(n) for color
*/
class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        for(int i = 0; i < graph.length; i++){//might be not connected graph
            if(color[i] == 0){
                color[i] = 1;
                if(!part(graph, i, color, -1)) return false;
            }
        }
        return true;
    }
    private boolean part(int[][] graph, int node, int[] color, int c){
        //all node's friend should be put into set
        for(int i = 0; i < graph[node].length; i++){
            int n = graph[node][i];//n is node's friend(edge)
            if(color[n] == c) continue;
            if(color[n] == -c) return false;
            color[n] = c;
            if(!part(graph, n, color, -c)) return false;//once we find a wrong node, we return false
        }
        return true;
    }
}


/*
DFS
put the right node to SetA or SetB

Time: O(n^2) all edges
Space: O(n) for set AB
*/
class Solution {
    public boolean isBipartite(int[][] graph) {
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        for(int i = 0; i < graph.length; i++){
            if(!setA.contains(i) && !setB.contains(i)){
                setA.add(i);
                if(!part(graph, i, setB, setA)) return false;
            }
        }
        return true;
    }
    private boolean part(int[][] graph, int node, Set<Integer> set, Set<Integer> antiSet){
        //all node's friend should be put into set
        for(int i = 0; i < graph[node].length; i++){
            int n = graph[node][i];//n is node's friend(edge)
            if(antiSet.contains(n)) return false;
            if(set.contains(n)) continue;
            set.add(n);
            if(!part(graph, n, antiSet, set)) return false;
        }
        return true;
    }
}







/*
SOLUTION REFERENCE:
TIME: 0821 - 2.5h
RESULT: 7.5% - 16ms
THOUGHTS:
DFS 遍历一个连通图的所有节点，
- 如果上过色：看颜色是否正确
- 如果没上色：上对应的颜色，然后接着遍历它的相邻节点
*/

class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        Arrays.fill(color, -1);// 1 and 0 and -1
        for(int i = 0; i < graph.length; i++){
                if(color[i] == -1 && !dfs(i, graph, color, 1)) 
                    return false; 
        }
        return true;
    }
    private boolean dfs(int index, int[][] graph, int[] color, int group){
        if(color[index] != -1) return color[index] == group;
        color[index] = group;
        for(int node : graph[index]){
            if(!dfs(node, graph, color, 1 - group)) return false;
        }
        return true;
    }
}



/*
SOLUTION 0:
TIME: 0821 - 2.5h
RESULT: 7.5% - 16ms
THOUGHTS:
对每个 index 遍历，把 index 放在一个 group 里面，
然后遍历他的联结点（这样可以遍历整个连通图，但是要注意不要走到环形连通图的死循环里，要检查这个点是不是已经遍历过的 index）
一旦遇到 false，就返回到主函数，并 return false
*/
class Solution {
    public boolean isBipartite(int[][] graph) {
        Set<Integer> groupA = new HashSet<Integer>();
        Set<Integer> groupB = new HashSet<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        boolean result = true;
        for(int i = 0; i < graph.length; i++){
            if(visited.add(i)){
                groupA.add(i);
                result = dfs(i, graph, groupA, groupB, visited); 
                if(result == false) return false;
            }
        }
        return result;
    }
    private boolean dfs(int index, int[][] graph, Set<Integer> current, Set<Integer> other, Set<Integer> visited){
        //check and add all link node to the other group
        for(int j = 0; j < graph[index].length; j++){
            int node = graph[index][j];
            if(other.contains(node)) continue;
            if(current.contains(node)) return false;
            other.add(node);
        }
        //loop the link
        for(int j = 0; j < graph[index].length; j++){
            boolean res = true;
            int node = graph[index][j];
            if(visited.add(node)) res = dfs(node, graph, other, current, visited);     
            if(res == false) return false;
        }

        return true;
    }
}



