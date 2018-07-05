/*
0704

190. Reverse Bits
https://leetcode.com/problems/reverse-bits/description/


*/

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        int lastnum = 0;

        for( int i = 0; i < 32; i ++){

            lastnum = n & 1;
            n >>> 1;

            result = result << 1;
            result = result | lastnum;
            
        }

    }
}