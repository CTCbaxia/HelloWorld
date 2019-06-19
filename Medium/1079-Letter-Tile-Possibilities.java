/*
MEDIUM
1079. Letter Tile Possibilities

*/
/*
Combination - DFS
*/
class Solution {
    public int numTilePossibilities(String tiles) {
        List<String> list = new ArrayList<>();
        char[] letters = new char[26];
        for(char c : tiles.toCharArray()){
            letters[c - 'A']++;
        }
        combination(letters, list, "");
        return list.size();
    }
    private void combination(char[] letters, List<String> list, String prefix){
        for(int i = 0; i < letters.length; i++){
            if(letters[i] > 0){
                String s = prefix + (char)('A' + i);
                letters[i]--;
                list.add(s);
                combination(letters, list, s);
                letters[i]++;
            }
        }
        return;
    }
}