/*
MEDIUM
825. Friends Of Appropriate Ages
https://leetcode.com/problems/friends-of-appropriate-ages/description/

TIME: 0821 - 2.5h
RESULT: 7.5% - 16ms
NOTES: 
1. 题目没有说图是连通图，所以在主 numFriendRequests 里面要遍历所有的 node，而不是直接 return 以 0 为起点的递归
*/
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



