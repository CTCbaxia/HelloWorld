/*
M
413. Arithmetic Slices
*/
/*
find the longest Arithmetic line with diff k, say len is len
if len is larger than 3, we can form at least one slice, 
res += (n - 1)(n - 2)/2

continue to find k with its len

Time: O(n)
Space: O(1)
*/
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if(A.length < 3) return 0;
        int k = A[1] - A[0];
        int len = 2;
        int res = 0;
        for(int i = 2; i < A.length; i++){
            if(A[i] - A[i - 1] == k) len++;
            else{
                if(len >= 3) res += (len - 1) * (len - 2) / 2;
                
                len = 2;
                k = A[i] - A[i - 1];
            }
        }
        if(len >= 3) res += (len - 1) * (len - 2) / 2;
        return res;
    }
}

//这个更厉害
public int numberOfArithmeticSlices(int[] A) {
    int curr = 0, sum = 0;
    for (int i=2; i<A.length; i++)
        if (A[i]-A[i-1] == A[i-1]-A[i-2]) {
            curr += 1;
            sum += curr;
        } else {
            curr = 0;
        }
    return sum;
}