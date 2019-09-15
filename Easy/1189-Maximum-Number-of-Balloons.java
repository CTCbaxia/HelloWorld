/*
E
1189. Maximum Number of Balloons
*/
/*
Map
Time: O(n)
Space: O(26)
*/
class Solution {
    public int maxNumberOfBalloons(String text) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : text.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int res = text.length();
        String target = "balloon";
        for(char c : target.toCharArray()){
            int count = map.getOrDefault(c, 0);
            if(c == 'l' || c == 'o') count /= 2;
            res = Math.min(res, count);
        }
        return res;
    }
}