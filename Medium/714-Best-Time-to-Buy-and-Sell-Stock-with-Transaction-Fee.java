/*
MEDIUM
939. Minimum Area Rectangle

*/
/*
Map
1. 遍历所有点，build map
2. 遍历所有点，看看这两个点构成的 rect 是否更小，再看可不可以找到能够合成 rect 的其他点 -- 这样复杂度会更低

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public int minAreaRect(int[][] points) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] p : points){
            if(!map.containsKey(p[0])) map.put(p[0], new ArrayList<>());
            map.get(p[0]).add(p[1]);
        }
        int res = Integer.MAX_VALUE;
        //这里遍历 points 而非 map
        for(int i = 0; i < points.length; i++){
            for(int j = i + 1; j < points.length; j++){
                int[] p1 = points[i];
                int[] p2 = points[j];
                if(p1[0] == p2[0] || p1[1] == p2[1]) continue;//if there share same x or y
                //get two 对角线的点
                int area = Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]);
                if(area < res){
                    //❤️这里 tricky 的点是：先算值是否更小，再看这是不是 rect
                    //因为 list.contains() 的操作是 O(n)
                    if(map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])){
                        res = area;
                    }  
                }
                
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}