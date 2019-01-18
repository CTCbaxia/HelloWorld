/*
MEDIUM
750. Number Of Corner Rectangles

*/
/*
排列组合法
先确定两个列，对于每行，都判断该行上的那两个列点能不能组成线（均为 1）
找到所有满足的行 (lines)
从 lines 里选择两行的组合即为这两个列条件下可以组成长方形的四个点


Time: O(m * n^2)
Space: O(mn)
*/
class Solution {
    public int countCornerRectangles(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;

        int result = 0;
        //find two points
        for(int j1 = 0; j1 < n; j1++){
            for(int j2 = j1 + 1; j2 < n; j2++){
                int lines = 0;
                for(int i = 0; i < m; i++){
                    if(grid[i][j1] == 1 && grid[i][j2] == 1){
                        lines++;
                    }
                }
                result += lines * (lines - 1) / 2;//组合公式
            }
        }
        return result;
    }
}


/*
对付稀疏矩阵：Store 1's in 
1. 先将所有为 1 的点存成list
2. 对于 list 中每两个可以组成 valid 长方形的组合，检查是不是 valid result

Time: O(m * n + N^2) N = number of 1,worst case can be mn -> O(m * n + (m*n)^2)
Space: O(mn)
*/
class Solution {
    public int countCornerRectangles(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        List<Set<Integer>> points = new ArrayList<>();
        //build points 
        for(int i = 0; i < m; i++) points.add(new HashSet<>());
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1) points.get(i).add(j);
            }
        }
        //find rectangles
        int result = 0;
        for(int i1 = 0; i1 < m; i1++){
            Iterator<Integer> it1 = points.get(i1).iterator();
            while(it1.hasNext()){
                int j1 = it1.next();
                for(int i2 = i1 + 1; i2 < m; i2++){
                    Iterator<Integer> it2 = points.get(i2).iterator();
                    while(it2.hasNext()){
                        int j2 = it2.next();
                        if(j2 <= j1) continue;//p1 and p2 should make a valid rectangle
                        if(points.get(i1).contains(j2) && points.get(i2).contains(j1)){
                            result++;
                        }
                            
                    }
                }
            }
        }
        return result;
    }
}
/*
Brute Force:
Use list for every col, gather rows that has a 1
Find two point that can make a distinct rectangle (not in same row or col)
check if four vectors are 1

Time: O(m^2 * n^2) 核心是需要找到矩阵上的两个点
Space: O(mn)
*/
class Solution {
    public int countCornerRectangles(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        List<List<Integer>> cols = new ArrayList<>();
        //build cols
        for(int i = 0; i < n; i++) cols.add(new ArrayList<>());
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1) cols.get(j).add(i);
            }
        }
        //find rectangles
        int result = 0;
        for(int i1 = 0; i1 < m; i1++){
            for(int j1 = 0; j1 < n; j1++){
                for(int i2 = i1 + 1; i2 < m; i2++){
                    for(int j2 = j1 + 1; j2 < n; j2++){
                        int p1 = grid[i1][j1];
                        int p2 = grid[i2][j2];
                        if(p1 != 1 || p2 != 1) continue;
                        
                        //they are both 1
                        if(cols.get(j1).contains(i2) && cols.get(j2).contains(i1))
                            result++;
                    }
                }
            }
        }
        return result;
    }
}