/*
MEDIUM
313. Super Ugly Number
https://leetcode.com/problems/super-ugly-number/description/

TIME: 0911 - 40min
RESULT: 8.5% - 254ms
NOTES:
ugly number 都是一个套路
*/
/*
SOLUTION 0: Array + two for loop
TIME: 0911 - 40min
RESULT: 68% - 16ms
*/
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        int[] index = new int[primes.length];
        ugly[0] = 1;
        
        for(int i = 1; i < n; i++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < primes.length; j++){
                min = Math.min(min, primes[j] * ugly[index[j]]);
            }
            for(int j = 0; j < primes.length; j++){
                if(primes[j] * ugly[index[j]] == min) index[j] += 1;
            }
            ugly[i] = min;
        }
        return ugly[n - 1];
    }
}
/*
SOLUTION 0: Array
TIME: 0911 - 40min
RESULT: 49% - 22ms
*/
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        int[] index = new int[primes.length];
        ugly[0] = 1;
        
        for(int i = 1; i < n; i++){
            int min = Integer.MAX_VALUE;
            int index_n = 0;
            for(int j = 0; j < primes.length; j++){
                int val = primes[j] * ugly[index[j]];
                if(min > val){
                    min = val;
                    index_n = j;
                }
            }
            index[index_n] += 1;
            if(ugly[i - 1] != min) ugly[i] = min;
            else i--;
        }
        return ugly[n - 1];
    }
}
/*
SOLUTION 0: Map
TIME: 0911 - 40min
RESULT: 8.5% - 254ms
*/

class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] res = new int[n];
        res[0] = 1;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();//<the prime, its index>
        
        for(int i = 0; i < primes.length; i++) map.put(primes[i], 0);
        
        for(int i = 1; i < n; i++){
            int min = Integer.MAX_VALUE;
            int index = i;
            for(int j = 0; j < primes.length; j++){
                int tmp = primes[j] * res[map.get(primes[j])];
                if(min > tmp){
                    min = tmp;
                    index = j;
                }
            }
            if(res[i - 1] != min) res[i] = min;
            else i--;
            
            map.put(primes[index], map.get(primes[index]) + 1);
            
        }
        return res[n - 1];
    }
}