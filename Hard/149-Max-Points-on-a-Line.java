/*
H
149. Max Points on a Line
*/
/*
For every point, try rest of the point
y = kx + b
Map<k, num of point with k>

***
If Same Point
just count for the same point because they can apply on any result for that point
Else
    If x1 = x2, make it Double.MAX_VALUE
    If normal, compute for k

***
String for division result
1. double loss precision
2. gcd to get 最大公约数 and use "y/x" for mapping
3. if delta x == 0, use "max", else if y == 0, default slope = 0


Time: O(n^2)
Space: O(n^2)
*/
class Solution {
    public int maxPoints(int[][] points) {
        int res = 0;
        for(int i = 0; i < points.length; i++){
            Map<String, Integer> map = new HashMap<>();
            int samePoint = 1;
            int max = 0;
            for(int j = i + 1; j < points.length; j++){
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                if(x == 0 && y == 0) samePoint++;
                else{
                    String slope = "";
                    if(y == 0) slope = "0";//avoid -0 and 0 to be different
                    else if(x == 0) slope = "MAX";
                    else{
                        int g = gcd(x, y);//最大公约数
                        slope = x/g + "/" + y/g;
                    }
                    map.put(slope, map.getOrDefault(slope, 0) + 1);
                    max = Math.max(max, map.get(slope));
                }
            }
            res = Math.max(res, max + samePoint);
        }
        return res;
    }
    // 用最大公约数是因为直接相除 double 会有数据不精确的问题，所以换成最大公约数的分数 string 形式
    private int gcd(int x, int y){
        if(x == 0) return y;
        else if(y == 0) return x;
        else return gcd(y, x%y);
    }
}
