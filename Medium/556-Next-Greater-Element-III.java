/*
MEDIUM
556. Next Greater Element III
https://leetcode.com/problems/next-greater-element-iii/description/

TIME: 0808 - 1h
RESULT: 99% - 2ms
NOTES: 
1. 注意大小 Integer.MAX_VALUE
*/
class Solution {
    public int nextGreaterElement(int n) {
        if(n < 10) return -1;
        List<Integer> list = new ArrayList<Integer>();
        while(n > 10){
            int tail = n % 10;
            list.add(tail);
            n /= 10;
            
            if(n % 10 < tail){
                for(int i = 0; i < list.size(); i++){//replace the rightmost of the current n with a little bit larger digit in the list
                    if(list.get(i) > n % 10){
                        int tmp = list.get(i);
                        list.set(i, n % 10);
                        n = n / 10 * 10 + tmp;
                        break;
                    }
                }

                for(int i = 0; i < list.size(); i++){//ascending order
                    n = n * 10 + list.get(i);
                    if(i < list.size() - 1 && (n > Integer.MAX_VALUE / 10 || (n == Integer.MAX_VALUE && list.get(i + 1) >= 8)))
                        return -1;
                }
                break;
            }
        }
        if(n > 10) return n;
        else return -1;
        
    }
}