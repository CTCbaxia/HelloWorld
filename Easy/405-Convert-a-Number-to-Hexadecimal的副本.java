/*
1089. Duplicate Zeros

*/
class Solution {
    public void duplicateZeros(int[] arr) {
        int numOfZeros = 0;
        int i = 0;
        while(i < arr.length){
            if(arr[i] != 0){
                i++;
            }else{
                while(i < arr.length && arr[i] == 0){
                    numOfZeros++;
                    i++;
                }
                
                for(int j = arr.length - 1 - numOfZeros; j >= i; j--){
                    arr[j + numOfZeros] = arr[j];
                }
                for(int j = 0; j < numOfZeros && i + j < arr.length; j++){
                    arr[i + j] = 0;
                }
                i += numOfZeros;
                numOfZeros = 0;
            }
            
        }
    }
}