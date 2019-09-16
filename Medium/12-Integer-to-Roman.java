/*
M
12. Integer to Roman
*/
/*
Iteration with the help of unit

Time: O(unit.length)
Space: O(lenOfResult)
*/
class Solution {
    public String intToRoman(int num) {
        int[] vals = {1000,900,500,400,100,90,50,40,10,9,5,4,1};//don't put 0
        String[] unit = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < vals.length; i++){
            while(num >= vals[i]){//should be large of equal
                sb.append(unit[i]);
                num -= vals[i];
            } 
        }
        
        return sb.toString();
    }
}


/*
Iteration

Time: O(lenOfResult * unit.length)
Space: O(lenOfResult)
*/
class Solution {
    public String intToRoman(int num) {
        int[] vals = {1000,900,500,400,100,90,50,40,10,9,5,4,1,0};
        String[] unit = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I",""};
        System.out.println(vals.length + " "+unit.length);
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            for(int i = 0; i < vals.length; i++){
                if(num >= vals[i]){//should be large of equal
                    sb.append(unit[i]);
                    num -= vals[i];
                    break;
                } 
            }
           
        }
        
        return sb.toString();
    }
}