/*
H
446. Arithmetic Slices II - Subsequence
*/
/*
T(i, d): for index i, with difference d, how many we have now
i: the index
d: the difference

dp[i,d] = dp[j,d] + 1 (for all j)
compute for all 

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int res = 0;
        Map<Integer, Integer>[] map = new HashMap[A.length];
        for(int i = 0; i < A.length; i++){
            map[i] = new HashMap<>();
            for(int j = 0; j < i; j++){
                long diff = (long) A[i] - A[j];
                if(diff >= Integer.MAX_VALUE || diff <= Integer.MIN_VALUE) continue;
                
                int d = (int) diff;
                int count1 = map[j].getOrDefault(d, 0);
                res += count1;//当之前没有d，只+0
                
                int count2 = map[i].getOrDefault(d, 0);
                map[i].put(d, count2 + count1 + 1);//这里计算到 i 这个点，d 的所有可能性，如果 count 1 = 0，那么下一次的 count1 就有值了（1）
            }
        }
        return res;
    }
}