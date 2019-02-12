/*
EASY
819. Most Common Word

*/
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> ban = new HashSet<>();
        
        for(String s : banned) ban.add(s);
        
        int start = 0, end = 0, maxNum = 0;
        for(int i = 0; i <= paragraph.length(); i++){
            if(i < paragraph.length() && Character.isLetter(paragraph.charAt(i))){
                end = i;
            }else{
                if(start <= end){//there is a word
                    String letter = paragraph.substring(start, end + 1).toLowerCase();
                    if(!ban.contains(letter)){
                        map.put(letter, map.getOrDefault(letter, 0) + 1);
                        maxNum = Math.max(map.get(letter), maxNum);
                    }
                }
                start = i + 1;
            }
        }
        for(String s : map.keySet()){
            if(map.get(s) == maxNum) return s;
        }
        return "";
    }
}