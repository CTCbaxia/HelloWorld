/*
MEDIUM
365. Water and Jug Problem
https://leetcode.com/problems/water-and-jug-problem/description/

TIME: 0913 - 1h
RESULT: 10% - 3ms
NOTES: 
- BFS 没懂
- GCD： https://leetcode.com/problems/water-and-jug-problem/discuss/83715/Math-solution-Java-solution

*/
/*
SOLUTION REFERENCE:
TIME: 0913 - 1h
RESULT: 10% - 3ms
*/
class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
    	//limit brought by the statement that water is finallly in one or both buckets
        if(x + y < z) return false;
        int divisor = GCD(x,y);
        return divisor == 0 ? ((z == 0) ? true : false) : ((z % divisor == 0) ? true : false);
    }
    private int GCD(int x, int y){
        int divisor = x;
        if(y < x) divisor = y;
        while(divisor > 0){
            if(x % divisor == 0 && y % divisor == 0){
                break;
            }
            divisor--;
        }
        return divisor;
    }
}