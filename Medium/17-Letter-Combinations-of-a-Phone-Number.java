/*
MEDIUM
17. Letter Combinations of a Phone Number

TIME: 
RESULT: 
NOTES:

*/
/*
DFS (backtracking) for Path

Time: O(n) only 3-4 letters for each n
Space: O(n) - path
*/
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits.length() == 0) return res;
        
        String[] numbers = new String[]{"", "", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        dfs(digits, 0, "", res, numbers);
        return res;
    }
    private void dfs(String digits, int start, String path, List<String> res, String[] numbers){
        if(start == digits.length()){
            res.add(path);
            return;
        }
        int d = digits.charAt(start) - '0';
        for(char c : numbers[d].toCharArray()){
            dfs(digits, start + 1, path + c, res, numbers);
        }
    }
}






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