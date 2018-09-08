/*
MEDIUM
60. Permutation Sequence
https://leetcode.com/problems/permutation-sequence/description/

TIME: 0908 - 4h
RESULT: 95% - 9ms
NOTES:
这题注意除余思维。要把变量 k 直接 k - 1，这样就不会涉及到切分 0 的情况了
*/
/*
SOLUTION REFERENCE:

TIME: 0908 - 15min
RESULT: 95% - 9ms
*/
class Solution {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n + 1];
        List<String> num = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();   
        
        int product = 1;
        factorial[0] = 1;
        for(int i = 1; i <= n; i++){
            product *= i;
            factorial[i] = product;
        }
        for(int i = 1; i <= n; i++){
            num.add(String.valueOf(i));
        }
        k--;//important! 整体 - 1， 这样第一梯队的 index 就都会落在第一梯队了
        for(int i = 1; i <= n; i++){
            int index = k / factorial[n - i];
            sb.append(num.get(index));
            num.remove(index);
            k = k - index * factorial[n - i];
        }
        return sb.toString();
        
    }

}

/*
SOLUTION 1:
TIME: 0908 - 2h
RESULT: 28% - 14ms
*/

class Solution {
    public String getPermutation(int n, int k) {
        List<String> res = new ArrayList<String>();
        permuSeq(n, k, res);
        StringBuilder sb = new StringBuilder();
        for(String s : res) sb.append(s);
        return sb.toString();
    }
    private void permuSeq(int n, int k, List<String> res){
        if(res.size() == n) return;
        
        int num = n - res.size();
        int mul = 1;
        while(--num > 0) mul *= num;
        
        int index = (k % mul == 0) ? k / mul - 1 : k / mul;//模块号
        int i = 0; 
        System.out.println("k:" + k +"   num:" + num + "   mul:" + mul + "  index:" + index);
        while(index >= 0 && i <= n){
            i++;
            if(!res.contains(String.valueOf(i))) index--;
        }
        res.add(String.valueOf(i));
        permuSeq(n, (k % mul == 0) ? mul : k % mul, res);
        return;
        
    }
}

/*
SOLUTION 0:
TIME: 0908 - 20min
RESULT: TLE
*/

class Solution {
    public String getPermutation(int n, int k) {
        List<String> res = new ArrayList<String>();
        Set<Integer> visited = new HashSet<Integer>();
        permuSeq(n, k, "", res, visited);
        return res.get(k - 1);
    }
    private void permuSeq(int n, int k, String pre, List<String> res, Set<Integer> visited){
        if(pre.length() == n){
            res.add(pre);
            return;
        }
        
        int i = 1;
        while(res.size() < k && i <= n){
            if(!visited.contains(i)){
                visited.add(i);
                permuSeq(n, k, pre + String.valueOf(i), res, visited);
                visited.remove(i);
            }
            i++;
        }
        return;
    }
}