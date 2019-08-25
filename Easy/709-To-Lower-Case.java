/*
E
709. To Lower Case
*/
class Solution {
    public String toLowerCase(String str) {
        int diff = 'A' - 'a';
        StringBuilder sb = new StringBuilder();
        for(char c : str.toCharArray()){
            if(c >= 'A' && c <= 'Z'){
                sb.append((char)(c - diff));
            } 
            else sb.append(c);
        }
        return sb.toString();
    }
}
