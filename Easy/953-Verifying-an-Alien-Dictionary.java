/*
953. Verifying an Alien Dictionary

*/
/*
Map + Compare Neighbors

Time: O(Num of Words)
Space: O(26)
*/
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        // make an order map
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < order.length(); i++){
            map.put(order.charAt(i), i);
        }
        //compare neighbors
        for(int i = 0; i < words.length - 1; i++){
            if(!isSorted(words[i], words[i + 1], map)) return false;
        }
        return true;
    }
    private boolean isSorted(String s1, String s2, Map<Character, Integer> map){
        int i = 0, j = 0;
        while(i < s1.length() && j < s2.length() && s1.charAt(i) == s2.charAt(j)){
            i++;
            j++;
        }
        
        if(i >= s1.length()) return true;
        else if(j >= s2.length()) return false;
        else{
            if(map.get(s1.charAt(i)) > map.get(s2.charAt(j))){
                return false;
            }else{
                return true;
            }
        }
    }
}