import java.util.*;
import java.math.*;
class Facebook_kthNearestPoint {
  public static void main(String[] args) {
    int[][] points = new int[][]{{1, 1},{2,2},{1,0},{0,2},{0,1}};
    kthnearest(points, 3);
    return;
  }
  public static void kthnearest(int[][] points, int k){
    //int[x, y, distance]
    PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
      public int compare(int[] i1, int[] i2){
        return i2[2] - i1[2];
      }
    });
    
    for(int[] point : points){
      int dis = point[0] + point[1];//manhattan distance
      pq.offer(new int[]{point[0], point[1], dis});
      if(pq.size() > k) pq.poll();
    }
    
    for(int[] point : pq){
      System.out.println(point[0] + " " + point[1]);
    }
  }
}

// O(nlogk)
//   O(k)
//   O(n) n
// extract max = O(logk)
// O(klogk)
// Arrays.sort(nlogn)
  
