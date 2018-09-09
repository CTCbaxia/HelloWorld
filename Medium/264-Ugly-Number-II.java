/*
MEDIUM
264. Ugly Number II
https://leetcode.com/problems/ugly-number-ii/description/

TIME: 0909 - 2h
RESULT: 56% - 5ms
NOTES:
1. 如果一种思路不成功，尝试重新思考问题，反着做题看会不会成功

*/

/*
SOLUTION REFERENCE: 
TIME: 0909 - 1h
RESULT: 56% - 5ms

THOUGHTS:
为了防止前后得到重复值（6），用 set 来检验是否和上一个值相同
其实没必要，因为按顺序拍出来的值，如果相同，一定是在同一个循环里面产生的。
因为如果遇到前后相同的值（一定会相邻出现），那么生成它的值一定就在上下两次循环中。
到了下一个循环，只有上次取过的值的指针向前一步，他的乘积值不再可能和上一次相同了。
所以若要相同，肯定是在上一次循环就相同了。
因此可以在同一轮循环里面直接移动值相同的指针。
*/
class Solution {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        
        int p2 = 0, p3 = 0, p5 = 0;
        for(int i = 1; i < n; i++){
            ugly[i] = Math.min(Math.min(ugly[p2] * 2, ugly[p3] * 3), ugly[p5] * 5);
            if(ugly[i] == ugly[p2] * 2) p2++;
            if(ugly[i] == ugly[p3] * 3) p3++;
            if(ugly[i] == ugly[p5] * 5) p5++;
        }
        return ugly[ugly.length - 1];
    }
}
//为了防止前后得到重复值（6），用 set 来检验是否和上一个值相同
//RESULT: 11% - 94ms
class Solution {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        Set<Integer> set = new HashSet<Integer>();
        ugly[0] = 1;
        
        int p2 = 0, p3 = 0, p5 = 0;
        for(int i = 1; i < n; i++){
            ugly[i] = Math.min(Math.min(ugly[p2] * 2, ugly[p3] * 3), ugly[p5] * 5);
            if(set.contains(ugly[i])) i--;
            
            else set.add(ugly[i]);
            if(ugly[i] == ugly[p2] * 2) p2++;
            else if(ugly[i] == ugly[p3] * 3) p3++;
            else if(ugly[i] == ugly[p5] * 5) p5++;
            
        }
        return ugly[ugly.length - 1];
    }
}
/*
SOLUTION 0: 正着算每一个数是不是 uglynumber
TIME: 
RESULT: TLE
*/
class Solution {
    public int nthUglyNumber(int n) {
        Set<Integer> uglyset = new HashSet<Integer>();
        int num = 0;
        while(n > 0){
            int i = ++num;
            for(int k = 2; k < 6; k++){
                while(i % k == 0 && !uglyset.contains(i)){
                    i /= k;
                }
                if(uglyset.contains(i)){
                    uglyset.add(num);
                    break;
                }
            }
            
            if(i == 1) uglyset.add(num);
            if(uglyset.contains(num)) n--;
        }
        return num;
    }
}