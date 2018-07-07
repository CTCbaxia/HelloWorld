/*
47. Permutations II
https://leetcode.com/problems/permutations-ii/description/

TIME: 0707

*/
public class Solution {
    public List<List<Integer>> permuteUnique(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> current = new ArrayList<Integer>();
        boolean[] visited = new boolean[num.length];
        permute(result, current, num, visited);
        return result;
    }
    
    private void permute(List<List<Integer>> result, List<Integer> current, int[] num, boolean[] visited) {
        if (current.size() == num.length) {
            result.add(new ArrayList<Integer>(current));
            return;
        }
        for (int i=0; i<visited.length; i++) {
            if (!visited[i]) {
                if (i > 0 && num[i] == num[i-1] && visited[i-1]) {
                    return;
                }
                visited[i] = true;
                current.add(num[i]);
                permute(result, current, num, visited);
                current.remove(current.size()-1);
                visited[i] = false;
            }
        }
    }
}