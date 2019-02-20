/*
EASY
191. Number of 1 Bits
1. >>> 无符号移动
2. >> 有符号移动

当移位的数是负数的时候，
1) >>将二进制高位用1补上，
2) >>>将二进制高位用0补上，
这就导致了>>>将负数的移位操作结果变成了正数（因为高位用0补上了）


*/
/*
Bit Manipulation

Time: O(1)
Space: O(1)
*/
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int num = 0;
        while(n != 0){//这里可能是负数，只能检测是否为 0 
            if((n & 1) == 1) num++;
            n = n >>> 1;
        }
        return num;
    }
}


/*
Bit Manipulation

Time: O(1)
Space: O(1)
*/
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int num = 0;
        for(int i = 0; i < 32; i++){//这里只比较 32 位，所以首位的 1 或者变换后 n 的大小没影响
            if((n & 1) == 1) num++;
            n = n >> 1;
            
            if(n == 0) return num;
        }
        return num;
    }
}


/*
Bit Manipulation -- 麻烦一些

Time: O(1)
Space: O(1)
*/
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for(int i = 0; i < 32; i++){
            if(((n >> i) & 1) == 1) count++;
        }
        return count;  
    }
}