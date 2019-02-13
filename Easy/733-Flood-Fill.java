/*
EASY
733. Flood Fill

*/
/*
Find Island - DFS

Time: O(mn)
Space: O(mn)
*/
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image.length == 0 || image[0].length == 0 || sr >= image.length || sc >= image[0].length || image[sr][sc] == newColor) return image;
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    private void dfs(int[][] image, int r, int c, int curColor, int newColor){
        if(r < 0 || c < 0 || r >= image.length || c >= image[0].length || image[r][c] != curColor) return;
        image[r][c] = newColor;
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int[] dir : directions){
            int i = r + dir[0];
            int j = c + dir[1];
            dfs(image, i, j, curColor, newColor);
        }
    }
}





/*
Find Island - BFS

Time: O(mn)
Space: O(mn)
*/
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        //because we don't use visited, we need check if curColor == newColor to avoid dead loop
        if(image.length == 0 || image[0].length == 0 || sr >= image.length || sc >= image[0].length || image[sr][sc] == newColor) return image;
        
        int m = image.length;
        int n = image[0].length;
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        Queue<int[]> q = new LinkedList<>();
        
        int curColor = image[sr][sc];
        q.offer(new int[]{sr, sc});
        image[sr][sc] = newColor;
        
        while(!q.isEmpty()){
            int[] pos = q.poll();
            
            for(int[] dir : directions){
                int r = pos[0] + dir[0];
                int c = pos[1] + dir[1];
                if(r >= 0 && r < m && c >= 0 && c < n && image[r][c] == curColor){
                    q.offer(new int[]{r, c});
                    image[r][c] = newColor;
                }
            }
        }
        
        return image;
    }
}