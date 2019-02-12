/*
EASY
973. K Closest Points to Origin

*/
/*
写好了 comparator 其实不需要 新建一个 class
Time: O(nlogk)
Space: O(n)
*/
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] p1, int[] p2){
                int d1 = p1[0] * p1[0] + p1[1] * p1[1];
                int d2 = p2[0] * p2[0] + p2[1] * p2[1];
                return d2 - d1;
            }
        });
        
        for(int i = 0; i < points.length; i++){
            pq.offer(points[i]);
            if(pq.size() > K) pq.poll();

        }
        
        int i = 0;
        while(pq.size() > 0){
            res[i++] = pq.poll();
        }
        return res;

    }

}

//新建 class
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>(){
            public int compare(Point p1, Point p2){
                return (int)(p2.dis - p1.dis);
            }
        });
        for(int i = 0; i < points.length; i++){
            int[] p = points[i];
            double distance = Math.pow(p[0], 2) + Math.pow(p[1], 2);
            Point point = new Point(p, distance);
            pq.offer(point);
            if(pq.size() > K) pq.poll();

        }
        int i = 0;
        while(pq.size() > 0){
            res[i++] = pq.poll().p;
        }
        return res;

    }
    class Point{
        int[] p;
        double dis;
        public Point(int[] _p, double _dis){
            p = _p;
            dis = _dis;
        }
    }
}