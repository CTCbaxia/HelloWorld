/*
MEDIUM
310. Minimum Height Trees
https://leetcode.com/problems/minimum-height-trees/description/

TIME: 0830 - 2h
RESULT: 45% - 33ms

NOTE:
1. 如果有一个 list 用来存放现有 path，那么如果 list<list>> allPath: allPath.add(path) 这个添加的是一个指向地址的指针，而非值。
所以对临时的，会不断变化的 path，allPath.add(path) 最终会造成有 N 个相同的 path（地址）被加入到 allPath 中，这些结果都指向同一个 path。
2. pathnew.remove(Integer.valueOf(n_next));//因为 n_next 是 int，会和 list.remove(index) 冲突
3. 这题还不如用list<String> -- 不不不，int 的内容千万不能转化成 string 来解。
思路：
只出现了一次的为 leaf
要找最长的一条 path 的中点，就是找最长的 path
那么和 merge interval 是一样的类型

1. 新建 hashmap 来存各节点的 edges 数量
2. 从 edges 数量为 1 的开始（leaf），按 merge 的方法找到所有的 path（path 的两头均为 left），存为 list
3. 找到最长的 list，获取其中间值
*/



/*
SOLUTION REFERENCE: 
TIME: 0830 - 2h
RESULT: 45% - 33ms

THOUGHTS:
先找到所有的叶子。
再找到紧邻所有叶子的所有节点，当把现在的叶子都移除的时候:
1. 如果它也称为了叶子，就加入下一个 level 的叶子遍历
2. 如果他没有称为叶子，就先不管

*** Hint***
对于线性图来说（没有成环），只可能有 1/2 个中心点

*/
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1){
            List<Integer> res = new ArrayList<Integer>();
            res.add(0);
            return res;
        }
        
        List<Set<Integer>> graph = new ArrayList<Set<Integer>>();
        for(int i = 0; i < n; i++) graph.add(new HashSet<Integer>());
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        List<Integer> leaves = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            if(graph.get(i).size() == 1) 
                leaves.add(i);
        }
        //如果不成环，一定会有 1 / 2 个中间点（不会超过2）
        while(n > 2){
            n = n - leaves.size();
            List<Integer> newLeaves = new ArrayList<Integer>();
            for(int leaf : leaves){
                int neighbor = graph.get(leaf).iterator().next();
                graph.get(neighbor).remove(leaf);//找到了与树叶的链接点，去掉树叶
                if(graph.get(neighbor).size() == 1)
                    newLeaves.add(neighbor);
            }
            leaves = newLeaves;
        }
        return leaves;
        
    }
}

10
[[2,3],[1,2],[3,4],[5,4],[6,4],[7,4],[8,4],[8,9],[9,0]]
/*
思路不对。
BFS 一层层递进出来不能够保证是最中心的两个点，因为有的叶子离中心近，有的离中心远。这样出来会偏离中心的。

*/
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1){
            List<Integer> res = new ArrayList<Integer>();
            res.add(0);
            return res;
        }
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        List<List<Integer>> toCenter = new ArrayList<List<Integer>>();
        Set<Integer> visited = new HashSet<Integer>();
        //generate the graph
        for(int[] edge : edges){
            if(!map.containsKey(edge[0])) map.put(edge[0], new ArrayList<Integer>());
            if(!map.containsKey(edge[1])) map.put(edge[1], new ArrayList<Integer>());
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);

        }
        //System.out.println(map);
        
        //find the leaves
        List<Integer> firstLevel = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            if(map.get(i).size() == 1){
                firstLevel.add(i);
                visited.add(i);
            }
        }
        toCenter.add(firstLevel);
        int size = firstLevel.size();
        
        while(size != 0){
            List<Integer> nextLevel = new ArrayList<Integer>();
            for(int i = 0; i < size; i++){
                int node = toCenter.get(toCenter.size() - 1).get(i);
                
                for(int j = 0; j < map.get(node).size(); j++){
                    int neighbor = map.get(node).get(j);
                    if(!visited.contains(neighbor)){
                        nextLevel.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            size = nextLevel.size();
            toCenter.add(nextLevel);
        }

        return toCenter.get(toCenter.size() - 2);
    }
}



//-----201807---------------------------------------------------------------------------
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, Integer> node_edges = new HashMap<Integer, Integer>();
        List<List<Integer>> allPath = new ArrayList<List<Integer>>();
        List<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < edges.length; i++){
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            node_edges.put(n1, node_edges.getOrDefault(n1,0) + 1);
            node_edges.put(n2, node_edges.getOrDefault(n2,0) + 1);
        }
        for(int i = 0; i < edges.length; i++){
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            if(node_edges.get(n1) == 1){
                // List<Integer> path = new ArrayList<Integer>();
                // path.add(n1);
                // path.add(n2);
                //find next node till another leaf
                startPath(n2, n1, edges, allPath, node_edges);
            }
            if(node_edges.get(n2) == 1){
                // List<Integer> path = new ArrayList<Integer>();
                // path.add(n2);
                // path.add(n1);
                //find next node till another leaf
                startPath(n1, n2, edges, allPath, node_edges);
            }
        }
