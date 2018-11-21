/*
HARD
282. Expression Add Operators

TIME: 
RESULT: 
*/
/*
DFS:

1. overflow: we use a long type.
2. leading zero.
3. we should save the current value that might be multiplied in the next recursion.
重点是存下当前的 curN, 以备下次 recursion 的时候乘法使用

Time: O(n * 3^n)
Space: O(1)
*/
class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(num, 0, target, "", 0, 0, res);
        return res;
    }
    private void dfs(String num, int start, int target, String path, long val, long mul, List<String> res){
        if(start == num.length()){//at the end of the num string
            if(val == target) res.add(path);
            return;
        }
        
        for(int i = start; i < num.length(); i++){
            if(i != start && num.charAt(start) == '0') break;//deal with leading 0
            
            String curS = num.substring(start, i + 1);
            long curN = Long.parseLong(curS);
            
            if(start == 0){
                dfs(num, i + 1, target, path + curS, val + curN, curN, res);
            }else{
                //add
                dfs(num, i + 1, target, path + "+" + curS, val + curN, curN, res);
                //subtract
                dfs(num, i + 1, target, path + "-" + curS, val - curN, -curN, res);
                //muple -- 先减去上一个值，然后加上 上一个值 * 当前值
                dfs(num, i + 1, target, path + "*" + curS, val - mul + mul * curN, mul * curN, res);                
            }
        }
        return;
    }
    
}