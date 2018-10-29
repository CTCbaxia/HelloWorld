/*
MEDIUM
29. Divide Two Integers
https://leetcode.com/problems/divide-two-integers/description/

TIME: 0902 - 1h
RESULT: 24% - 30ms
NOTES:
1. 	Integer.MAX_VALUE: 2147483647
	Integer.MIN_VALUE: -2147483648
2. Math.abs(Integer.MIN_VALUE) = -2147483648
3. Integer.MIN_VALUE - 正整数 = 2147483648 - 正整数 
4. 这题需要注意 dividend 和 divisor 分别为 Integer.MIN_VALUE 的时候

*/
class Solution {
    public int divide(int dividend, int divisor) {
        int n1 = 0;//divisor result
        int n2 = 0;//remainder
        int sign = 1;
        
        if(dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) sign = -1;
        
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        
        //corner case:
        if(divisor == Integer.MIN_VALUE){//Math.abs(Integer.MIN_VALUE) = -2147483648
            if(dividend == Integer.MIN_VALUE){
                return 1;//remainder = 0
            }else{
                return 0;//remainder = divident
            }
        }
        //another corner case:
        if(dividend == Integer.MIN_VALUE){//Math.abs(Integer.MIN_VALUE) = -2147483648
            if(divisor == 1) return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;//remainder = 0
            else{
                dividend -= divisor; //Integer.MIN_VALUE - 正整数 = 2147483648 - 正整数 
                n1++;
            }
        }

        //main solution
        while(divisor <= dividend){
            int tmp = divisor;
            int count = 1;
            while(tmp <= Integer.MAX_VALUE >> 1 && (tmp << 1) <= dividend){//make sure tmp will not overflow
                tmp = tmp << 1;
                count = count << 1;
             }
            dividend -= tmp;
            n1 += count;
        }
        n2 = dividend;
        
        // System.out.println(n1); 
        // System.out.println(n2); //remainder
        return sign == 1 ? n1 : -n1;
    }

}





/*
SOLUTION REFERENCE: 位运算
TIME: 0902 - 1h
RESULT: 24% - 30ms
*/
class Solution {
    public int divide(int dividend, int divisor) {
        
        int sign = 1;
        if(dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) sign = -1;
        
        if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if(divisor == Integer.MIN_VALUE){
            if(dividend == Integer.MIN_VALUE) return 1;
            else return 0;
        }
        int absDividend = Math.abs(dividend);
        int absDivisor = Math.abs(divisor);
        int count = 0;
        if(dividend == Integer.MIN_VALUE){
            absDividend = absDividend - absDivisor;
            count = 1;
        }
        while(absDividend >= absDivisor){
            int tmp = absDivisor;
            int multip = 1;
            while((tmp << 1) < absDividend && tmp <= 1073741823){
                tmp = tmp << 1;
                multip = multip << 1;
            }
            absDividend -= tmp;
            count += multip;
            
        }
        return (sign == -1) ? -count : count;
    }

}

/*
SOLUTION REFERENCE: 两倍二分
TIME: 0902 - 1h
RESULT: 28% - 29ms
*/
class Solution {
    public int divide(int dividend, int divisor) {
        
        int sign = 1;
        if(dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) sign = -1;
        
        if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if(divisor == Integer.MIN_VALUE){
            if(dividend == Integer.MIN_VALUE) return 1;
            else return 0;
        }
        return (sign == -1) ? -sub(Math.abs(dividend), Math.abs(divisor), 0) : sub(Math.abs(dividend), Math.abs(divisor), 0);
    }
    private int sub(int absDividend, int absDivisor, int count){
        int tmp = absDivisor;
        int tmpCount = 1;
        while ((tmp + tmp) < absDividend && tmp <= 1073741823 ){// 2147483647 的一半
            tmp += tmp;
            tmpCount += tmpCount;
        }
        
        int res = absDividend - tmp;
        if(res >= 0) 
            return sub(res, absDivisor, count + tmpCount);
        else
            return count;
    }
}
/*
SOLUTION REFERENCE: 十倍（不符合题目要求）
TIME: 0902 - 1h
RESULT: 97% - 21ms
*/
class Solution {
    public int divide(int dividend, int divisor) {
        int sign = 1;
        if(dividend < 0) sign = -1;
        if(divisor < 0) sign *=-1;
        
        //这一句必要，因为进入 sub 之后，得到的结果为 2147483647 + 1 = -2147483648。而 divisor = -1 的期待结果为 2147483647；divisor = 1 的期待结果为 -2147483648
        //而当 divisor 为非 -1 的其他数时，sub 的结果都会小于 2147483647
        if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        //这一句，如果 divisor == Integer.MIN_VALUE， 那么除了除数为他本身，其他所有 int 的结果均为 0. 在这里限制是为了不要进入 sub 然后形成减法混乱（因为 Math.abs(Integer.MIN_VALUE) = -2147483648）
        if(divisor == Integer.MIN_VALUE){
            if(dividend == Integer.MIN_VALUE) return 1;
            else return 0;
        }

        return sign * sub(Math.abs(dividend), Math.abs(divisor), 0);
    }
    private int sub(int absDividend, int absDivisor, int count){

        int tmp = absDivisor;
        int tmpCount = 1;
        while (tmp < Integer.MAX_VALUE / 10 && absDividend > tmp * 10){
            tmp = tmp * 10;
            tmpCount = tmpCount * 10;
        }

        int res = absDividend - tmp;
        if(res >= 0) 
            return sub(res, absDivisor, count + tmpCount);
        else
            return count;
    }
}