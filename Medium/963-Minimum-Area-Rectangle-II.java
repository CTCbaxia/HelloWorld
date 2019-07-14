/*
M
963. Minimum Area Rectangle II
*/
/*
Brute Force: Compare all pairs
pair all points, use [diagonal length + center point] as key, [the paired points] as value
For n points, rectangle number should be within O(n^2), 
and the last two for loops is used to compare area of different rectangles, so it is at most O(n^2).

Map<String, List<int[]>>
key:
    String: diagonal length + center point
    List<int[]>: [1,3],[2,4]... each as a point
value:
    all pairs of points that can form a rect
    
Time: O(n^2)
Space: O(n^2)
*/
class Solution {
    public double minAreaFreeRect(int[][] points) {
        Map<String, List<int[]>> map = new HashMap<>();
        int n = points.length;
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j <n; j++){
                double len = Math.pow((points[i][0] - points[j][0]),2) + Math.pow((points[i][1] - points[j][1]),2);
                double x = (double)(points[i][0] + points[j][0])/2;//!!NOTE:should be double on the right
                double y = (double)(points[i][1] + points[j][1])/2;
                String s = len + " " + x + " " + y;
                map.putIfAbsent(s, new ArrayList<>());
                map.get(s).add(new int[]{i,j});
            }
        }
        //compute the smallest area
        double min = Double.MAX_VALUE;
        for(String key : map.keySet()){
            List<int[]> list = map.get(key);
            for(int i = 0; i < list.size(); i++){
                for(int j = i + 1; j < list.size(); j++){
                    int[] p1 = points[list.get(i)[0]];
                    int[] p2 = points[list.get(i)[1]];
                    int[] p3 = points[list.get(j)[0]];
                    double width = Math.sqrt(Math.pow(p1[0] - p3[0], 2) + Math.pow(p1[1] - p3[1], 2));
                    double height = Math.sqrt(Math.pow(p2[0] - p3[0], 2) + Math.pow(p2[1] - p3[1], 2));
                    min = Math.min(min, width * height);
                }
            }
        }
        return min != Double.MAX_VALUE ? min : 0;
        
    }
}
