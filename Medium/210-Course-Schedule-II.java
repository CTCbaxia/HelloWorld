/*
MEDIUM
210. Course Schedule II
https://leetcode.com/problems/course-schedule-ii/discuss/59317/Two-AC-solution-in-Java-using-BFS-and-DFS-with-explanation

TIME: 0831 - 5min
RESULT: 93% - 7ms
METHOD:
1. BFS + topological sorting
2. DFS


*/
/*
SOLUTION 0: BFS
TIME: 0831 - 5min
RESULT: 93% - 7ms
NOTES:
和下面是同一个思路，但是快很多。
graph 存储的对象不一样，时间复杂度影响很大。要知道你的 graph 是需要帮助你找什么。
这个题用 topological sorting 需要找的就是上完一门课之后，先修课被满足了一门的高级课程。
*/
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        int[] degree = new int[numCourses];
        
        for(int i = 0; i < numCourses; i++) graph.add(new ArrayList<Integer>());
        for(int i = 0; i < prerequisites.length; i++){
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);//for each course, add every course that depend on it to its list
            degree[prerequisites[i][0]]++;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < numCourses; i++){
            if(degree[i] == 0) queue.offer(i);
        }
            
        int[] result = new int[numCourses];
        int num = 0;
        while(!queue.isEmpty()){
            int course = queue.poll();
            result[num++] = course;
            for(int depen : graph.get(course)){
                degree[depen]--;
                if(degree[depen] == 0) queue.offer(depen);
            }
        }
        return num == numCourses ? result : new int[0];
    }
}



/*
SOLUTION 0: BFS
TIME: 0831 - 30min
RESULT: 0% - 314ms

*/
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        int[] degree = new int[numCourses];
        
        for(int i = 0; i < numCourses; i++) graph.add(new ArrayList<Integer>());
        for(int i = 0; i < prerequisites.length; i++){
            graph.get(prerequisites[i][0]).add(prerequisites[i][1]);//for each course, add its pre to its list
            degree[prerequisites[i][0]]++;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < numCourses; i++){
            if(graph.get(i).size() == 0) queue.offer(i);
        }
            
        int[] result = new int[numCourses];
        int num = 0;
        while(!queue.isEmpty()){
            int course = queue.poll();
            result[num++] = course;
            for(int i = 0; i < numCourses; i++){

                if(graph.get(i).contains(course)){//这里的遍历很耗时
                    degree[i]--;
                    if(degree[i] == 0) 
                        queue.offer(i);
                }
            }
        }
        return num == numCourses ? result : new int[0];
    }
}




/*
SOLUTION 1: DFS
TIME: 0831 - 30min
RESULT: 8% - 75ms
THOUGHTS:
重点是有两个缓存 preList 和 finished
preList：某一门课的所有先修课列表，如果后面要求的先修课的先修课已经在 preList 里面了，则成为环状，不可能完成任务
finished：记录所有已完成的课程。注意因为最终输出的顺序也是根据 finished 生成，所有在主函数里面检查是否已经完成，不然顺序会打乱
*/

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        int[] result = new int[numCourses];
        
        for(int i = 0; i < numCourses; i++) graph.add(new ArrayList<Integer>());
        for(int i = 0; i < prerequisites.length; i++){
            graph.get(prerequisites[i][0]).add(prerequisites[i][1]);//for each course, add its pre to its list
        }

        Set<Integer> preList = new HashSet<Integer>();
        List<Integer> finished = new ArrayList<Integer>();
        
        for(int i = 0; i < numCourses; i++){
            if(!finished.contains(i)){
                if(!finish(i, graph, preList, finished)) 
                    return new int[0];
            }
        }
        for(int i = 0; i < numCourses; i++) result[i] = finished.get(i);
        return result;
    }
    private boolean finish(int course, List<List<Integer>> graph, Set<Integer> preList, List<Integer> finished){
        preList.add(course);
        for(int pre : graph.get(course)){
            if(finished.contains(pre)) continue;
            if(preList.contains(pre)) return false;
            if(!finish(pre, graph, preList, finished)) return false;
        }
        preList.remove(course);
        finished.add(course);
        return true;
    }
}






//------201807-----------------------------------------------------------------------------------
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] incLinkCounts = new int[numCourses];
        List<List<Integer>> adjs = new ArrayList<>(numCourses);
        initialiseGraph(incLinkCounts, adjs, prerequisites);
        return solveByBFS(incLinkCounts, adjs);
        //return solveByDFS(adjs);
    }


    private void initialiseGraph(int[] incLinkCounts, List<List<Integer>> adjs, int[][] prerequisites){
        int n = incLinkCounts.length;
        while (n-- > 0) adjs.add(new ArrayList<>());
        for (int[] edge : prerequisites) {
            incLinkCounts[edge[0]]++;//每门课之前需要上的课程数
            adjs.get(edge[1]).add(edge[0]);//每门课是哪些课的先修课
        }
    }

    private int[] solveByBFS(int[] incLinkCounts, List<List<Integer>> adjs){
        int[] order = new int[incLinkCounts.length];
        Queue<Integer> toVisit = new ArrayDeque<>();
        for (int i = 0; i < incLinkCounts.length; i++) {
            if (incLinkCounts[i] == 0) toVisit.offer(i);
        }
        int visited = 0;
        while (!toVisit.isEmpty()) {
            int from = toVisit.poll();
            order[visited++] = from;
            for (int to : adjs.get(from)) {
                incLinkCounts[to]--;
                if (incLinkCounts[to] == 0) toVisit.offer(to);
            }
        }
        return visited == incLinkCounts.length ? order : new int[0]; 
    }
}