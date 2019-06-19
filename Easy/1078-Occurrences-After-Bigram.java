/*
EASY
1078. Occurrences After Bigram

*/
/*
Loop with pointer
*/
class Solution {
    public String[] findOcurrences(String text, String first, String second) {
        String[] words = text.split(" ");
        List<String> res = new ArrayList<>();
        for(int i = 0; i < words.length; i++){
            if(words[i].equals(first)){
                if(i + 1 < words.length && words[i + 1].equals(second)){
                    if(i + 2 < words.length) res.add(words[i + 2]);
                }
            }
        }
        String[] result = new String[res.size()];
        for(int i = 0; i < res.size(); i++){
            result[i] = res.get(i);
        }
        
        return result;
    }
}