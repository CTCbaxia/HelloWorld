/*
MEDIUM
556. Next Greater Element III
https://leetcode.com/problems/next-greater-element-iii/description/

TIME: 0808 - 1h
RESULT: 99% - 2ms
NOTES: 
1. 注意大小 Integer.MAX_VALUE
*/
/*
类似于 next permutation：
找到drop，swap，对于剩下的 reverse
这题需要：
1. 先变成array
2. 最后用 long 先存

Time: O(1)
Space: O(1)
*/
class Solution {
    public int nextGreaterElement(int n) {
        char[] ch = String.valueOf(n).toCharArray();
        
        //find the drop
        int i, j;
        for(i = ch.length - 2; i >= 0; i--){
            if(ch[i] < ch[i + 1]) break;
        }
        if(i < 0) return -1;//no such drop find
        
        //swap, there are only no more than 10 digits, so just loop
        for(j = ch.length - 1; j > i; j--){
            if(ch[j] > ch[i]){
                swap(ch, i, j);
                break;
            }
        }
        //reverse the later chars
        int lo = i + 1, hi = ch.length - 1;
        while(lo < hi){
            swap(ch, lo++, hi--);
        }
        
        //parse to long
        long val = Long.parseLong(new String(ch));
        return val > Integer.MAX_VALUE ? -1 : (int) val;
    }
    private void swap(char[] ch, int i, int j){
        char tmp = ch[j];
        ch[j] = ch[i];
        ch[i] = tmp;
        return;
    }
}









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