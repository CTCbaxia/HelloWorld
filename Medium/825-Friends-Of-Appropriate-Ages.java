/*
MEDIUM
825. Friends Of Appropriate Ages
https://leetcode.com/problems/friends-of-appropriate-ages/description/

TIME: 0821 - 2.5h
RESULT: 83% - 9ms
NOTES: 
1. 找到关键的关系：b belongs to ( 0.5 * a + 7, a)
2. 注意看题目给的范围
*/
/*
SOLUTION REFERENCE:
TIME: 0821 - 30min
RESULT: 83% - 9ms
THOUGHTS:
	- b belongs to ( 0.5 * a + 7, a)
	- a needs to be larger than 15 according to the area in front
*/
class Solution {
    public int numFriendRequests(int[] ages) {
        int result = 0;
        int[] numsInAge = new int[121], sumInAge = new int[121];
        for(int i : ages) numsInAge[i]++;
        for(int i = 1; i <= 120; i++){
            sumInAge[i] = sumInAge[i-1] + numsInAge[i];
        }
        for(int i = 15; i <= 120; i++){
            if(numsInAge[i] == 0) continue;
            result += (sumInAge[i] - sumInAge[i / 2 + 7] - 1) * numsInAge[i];// to remove itself, minus 1
        }
        return result;
    }
}
/*
SOLUTION 1:
TIME: 0821 - 30min
RESULT: 9% - 41ms
*/
class Solution {
    public int numFriendRequests(int[] ages) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> disAges = new ArrayList<Integer>();
        
        for(int i = 0; i < ages.length; i++){
            if(!map.containsKey(ages[i])) disAges.add(ages[i]);
            map.put(ages[i], map.getOrDefault(ages[i], 0) + 1);
        }
        Collections.sort(disAges);
        
        int result = 0;
        for(int i = 0; i < disAges.size(); i++){
            int num = 0;
            if(i > 0){
                for(int j = 0; j < i; j++){
                    int a = disAges.get(i), b = disAges.get(j);
                    if(!(b <= 0.5 * a + 7) && !(b > a) && !(b > 100 && a < 100)){
                        num += map.get(b);
                    }
                }
            }
            int freq = map.get(disAges.get(i));
            result += num * freq + ((disAges.get(i) > 14) ? freq * (freq - 1) : 0);
        }
        
        return result;
    }
}
/*
SOLUTION 0:
TIME: 0821 - 1h
RESULT: 4% - 155ms
*/
class Solution {
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int num = 0, result = 0;
        for(int i = 0; i < ages.length; i++){
            map.put(ages[i], map.getOrDefault(ages[i], 0) + 1);
            
            if(!(i > 0 && ages[i - 1] == ages[i])){
                if(i > 1 && ages[i - 1] > 14) result += map.get(ages[i - 1]) * (map.get(ages[i - 1]) - 1);
                num = 0;
                for(int j = 0; j < i; j++){
                    if(!(ages[j] <= 0.5 * ages[i] + 7) && !(ages[j] > ages[i]) && !(ages[j] > 100 && ages[i] < 100)){
                        num++;
                    }
                }
            }
            result += num;
        }
        result += map.get(ages[ages.length - 1]) * (map.get(ages[ages.length - 1]) - 1);
        return result;
    }
}




