/*
EASY
447. Number of Boomerangs

*/
/*
Map 对于每个点，把它作为中心点，算和其他所有点的距离
distance: 勾股定理

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int sum = 0;
        for(int i = 0; i < points.length; i++){
            int[] p1 = points[i];
            Map<Integer, Integer> map = new HashMap<>();//只需要知道同等距离的数目，不需要知道具体有哪些
            for(int j = 0; j < points.length; j++){
                if(i == j) continue;
                
                int[] p2 = points[j];
                int dx = p1[0] - p2[0];
                int dy = p1[1] - p2[1];
                int d = dx * dx + dy * dy;
                
                map.put(d, map.getOrDefault(d, 0) + 1);
            }
            for(int k : map.values()){
                sum += k > 0 ? k * (k - 1) : 0;
            }
        }
        return sum;
    }
}




/*
distance: 勾股定理

Map<Double, List<int[]>>: 距离 -- 对应的点们
*/
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int sum = 0;
        for(int i = 0; i < points.length; i++){
            int[] p1 = points[i];
            Map<Double, List<int[]>> map = new HashMap<>();
            for(int j = 0; j < points.length; j++){
                if(i == j) continue;
                
                int[] p2 = points[j];
                double d = Math.pow(p1[0] - p2[0],2) + Math.pow(p1[1] - p2[1],2);
                if(!map.containsKey(d)) map.put(d, new ArrayList<>());
                map.get(d).add(p2);
            }
            for(double k : map.keySet()){
                int n = map.get(k).size();
                sum += n > 0 ? n * (n - 1) : 0;
            }
        }
        return sum;
    }
}