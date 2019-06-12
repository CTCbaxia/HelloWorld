/*
MEDIUM
885. Spiral Matrix III
*/
/*
四个方向一步步走，每次更新该方向要走的steps

Time: O(m * n)
Space: O(1)
*/
class Solution {
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][2];
        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        int i = r0, j = c0;
        int index = 0, d = 0, len = 0;//d is the direction index, len is the steps
        
        res[index++] = new int[]{i,j};
        
        while(index < R * C){
            if(d == 0 || d == 2) len++;//when go east or west, steps ++
            for(int k = 0; k < len; k++){
                i += directions[d][0];
                j += directions[d][1];
                if(isInBound(i, R, j, C)) res[index++] = new int[]{i,j};
            }
            d = (d + 1) % 4;
        }
        return res;
    }

    private boolean isInBound(int i, int R, int j, int C){
        return i < R && i >= 0 && j < C && j >= 0;
    }

}



/*
四个方向一步步走，每次更新边界

Time: O(m * n)
Space: O(1)
*/
class Solution {
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][2];
        int rUp = r0 - 1, rDown = r0 + 1, cLeft = c0 - 1, cRight = c0 + 1;
        int i = r0, j = c0;
        int index = 0;
        while(index < R * C){
            while(j <= cRight && index < R * C){
                if(isInBound(i, R, j, C)) res[index++] = new int[]{i,j};
                j++;
            }
            i++;j--;
            cRight++;
            
            while(i <= rDown && index < R * C){
                if(isInBound(i, R, j, C)) res[index++] = new int[]{i,j};
                i++;
            }
            i--;j--;
            rDown++;
            
            while(j >= cLeft && index < R * C){
                if(isInBound(i, R, j, C)) res[index++] = new int[]{i,j};
                j--;
            }
            i--;j++;
            cLeft--;
            
            while(i >= rUp && index < R * C){
                if(isInBound(i, R, j, C)) res[index++] = new int[]{i,j};
                i--;
            }
            j++;i++;
            rUp--;
        }
        return res;
    }

    private boolean isInBound(int i, int R, int j, int C){
        return i < R && i >= 0 && j < C && j >= 0;
    }

}