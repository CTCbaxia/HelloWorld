/*
EASY
408. Valid Word Abbreviation

RESULT: 
NOTES: 
*/
/*
Two pointers

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        char[] w = word.toCharArray();
        char[] a = abbr.toCharArray();

        while(i < w.length && j < a.length){
            //if it is digit, we get the actual number
            if(Character.isDigit(a[j]) && a[j] != '0'){//Caution! the number should not contain any leading zero
                int num = 0;
                while(j < a.length && Character.isDigit(a[j])){//Caution! check the boundary before use a[j]
                    num = num * 10 + a[j++] - '0';
                }
                i += num;
            }else{
            //if it is not the digit, we check one by one
                if(w[i++] != a[j++]) return false;
            }
        }
        
        return i == w.length && j == a.length;
    }
}
/*
TEST CASE"
"a"
"01"

 !!! the number should not contain any leading zero
*/