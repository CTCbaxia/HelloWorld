/*
M
788. Rotated Digits
*/
/*
Dynamic Programming + iteration (more concise)
因为每个 digit 都是单独的数字，只需要把他们拆分为之前有的内容就好

dp[i] = 0 invalid num
dp[i] = 1 valid same num
dp[i] = 2 valid diff num (qualified)

Time: O(N)
Space: O(10000)
*/
class Solution {
    public int rotatedDigits(int N) {
        int[] dp = new int[10001];
        int count = 0;
        for(int i = 0; i <= N; i++){
            if(i < 10){
                if(i == 0 || i == 1 || i == 8) dp[i] = 1;
                else if(i == 2 || i == 5 || i == 6 || i == 9){
                    dp[i] = 2;
                    count++;
                }else dp[i] = 0;
            }else{
                int partA = i / 10;
                int partB = i % 10;
                dp[i] = dp[partA] * dp[partB];
                if(dp[i] > 1) count++;
            }
        }
        return count;
    }
}
/*
Dynamic Programming + iteration

Time: O(N)
Space: O(10000)
*/
class Solution {
    public int rotatedDigits(int N) {
        int[] dp = new int[10001];
        int count = 0;
        for(int i = 0; i <= N; i++){
            if(i < 10){
                if(i == 0 || i == 1 || i == 8) dp[i] = 1;
                else if(i == 2 || i == 5 || i == 6 || i == 9){
                    dp[i] = 2;
                    count++;
                }else dp[i] = 0;
            }else if(i >= 10 && i < 100){
                int highest = i / 10;
                int pre = i % 10;
                dp[i] = dp[highest] * dp[pre];
                if(dp[i] > 1) count++;
            }else if(i >= 100 && i < 1000){
                int highest = i / 100;
                int pre = i % 100;
                dp[i] = dp[highest] * dp[pre];
                if(dp[i] > 1) count++;
            }else if(i >= 1000 && i < 10000){
                int highest = i / 1000;
                int pre = i % 1000;
                dp[i] = dp[highest] * dp[pre];
                if(dp[i] > 1) count++;
            }
        }
          
        return count;
    }
}


/*
One pass + flag

Time: O(N * 4) = O(N)  N  will be in range [1, 10000]
Space: O(1)
*/
class Solution {
    public int rotatedDigits(int N) {
        Set<Integer> validChange = new HashSet<>();
        Set<Integer> validUnchange = new HashSet<>();
        validChange.add(2);//会 return 一个值的不能连续调用 method
        validChange.add(5);
        validChange.add(6);
        validChange.add(9);
        validUnchange.add(0);
        validUnchange.add(1);
        validUnchange.add(8);
        
        int count = 0;
        int n = 1;
        while(n <= N){//should be <=!!
            int num = n;//should be assigned to another param
            boolean hasChanged = false;
            while(num > 0){
                int digit = num % 10;
                num /= 10;
                if(validChange.contains(digit)) hasChanged = true;
                else if(validUnchange.contains(digit)) continue;
                else{
                    hasChanged = false;
                    break;
                } 
            }
            if(hasChanged) count++;
            n++;
        }
        return count;
    }
}
/*
❌错误连篇的 code
One pass + flag
*/
class Solution {
    public int rotatedDigits(int N) {
        Set<Integer> validChange = new HashSet<>();
        Set<Integer> validUnchange = new HashSet<>();
        validChange.add(2);//❌会 return 一个值的不能连续调用 method
        validChange.add(5);
        validChange.add(6);
        validChange.add(9);
        validUnchange.add(0);
        validUnchange.add(1);
        validUnchange.add(8);
        
        int count = 0;
        int n = 1;
        while(n <= N){//❌should be <=!!
            int num = n;//❌should be assigned to another param
            boolean hasChanged = false;
            while(num > 0){
                int digit = num % 10;
                num /= 10;
                //❌这里的判断会使得如果一开始有一个 hasChanged = true，后面不管什么结果都会count++
                if(validChange.contains(digit)) hasChanged = true;
                else if(validUnchange.contains(digit)) continue;
                else break;
            }
            if(hasChanged) count++;
            n++;
        }
        return count;
    }
}
