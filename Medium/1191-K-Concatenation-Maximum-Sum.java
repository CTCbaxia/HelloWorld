/*
M
1191. K-Concatenation Maximum Sum
*/
/*
sum: sum of all numbers from arr
if sum > 0, then result = sum(arr) * (k - 2)  + maxSubArray(concat(arr,arr))
if sum <= 0, then result = max(0, maxSubArray(concat(arr,arr)))

Time: O(n)
Space: O(1)
*/
class Solution {
    int mod = (int)(1e9+7);
    public int kConcatenationMaxSum(int[] arr, int k) {
        int n = arr.length;
        int sum = 0;
        int frontMax = 0;
        for(int i = 0; i < n; i++){
            sum += arr[i] % mod;
            frontMax = Math.max(frontMax, sum);
        }

        int maxSubSum = 0;
        int[] maxSum = new int[n];
        for(int i = 0; i < n; i++){
            if(i == 0){
                maxSum[i] = arr[i];
            }else{
                if(maxSum[i - 1] > 0){
                    maxSum[i] = (maxSum[i - 1] + arr[i]) % mod;
                }else{
                    maxSum[i] = arr[i] % mod;
                }
            }
            maxSubSum = Math.max(maxSubSum, maxSum[i]);
        }
        int endMax = maxSum[n - 1];
            
        int resSum = 0;
        if(sum > 0){
            for(int i = 0; i < k - 2; i++){
                resSum = (resSum + sum) % mod;
            }
            return (endMax + resSum + frontMax) % mod;
        }else{
            return Math.max(maxSubSum, (endMax + frontMax) % mod);
        }
    }
}