System.out.println(allPath);
        int[] length = new int[allPath.size()];
        for(int i = 1; i < length.length; i++){
            length[i] = allPath.get(i).size();
        }
        Arrays.sort(length);
        int longest = length[length.length - 1];
        
        for(int i = 1; i < allPath.size(); i++){
            if(allPath.get(i).size() == longest){
                if(longest % 2 == 1){
                    int res = allPath.get(i).get(longest/2);
                    if(!result.contains(res)) result.add(res);
                }else{
                    int res = allPath.get(i).get(longest/2);
                    int res2 = allPath.get(i).get(longest/2 - 1);
                    if(!result.contains(res)) result.add(res); 
                    if(!result.contains(res2)) result.add(res2); 
                }
            }
        }
        return result;
        
    }
    private void startPath(int node, int leaf, int[][] edges, List<List<Integer>> allPath, Map<Integer, Integer> node_edges){
        
        for(int i = 0; i < edges.length; i++){
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            if(n1 == node || n2 == node ){
                int n_next = n2;
                if(n2 == node) n_next = n1;
                List<Integer> pathnew = new ArrayList<Integer>();
                pathnew.add(leaf);
                pathnew.add(node);
                if(pathnew.contains(n_next)) continue;
                pathnew.add(n_next);
                if(node_edges.get(n_next) == 1){
                    allPath.add(pathnew);
                }else{
                   findNext(n_next, edges, pathnew, allPath, node_edges); 
                }
            }
        }

        return;
    }
    //if longer than 3 nodes
     private void findNext(int node, int[][] edges, List<Integer> pathnew, List<List<Integer>> allPath, Map<Integer, Integer> node_edges){
         
        for(int i = 0; i < edges.length; i++){
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            
            if(pathnew.contains(n1) && pathnew.contains(n2)) continue;
    
            if(n1 == node || n2 == node ){
                int n_next = n2;
                if(n2 == node) n_next = n1;
                pathnew.add(n_next);
                if(node_edges.get(n_next) == 1){
                    allPath.add(pathnew);
                    //System.out.println("pathnew:" + pathnew);
                    //System.out.println("allPath:" + allPath);
                }else{
                    findNext(n_next, edges, pathnew, allPath, node_edges); 
                }
                //这里还是有问题，因为 List 的传地址特性，在任何地方改变会影响所有后续的结果
                pathnew.remove(Integer.valueOf(n_next));//因为 n_next 是 int，会和 list.remove(index) 冲突

            }
        }
        return;
    }
}









//对于单整数应该是 work 了，但是..用 string 存储没有考虑到 int > 10 的情况啊...
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        
        List<Integer> result = new ArrayList<Integer>();
        int tmp = 0; 
        result.add(tmp);
        if(edges.length == 0) return result;
            
        
        Map<Integer, Integer> node_edges = new HashMap<Integer, Integer>();
        List<String> allPath = new ArrayList<String>();
        
        for(int i = 0; i < edges.length; i++){
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            node_edges.put(n1, node_edges.getOrDefault(n1,0) + 1);
            node_edges.put(n2, node_edges.getOrDefault(n2,0) + 1);
        }

        for(int i = 0; i < edges.length; i++){
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            if(node_edges.get(n1) == 1){
                Set<Integer> preInt = new HashSet<Integer>();
                String prePath = Integer.toString(n1) + Integer.toString(n2);
                if(node_edges.get(n2) == 1){
                    allPath.add(prePath);
                    continue;
                }
                preInt.add(n1);
                preInt.add(n2);

                findNext(n2, edges, prePath, preInt, allPath, node_edges); 
            }
            if(node_edges.get(n2) == 1){
                Set<Integer> preInt = new HashSet<Integer>();
                String prePath = Integer.toString(n2) + Integer.toString(n1);
                preInt.add(n1);
                preInt.add(n2);

                findNext(n1, edges, prePath, preInt, allPath, node_edges); 
            }
        }
