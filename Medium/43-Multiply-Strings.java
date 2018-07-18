/*
MEDIUM
43. Multiply Strings
https://leetcode.com/problems/multiply-strings/description/
TIME: 0718 - 5h
RESULT: 98% - 15ms
NOTE:
1. for(int digit : stack) 是顺序迭代；stack.pop() 是逆序弹出
2. Stack是类，Queue是接口：Queue<Integer> result = new LinkedList<Integer>();
3. 注意根据需要的操作顺序来选择数据结构：
    - 比如算 subproduct 是从末位算起，而之后需要按照末位继续使用它，就用 queue。
    - 比如算 final result是从末位算起，但是需要从首位开始输出结果，就用 stack。
4. 关于 char int 转换
    - char to int: char - '0'
    - int to char: (char)(res.pop() + '0')
5. StringBuilder 比 s = s + 'char' 要快很多
6. 注意数字计算的技巧和规律 
*/

/*
TIME: 0718 - 5h
RESULT: 98% - 15ms
*/

class Solution {
    public String multiply(String num1, String num2) {
        
        String result = "";
        int n1 = num1.length();
        int n2 = num2.length();
        if(n1 == 0 || n2 == 0 || num2.charAt(0) == '0' || num1.charAt(0) == '0') return "0";
        
        int[] position = new int[n1 + n2];
        for(int i = n1 - 1; i >= 0; i--){
            for(int j = n2 - 1; j >= 0; j--){
                
                int digit1 = num1.charAt(i) - '0';
                int digit2 = num2.charAt(j) - '0';
                position[i + j + 1] = position[i + j + 1] + digit1 * digit2;
            }
        }
        
        int addup = 0;
        for(int i = position.length - 1; i >=0; i--){
            int tmp = 0;
            tmp = position[i] + addup;
            position[i] = tmp % 10;
            addup = tmp / 10;
        }
        /* StringBuilder 快很多
        for(int i = 0; i < position.length; i++){
            
            if(i == 0 && position[i] == 0) continue;
            result = result + (char)(position[i] + '0');
        }
        return result;
        */
        StringBuilder sb = new StringBuilder();
        for (int num : position) sb.append(num);
        while (sb.length() != 0 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

/*
TIME: 0718 - 5h
RESULT: 21% - 35ms
*/


class Solution {
    public String multiply(String num1, String num2) {
        List<Queue<Integer>> res = new ArrayList<Queue<Integer>>();
        String result = "";
        int count = 0;
        if(num2.length() == 0 || num1.length() == 0 || num2.charAt(0) == '0' || num1.charAt(0) == '0') return "0";
        
        String firstnum = num1;
        String secondnum = num2;
        if(num2.length() > num1.length()){ firstnum = num2;secondnum = num1;}

        for(int i = secondnum.length() - 1; i >= 0; i--){  
            Queue<Integer> product = product(firstnum, secondnum.charAt(i), count);
            res.add(product);  
            count++;
        }
         //add all product
        int resnum = secondnum.length();
        int addup = 0;
        Stack<Integer> finalres = new Stack<Integer>();
        while(res.get(resnum - 1).size() > 0){
            int tmp = 0;
            for(int i = 0; i < resnum; i++){
                if(res.get(i).size() > 0) tmp = tmp + res.get(i).poll();
            }
            finalres.push((tmp + addup) % 10);
            addup =  (tmp + addup) / 10;
        }
        
        if(addup > 0) finalres.push(addup);
        
        int n = finalres.size();
        for(int i = 0; i < n; i++){
            result = result + (char)(finalres.pop() + '0');
        }
        return result;
        
    }
    private Queue<Integer> product(String num, char c, int count){
        int tmp = 0;
        int addup = 0;

        Queue<Integer> result = new LinkedList<Integer>();
        int n1 = c - '0';
        while(count > 0){
            result.add(0);
            count--;
        }        
        for(int i = num.length() - 1; i >= 0; i--){
            tmp = (num.charAt(i) - '0') * n1;
            result.add((tmp + addup) % 10);
            addup = (tmp + addup) / 10;
        }
        if(addup > 0) result.add(addup);
        return result;
    }
}
/*
Wrong Code:

一个非常麻烦还做错了的思路：
每次算的的 subproduct 用 stack 存储，再倒序弹出到 string 里面。
加和 subproduct 的时候又从倒序开始加和，存到 stack 里面，然后再倒序弹出存到 final result 里面

会有错误：如果某一次求 subproduct 的时候，位数超过了正常的结果一位，那么后面倒着数的都不成立了。
比如：
"123456789"
"987654321"


所以至少用 queue 搭配 stack 会更好
*/
class Solution {
    public String multiply(String num1, String num2) {
        List<String> res = new ArrayList<String>();
        String result = "";
        int count = 0;
        if(num2.length() == 0 || num1.length() == 0 || num2.charAt(0) == '0' || num1.charAt(0) == '0') return "0";
        
        String firstnum = num1;
        String secondnum = num2;
        if(num2.length() > num1.length()){ firstnum = num2;secondnum = num1;}

        for(int i = secondnum.length() - 1; i >= 0; i--){  
            String product = product(firstnum, secondnum.charAt(i), count);
            res.add(product);
            //System.out.println(res);
            count++;
        }
        
        //add all product
        int resnum = secondnum.length();
        int digitnum = res.get(resnum - 1).length();
        
        int addup = 0;
        Stack<Integer> finalres = new Stack<Integer>();
        for(int i = digitnum - 1; i >= 0 ; i--){
            int charnum = 0;
            int tmp = 0;
            
            for(int j = resnum - 1; j >= 0 ; j--){  
                if(i - charnum >= 0) tmp = tmp + (res.get(j).charAt(i - charnum) - '0');
                charnum ++;
            }  
            finalres.push((tmp + addup) % 10);
            addup =  (tmp + addup) / 10;
        }
        
        //System.out.println(finalres);
        if(addup > 0) finalres.push(addup);
        
        int n = finalres.size();
        for(int i = 0; i < n; i++){
            result = result + (char)(finalres.pop() + '0');
        }
        return result;
        
    }
    private String product(String num, char c, int count){
        int tmp = 0;
        int addup = 0;
        Stack<Integer> res = new Stack<Integer>();
        String result = "";
        int n1 = c - '0';
        for(int i = num.length() - 1; i >= 0; i--){
            tmp = (num.charAt(i) - '0') * n1;
            res.push((tmp + addup) % 10);
            addup = (tmp + addup) / 10;
        }
        if(addup > 0) res.push(addup);
        int n = res.size();
        for(int i = 0; i < n; i++){
            result = result + (char)(res.pop() + '0');
        }
        while(count > 0){
            result = result + '0';
            count--;
        }
        return result;
    }
}


