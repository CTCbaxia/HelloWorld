/*
H
1125. Smallest Sufficient Team
*/
//dfs
class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < people.size(); i++){
            for(String skill : people.get(i)){
                if(!map.containsKey(skill)) map.put(skill, new ArrayList<>());
                map.get(skill).add(i);
            }
        }
        
        
    }
    private int[] dfs(Map<String, List<Integer>> map, String[] req_skills, int[] team){
    } 
}
