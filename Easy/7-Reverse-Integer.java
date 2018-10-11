/*
Solution 1

Note:
递归方式
对于一个type进行运算，得出的结果也会遵循type的limit（即使还没有赋值给int type的变量）
所以 preresult * 10 + tail 这个运算的结果如果溢出了，得到的值已经是溢出之后的值了
再赋给newresult的时候，等号左右两边完全相等
所以用newresult!=preresult*10+tail得到的结果一定是false的
但如果倒过来，溢出的newresult是不能推回到preresult 的
所以preresult != (newresult - tail) / 10的值一定是true的
 
负数取余运算:
http://liujinpan75.iteye.com/blog/548625


*/
class Solution {
    public int reverse(int x) {
        int res = 0;
        int sign = 1;
        if(x < 0){
            if(x == Integer.MIN_VALUE) return 0;
            sign = -1;
            x = -x;
        }
        while(x != 0){
            int digit = x % 10;
            if(res > Integer.MAX_VALUE / 10) return 0;
            else res = res * 10 + digit;
            x /= 10;
        }
        return res * sign;
    }
}


//如果溢出，值一定对不上
class Solution {
    public int reverse(int x) {
        int preresult = 0;
        int newresult = 0;
        while (x != 0){
            int tail = x % 10;
            newresult = preresult * 10 + tail;
            if(preresult != (newresult - tail) / 10){
                return 0;
            }else{
                preresult = newresult;
            }
            x = x / 10;
        }
        return newresult;
    }
}

/*
Input: 1534236469
Output:0
Expected: 0
*/



/*
Wrong Code
*/
public class Solution {
    public int Reverse(int x) {
        int preresult = 0;
        int newresult = 0;
        while (x!= 0){
            int tail = x%10;
            newresult =preresult*10+tail;
            
            if(newresult!=preresult*10+tail){
                return 0;
            }else{
                preresult=newresult;
            }
            x=x/10;
        }
        return newresult;
    }
}


/*
Input: 1534236469
Output:1056389759
Expected: 0

*/




/*
Solution 2: 直接利用 Integer.MAX_VALUE / Integer.MIN_VALUE 的值判断

Note:
在需要 *10 之前运算看是否大于 Integer.MAX_VALUE / 10，若大于，则 * 10 + tail之后必然会溢出
还要考虑临界情况，Integer.MAX_VALUE=2147483647
有没有可能在 result > Integer.MAX_VALUE / 10 的时候ok，但是加上tail之后就溢出了呢？
如果result=214748364; tail=8
那么result * 10 + tail就溢出了。
然而这种情况不可能出现，因为反过来这个数在最开始的时候就不会存在
所以除非最后的tail>7，新的result才会溢出；而反过来，对于同样的十位数，最后的tail（原始值的首位）只可能<=2
*/



class Solution {
    public int reverse(int x) {
        int result = 0;

        while (x != 0){
            int tail = x % 10;
            if(result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10){
                return 0;
            }
            result = result * 10 + tail;   
            x = x / 10;
        }
        return result;
    }
}


/*
别人家的code

*/
class Solution {
    public int reverse(int x)
    {
        int result = 0;

        while (x != 0)
        {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result)
            { return 0; }
            result = newResult;
            x = x / 10;
        }

        return result;
    }
}


