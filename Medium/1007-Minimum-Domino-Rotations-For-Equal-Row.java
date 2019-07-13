/*
M
1007. Minimum Domino Rotations For Equal Row
*/
/*
Greedy: A and B shoule be the same as the first number, count how many changes to make all A, and all B
One pass and two counter

Try A[0]
Try B[0]
return -1
One observation is that, if A[0] works, no need to check B[0].
Because if both A[0] and B[0] exist in all dominoes,
the result will be the same.

Time: O(n)
Space: O(1)
*/
class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        int countA = 0, countB = 0, n = A.length;//countA: num of index needs to be changed
        int min = Integer.MAX_VALUE;//[1,6]
        for(int i = 0; i < n; i++){
            if(A[i] != A[0] && B[i] != A[0]) break;//there should be at least one A[0]
            if(A[i] != A[0] && B[i] == A[0]) countA++;//can swap AB to make A all A[0]
            if(B[i] != A[0] && A[i] == A[0]) countB++;
            if(i == n - 1) min = Math.min(countA, countB);//update min when go out of the loop
        }
        countA = 0;
        countB = 0;
        for(int i = 0; i < n; i++){
            if(A[i] != B[0] && B[i] != B[0]) break;
            if(A[i] != B[0] && B[i] == B[0]) countA++;
            if(B[i] != B[0] && A[i] == B[0]) countB++;
            if(i == n - 1) min = Math.min(min, Math.min(countA, countB));
        }
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}

/*
Greedy: A and B shoule be the same as the first number, count how many changes to make all A, and all B
One pass and four counter

The problem is to swap number in A and B to make at least one row be all the same
to make it possible, all num in A should match the first one in A (a), all num in B should match the first in B (b)
if num in A does not match a, see if num in B matches
1) match, countA++
2) not match, mark countA as -1 to tell the furture, A can not be straight

[NOTE!!!]: 
there can be less change to make B straight a, and it is not (A.length - countA) because some index no need to swap
so there are four cases:
A[a,a,a...]
B[b,b,b...]
B[a,a,a...]
A[b,b,b...]

Time: O(n)
Space: O(1)
*/
class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        int countAToA = 0, countBToB = 0, countAToB = 1, countBToA = 1, n = A.length;
        int a = A[0], b = B[0];//2 <= A.length == B.length <= 20000
        for(int i = 1; i < n; i++){
            if(A[i] != a){
                if(B[i] == a && countAToA >= 0) countAToA++;//change to keep A and previous also works
                else countAToA = -1;//impossible to make straight A, mark impossible for later
            } 
            if(B[i] != b){
                if(A[i] == b && countBToB >= 0) countBToB++;
                else countBToB = -1;
            }
            if(B[i] != a){
                if(A[i] == a && countBToA >= 0) countBToA++;
                else countBToA = -1;
            }
            if(A[i] != b){
                if(B[i] == b && countAToB >= 0) countAToB++;
                else countAToB = -1;
            }
        }
        if(countAToA == -1 && countBToB == -1) return -1;
        else if(countAToA == -1) return Math.min(countBToB, countAToB);//can make straight b
        else if(countBToB == -1) return Math.min(countAToA, countBToA);//can make straight a
        else return Math.min(Math.min(countAToA, countBToB), Math.min(countAToB, countBToA));
    }
}
