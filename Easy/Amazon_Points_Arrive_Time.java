/*
EASY
find numbers appear odd times in an array

*/
class Solution{
    public Map<String, Integer> Amazon_Points_Arrive_Time(List<String> command){
        Map<String, int[]> directions = new HashMap<>();
        directions.put("left", new int[]{0,-1});
        directions.put("right", new int[]{0,1});
        directions.put("up", new int[]{-1,0});
        directions.put("down", new int[]{1,0});

        Map<String, Integer> map = new HashMap<>();
        int x = 0, y = 0;
        String pos0 = "(" + Integer.toString(x) + "," + Integer.toString(x) + ")";
        map.put(pos0, 1);
        for(String c : command){
            int[] dir = directions.get(c);
            x += dir[0];
            y += dir[1];

            String pos = "(" + Integer.toString(x) + "," + Integer.toString(y) + ")";
            map.put(pos, map.getOrDefault(pos, 0) + 1);
        }
        return map;
    }    
}
