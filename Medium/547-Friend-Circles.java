/*
MEDIUM
547. Friend Circles

*/
/*
连通图数量问题
DFS Recursion + Mark visited
use M[i][i] == 0 to mark if visited or not
!!Need to loop whole line (all friends of a person) rather than only half. 

Time: O(n^2) only travel every person once
Space: O(n) recursion may wait n
*/
class Solution {
    public int findCircleNum(int[][] M) {
        int res = 0;
        for(int i = 0; i < M.length; i++){
            if(M[i][i] == 0) continue;//this person has been added to some circle(visited)
            res++;//a new person(circle)
            addToCircle(M, i);//find circle for this person
        }
        return res;
    }
    private void addToCircle(int[][] M, int i){
        M[i][i] = 0;//mark this person as visited(in some circle)
        for(int j = 0; j < M[0].length; j++){
            if(j == i) continue;
            if(M[i][j] == 1 && M[j][j] != 0){//if there is a friend tie between ij and j hasn't been visited
                addToCircle(M, j);
            }
        }
        return;
    }
}