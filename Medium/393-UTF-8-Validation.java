/*
MEDIUM
393. UTF-8 Validation

TIME: 
RESULT: 
NOTES:
prefix 0b represent for binary representation
*/
/*
Bit Manipulation


Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean validUtf8(int[] data) {
        int count = 0;
        for(int i = 0; i < data.length; i++){
            if(count == 0){//for the start byte, check what kind of byte they are
                if(data[i] >> 5 == 0b110) count = 1;
                else if(data[i] >> 4 == 0b1110) count = 2;
                else if(data[i] >> 3 == 0b11110) count = 3;
                else if(data[i] >> 7 != 0) return false;//have to be the last "if", for previous conditions data[i] >> 7 == 0
            }else{
                if(data[i] >> 6 != 0b10) return false;
                count--;
            }
        }
        return count == 0;//need enough bytes followed by things like 110xxxxxx
    }
}