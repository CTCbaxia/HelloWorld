/*
EASY
371. Sum of Two Integers

TIME: 
RESULT: 
NOTES:
求二进制负数
对于正数首相表示 1 
然后除符号位每一位取反，求得反码
然后求得补码（反码末尾 + 1）
这就是负数的表示

I really recommend those who are afraid of doing bitwise problems to have a try of the lab 1 in CMU 15-213, which can be easily found on Google
*/

/*
Bit Manipulation:
按 32 位每个bit计算：add every bit and using a carry
按位运算的话，其实加减都是一样，对于位运算都是相加

Time: O(32)
Space: O(1)
*/
class Solution {
    public int getSum(int a, int b) {
        int carry = 0;
        int res = 0;
        int ai, bi = 0;
        for(int i = 0; i < 32; i++){
            ai = (a >> i) & 1;
            bi = (b >> i) & 1;
            
            //左边res是上一轮结果，(ai ^ bi ^ carry) << i是现在 ith 的结果，按位相加
            res = res | (ai ^ bi ^ carry) << i;
            
            //if 2 or 3 of (ai,bi,carry) is 1, then carry = 1
            carry = (ai & bi) | (ai & carry) | (bi & carry);
        }
        return res;
    }
}