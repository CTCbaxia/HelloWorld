/*
HARD
772. Basic Calculator III

TIME: 
RESULT: 
*/
/*
Recursively do with ()
Global i to better locate index

Time: O(n)
Space: O(n)
*/

class Solution {
    int i = 0;//global i to loop the whole string
    public int calculate(String s) {
        return calHelper(s);
    }
    public int calHelper(String s){
        char sign = '+';//记录上一个符号
        int num = 0;
        Stack<Integer> stack = new Stack<>();//里面只存最后需要相加的数
        for(;i < s.length() && sign != ')'; i++){//不需要初始化
            if(s.charAt(i) == ' ') continue;
            if(s.charAt(i) == '('){//get num: num is always before +-*/) but after(
                i++;
                num = calHelper(s);//if(), recursively get the num inside ()
            }else{
                num = getNum(s);
            }
            //calculate based on last sign
            if(sign == '+') stack.push(num);
            else if(sign == '-') stack.push(-num);
            else if(sign == '*') stack.push(stack.pop() * num);
            else if(sign == '/') stack.push(stack.pop() / num);
            
            if(i < s.length()) sign = s.charAt(i);//be careful, after we getNum, i might be the end
        }
        int res = 0;//pop result from stack
        while(!stack.isEmpty()) res += stack.pop();
        return res;
            
    }
    public int getNum(String s){
        int num = 0;
        while(i < s.length() && Character.isDigit(s.charAt(i))){
            num = num * 10 + (s.charAt(i++) - '0');
        }//now i will be the first index that is not number
        return num;
        
    }
}




//来来的
class Solution {
public:
    int calculate(string s) {
        int i = 0;
        return calculate(s, i);
    }
    
    int calculate(string& s, int& i) {
        int num = 0;
        char sign = '+';
        vector<int> v;
        for (;i < s.size() && sign != ')'; i++) {
            if (s[i] == ' ') continue;
            int num = s[i] == '(' ? calculate(s, ++i) : getNum(s, i);
            switch(sign) {
                case '+' : v.push_back(num); break;
                case '-' : v.push_back(-num); break;
                case '*' : v.back() *= num; break;
                case '/' : v.back() /= num; break;
            }
            sign = s[i];
        }
        int res = 0;
        for (auto n : v) res += n;
        return res;
    }
    
    int getNum(string& s, int& i) {
        int res = 0;
        while (i < s.size() && isdigit(s[i]))
            res = res * 10 + s[i++] - '0';
        return res;
    }
};



class Solution {
    public int calculate(String s) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> signs = new Stack<>();
        int num = 0;
        char sign = '+';
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num*10 + c - '0';
            }
            if(!Character.isDigit(c) && c != ' ' || i == s.length() - 1){
                if(c != ')'){
                    if(i != s.length() - 1) signs.push(c);
                    nums.push(num);
                }else{
                    sign = signs.pop();
                    while(sign != '('){
                        if(sign == '+') nums.push(nums.pop() + num);
                        else if(sign == '-') nums.push(nums.pop() - num);
                        else if(sign == '*') nums.push(nums.pop() * num);
                        else if(sign == '/') nums.push(nums.pop() / num);
                        num = nums.pop();
                        sign = signs.pop();
                    }
                    nums.push(num);
                }
                num = 0;
            }
        }
        System.out.println("nums:" + nums + " sign:" +signs);
        if(!nums.isEmpty()) num = nums.pop();
        while(!nums.isEmpty() && !signs.isEmpty()){
            sign = signs.pop();
            if(sign == '+') nums.push(nums.pop() + num);
            else if(sign == '-') nums.push(nums.pop() - num);
            else if(sign == '*') nums.push(nums.pop() * num);
            else if(sign == '/') nums.push(nums.pop() / num);  
            
        }
        System.out.println("nums:" + nums + " sign:" +sign);
        return nums.isEmpty() ? num : nums.pop();
    }
}