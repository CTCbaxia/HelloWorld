/*
MEDIUM
433. Minimum Genetic Mutation
https://leetcode.com/problems/minimum-genetic-mutation/description/

TIME: 0830 - 30min 
RESULT: 99% - 3ms
NOTES: 见注释
*/
/*
DFS: use visited map to avoid going into dead loop
Nodes are labeled uniquely.

Time: O(n)
Space: O(1)
*//**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Map<Integer, UndirectedGraphNode> visited = new HashMap<>();
        return clone(node, visited);
    }
    private UndirectedGraphNode clone(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> visited){
        if(node == null) return null;
        else if(visited.containsKey(node.label)){
            return visited.get(node.label);//返回 UndirectedGraphNode 之后这个值是可以持续 updated 的
        } 
        
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        visited.put(copy.label, copy);//必须加在 for loop 之前，因为如果 #2, 2,3,4 就会导致一开始永远在 2 处死循环 
        for(UndirectedGraphNode n : node.neighbors){
            copy.neighbors.add(clone(n, visited));
        }
        return copy;        
    }
}



/*
SOLUTION 0: DFS + 避免死循环
TIME: 0830 - 30min 
RESULT: 99% - 3ms
NOTES: 见注释

*/
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Map<Integer, UndirectedGraphNode> visited = new HashMap<Integer, UndirectedGraphNode>();
        return clone(node, visited);
    }
    private UndirectedGraphNode clone(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> visited){
        if(node == null) return null;
        if(visited.containsKey(node.label)) return visited.get(node.label);//返回 UndirectedGraphNode 之后这个值是可以持续 updated 的
        
        UndirectedGraphNode clonenode = new UndirectedGraphNode(node.label);
        visited.put(node.label, clonenode);//必须加在 for loop 之前，因为如果 #2, 2,3,4 就会导致一开始永远在 2 处死循环
        for(UndirectedGraphNode neighbor : node.neighbors){
            clonenode.neighbors.add(clone(neighbor, visited));
        }
        return clonenode;
    }
}

