/*
MEDIUM
17. Letter Combinations of a Phone Number

TIME: 
RESULT: 
NOTES:

*/
/*
DFS

Time: O(n)
Space: O(n) -recursive
*/
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits.length() == 0) return result;
        dfs(digits, 0, "", result);
        return result;
    }
    private void dfs(String digits, int index, String prefix, List<String> result){
        if(index == digits.length()){
            result.add(prefix);
            return;
        } 
        String[] strings = {"", "*", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        int n = digits.charAt(index) - '0';
        for(char c : strings[n].toCharArray()){
            dfs(digits, index + 1, prefix + c, result);
        }
        return;
    }
}