System.out.println(allPath);
        result.clear();
        int[] length = new int[allPath.size()];
        
        for(int i = 0; i < length.length; i++){
            length[i] = allPath.get(i).length();
        }
        
        Arrays.sort(length);
        int longest = length[length.length - 1];
        
        
        for(int i = 0; i < allPath.size(); i++){
            
            if(allPath.get(i).length() == longest){

                if(longest % 2 == 1){
                    int res = allPath.get(i).charAt(longest/2) - '0';
                    if(!result.contains(res)) result.add(res);
                }else{
                    int res = allPath.get(i).charAt(longest/2) - '0';
                    int res2 = allPath.get(i).charAt(longest/2 - 1) - '0';
                    if(!result.contains(res)) result.add(res); 
                    if(!result.contains(res2)) result.add(res2); 
                }
            }
        }
        return result;
        
    }

     private void findNext(int node, int[][] edges, String prePath, Set<Integer> preInt, List<String> allPath, Map<Integer, Integer> node_edges){

        for(int i = 0; i < edges.length; i++){
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            
            if(preInt.contains(n1) && preInt.contains(n2)) continue;
            if(n1 == node || n2 == node ){

                int n_next = n2;
                if(n2 == node) n_next = n1;

                preInt.add(n_next);
                if(node_edges.get(n_next) == 1){
                    allPath.add(prePath + Integer.toString(n_next));
                }else{

                    findNext(n_next, edges, prePath + Integer.toString(n_next), preInt, allPath, node_edges); 
                }
                preInt.remove(n_next);

            }
        }
        return;
    }
}




public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    if (n == 1) return Collections.singletonList(0);
    //构造一个 hashset 来存所有的数组，把每一个元素具有的链接都找出来
    List<Set<Integer>> adj = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) adj.add(new HashSet<>());
    for (int[] edge : edges) {
        adj.get(edge[0]).add(edge[1]);
        adj.get(edge[1]).add(edge[0]);
    }

    List<Integer> leaves = new ArrayList<>();
    for (int i = 0; i < n; ++i)
        if (adj.get(i).size() == 1) leaves.add(i);

    while (n > 2) {
        n -= leaves.size();
        List<Integer> newLeaves = new ArrayList<>();
        for (int i : leaves) {
            int j = adj.get(i).iterator().next();
            adj.get(j).remove(i);
            if (adj.get(j).size() == 1) newLeaves.add(j);
        }
        leaves = newLeaves;
    }
    return leaves;
}






class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<List<Integer>> relation = new ArrayList<List<Integer>>();
        Map<Integer,Integer> steps = new HashMap<Integer,Integer>();
        List<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            relation.add(new ArrayList<Integer>());
        }
        for(int[] edge : edges){
            relation.get(edge[0]).add(edge[1]);
            relation.get(edge[1]).add(edge[0]);
        }
        for(int i = 0; i < n; i++){
            if(relation.get(i).size() == 1) steps.put(i, 1);
        }
        //relation.size() > 2
            int num = 0;
        while(num<1){
            for(int node : steps.keySet()){
                System.out.println(relation.get(node).get(0));
            }
            num++;
        }
        
        
        return result;

        
    }
}


????
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Set<Integer>> relation = new ArrayList<Set<Integer>>();
        Map<Integer,Integer> steps = new HashMap<Integer,Integer>();
        List<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            relation.add(new HashSet<Integer>());
        }
        for(int[] edge : edges){
            relation.get(edge[0]).add(edge[1]);
            relation.get(edge[1]).add(edge[0]);
        }
        for(int i = 0; i < n; i++){
            if(relation.get(i).size() == 1) steps.put(i, 1);
        }
        //relation.size() > 2
            int num = 0;
        while(num<1){
            for(int node : steps.keySet()){
                System.out.println(relation.get(node).iterator().next());
            }
            num++;
        }
        
        
        return result;

        
    }
}