/*
EASY
896. Monotonic Array

RESULT: 
NOTES: 
*/
/*
asc + desc + && 趋势

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean isMonotonic(int[] A) {
        boolean asc = true;
        boolean desc = true;
        for(int i = 1; i < A.length; i++){
            asc = asc && A[i - 1] <= A[i];
            desc = desc && A[i - 1] >= A[i];
        }
        return asc || desc;
    }
}



/*
Sign for trend(asc or desc) and Check trend 

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean isMonotonic(int[] A) {
        if(A.length == 0) return true;

        int sign = 0;//get trend for the whole array, sign
        if(A[A.length - 1] - A[0] > 0) sign = 1;
        else if(A[A.length - 1] - A[0] == 0) sign = 0;
        else sign = -1;
        
        for(int i = 1; i < A.length; i++){
            if(sign == 0 && A[i] != A[i - 1]) return false;//all elements should be the same
            else if((A[i] - A[i - 1]) * sign < 0) return false;//check if different trend
        }
        return true;
    }
}

