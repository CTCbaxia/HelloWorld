/*
HARD
282. Expression Add Operators

TIME: 
RESULT: 
*/
/*
DFS: 重要的是怎么处理 * --每次存储上一轮的数字

1. overflow: we use a long type.
2. leading zero.
3. we should save the current value that might be multiplied in the next recursion.
重点是存下当前的 curN, 以备下次 recursion 的时候乘法使用

Time: O(n * 3^n)
Space: O(1)
*/
class Solution {
    public List<String> addOperators(String num, int target){
        List<String> result = new ArrayList<>();
        dfs(num, target, 0, "", 0, 0, result);
        return result;
    }                                
    private void dfs(String num, int target, int start, String path, long val, long preVal, List<String> result){
        if(start == num.length()){//at the end of the num string
            if(val == target) result.add(path);
            return;
        }
        for(int i = start; i < num.length(); i++){
            if(num.charAt(start) == '0' && i != start) break;//deal with leading 0

            String curS = num.substring(start, i + 1);
            long curVal = Long.parseLong(curS);

            if(start == 0){
                dfs(num, target, i + 1, curS, curVal, curVal, result);//初始化
            }else{
                //add
                dfs(num, target, i + 1, path + "+" + curS, val + curVal, curVal, result);
                //subtract
                dfs(num, target, i + 1, path + "-" + curS, val - curVal, -curVal, result);
                //multiply -- 先减去上一个值，然后加上 上一个值 * 当前值
                dfs(num, target, i + 1, path + "*" + curS, val - preVal + preVal * curVal, preVal * curVal, result);
            }
        }
        return;
    }   
    
}






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