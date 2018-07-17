/*
MEDIUM
310. Minimum Height Trees
https://leetcode.com/problems/minimum-height-trees/description/
TIME: 0717 
RESULT: 
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


