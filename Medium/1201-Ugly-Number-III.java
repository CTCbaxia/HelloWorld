/*
M
1201. Ugly Number III
*/

/*
Binary Search
https://leetcode.com/problems/ugly-number-iii/discuss/387780/Java-Binary-Search-Venn-Diagram-Explain-Math-Formula

for num, how many numbers from 1 to num, can be divided by a
num/a
ex:
4/2 = 2 -- (2,4)
8/2 = 4 -- (2,4,6,8)
12/2 = 6 -- (2,4,6,8,10,12)

12/3 = 4 -- (3,6,9,12)
the number of element from 1 to 12 can be divided by 2 or 3 is
12/2 + 12/3 - 12/lcm(2,3)

How to get lowest common multiple?
a * b / gcd(a, b)

How to get greatest common divider
if a == 0, return b;
else return (b % a, a) 不断的约

Time: O(log 10^9)
Space: O(1)
*/
class Solution {
    public int nthUglyNumber(int n, int a, int b, int c) {
        //反复使用的值直接复用
        long ab = lcm(a,b);
        long ac = lcm(a,c);
        long bc = lcm(b,c);
        long abc = lcm(a, lcm(b,c));
        
        int l = 1, r = 2 * 1000000000;
        while(l < r){//largerOrEqual
            int m = l + (r - l)/2;
            int count = (int) (m/a + m/b + m/c - m/ab - m/bc - m/ac + m/abc);
            
            //m 能使得 count == n 并不意味着答案就是 m，这只是说明 1 - m 中有 n 个
            //但是 m 有可能无法除任何数，而是一个小于 m 的数达成这个目的
            //所以需要找 largerOrEqual，这样是不断往左边 hold
            if(count < n) l = m + 1;
            else r = m;
        }
        return r;
    }
    private long gcd(long a, long b){
        if(a == 0) return b;
        return gcd(b % a, a);
    }
    private long lcm(long a, long b){
        return a * b / gcd(a, b);
    }
}


//因为 lcm(a,b)，lcm(b,c)，lcm(a,c)会反复使用，可以算出来复用
class Solution {
    public int nthUglyNumber(int n, int a, int b, int c) {
        int l = 1, r = 2 * 1000000000;
        while(l < r){//largerOrEqual
            int m = l + (r - l)/2;
            int count = (int) (m/a + m/b + m/c
                - m/lcm(a,b) - m/lcm(b,c) - m/lcm(a,c) + m/lcm(a, lcm(b,c)));
            
            //m 能使得 count == n 并不意味着答案就是 m，这只是说明 1 - m 中有 n 个
            //但是 m 有可能无法除任何数，而是一个小于 m 的数达成这个目的
            //所以需要找 largerOrEqual，这样是不断往左边 hold
            if(count < n) l = m + 1;
            else r = m;
        }
        return r;
    }
    private long gcd(long a, long b){
        if(a == 0) return b;
        return gcd(b % a, a);
    }
    private long lcm(long a, long b){
        return a * b / gcd(a, b);
    }
}