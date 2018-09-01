/*
MEDIUM
332. Reconstruct Itinerary
https://leetcode.com/problems/reconstruct-itinerary/description/

TIME: 0831 - 4h
RESULT: 44% - 10ms


*/
/*
SOLUTION REFERENCE: Eulerian Path
TIME: 0831 - 1h
RESULT: 51% - 9ms

NOTES:
Eulerian Path: a trial in a finite graph which visits every edge exactly once --> 每个票用一次且全部用掉
	1. 图是连通的
	2. 满足下面两个条件中的一个：
		- 所有点的 入度 = 出度
		- 有且只有一个点的入度比出度少 1 （起点），有且只有一个点的入度比出度多 1 （终点），且其余点入度 = 出度。（其实整体来看还是 入度 = 出度）

若已知存在欧拉路径，如何找到欧拉路径：
	1. Fleury 算法
	2. Hierholzer 算法

下面的实现为 Hierholzer 算法：
	- TC: O(n + nlogn +n)： 建图 O(n + nlogn) + 遍历 O(n)
	- SC: O(n)

从起点开始对每一个点进行 DFS，对于点 A 遍历到的出度点 B，删除 A 中的这个出度点 B（说明已经访问到了），并继续遍历 B 的所有出度点。
当点 X 没有未访问到的出度点了，它就是现有循环的出口，把它加到 path 的最左边。
*/

class Solution {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        LinkedList<String> path = new LinkedList<String>();//这里因为 List 是接口，LinkedList 是他的实现，所以可以直接返回 LinkedList；当然也可以用 List<String> list = new ArrayList<String>();
        for(int i = 0; i < tickets.length; i++){
            String departure = tickets[i][0];
            String arrival = tickets[i][1];
            if(!map.containsKey(departure)) map.put(departure, new PriorityQueue<String>());//直接按顺序排序
            map.get(departure).offer(arrival);
        }
        System.out.println(map);
        dfs(map, "JFK", path);
        return path;
    }
    private void dfs(Map<String, PriorityQueue<String>> map, String departure, LinkedList<String> path){
        PriorityQueue<String> pq = map.get(departure);
        while(pq != null && pq.size() != 0){
            dfs(map, pq.poll(), path);
        }
        path.addFirst(departure);
    }
}


/*
SOLUTION 0: Brute Force + DFS + Backtracking
TIME: 0831 - 3h
RESULT: 51% - 9ms


THOUGHTS:
1. 只需要找到一个路径，那怎么在找到路径之后退出 DFS 呢？--> 用 boolean 返回值

NOTES:
1. add + remove 的 DFS 遍历，不要改变你的原始数据，会使得遍历的顺序混乱。
places: [KUL, NRT] 
places: [NRT, KUL] 
好的方法是： arrivals.add(i, dest);（在原有位置重新插入）

2. 有重复 arrival 出现的可能，所以 Map<String, List<String>> 而非 Map<String, Set<String>>

3. 对于“判断其是否为终点(到达终止点)”的语句 应该放在 “判断是否结束旅程” 之后。以免最后到达一个终止点但是却返回了 false

4. map.values() 得到所有 value 值的 collection，可以用 for each
*/
class Solution {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, List<String>> map = new HashMap<>();//这里用 List 而不是 Set，因为行程可能会重复（from to 一样），但 Set 不允许重复
        List<String> result = new ArrayList<String>();
        int len = tickets.length + 1;
        for(int i = 0; i < tickets.length; i++){//generate the map
            String from = tickets[i][0];
            String to = tickets[i][1];
            if(!map.containsKey(from)){
                List<String> fromPlace = new ArrayList<String>();
                map.put(from, fromPlace);
            } 
            map.get(from).add(to);
        }
        //sorting the to places by lexical order
        for(List<String> list : map.values()){
            Collections.sort(list);
        }
        
        result.add("JFK");
        if(findNext(map, "JFK", result, len)) return result;
        return null;
    }
    private boolean findNext(Map<String, List<String>> map, String from, List<String> result, int len){
        if(result.size() == len) return true;
        if(!map.containsKey(from)) return false;//这个应该放在判断 size 之后，因为有可能最后的点没有 destination

        List<String> arrivals = map.get(from);
        for(int i = 0; i < arrivals.size(); i++){
            String dest = arrivals.get(i);
            result.add(dest);
            arrivals.remove(dest);
            if(findNext(map, dest, result, len)) return true;
            result.remove(result.size() - 1);//如果是 result.remove(dest), 可能会移除之前出现的一个相同的地址（会移除首次出现的该值）
            arrivals.add(i, dest);
        }
        
        return false;
    }
}


/*
Wrong Code that make you learn
*/
class Solution {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, List<String>> map = new HashMap<>();//这里用 List 而不是 Set，因为行程可能会重复（from to 一样），但 Set 不允许重复
        List<List<String>> sort = new ArrayList<List<String>>();
        List<String> result = new ArrayList<String>();
        int len = tickets.length;
        for(int i = 0; i < tickets.length; i++){//generate the map
            String from = tickets[i][0];
            String to = tickets[i][1];
            if(!map.containsKey(from)){
                List<String> fromPlace = new ArrayList<String>();
                map.put(from, fromPlace);
                sort.add(fromPlace);
            } 
            map.get(from).add(to);
        }
        //sorting the to places by lexical order，这块的排序也有更新
        for(int i = 0; i < sort.size(); i++){
            Collections.sort(sort.get(i));
        }
        
        findNext(map, "JFK", result, len);
        return result;
    }
    private void findNext(Map<String, List<String>> map, String from, List<String> result, int len){
        result.add(from);
        if(result.size() == len){
            result.add(map.get(from).iterator().next());
            return;
        }
        List<String> places = map.get(from);
        for(int i = 0; i < places.size(); i++){
            String next = places.get(i);
            map.get(from).remove(next);//这样会改变 list 内部顺序的
            findNext(map, next, result, len);
            if(result.size() == len + 1) return;
            map.get(from).add(next);//这样会改变 list 内部顺序的
        }
        result.remove(from);
        return;
    }
}



[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]

[["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]