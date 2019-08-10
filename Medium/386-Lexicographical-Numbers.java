/*
Recursion
Duration: 40 min

这个 input 大小，肯定不能 n^2 以上，那么只能 nlogn 以及一下。但是 nlogn 涉及排序，又会跟数字大小有关，最优解应该是 n

一开始的想法是分为 add 0 和 add 1 的
1 -(add 0) - 10 - (add 0) - 100
                              | add 1
                             101, 102, 103 .. 109
             11 - (add 0) - 110                
                              | add 1
                             111, 112, 113 .. 119                             

后来不知道为什么突然想到了 recursion，其实就是每次加一个数之前，都check一下他们的高一个数量级之间，有没有可以插入的数

Time: O(n)
Space: O(1)

*/
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        if(n < 1) return res;
        int start = 1;
        int end = n > 9 ? 10 : n + 1;//end is exclusive
        addAll(start, end, n, res);
        return res;
    }
    private void addAll(int start, int end, int n, List<Integer> res){
        for(int i = start; i < end && i <= n; i++){
            res.add(i);
            addAll(i * 10, (i + 1) * 10, n, res);
        }
    }
}
