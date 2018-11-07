/*
MEDIUM
886. Possible Bipartition

TIME: 
RESULT: 
NOTES:
*/
/*
Build Graph + DFS + Color Paint

Build graph first
We only have two groups(-1, 1), make color[] for assigned group info
DFS the person and dislike link until all people in this link are assigned group(color) or 冲突

Time: O(N + Len(dislikes))
Space: O(N + Len(dislikes))
*/
class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] color = new int[N + 1];//1: group A, -1: group B, 0: no group
        //build graph
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int[] d : dislikes){
            graph.get(d[0]).add(d[1]);
            graph.get(d[1]).add(d[0]);
        }
        
        //build group
        for(int i = 1; i < graph.size(); i++){
            if(color[i] == 0){
                if(!buildGroup(graph, color, i, 1)) return false;
            }
        }
        return true;
    }
    //make i colored c, and make i's anti colored -c (also check)
    private boolean buildGroup(List<List<Integer>> graph, int[] color, int i, int c){
        if(color[i] != 0) return color[i] == c;
        
        color[i] = c;
        for(int anti : graph.get(i)){
            if(!buildGroup(graph, color, anti, -c)) return false;
        }
        return true;
    }
}
