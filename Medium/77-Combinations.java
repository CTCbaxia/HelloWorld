/*
MEDIUM
77. Combinations

NOTES:
Time： 结果数 * 得到每个结果需要的操作数 = C(n,k) * k
Space:
*/
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        combineHelper(1, n, k, new ArrayList<Integer>(), result);
        return result;
    }
    public void combineHelper(int index, int n, int remaining, List<Integer> list, List<List<Integer>> result){
        if(remaining == 0) result.add(new ArrayList<Integer>(list));
        for(int i = index; i <= n; i++){
            list.add(i);
            combineHelper(i + 1, n, remaining - 1, list, result);
            list.remove(list.size() - 1);
        }
        return;
    }
}