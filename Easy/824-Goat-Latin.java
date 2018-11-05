/*
EASY
824. Goat Latin

RESULT: 
NOTES: 
*/
/*
Split word and check:

Time: O(n) len of S
Space: O(1)
*/
class Solution {
    public String toGoatLatin(String S) {
        String[] str = S.split(" ");
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < str.length; i++){
            String word = str[i];
            char c = word.charAt(0);
            if(!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')){
                StringBuilder tmp = new StringBuilder();
                tmp.append(word).deleteCharAt(0);
                tmp.append(c);
                word = tmp.toString();//modify the word
            }
            sb.append(word).append("ma");
            
            //add aaaa
            int num = i;
            while(num-- >= 0) sb.append("a");
            
            sb.append(" ");
        }
        
        sb.deleteCharAt(sb.length() - 1);//remove the last " " space
        return sb.toString();
        
    }
}