/*
EASY
408. Valid Word Abbreviation

RESULT: 
NOTES: 
*/
/*
Two Pointers
if letter, compare
if digit, wait until all digit comming out -- num

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        char[] wc = word.toCharArray();
        char[] ac = abbr.toCharArray();
        int i = 0, j = 0;
        while(i < wc.length && j < ac.length){
            char w = wc[i];
            char a = ac[j];
            if(Character.isLetter(w) && Character.isLetter(a)){
                if(w != a) return false;
                i++;
                j++;
            }else{//abbr is num
                if(ac[j] == '0') return false;//should not have leading 0
                int num = 0;
                while(j < ac.length && Character.isDigit(ac[j])){
                    num = num * 10 + ac[j] - '0';
                    j++;
                }
                i = i + num;
            }
        }
        return i == wc.length && j == ac.length;
    }
}



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