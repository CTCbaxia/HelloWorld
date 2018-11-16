/*
EASY
118. Pascal's Triangle

TIME: 
RESULT: 
NOTES:
*/
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