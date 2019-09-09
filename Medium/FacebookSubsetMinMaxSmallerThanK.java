/*
Facebook - sparse dot
给一系列 array，一个 target k
求所有的 subset，使得 subset 的 min + max <= k

Time: O(nlogn)
Space: O(1)
*/
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        int[] nums = new int[]{2,4,5,7};
        System.out.println(findSubset(nums, 8));//5
    }
    private static int findSubset(int[] nums, int k){
        int[] pows = new int[nums.length + 1];
        int base = 1;
        for(int i = 0; i < pows.length; i++){
            pows[i] = base;
            base = base << 1;
        }
        
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1;
        int res = 0;
        while(l <= r){
            if(nums[l] + nums[r] <= k){
                res += pows[r - l];// l must be in the set, r may or maynot
                l++;
            }else{
                r--;
            }
        }
        return res;
    }
}