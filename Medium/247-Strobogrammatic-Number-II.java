/*
MEDIUM
247. Strobogrammatic Number II

TIME: 
RESULT: 64% - 11ms
NOTES:

*/
/*
Iterative + BFS

Time: O(5^n)
Space: O(5^n)
RESULT: 64% - 11ms
*/
class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> result = new ArrayList<String>();
        List<String> odd = new ArrayList<String>(Arrays.asList("0", "1", "8"));
        
        if(n % 2 != 0) result = odd;
        else result.add("");
        
        //for every digits, kind of like bfs
        for(int i = 0; i < n/2; i++){
            List<String> newR = new ArrayList<String>();
            for(String s : result){
                if(i != n/2 - 1)
                    newR.add("0" + s + "0");
                newR.add("1" + s + "1");
                newR.add("6" + s + "9");
                newR.add("8" + s + "8");
                newR.add("9" + s + "6");
            }
            result = newR;
        }
        return result;
    }

}


class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> result = new ArrayList<String>();
        dfs(0, n - 1, new StringBuilder(), result);
        return result;
        
    }
    private void dfs(int lo, int hi, StringBuilder sb, List<String> res){
        if(lo > hi) res.add(sb.toString());
        else if(lo == hi){
            int index = sb.length() / 2;
            sb.insert(index, "8");
            res.add(sb.toString());
            sb.deleteCharAt(index).insert(index, "1");
            res.add(sb.toString());
            sb.deleteCharAt(index).insert(index, "0");
            res.add(sb.toString());
            sb.deleteCharAt(index);
        }else if(lo < hi){
            int index = sb.length() / 2;
            sb.insert(index, "11");
            dfs(lo + 1, hi - 1, sb, res);
            sb.deleteCharAt(index).deleteCharAt(index).insert(index, "69");
            dfs(lo + 1, hi - 1, sb, res);
            sb.deleteCharAt(index).deleteCharAt(index).insert(index, "88");
            dfs(lo + 1, hi - 1, sb, res);
            sb.deleteCharAt(index).deleteCharAt(index).insert(index, "96");
            dfs(lo + 1, hi - 1, sb, res);
            if(lo != 0){
                sb.deleteCharAt(index).deleteCharAt(index).insert(index, "00");
                dfs(lo + 1, hi - 1, sb, res); 
            }
            sb.deleteCharAt(index).deleteCharAt(index);
        }
        return;
    }
}