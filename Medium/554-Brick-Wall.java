/*
MEDIUM
554. Brick Wall

TIME: 
RESULT: 
NOTES:

*/
/*
Map + update count result
Map: <end point, frequence>
result: everytime you update the map, also update the result if get larger count

Time: O(n)
Space: O(n)
*/
    class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(List<Integer> level : wall){//需要声明 type
            int end = 0;
            for(int i = 0; i < level.size() - 1; i++){
                end += level.get(i);
                map.put(end, map.getOrDefault(end, 0) + 1);
                res = Math.max(res, map.get(end));
            }
        }
        return wall.size() - res;//需要穿过几道墙
    }
}




/*same as above
Map + update count result
Map: <cross line index, count of rows that has that cross line>
result: everytime you update the map, also update the result if get larger count

Time: O(n)
Space: O(n)
*/
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(List<Integer> row : wall){
            int cross = 0;
            for(int i = 0; i < row.size() - 1; i++){
                cross += row.get(i);
                map.put(cross, map.getOrDefault(cross, 0) + 1);
                count = Math.max(count, map.get(cross));
            }
        }
        if(map.size() == 0) return wall.size();
        else return wall.size() - count;
    }
}