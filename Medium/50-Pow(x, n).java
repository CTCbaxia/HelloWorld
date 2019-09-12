/*
MEDIUM
50. Pow(x, n)
https://leetcode.com/problems/powx-n/description/

TIME: 0902 - 20min
RESULT: 39% - 13ms
*/
/*
Recursion

**tips:
任何数的 0 次方都等于 0

Time: O(logn)
Space: O(logn)
*/
class Solution {
    public double myPow(double x, int n) {
        // if(x == 0) return x;
        if(n < 0){
            return 1/x * myPow(1/x, -(n + 1));//for Integer.MIN_VALUE;
        } 
        if(n == 0) return 1;
        
        //here n >= 0
        if(n % 2 == 0){
            double half = myPow(x, n >> 1);
            return half * half;
        }else{
            return x * myPow(x, n - 1);
        }
    }
}


/*
Iteration

**tips:
任何数的 0 次方都等于 0

Time: O(logn)
Space: O(logn)
*/
class Solution {
    public double myPow(double x, int n) {
        if(n == 0) return 1;
        double res = 1;
        if(n < 0){
            x = 1/x;
            n = -(n + 1);
            res *= x;//current x 分数
        } 
        //x >= 0, n > 0
        while(n > 0){
            //here n >= 0
            if(n % 2 == 0){
                x = x * x;
                n = n >> 1;
            }else{
                res *= x;//will always arrive here
                n = n - 1;
            }
        }
        return res;
    }
}



/*
FB 变种：If we cannot use * or /
works only for x >= 0 and n >= 0

For example to calculate 5^6.
1) First 5 times add 5, we get 25. (5^2)
2) Then 5 times add 25, we get 125. (5^3)
3) Then 5 time add 125, we get 625 (5^4)
4) Then 5 times add 625, we get 3125 (5^5)
5) Then 5 times add 3125, we get 15625 (5^6)

**tips:
任何数的 0 次方都等于 0

Time: O(logn)
Space: O(logn)
*/
class Solution {
    public double myPow(double x, int n) {
        //for x >= 0 and n >= 0
        if(n == 0) return 1;
        double answer = x, increment = x;
        for(int i = 1; i < n; i++){
            for(int j = 1; j < x; j++){
                answer += increment;
            }
            increment = answer;
        }
        return answer;
    }
}



//Internship =======================================

class Solution {
    public double myPow(double x, int n) {
        if(n == 0) return 1;
        if(n > 0){
            //odd
            if(n % 2 != 0) return x * myPow(x * x, (n - 1) / 2);
            //even
            else return myPow(x * x, n / 2); 
        }else{
            return 1/x * myPow(1/x, -(n + 1));//1.00000, -2147483648
        }

    }
}


/*
Divide and Conquer

1. be careful for x == 0 and n < 0 --> 1/x
2. be careful for n < 0 : deal with it only when we use x
Time: O(logn)  T(n) = T(n/2)
Space: O(1)
NOTES:
1. 对于 int，当要取负运算的时候注意边界
2. 要学会减少复杂的子运算，用更简单的表达式代替（能储存子运算的结果，在后面调用的时候就不要重复进行该运算了）：return tmp * tmp * x; 而不是 myPow(x, n/2) * myPow(x, n/2 + 1)

*/
class Solution {
    public double myPow(double x, int n) {
        if(x == 0) return 0;//be careful for 1/x
        if(n == 0) return 1;
        
        double num = myPow(x, n/2);
        if(n % 2 == 0){
            return num * num;
        }else{
            return (n > 0 ? x : 1/x) * (num * num);//only here we have x, so cover negative sign here
        }
    }
}




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

