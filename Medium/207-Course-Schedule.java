/*
MEDIUM
207. Course Schedule
https://leetcode.com/problems/course-schedule/description/

TIME: 0830 - 3h
RESULT: 76% - 9ms
NOTES:
1. 这题给了 numCourses，其实可以不用 map，直接用 list 来记录 graph，访问的时候直接寻找节点 (快很多)
2. Topological sorting: https://en.wikipedia.org/wiki/Topological_sorting#Algorithms
3. how graph is presented: https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/representing-graphs
*/
/*
SOLUTION 1: BFS + Topological sorting + ArrayList
TIME: 0830 - 1h
RESULT: 76% - 9ms
*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] degree = new int[numCourses];
        int count = 0;
        //generate the graph<pre, its dependent>
        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < prerequisites.length; i++){
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            degree[prerequisites[i][0]]++;
        }
        
        for(int i = 0; i < degree.length; i++){
            if(degree[i] == 0) queue.add(i);
        }
        
        while(!queue.isEmpty()){
            int course = queue.poll();
            count++;
            for(int i = 0; i < graph.get(course).size(); i++){
                int depen = graph.get(course).get(i);
                degree[depen]--;
                if(degree[depen] == 0) queue.add(depen);
            }
        }
        return count == numCourses;
    }

}
/*
SOLUTION 1: BFS + Topological sorting + Map
TIME: 0830 - 1h
RESULT: 46% - 21ms
*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] degree = new int[numCourses];
        int count = 0;
        //generate the graph<pre, its dependent>
        for(int i = 0; i < prerequisites.length; i++){
            int course = prerequisites[i][1];
            if(!graph.containsKey(course)) graph.put(course, new HashSet<Integer>());
            graph.get(course).add(prerequisites[i][0]);
            degree[prerequisites[i][0]]++;
        }
        
        for(int i = 0; i < degree.length; i++){
            if(degree[i] == 0) queue.add(i);
        }
        
        while(!queue.isEmpty()){
            int course = queue.poll();
            count++;
            
            Set<Integer> set = graph.get(course);
            if(set == null) continue;
            
            Iterator<Integer> it = set.iterator();
            while(it.hasNext()){
                int depen = it.next();
                degree[depen]--;
                if(degree[depen] == 0) queue.add(depen);
            }
        }
        return count == numCourses;
    }

}
/*
SOLUTION 0: DFS
TIME: 0830 - 40min
RESULT: 52% - 17ms

THOUGHTS:
For DFS, it will first visit a node, then one neighbor of it, then one neighbor of this neighbor... and so on. 
If it meets a node which was visited in the current process of DFS visit, a cycle is detected and we will return false. 
Otherwise it will start from another unvisited node and repeat this process till all the nodes have been visited. 
Note that you should make two records: one is to record all the visited nodes and the other is to record the visited nodes in the current DFS visit.

先生成先修课的 graph
对每一个课程进行完成，如果有先修课要求（先修课列表添加在 preList set 里面，如果后续这门课完成了，就在 preList 移除），就先完成先修课（完成课程添加在 finished set 里面）
如果一门要完成的课程的先修课已经在 preList 里面了，就会陷入死循环（永远无法完成这门课）- return false
*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
        Set<Integer> finished = new HashSet<Integer>();
        Set<Integer> preList = new HashSet<Integer>();
        
        //generate the graph
        for(int i = 0; i < prerequisites.length; i++){
            int course = prerequisites[i][0];
            if(!graph.containsKey(course)) graph.put(course, new HashSet<Integer>());
            graph.get(course).add(prerequisites[i][1]);
        }
        
        for(int i = 0; i < numCourses; i++){
            if(!route(i, graph, finished, preList)) return false;
        }
        return true;
    }
    //一旦发现需要完成的 preList 路径中有环状，就返回 false
    private boolean route(int cur,  Map<Integer, Set<Integer>> graph, Set<Integer> finished, Set<Integer> preList){
        boolean res = true;
        preList.add(cur);
        Set<Integer> set = graph.get(cur);
        if(set == null || set.size() == 0){
            finished.add(cur);
            return true;
        } 
        Iterator<Integer> it = set.iterator();
        while(it.hasNext()){
            int pre = it.next();
            if(finished.contains(pre)) continue;
            if(preList.contains(pre)) return false;
            if(!route(pre, graph, finished, preList)) return false;
        }
        preList.remove(cur);
        finished.add(cur);
        return true;
    }
}

//直接数组记录访问情况
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
        int[] courses = new int[numCourses];

        //generate the graph
        for(int i = 0; i < prerequisites.length; i++){
            int course = prerequisites[i][0];
            if(!graph.containsKey(course)) graph.put(course, new HashSet<Integer>());
            graph.get(course).add(prerequisites[i][1]);
        }
        
        for(int i = 0; i < numCourses; i++){
            if(!route(i, graph, courses)) return false;
        }
        return true;
    }
    //一旦发现需要完成的 preList 路径中有环状，就返回 false
    private boolean route(int cur,  Map<Integer, Set<Integer>> graph, int[] courses){
        
        courses[cur] = 1;//visiting
        Set<Integer> set = graph.get(cur);
        if(set == null || set.size() == 0){
            courses[cur] = -1;//visited
            return true;
        } 
        Iterator<Integer> it = set.iterator();
        while(it.hasNext()){
            int pre = it.next();
            if(courses[pre] == -1) continue;
            if(courses[pre] == 1) return false;
            if(!route(pre, graph, courses)) return false;
        }
        courses[cur] = -1;
        return true;
    }
}