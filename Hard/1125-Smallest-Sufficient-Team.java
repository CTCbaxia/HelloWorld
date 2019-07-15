/*
H
1125. Smallest Sufficient Team
*/
/*
DFS - backtracking
1) build skill map to map each skill to all persons with that skill
2) backtracking to loop required skills and add a person with that skill to the team
    a) pruning: when current team is larger than a previously formed team
    b) remove a person if he is added in this loop (need check)
3) when formed a team, copy to minTeam (len has alreay been checked in 2a)

Time: O(16*60 + (60)^16) 一共最多走16层，每层最多有60个人有该技能
Space: O(16*60)
*/
class Solution {
    Set<Integer> minTeam = new HashSet<>();
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < people.size(); i++){
            for(String skill : people.get(i)){
                if(!map.containsKey(skill)) map.put(skill, new ArrayList<>());
                map.get(skill).add(i);
            }
        }
        dfs(map, req_skills, 0, new HashSet<Integer>());
        
        //transfer List minTeam to Array result
        int[] res = new int[minTeam.size()];
        int i = 0;
        for(Integer person : minTeam){
            res[i++] = person;
        }
        return res;
    }
    private void dfs(Map<String, List<Integer>> map, String[] req_skills, int index, Set<Integer> team){
        if(minTeam.size() != 0 && team.size() >= minTeam.size()) return;//pruning, if current is largerOrEqual than previous formed
        if(index == req_skills.length){ //formed a team
            minTeam = new HashSet<>(team);//should new one rather than pass reference
            return;
        }
        
        
        if(!map.containsKey(req_skills[index])) return;//if nobody has that skill, return and cannot make up a team
        
        List<Integer> persons = map.get(req_skills[index]);
        
        for(int i = 0; i < persons.size(); i++){
            //add every person who has this skill, the person might have already been added before
            int personIndex = persons.get(i);
            boolean previousExist = team.contains(personIndex);
            if(!previousExist) team.add(personIndex);//add person into the team
            dfs(map, req_skills, index + 1, team);
            if(!previousExist) team.remove(personIndex);//set should remove the exact personIndex
        }
        return;
    } 
}



/*
DFS - backtracking
1) build skill map to map each skill to all persons with that skill
2) backtracking to loop required skills and add a person with that skill to the team
    a) maintain a current skill set to pruning because person can have multiple skills meet the requirement
    b) record unique skills from this person (not previously added), remove them when back track
3) when formed a team, compare with minTeam

Time: O(16*60 + (16*60)^16) 一共最多走16层，每层最多有60个人有该技能，对于每个人遍历他的所有技能，最多可能有16个技能
Space: O(16*60)
*/
class Solution {
    List<Integer> minTeam = new ArrayList<>();
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < people.size(); i++){
            for(String skill : people.get(i)){
                if(!map.containsKey(skill)) map.put(skill, new ArrayList<>());
                map.get(skill).add(i);
            }
        }
        dfs(map, people, req_skills, 0, new HashSet<String>(), new ArrayList<Integer>());
        
        //transfer List minTeam to Array result
        int[] res = new int[minTeam.size()];
        for(int i = 0; i < res.length; i++){
            res[i] = minTeam.get(i);
        }
        return res;
    }
    private void dfs(Map<String, List<Integer>> map, List<List<String>> people, String[] req_skills, int index, Set<String> curSkills, List<Integer> team){
        if(index == req_skills.length){ //formed a team
            if(minTeam.size() == 0 || team.size() < minTeam.size()) minTeam = new ArrayList<>(team);//should new one rather than pass reference
            return;
        }
        if(curSkills.contains(req_skills[index])){//pruning, if the previously added person has this skill, skip this skill
            dfs(map, people, req_skills, index + 1, curSkills, team);
            return;
        } 
        
        if(!map.containsKey(req_skills[index])) return;//if nobody has that skill, return and cannot make up a team
        
        List<Integer> persons = map.get(req_skills[index]);
        for(int i = 0; i < persons.size(); i++){//loop every person that has this skill [!!!NOTE] if person is null, cannot .size
            int personIndex = persons.get(i);
            List<String> personUniqueSkills = new ArrayList<>();
            List<String> personSkills = people.get(personIndex);
            
            for(String skill : personSkills){//mark person unique skill for backtracking, add update current skill
                if(!curSkills.contains(skill)) personUniqueSkills.add(skill);
                curSkills.add(skill);
            }
            team.add(personIndex);//add person into the team
            dfs(map, people, req_skills, index + 1, curSkills, team);
            
            //backtracking
            for(String skill : personUniqueSkills){//backtrack to the condition when this person is not added to the team
                curSkills.remove(skill);
            }
            team.remove(team.size() - 1);//list can only remove index rather than num
        }
        return;
    } 
}
