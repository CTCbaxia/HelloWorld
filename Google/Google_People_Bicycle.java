/*
MEDIUM
Google_People_Bicycle
题目：

2D平面上，有m个人(P)，n辆自行车(B)，还有空白(O)满足以下条件
1.m < n
2.不存在两个人，到同一辆自行车距离相等, 距离用abs(x1-x2) + abs(y1-y2)定义 3.每个人尽量找离自己最近的自行车，一旦某辆自行车被占，其他人只能找别的自行车。 例:
OPOBOOP 
OOOOOOO 
OOOOOOO 
OOOOOOO
BOOBOOB
红色的人找到第一行的自行车，距离最近。 蓝色的人离第一行自行车最近，但自行车已经被红色人占有，所以他只能找离他第二近的 ，右下角的自行车。
问:把人和自行车配对，输出vector<pair<int, int>>每个人对应的自行车. {i, j} 是人i对应 自行车j
*/

/*
我当时跟面试官讨论了几种解法，感觉这是一道开放性问题，可以有很多解法，我最后写的是一个比较简单的解法，假设函数给定一堆人和车以及他们的坐标，然后求出所有人车距离，把这些数值放在一个minheap里面，每次拿最小的距离，同时记录下来这个人和这个车已经match过了，这样依次匹配。这种解法的缺点是没考虑tie的情况，总之需要跟面试官讨论，然后选择一种面试官认可的解法。
我提了这个解法，就是找到一个解使得匹配之后所有人和车的距离之和是最短的，但面试官说没那么复杂，用greedy的方法先match到最短的，然后依次类推

Time: O(mn * mn * logmn)???
Space: O(mn)
*/
class Google_People_Bicycle {
    public class Pair{
        int[] p;
        int[] b;
        public Pair(int[] p, int[] b){
            this.p = p;
            this.b = b;
        }
    }
    public List<Pair> matchingBicycle(char[][] matrix){
        PriorityQueue<Pair> pq = new PriorityQueue(new Comparator<Pair>(){
            public int compare(Pair p1, Pair p2){
                int dis1 = Math.abs(p1.p[0] - p1.b[0]) + Math.abs(p1.p[1] - p1.b[1]);
                int dis2 = Math.abs(p2.p[0] - p2.b[0]) + Math.abs(p2.p[1] - p2.b[1]);

                return dis1 - dis2;
            }
        });

        List<int[]> people = new ArrayList<>();
        List<int[]> bicyle = new ArrayList<>();

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 'P') people.add(new int[]{i,j});
                if(matrix[i][j] == 'B') bicyle.add(new int[]{i,j});

            }
        }
        //put every match into pq
        for(int[] p : people){
            for(int[] b : bicyle){
                pq.offer(new Pair(p, b));
            }
        }

        //matching
        List<Pair> res = new ArrayList<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        while(!pq.isEmpty()){
            Pair pair = pq.poll();
            if(visited[pair.p[0]][pair.p[1]] || visited[pair.b[0]][pair.b[1]]) continue;

            res.add(pair);
            visited[pair.p[0]][pair.p[1]] = true;
            visited[pair.b[0]][pair.b[1]] = true;
            System.out.println(pair.p[0] + "," + pair.p[1] + " " + pair.b[0] + "," + pair.b[1] );
        }
        return res;
    }
}