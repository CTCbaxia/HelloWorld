/*
EASY
118. Pascal's Triangle

TIME: 
RESULT: 
NOTES:
*/
/*
Iteration: Add Level by Level

Time: O(n)
Space: O(1)
*/
class Solution {
    public List<List<Integer>> generate(int numRows){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        if(numRows <= 0) return result;
        
        cur.add(1);
        result.add(cur);
        
        while(result.size() < numRows){
            List<Integer> pre = result.get(result.size() - 1);
            cur = new ArrayList<>();
            cur.add(1);
            for(int i = 0; i < pre.size() - 1; i++){
                cur.add(pre.get(i) + pre.get(i + 1));
            }
            cur.add(1);
            result.add(cur);
        }
        return result;        
    }

}
/*
Recursion: Add Level by Level

Time: O(n)
Space: O(1)
*/
class Solution {
    public List<List<Integer>> generate(int numRows){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        if(numRows <= 0) return result;
        
        res.add(1);
        result.add(res);
        buildLevel(1, numRows, result);
        return result;        
    }
    private void buildLevel(int level, int numRows, List<List<Integer>> result){
        if(level == numRows) return;

        List<Integer> pre = result.get(level - 1);
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for(int i = 0; i < pre.size() - 1; i++){
            cur.add(pre.get(i) + pre.get(i + 1));
        }
        cur.add(1);
        result.add(cur);
        
        buildLevel(level + 1, numRows, result);
        return;
    }
}


/*
Add Level by Level

Time: O(n)
Space: O(1)
*/
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if(numRows == 0) return result;
        
        List<Integer> level = new ArrayList<>();
        level.add(1);
        result.add(level);
        
        int k = 0;
        while(k < numRows - 1){
            level = new ArrayList<>();
            List<Integer> lastLevel = result.get(k);
            level.add(lastLevel.get(0));//add leftmost
            
            for(int i = 1; i < lastLevel.size(); i++){
                int sum = lastLevel.get(i) + lastLevel.get(i - 1);
                level.add(sum);
            }
            
            level.add(lastLevel.get(lastLevel.size() - 1));//add rightmost
            
            result.add(level);
            k++;
        }
        return result;
    }
}