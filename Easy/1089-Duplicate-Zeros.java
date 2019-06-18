/*
1089. Duplicate Zeros

Time: O()
*/
/*
Two pass:
Count num of zeros and go from right to left to assign value

Time: O(n)
Space: O(1)
*/
class Solution {
    public void duplicateZeros(int[] arr) {
        int numOfZeros = 0;
        //go from left to right and count for #zeros
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 0) numOfZeros++;
        }
        //go from right to left and assign numbers
        for(int i = arr.length - 1; i >= 0; i--){
            if(i + numOfZeros < arr.length){//need to check if it is inside range
                arr[i + numOfZeros] = arr[i];
            }
            if(arr[i] == 0){//if there is a 0, copy a zero and numOfZeros--
                numOfZeros--;
                if(i + numOfZeros < arr.length) arr[i + numOfZeros] = 0;
            }
        }
    }
}

/*


Time: O(n^2) - worst case: 010101
Space: O(1)
*/
class Solution {
    public void duplicateZeros(int[] arr) {
        int numOfZeros = 0;
        int i = 0;
        while(i < arr.length){
            if(arr[i] != 0){
                i++;
            }else{
                int start = i;
                while(i < arr.length && arr[i] == 0){
                    numOfZeros++;
                    i++;
                }
                //from right to left
                for(int j = arr.length - 1 - numOfZeros; j >= start; j--){
                    arr[j + numOfZeros] = arr[j];
                }
                i = start + numOfZeros * 2;
                numOfZeros = 0;
            }
            
        }
    }
}
