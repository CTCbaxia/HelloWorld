/*
MEDIUM
923. 3Sum With Multiplicity

TIME: 
RESULT: 
NOTES:

*/
class Solution {
    public int threeSumMulti(int[] A, int target) {
        long result = 0;
        long mod = 1000000007;
        Arrays.sort(A);
        for(int i = 0; i < A.length - 2; i++){
            int t = target - A[i];
            int lo = i + 1, hi = A.length - 1;
            int res = 0;
            while(lo < hi){
                if(A[lo] + A[hi] == t){
                    int loNum = 1, hiNum = 1;
                    if(A[lo] == A[hi]){
                        res += (hi - lo + 1) * (hi - lo)/2;
                        lo = hi;
                    }else{
                        while(lo < hi && A[lo] == A[lo + 1]){
                            lo++;
                            loNum++;
                        }
                        while(lo < hi && A[hi] == A[hi - 1]){
                            hi--;
                            hiNum++;
                        }
                        res += loNum * hiNum;
                    }
                    lo++;
                    hi--;
                }else if(A[lo] + A[hi] < t){
                    lo++;
                }else{
                    hi--;
                }
                
            }
            result += res;
        }
        return (int) (result % mod);
    }
}