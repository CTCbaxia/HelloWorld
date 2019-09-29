/*
H
850. Rectangle Area II
*/
/*
Line Sweep - Active Set
1. do line sweep for y
2. in x-axis, manage active interval for that y, merge the intervals to get the coverage to be calculated for the next level
3. (y - prey) * coverage

Time: O(n*(m^2)log(m^2))
y line would be n, x interval merges will be m^2 for each y-line
或者如果有N个长方形
O(N^2lognN)

Space: O(m^2)
*/
class Solution {
    public class Line{
        int y;
        int[] x;
        boolean isBegin;
        public Line(int _y, int[] _x, boolean _isBegin){
            y = _y;
            x = _x;
            isBegin = _isBegin;
        }
    }
    public int rectangleArea(int[][] rectangles) {
        //preprocess y line and put into the pq
        List<Line> lines = new ArrayList<>();
        for(int[] r : rectangles){
            lines.add(new Line(r[1], new int[]{r[0], r[2]}, true));
            lines.add(new Line(r[3], new int[]{r[0], r[2]}, false));
        }
        
        Collections.sort(lines, new Comparator<Line>(){
            public int compare(Line l1, Line l2){
                return l1.y - l2.y;
            }
        });
        
        List<int[]> active = new ArrayList<>();
        int preY = 0;
        long res = 0;
        for(int i = 0; i < lines.size(); i++){
            Line l = lines.get(i);
            
            if(l.y != preY){//compute coverage for the previous line
                long coverage = compute(active);
                res += (l.y - preY) * coverage;
                preY = l.y;
            }
            //update active set
            if(l.isBegin) active.add(l.x);
            else{
                for(int j = 0; j < active.size(); j++){
                    int[] tmp = active.get(j);
                    if(tmp[0] == l.x[0] && tmp[1] == l.x[1]){
                        active.remove(j);
                        break;
                    } 
                }
            } 
        }
        res %= 1000000007;
        return (int) res;
    }
    private long compute(List<int[]> active){
        Collections.sort(active, new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2){
                return i1[0] - i2[0];
            }
        });
        long res = 0;
        int[] cur = {-1,-1};
        for(int[] a : active){
            if(a[0] > cur[1]){
                res += cur[1] - cur[0];
                cur[0] = a[0];
                cur[1] = a[1];
            }else{
                cur[1] = Math.max(cur[1], a[1]);//merge
            }
        }
        //last one
        res += cur[1] - cur[0];
        return res;
    }
}