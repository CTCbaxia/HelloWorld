/*
MEDIUM
50. Pow(x, n)
https://leetcode.com/problems/powx-n/description/

TIME: 0902 - 20min
RESULT: 39% - 13ms
NOTES:
1. 对于 int，当要取负运算的时候注意边界
2. 要学会减少复杂的子运算，用更简单的表达式代替（能储存子运算的结果，在后面调用的时候就不要重复进行该运算了）：return tmp * tmp * x; 而不是 myPow(x, n/2) * myPow(x, n/2 + 1)
*/

/*
SOLUTION REFERENCE: 二分法
TIME: 0902 - 5min
RESULT: 39% - 13ms

*/
class Solution {
    public double myPow(double x, int n) {
        if(n == 0) return 1;
        if(n == 1) return x;
        //一开始就把问题标准化（后面默认按 n > 0 计算），但是要注意边界
        if(n < 0) return 1 / (x * myPow(x, -(n + 1))); //不能写为 if(n < 0) return 1 / myPow(x, -n); 因为 n = -2147483648 的时候溢出
        
        double tmp = myPow(x, n/2);
        if(n % 2 == 0){
            return tmp * tmp;
        }else{
            return tmp * tmp * x;
        }
    }
}

/*
SOLUTION 0: 二分法
TIME: 0902 - 20min
RESULT: 39% - 13ms
*/


class Solution {
    public double myPow(double x, int n) {
        if(n == 0) return 1;
        if(n == 1) return x;
        if(n == -1) return 1/x;
        
        if(n % 2 == 0){
            double tmp = myPow(x, n/2);
            return tmp * tmp;
        }else{
            double tmp = myPow(x, n/2);
            double tmp2 = (n > 0) ? myPow(x, n/2 + 1) : myPow(x, n/2 - 1) ;//要学会减少子运算
            return tmp * tmp2;
        }
    }
}

