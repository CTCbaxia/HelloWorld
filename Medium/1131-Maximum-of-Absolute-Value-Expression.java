/*
M
1131. Maximum of Absolute Value Expression
*/
/*

当 O(n^2) 明显不是结果的时候，考虑预处理
1) 绝对值所以只需要考虑 i < j 的情况

思路：
按理说应该所有 ij 都需要运算一遍，但是还需要更快，就考虑
1) 预处理的时候减少了一些不必要的运算
2) 对于要求的结果找到可以利用的 pattern

比如排序？但是因为引入了 i j 的index，排序会丢失，所以不考虑
比如dp？因为需要遍历所有 ij，并且每个只需要遍历一遍，没有重复值，所以不考虑dp

有没有办法 O(n) 就得到想要的结果，找到可以利用的 pattern
看到绝对值就拆绝对值，考虑一共有哪些情况
|A[i] - A[j]| = A[i] - A[j] or A[j] - A[i]
|B[i] - B[j]| = B[i] - B[j] or B[j] - B[i]
|i - j| = j - i
组合出来就四种情况
(A[i] + B[i] - i) - (A[j] + B[j] - j)
(A[i] - B[i] - i) - (A[j] - B[j] - j)
(A[j] - B[j] + j) - (A[i] - B[i] + i)
(A[j] + B[j] + j) - (A[i] + B[i] + i)
只需要算这四个最大值里面的最大值就好，变成了一维遍历 AB

Time: O(n)
Space: O(1)
*/
class Solution {
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int maxPlus1, maxPlus2, maxMinus1, maxMinus2;
        maxPlus1 = maxPlus2 = maxMinus1 = maxMinus2 = Integer.MIN_VALUE;
        int minPlus1, minPlus2, minMinus1, minMinus2;
        minPlus1 = minPlus2 = minMinus1 = minMinus2 = Integer.MAX_VALUE;
        
        for(int i = 0; i < n; i++){
            int plus1 = arr1[i] + arr2[i] + i;
            maxPlus1 = Math.max(maxPlus1, plus1);
            minPlus1 = Math.min(minPlus1, plus1);
            
            int plus2 = arr1[i] + arr2[i] - i;
            maxPlus2 = Math.max(maxPlus2, plus2);
            minPlus2 = Math.min(minPlus2, plus2);
            
            int minus1 = arr1[i] - arr2[i] + i;
            maxMinus1 = Math.max(maxMinus1, minus1);
            minMinus1 = Math.min(minMinus1, minus1);
            
            int minus2 = arr1[i] - arr2[i] - i;
            maxMinus2 = Math.max(maxMinus2, minus2);
            minMinus2 = Math.min(minMinus2, minus2);
        }
        
        return Math.max(Math.max(maxPlus1 - minPlus1, maxPlus2 - minPlus2), Math.max(maxMinus1 - minMinus1, maxMinus2 - minMinus2));
    }
}


/*
❌
没有考虑到 i，j 的加入会影响 max min 的值

Time: O(n)
Space: O(1)
*/
class Solution {
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int maxPlus, maxMinus, minPlus, minMinus;
        maxPlus = maxMinus = Integer.MIN_VALUE;
        minPlus = minMinus = Integer.MAX_VALUE;
        
        int maxPlusIndex, maxMinusIndex, minPlusIndex, minMinusIndex;
        maxPlusIndex = maxMinusIndex = minPlusIndex = minMinusIndex = 0;
        
        for(int i = 0; i < n; i++){
            int plus = arr1[i] + arr2[i];
            int minus = arr1[i] - arr2[i];
            
            if(plus > maxPlus){
                maxPlus = plus;
                maxPlusIndex = i;
            }
            if(plus < minPlus){
                minPlus = plus;
                minPlusIndex = i;
            }
            if(minus > maxMinus){
                maxMinus = plus;
                maxMinusIndex = i;
            }
            if(minus < minMinus){
                minMinus = minus;
                minMinusIndex = i;
            }
        }
        
        int res = Integer.MIN_VALUE;
        if(arr1[maxPlusIndex] >= arr1[minPlusIndex] && arr2[maxPlusIndex] >= arr2[minPlusIndex]){
            res = Math.max(res, (maxPlus - maxPlusIndex) - (minPlus - minPlusIndex));
        } 
        if(arr1[maxMinusIndex] >= arr1[minMinusIndex]  && arr2[minMinusIndex] > arr2[maxMinusIndex]){
            res = Math.max(res, (maxMinus - maxMinusIndex) - (minMinus - minMinusIndex));
        } 
        if(arr1[maxMinusIndex] > arr1[minMinusIndex] && arr2[minMinusIndex] >= arr2[maxMinusIndex]){
            res = Math.max(res, (maxMinus + maxMinusIndex) - (minMinus + minMinusIndex));
        } 
        if(arr1[maxPlusIndex] > arr1[minPlusIndex] && arr2[minMinusIndex] >= arr2[maxMinusIndex]){
            res = Math.max(res, (maxPlus + maxPlusIndex) - (minPlus + minPlusIndex));
        } 

        return res;
    }
}
